package com.junshou.service.seckill.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.junshou.common.entity.SeckillStatus;
import com.junshou.seckill.pojo.SeckillGoods;
import com.junshou.seckill.pojo.SeckillOrder;
import com.junshou.service.seckill.dao.SeckillGoodsMapper;
import com.junshou.service.seckill.dao.SeckillOrderMapper;
import com.junshou.service.seckill.service.SeckillOrderService;
import com.junshou.service.seckill.task.MultiThreadingCreateOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/****
 * @Author: X
 * @Description:SeckillOrder业务层接口实现类
 *****/
@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    @Autowired
    private MultiThreadingCreateOrder multiThreadingCreateOrder;
    @Autowired
    private RedisTemplate redisTemplate;

    /***
     * 关闭订单回滚库存[修改状态]
     * @param username
     */
    @Override
    public void closeSeckillOrder(String username) throws Exception {
        //删除Redis中的订单
        redisTemplate.boundHashOps("SeckillOrder_").delete(username);
        //查询用户排队状态
        SeckillStatus seckillStatus = (SeckillStatus) redisTemplate.boundHashOps("UserQueueStatus_").get(username);
        //删除排队信息
        clearUserQueue(username);
        //回滚库存
        //查询秒杀商品
        String namespace = "SeckillGoods_" + seckillStatus.getTime();
        SeckillGoods seckillGoods = (SeckillGoods) redisTemplate.boundHashOps(namespace).get(seckillStatus.getGoodsId());
        //如果商品为空
        if (null == seckillGoods) {
            //数据库中查询
            seckillGoods = seckillGoodsMapper.selectByPrimaryKey(seckillStatus.getGoodsId());
            //更新数据库库存
            seckillGoods.setStockCount(1);
            seckillGoodsMapper.updateByPrimaryKeySelective(seckillGoods);
        }else {
            seckillGoods.setStockCount(seckillGoods.getStockCount()+1);
        }
        redisTemplate.boundHashOps(namespace).put(seckillGoods.getId(),seckillGoods);
        //队列
        redisTemplate.boundListOps("SeckillGoodsCountList_" + seckillGoods.getGoodsId()).leftPush(seckillGoods.getId());
    }

    /***
     * 修改秒杀订单状态
     * @param username
     * @param timeEnd
     * @param transactionId
     */
    @Override
    public void updataPayStatus(String username, String timeEnd, String transactionId) {
        //查询信息
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.boundHashOps("SeckillOrder_").get(username);
        if (null != seckillOrder) {
            try {
                //修改订单状态信息
                seckillOrder.setStatus("1");
                seckillOrder.setTransactionId(transactionId);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                Date payTime = dateFormat.parse(timeEnd);
                seckillOrder.setPayTime(payTime);
                seckillOrderMapper.insertSelective(seckillOrder);

                //删除Redis中的订单
                redisTemplate.boundHashOps("SeckillOrder_").delete(username);

                //删除用户排队信息
                clearUserQueue(username);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @description: 抢单状态查询
     * @param username
     * @return:
     * @author: X
     * @date: 2020/2/15
     */
    @Override
    public SeckillStatus queryStatus(String username) {
        return (SeckillStatus) redisTemplate.boundHashOps("UserQueueStatus_").get(username);
    }

    /**
     * @param time
     * @param id
     * @param username
     * @description: 添加秒杀订单
     * @return:
     * @author: X
     * @date: 2020/2/15
     */
    @Override
    public Boolean addSeckillOrder(String time, Long id, String username) {
        //记录用户排队次数
        Long userQueueStatus = redisTemplate.boundHashOps("UserQueueCount_").increment(username, 1);
        if (userQueueStatus>1){
            //100表示重复排队
            throw new RuntimeException("100");
        }

        //创建排队对象
        SeckillStatus seckillStatus = new SeckillStatus(username, new Date(), 1, id, time);
        //用户抢单排队
        redisTemplate.boundListOps("SeckillOrderQueue_").leftPush(seckillStatus);
        //用户抢单状态 用于查询
        redisTemplate.boundHashOps("UserQueueStatus_").put(username,seckillStatus);

        //异步执行
        multiThreadingCreateOrder.createOrder();
        return true;
    }

    /**
     * SeckillOrder条件+分页查询
     *
     * @param seckillOrder 查询条件
     * @param page         页码
     * @param size         页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<SeckillOrder> findPage(SeckillOrder seckillOrder, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        Example example = createExample(seckillOrder);
        //执行搜索
        return new PageInfo<SeckillOrder>(seckillOrderMapper.selectByExample(example));
    }

    /**
     * SeckillOrder分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<SeckillOrder> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<SeckillOrder>(seckillOrderMapper.selectAll());
    }

    /**
     * SeckillOrder条件查询
     *
     * @param seckillOrder
     * @return
     */
    @Override
    public List<SeckillOrder> findList(SeckillOrder seckillOrder) {
        //构建查询条件
        Example example = createExample(seckillOrder);
        //根据构建的条件查询数据
        return seckillOrderMapper.selectByExample(example);
    }


    /**
     * SeckillOrder构建查询对象
     *
     * @param seckillOrder
     * @return
     */
    public Example createExample(SeckillOrder seckillOrder) {
        Example example = new Example(SeckillOrder.class);
        Example.Criteria criteria = example.createCriteria();
        if (seckillOrder != null) {
            // 主键
            if (!StringUtils.isEmpty(seckillOrder.getId())) {
                criteria.andEqualTo("id", seckillOrder.getId());
            }
            // 秒杀商品ID
            if (!StringUtils.isEmpty(seckillOrder.getSeckillId())) {
                criteria.andEqualTo("seckillId", seckillOrder.getSeckillId());
            }
            // 支付金额
            if (!StringUtils.isEmpty(seckillOrder.getMoney())) {
                criteria.andEqualTo("money", seckillOrder.getMoney());
            }
            // 用户
            if (!StringUtils.isEmpty(seckillOrder.getUserId())) {
                criteria.andEqualTo("userId", seckillOrder.getUserId());
            }
            // 商家
            if (!StringUtils.isEmpty(seckillOrder.getSellerId())) {
                criteria.andEqualTo("sellerId", seckillOrder.getSellerId());
            }
            // 创建时间
            if (!StringUtils.isEmpty(seckillOrder.getCreateTime())) {
                criteria.andEqualTo("createTime", seckillOrder.getCreateTime());
            }
            // 支付时间
            if (!StringUtils.isEmpty(seckillOrder.getPayTime())) {
                criteria.andEqualTo("payTime", seckillOrder.getPayTime());
            }
            // 状态，0未支付，1已支付
            if (!StringUtils.isEmpty(seckillOrder.getStatus())) {
                criteria.andEqualTo("status", seckillOrder.getStatus());
            }
            // 收货人地址
            if (!StringUtils.isEmpty(seckillOrder.getReceiverAddress())) {
                criteria.andEqualTo("receiverAddress", seckillOrder.getReceiverAddress());
            }
            // 收货人电话
            if (!StringUtils.isEmpty(seckillOrder.getReceiverMobile())) {
                criteria.andEqualTo("receiverMobile", seckillOrder.getReceiverMobile());
            }
            // 收货人
            if (!StringUtils.isEmpty(seckillOrder.getReceiver())) {
                criteria.andEqualTo("receiver", seckillOrder.getReceiver());
            }
            // 交易流水
            if (!StringUtils.isEmpty(seckillOrder.getTransactionId())) {
                criteria.andEqualTo("transactionId", seckillOrder.getTransactionId());
            }
        }
        return example;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        seckillOrderMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改SeckillOrder
     *
     * @param seckillOrder
     */
    @Override
    public void update(SeckillOrder seckillOrder) {
        seckillOrderMapper.updateByPrimaryKey(seckillOrder);
    }

    /**
     * 增加SeckillOrder
     *
     * @param seckillOrder
     */
    @Override
    public void add(SeckillOrder seckillOrder) {
        seckillOrderMapper.insert(seckillOrder);
    }

    /**
     * 根据ID查询SeckillOrder
     *
     * @param id
     * @return
     */
    @Override
    public SeckillOrder findById(Long id) {
        return seckillOrderMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询SeckillOrder全部数据
     *
     * @return
     */
    @Override
    public List<SeckillOrder> findAll() {
        return seckillOrderMapper.selectAll();
    }

    /***
     * 清理用户排队抢单信息
     * 问题：
     */
    public void clearUserQueue(String username) {
        //排队标识
        redisTemplate.boundHashOps("UserQueueCount_").delete(username);
        //排队信息清理掉
        redisTemplate.boundHashOps("UserQueueStatus_").delete(username);
    }
}
