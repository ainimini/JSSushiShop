package com.junshou.service.order.service.impl;

import com.junshou.goods.feign.SkuFeign;
import com.junshou.goods.feign.SpuFeign;
import com.junshou.goods.pojo.Sku;
import com.junshou.goods.pojo.Spu;
import com.junshou.order.pojo.OrderItem;
import com.junshou.service.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CartServiceImpl
 * @Description 购物车服务实现类
 * @Author X
 * @Data 2020/2/9
 * @Version 1.0
 **/
@Service
public class CartServiceImpl implements CartService {

    private static final String CART="cart_";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SpuFeign spuFeign;

    /**
     * @description: 查询购物车列表数据
     * @author: X
     * @updateTime: 2020/2/9 22:00
     * @param username
     * @param num
     * @param skuId
     */
    @Override
    public void addCart(String skuId, Integer num, String username) {
        //1.查询redis中相对应的商品信息
        OrderItem orderItem = (OrderItem) redisTemplate.boundHashOps(CART+username).get(skuId);
        if (orderItem != null){
            //2.如果当前商品在redis中的存在,则更新商品的数量与价钱
            orderItem.setNum(orderItem.getNum()+num);
            if (orderItem.getNum()<=0){
                //删除该商品
                redisTemplate.boundHashOps(CART+username).delete(skuId);
                return;
            }
            orderItem.setMoney(orderItem.getNum()*orderItem.getPrice());
            orderItem.setPayMoney(orderItem.getNum()*orderItem.getPrice());
        }else {
            //3.如果当前商品在redis中不存在,将商品添加到redis中
            Sku sku = skuFeign.findById(skuId).getData();
            Spu spu = spuFeign.findById(sku.getSpuId()).getData();

            //封装orderItem
            orderItem = this.sku2OrderItem(sku,spu,num);
        }

        //3.将orderitem添加到redis中
        redisTemplate.boundHashOps(CART+username).put(skuId,orderItem);
    }

    /**
     * @description: 查询购物车列表数据
     * @author: X
     * @updateTime: 2020/2/9 22:00
     * @return: map
     * @param username
     */
    @Override
    public Map list(String username) {
        Map map = new HashMap();

        List<OrderItem> orderItemList = redisTemplate.boundHashOps(CART + username).values();
        map.put("orderItemList",orderItemList);

        //商品的总数量与总价格
        Integer totalNum = 0;
        Integer totalMoney = 0;

        for (OrderItem orderItem : orderItemList) {
            totalNum+=orderItem.getNum();
            totalMoney+=orderItem.getMoney();
        }

        map.put("totalNum",totalNum);
        map.put("totalMoney",totalMoney);

        return map;
    }

    private OrderItem sku2OrderItem(Sku sku, Spu spu, Integer num) {
        OrderItem orderItem = new OrderItem();
        orderItem.setSpuId(sku.getSpuId());
        orderItem.setSkuId(sku.getId());
        orderItem.setName(sku.getName());
        orderItem.setPrice(sku.getPrice());
        orderItem.setNum(num);
        orderItem.setMoney(orderItem.getPrice()*num);
        orderItem.setPayMoney(orderItem.getPrice()*num);
        orderItem.setImage(sku.getImage());
        orderItem.setWeight(sku.getWeight()*num);
        //分类信息
        orderItem.setCategoryId1(spu.getCategory1Id());
        orderItem.setCategoryId2(spu.getCategory2Id());
        orderItem.setCategoryId3(spu.getCategory3Id());
        return orderItem;
    }
}
