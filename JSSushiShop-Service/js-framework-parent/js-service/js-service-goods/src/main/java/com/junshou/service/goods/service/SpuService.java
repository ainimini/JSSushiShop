package com.junshou.service.goods.service;

import com.github.pagehelper.PageInfo;
import com.junshou.goods.pojo.Goods;
import com.junshou.goods.pojo.Spu;

import java.util.List;

/****
 * @Author: X
 * @Description:Spu业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface SpuService {

    /**
     * @title: 商品审核并自动上架
     * @description:
     * @author: X
     * @updateTime: 2020/2/3 8:55
     * @param id
     */
    void audit(String id);

    /**
     * @title: 商品下架
     * @description:
     * @author: X
     * @updateTime: 2020/2/3 8:55
     * @return:
     * @param id
     */
    void pull(String id);

    /**
     * @title: 商品上架
     * @description:
     * @author: X
     * @updateTime: 2020/2/3 8:55
     * @return:
     * @param id
     */
    void put(String id);

    /**
     * @title: 还原商品
     * @description:
     * @author: X
     * @updateTime: 2020/2/3 8:55
     * @return:
     * @param id
     */
    void restore(String id);

    /**
     * @title: 物理删除商品
     * @description:
     * @author: X
     * @updateTime: 2020/2/3 8:56
     * @return:
     * @param id
     */
    void realDel(String id);

    /**
     * @title: 查找商品
     * @description: 根据spu id 查找商品信息
     * @author: X
     * @updateTime: 2020/2/2 10:48
     * @param spuId
     */
    Goods findGoodsById(String spuId);
    
    /**
     * @title: 添加商品
     * @description:
     * @author: X
     * @updateTime: 2020/2/1 17:51
     * @param goods
     */
    void saveGoods(Goods goods);

    /***
     * Spu多条件分页查询
     * @param spu
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(Spu spu, int page, int size);

    /***
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(int page, int size);

    /***
     * Spu多条件搜索方法
     * @param spu
     * @return
     */
    List<Spu> findList(Spu spu);

    /***
     * 删除Spu
     * @param id
     */
    void delete(String id);

    /***
     * 修改Spu数据
     * @param spu
     */
    void update(Spu spu);

    /***
     * 新增Spu
     * @param spu
     */
    void add(Spu spu);

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
     Spu findById(String id);

    /***
     * 查询所有Spu
     * @return
     */
    List<Spu> findAll();
}
