package com.junshou.service.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.junshou.common.util.IdWorker;
import com.junshou.goods.feign.SkuFeign;
import com.junshou.order.pojo.Order;
import com.junshou.order.pojo.OrderItem;
import com.junshou.service.order.dao.*;
import com.junshou.service.order.service.OrderService;
import com.junshou.user.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/****
 * @Author: X
 * @Description:Order业务层接口实现类
 *****/
@Service
public class OrderServiceImpl implements OrderService {

    private static final String CART = "cart_";

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private SkuFeign skuFeign;
    @Autowired
    private UserFeign userFeign;

    /**
     * @param order
     * @description: 下订单
     * @author: X
     * @date: 2020/2/11
     */
    @Override
    public void addOrder(Order order) {
        /***
         * 未实现功能
         * 价格校验
         */
        //id
        order.setId(String.valueOf(idWorker.nextId()));
        //获取订单明细->购物车集合
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        //获取勾选商品ID 需要下单的商品 将下单的商品ID从购物车中移除
        List<String> skuIds = order.getSkuIds();
        for (String skuId : skuIds) {
            orderItems.add((OrderItem) redisTemplate.boundHashOps(CART + order.getUsername()).get(skuId));
            redisTemplate.boundHashOps(CART + order.getUsername()).delete(skuId);
        }
        //总数量
        int totalNum = 0;
        //总金额
        int totalMoney = 0;

        //封装Map<String,Integer>封装递减数据
        HashMap<String, Integer> decrMap = new HashMap<>();

        for (OrderItem orderItem : orderItems) {
            totalNum += orderItem.getNum();
            totalMoney += orderItem.getMoney();

            //订单明细id
            orderItem.setId(String.valueOf(idWorker.nextId()));
            //订单明细所属的订单
            orderItem.setOrderId(orderItem.getId());
            //是否退货 0:不退货 1:退货
            orderItem.setIsReturn("0");
            decrMap.put(orderItem.getSkuId(), orderItem.getNum());
        }
        /***
         * 订单添加一次
         */
        //创建时间
        order.setCreateTime(new Date());
        //修改时间
        order.setUpdateTime(order.getCreateTime());
        //订单来源 1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面
        order.setSourceType("1");
        //订单状态 0:未支付 1:已支付
        order.setOrderStatus("0");
        //支付状态  0:未支付 1:已支付
        order.setPayStatus("0");
        //是否删除 0:没删除 1:已删除
        order.setIsDelete("0");
        /***
         * 获取订单明细->购物车集合
         */
        //订单购买商品总数
        order.setTotalNum(totalNum);
        //订单总金额
        order.setTotalMoney(totalMoney);
        //实付金额
        order.setPayMoney(totalMoney);
        //添加订单信息
        orderMapper.insertSelective(order);
        /***
         * 明细添加多次
         */
        //循环添加订单明细
        for (OrderItem orderItem : orderItems) {
            orderItemMapper.insertSelective(orderItem);
        }
        //库存递减
        skuFeign.decrCount(decrMap);
        //添加用户积分
        userFeign.addPoints(1);
    }

    /**
     * Order条件+分页查询
     * @param order 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Order> findPage(Order order, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(order);
        //执行搜索
        return new PageInfo<Order>(orderMapper.selectByExample(example));
    }

    /**
     * Order分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Order> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Order>(orderMapper.selectAll());
    }

    /**
     * Order条件查询
     * @param order
     * @return
     */
    @Override
    public List<Order> findList(Order order){
        //构建查询条件
        Example example = createExample(order);
        //根据构建的条件查询数据
        return orderMapper.selectByExample(example);
    }


