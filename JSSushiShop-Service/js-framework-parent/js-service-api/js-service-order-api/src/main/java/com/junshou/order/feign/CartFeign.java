package com.junshou.order.feign;

import com.junshou.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "order")
public interface CartFeign {

    @GetMapping("/cart/addCart")
    Result addCart(@RequestParam("skuId") String skuId, @RequestParam("num") Integer num);

    @GetMapping("/cart/list")
    Map list();
}
