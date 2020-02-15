package com.junshou.service.seckill.service;

import com.github.pagehelper.PageInfo;
import com.junshou.seckill.pojo.SeckillGoods;

import java.util.List;

/****
 * @Author: X
 * @Description:SeckillGoods业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface SeckillGoodsService {

    /**
     * @description: 根据时间和ID查询秒杀商品详情
     * @param time
     * @param id
     * @return:
     * @author: X
     * @date: 2020/2/15
     */
    SeckillGoods details (String time,Long id);

    /**
     * @param time
     * @description: 根据时间区间查询秒杀频道列表数据
     * @return:
     * @author: X
     * @date: 2020/2/14
     */
    List<SeckillGoods> list(String time);

    /***
     * SeckillGoods多条件分页查询
     * @param seckillGoods
     * @param page
     * @param size
     * @return
     */
    PageInfo<SeckillGoods> findPage(SeckillGoods seckillGoods, int page, int size);

    /***
     * SeckillGoods分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<SeckillGoods> findPage(int page, int size);

    /***
     * SeckillGoods多条件搜索方法
     * @param seckillGoods
     * @return
     */
    List<SeckillGoods> findList(SeckillGoods seckillGoods);

    /***
     * 删除SeckillGoods
     * @param id
     */
    void delete(Long id);

    /***
     * 修改SeckillGoods数据
     * @param seckillGoods
     */
    void update(SeckillGoods seckillGoods);

    /***
     * 新增SeckillGoods
     * @param seckillGoods
     */
    void add(SeckillGoods seckillGoods);

    /**
     * 根据ID查询SeckillGoods
     * @param id
     * @return
     */
     SeckillGoods findById(Long id);

    /***
     * 查询所有SeckillGoods
     * @return
     */
    List<SeckillGoods> findAll();
}
