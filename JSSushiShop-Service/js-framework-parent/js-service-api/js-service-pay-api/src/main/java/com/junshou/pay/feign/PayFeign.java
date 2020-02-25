package com.junshou.pay.feign;

import com.junshou.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author X
 * @version 1.0
 * @ClassName PayFeign
 * @description 支付feign调用接口
 * @date 2020/2/25
 **/
@FeignClient(name = "pay")
@RequestMapping("/wxpay")
public interface PayFeign {

    @GetMapping("/nativePay")
    Result nativePay(@RequestParam("orderId") String orderId, @RequestParam("money")Integer money);

    @GetMapping("/query/{orderId}")
    Result queryOrder(@PathVariable("orderId") String orderId);

    /***
     * 基于微信关闭订单
     * @param orderId
     * @return
     */
    @PutMapping("/close/{orderId}")
    Result closeOrder(@PathVariable("orderId") String orderId);
}
