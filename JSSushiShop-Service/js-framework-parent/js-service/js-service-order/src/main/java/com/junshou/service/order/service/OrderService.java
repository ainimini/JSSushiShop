package com.junshou.service.order.service;

import com.github.pagehelper.PageInfo;
import com.junshou.order.pojo.Order;

import java.text.ParseException;
import java.util.List;

/****
 * @Author: X
 * @Description:Order业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface OrderService {

    /***
     * 关闭订单回滚库存[修改状态]
     * @param orderId
     */
    void closeOrder(String orderId) throws Exception;

    /***
     * 修改支付时间
     * 修改支付状态
     * @param orderId 商户订单号
     * @param timeEnd 支付时间
     * @param transactionId 微信支付订单号
     */
    void updatePayStatus(String orderId,String timeEnd,String transactionId) throws Exception;

    /**
     * @description: 下订单
     * @param order
     * @author: X
     * @date: 2020/2/11
     */
    void addOrder(Order order);

    /***
     * Order多条件分页查询
     * @param order
     * @param page
     * @param size
     * @return
     */
    PageInfo<Order> findPage(Order order, int page, int size);

    /***
     * Order分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Order> findPage(int page, int size);

    /***
     * Order多条件搜索方法
     * @param order
     * @return
     */
    List<Order> findList(Order order);

    /***
     * 删除Order
     * @param id
     */
    void delete(String id);

    /***
     * 修改Order数据
     * @param order
     */
    void update(Order order);

    /***
     * 新增Order
     * @param order
     */
    void add(Order order);

    /**
     * 根据ID查询Order
     * @param id
     * @return
     */
     Order findById(String id);

    /***
     * 查询所有Order
     * @return
     */
    List<Order> findAll();
}