    /**
     * Order构建查询对象
     * @param order
     * @return
     */
    public Example createExample(Order order){
        Example example=new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        if(order!=null){
            // 订单id
            if(!StringUtils.isEmpty(order.getId())){
                    criteria.andEqualTo("id",order.getId());
            }
            // 数量合计
            if(!StringUtils.isEmpty(order.getTotalNum())){
                    criteria.andEqualTo("totalNum",order.getTotalNum());
            }
            // 金额合计
            if(!StringUtils.isEmpty(order.getTotalMoney())){
                    criteria.andEqualTo("totalMoney",order.getTotalMoney());
            }
            // 优惠金额
            if(!StringUtils.isEmpty(order.getPreMoney())){
                    criteria.andEqualTo("preMoney",order.getPreMoney());
            }
            // 邮费
            if(!StringUtils.isEmpty(order.getPostFee())){
                    criteria.andEqualTo("postFee",order.getPostFee());
            }
            // 实付金额
            if(!StringUtils.isEmpty(order.getPayMoney())){
                    criteria.andEqualTo("payMoney",order.getPayMoney());
            }
            // 支付类型，1、在线支付、0 货到付款
            if(!StringUtils.isEmpty(order.getPayType())){
                    criteria.andEqualTo("payType",order.getPayType());
            }
            // 订单创建时间
            if(!StringUtils.isEmpty(order.getCreateTime())){
                    criteria.andEqualTo("createTime",order.getCreateTime());
            }
            // 订单更新时间
            if(!StringUtils.isEmpty(order.getUpdateTime())){
                    criteria.andEqualTo("updateTime",order.getUpdateTime());
            }
            // 付款时间
            if(!StringUtils.isEmpty(order.getPayTime())){
                    criteria.andEqualTo("payTime",order.getPayTime());
            }
            // 发货时间
            if(!StringUtils.isEmpty(order.getConsignTime())){
                    criteria.andEqualTo("consignTime",order.getConsignTime());
            }
            // 交易完成时间
            if(!StringUtils.isEmpty(order.getEndTime())){
                    criteria.andEqualTo("endTime",order.getEndTime());
            }
            // 交易关闭时间
            if(!StringUtils.isEmpty(order.getCloseTime())){
                    criteria.andEqualTo("closeTime",order.getCloseTime());
            }
            // 物流名称
            if(!StringUtils.isEmpty(order.getShippingName())){
                    criteria.andEqualTo("shippingName",order.getShippingName());
            }
            // 物流单号
            if(!StringUtils.isEmpty(order.getShippingCode())){
                    criteria.andEqualTo("shippingCode",order.getShippingCode());
            }
            // 用户名称
            if(!StringUtils.isEmpty(order.getUsername())){
                    criteria.andLike("username","%"+order.getUsername()+"%");
            }
            // 买家留言
            if(!StringUtils.isEmpty(order.getBuyerMessage())){
                    criteria.andEqualTo("buyerMessage",order.getBuyerMessage());
            }
            // 是否评价
            if(!StringUtils.isEmpty(order.getBuyerRate())){
                    criteria.andEqualTo("buyerRate",order.getBuyerRate());
            }
            // 收货人
            if(!StringUtils.isEmpty(order.getReceiverContact())){
                    criteria.andEqualTo("receiverContact",order.getReceiverContact());
            }
            // 收货人手机
            if(!StringUtils.isEmpty(order.getReceiverMobile())){
                    criteria.andEqualTo("receiverMobile",order.getReceiverMobile());
            }
            // 收货人地址
            if(!StringUtils.isEmpty(order.getReceiverAddress())){
                    criteria.andEqualTo("receiverAddress",order.getReceiverAddress());
            }
            // 订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面
            if(!StringUtils.isEmpty(order.getSourceType())){
                    criteria.andEqualTo("sourceType",order.getSourceType());
            }
            // 交易流水号
            if(!StringUtils.isEmpty(order.getTransactionId())){
                    criteria.andEqualTo("transactionId",order.getTransactionId());
            }
            // 订单状态 
            if(!StringUtils.isEmpty(order.getOrderStatus())){
                    criteria.andEqualTo("orderStatus",order.getOrderStatus());
            }
            // 支付状态 0:未支付 1:已支付
            if(!StringUtils.isEmpty(order.getPayStatus())){
                    criteria.andEqualTo("payStatus",order.getPayStatus());
            }
            // 发货状态 0:未发货 1:已发货 2:已送达
            if(!StringUtils.isEmpty(order.getConsignStatus())){
                    criteria.andEqualTo("consignStatus",order.getConsignStatus());
            }
            // 是否删除
            if(!StringUtils.isEmpty(order.getIsDelete())){
                    criteria.andEqualTo("isDelete",order.getIsDelete());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        orderMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Order
     * @param order
     */
    @Override
    public void update(Order order){
        orderMapper.updateByPrimaryKey(order);
    }

    /**
     * 增加Order
     * @param order
     */
    @Override
    public void add(Order order){
        orderMapper.insert(order);
    }

    /**
     * 根据ID查询Order
     * @param id
     * @return
     */
    @Override
    public Order findById(String id){
        return  orderMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Order全部数据
     * @return
     */
    @Override
    public List<Order> findAll() {
        return orderMapper.selectAll();
    }
}
