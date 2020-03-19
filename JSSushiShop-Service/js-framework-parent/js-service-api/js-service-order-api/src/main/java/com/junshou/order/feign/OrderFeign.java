package com.junshou.order.feign;

import com.junshou.common.entity.Result;
import com.junshou.order.pojo.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "order")
public interface OrderFeign {

    @PostMapping("/order")
    public Result<List<Order>> addOrder(@RequestBody Order order) throws Exception;

    @GetMapping("/order/{id}")
    Result<Order> findById(@PathVariable("id") String id);
}
