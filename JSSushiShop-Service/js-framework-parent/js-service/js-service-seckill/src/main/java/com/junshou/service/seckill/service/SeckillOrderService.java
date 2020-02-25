package com.junshou.service.seckill.service;

import com.github.pagehelper.PageInfo;
import com.junshou.common.entity.SeckillStatus;
import com.junshou.seckill.pojo.SeckillOrder;

import java.util.List;

/****
 * @Author: X
 * @Description:SeckillOrder业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface SeckillOrderService {

    /***
     * 关闭订单回滚库存[修改状态]
     * @param username
     */
    void closeSeckillOrder(String username) throws Exception;

    /***
     * 修改秒杀订单状态
     * @param username
     * @param timeEnd
     * @param transactionId
     */
    void updataPayStatus(String username,String timeEnd,String transactionId);

    /**
     * @param username
     * @description: 抢单状态查询
     * @return:
     * @author: X
     * @date: 2020/2/15
     */
    SeckillStatus queryStatus(String username);

    /**
     * @param time
     * @param id
     * @param username
     * @description: 添加秒杀订单
     * @return:
     * @author: X
     * @date: 2020/2/15
     */
    Boolean addSeckillOrder(String time, Long id, String username);

    /***
     * SeckillOrder多条件分页查询
     * @param seckillOrder
     * @param page
     * @param size
     * @return
     */
    PageInfo<SeckillOrder> findPage(SeckillOrder seckillOrder, int page, int size);

    /***
     * SeckillOrder分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<SeckillOrder> findPage(int page, int size);

    /***
     * SeckillOrder多条件搜索方法
     * @param seckillOrder
     * @return
     */
    List<SeckillOrder> findList(SeckillOrder seckillOrder);

    /***
     * 删除SeckillOrder
     * @param id
     */
    void delete(Long id);

    /***
     * 修改SeckillOrder数据
     * @param seckillOrder
     */
    void update(SeckillOrder seckillOrder);

    /***
     * 新增SeckillOrder
     * @param seckillOrder
     */
    void add(SeckillOrder seckillOrder);

    /**
     * 根据ID查询SeckillOrder
     *
     * @param id
     * @return
     */
    SeckillOrder findById(Long id);

    /***
     * 查询所有SeckillOrder
     * @return
     */
    List<SeckillOrder> findAll();
}
