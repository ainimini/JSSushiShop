package com.junshou.service.order.service;

import java.util.Map;

/**
 * @ClassName CartService
 * @Description 购物车服务
 * @Author X
 * @Data 2020/2/9-21:31
 * @Version 1.0
 **/
public interface CartService {
    //添加购物车
    void addCart(String skuId,Integer num,String username);

    //查询购物车数据
    Map list(String username);
}
