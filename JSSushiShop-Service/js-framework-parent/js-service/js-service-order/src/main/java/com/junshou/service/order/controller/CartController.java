package com.junshou.service.order.controller;

import com.junshou.common.entity.StatusCode;
import com.junshou.common.entity.Result;
import com.junshou.common.util.TokenDecodeUtil;
import com.junshou.service.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
/**
 * @ClassName CartController
 * @Description 购物车控制层
 * @Author X
 * @Data 2020/2/9
 * @Version 1.0
 **/
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 加入购物车
     * @author: X
     * @updateTime: 2020/2/9 21:35
     * @param skuId
     * @param num
     */
    @GetMapping("/addCart")
    public Result addCart(@RequestParam("skuId") String skuId, @RequestParam("num") Integer num){

        //动态获取当前人信息,暂时静态
        //String username = "junshou";
        String username = TokenDecodeUtil.getUserInfo().get("username");
        cartService.addCart(skuId,num,username);
        return new Result(true, StatusCode.OK,"加入购物车成功");
    }

    @GetMapping("/list")
    public Map list(){
        //动态获取当前人信息,暂时静态
        //String username = "junshou";
        String username = TokenDecodeUtil.getUserInfo().get("username");
        Map map = cartService.list(username);
        return map;
    }
}
