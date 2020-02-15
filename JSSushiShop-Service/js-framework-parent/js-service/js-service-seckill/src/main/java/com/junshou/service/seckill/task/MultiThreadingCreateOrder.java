package com.junshou.service.seckill.task;

import com.junshou.common.entity.SeckillStatus;
import com.junshou.common.util.IdWorker;
import com.junshou.seckill.pojo.SeckillGoods;
import com.junshou.seckill.pojo.SeckillOrder;
import com.junshou.service.seckill.dao.SeckillGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author X
 * @version 1.0
 * @ClassName MultiThreadingCreateOrder
 * @description
 * @date 2020/2/15
 **/
@Component
public class MultiThreadingCreateOrder {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    @Autowired
    private IdWorker idWorker;

    /***
     * 多线程下单操作
     * @Async 该方法会异步执行（底层多线程方式）
     */
    @Async
    public void createOrder() {
        try {
            //congRedis队列中获取用户排队信息
            SeckillStatus seckillStatus = (SeckillStatus) redisTemplate.boundListOps("SeckillOrderQueue_").rightPop();
            if (null == seckillStatus) {
                return;
            }
            //定义时区 商品ID 用户名
            String time = seckillStatus.getTime();
            Long id = seckillStatus.getGoodsId();
            String username = seckillStatus.getUsername();

            //先到SeckillGoodsCountList_ID队列中获取该商品的信息 如果可以获取到 下单
            Object goods = redisTemplate.boundListOps("SeckillGoodsCountList_" + seckillStatus.getGoodsId()).rightPop();
            //如果不能获取到该商品的队列信息 表示没有库存 清理排队信息
            if (null == goods) {
                //没有库存 清理排队信息
                clearUserQueue(username);
                return;
            }
            //查询秒杀商品
            String namespace = "SeckillGoods_" + time;
            SeckillGoods seckillGoods = (SeckillGoods) redisTemplate.boundHashOps(namespace).get(id);
            //判断是否有库存
            if (null == seckillGoods || seckillGoods.getStockCount() <= 0) {
                throw new RuntimeException("已售罄");
            }
            //创建订单对象
            SeckillOrder seckillOrder = new SeckillOrder();
            seckillOrder.setId(idWorker.nextId());
            //秒杀商品ID
            seckillOrder.setSeckillId(id);
            //支付金额
            seckillOrder.setMoney(seckillGoods.getCostPrice());
            //用户
            seckillOrder.setSellerId(username);
            //创建时间
            seckillOrder.setCreateTime(new Date());
            //状态，0未支付，1已支付
            seckillOrder.setStatus("0");
            /***
             * 将订单存放在Redis
             * 一个用户只允许一次下单
             */
            redisTemplate.boundHashOps("SeckillOrder_").put(username, seckillOrder);

            /***
             * 库存递减
             * 商品如果是最后一个 将商品移除Redis
             * 将Redis数据存储到Mysql中
             */
            seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
            //获取该商品对用的队列数量
            Long size = redisTemplate.boundListOps("SeckillGoodsCountList_" + seckillStatus.getGoodsId()).size();
            //if (seckillGoods.getStockCount() <= 0) {
            if (size <= 0) {
                //同步数量
                seckillGoods.setStockCount(size.intValue());
                //同步数据到Mysql
                seckillGoodsMapper.updateByPrimaryKeySelective(seckillGoods);
                //删除Redis对应数据
                redisTemplate.boundHashOps(namespace).delete(id);
            } else {
                //同步数据到Redis
                redisTemplate.boundHashOps(namespace).put(id, seckillGoods);
            }
            //更新状态
            seckillStatus.setOrderId(seckillOrder.getId());
            //支付金额
            seckillStatus.setMoney(Float.valueOf(seckillGoods.getCostPrice()));
            //代付款状态
            seckillStatus.setStatus(2);
            redisTemplate.boundHashOps("UserQueueStatus_").put(username, seckillStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
