package com.junshou.service.order.controller;

import com.github.pagehelper.PageInfo;
import com.junshou.common.util.TokenDecodeUtil;
import com.junshou.order.pojo.Order;
import com.junshou.service.order.service.OrderService;
import com.junshou.common.entity.Result;
import com.junshou.common.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/****
 * @Author: X
 * @Description:
 *****/

@RestController
@RequestMapping("/order")
@CrossOrigin
@Api(value = "订单管理接口", description = "订单管理接口，提供页面的增、删、改、查")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /***
     * Order分页条件搜索实现
     * @param order
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    @ApiOperation("Order分页条件搜索实现")
    public Result<PageInfo> findPage(@RequestBody(required = false)  Order order, @PathVariable  int page, @PathVariable  int size){
        //调用OrderService实现分页条件查询Order
        PageInfo<Order> pageInfo = orderService.findPage(order, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Order分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    @ApiOperation("Order分页搜索实现")
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用OrderService实现分页查询Order
        PageInfo<Order> pageInfo = orderService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索订单数据
     * @param order
     * @return
     */
    @PostMapping(value = "/search" )
    @ApiOperation("多条件搜索订单数据")
    public Result<List<Order>> findList(@RequestBody(required = false)  Order order){
        //调用OrderService实现条件查询Order
        List<Order> list = orderService.findList(order);
        return new Result<List<Order>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除订单数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    @ApiOperation("根据ID删除订单数据")
    public Result delete(@PathVariable String id){
        //调用OrderService实现根据主键删除
        orderService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Order数据
     * @param order
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    @ApiOperation("修改Order数据")
    public Result update(@RequestBody Order order,@PathVariable String id){
        //设置主键值
        order.setId(id);
        //调用OrderService实现修改Order
        orderService.update(order);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Order数据 提交订单
     * @param order
     * @return
     */
    @PostMapping
    @ApiOperation("新增Order数据 提交订单")
    public Result<List<Order>> addOrder(@RequestBody Order order) throws Exception {
        //获取用户名 并赋值给当前对象
        String username = TokenDecodeUtil.getUserInfo().get("username");
        order.setUsername(username);
        //调用OrderService实现添加Order
        Order orderInfo = orderService.addOrder(order);
        return new Result<List<Order>>(true,StatusCode.OK,"成功提交订单",orderInfo);
    }

    /***
     * 根据ID查询Order数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询Order数据")
    public Result<Order> findById(@PathVariable String id){
        //调用OrderService实现根据主键查询Order
        Order order = orderService.findById(id);
        return new Result<Order>(true,StatusCode.OK,"查询成功",order);
    }

    /***
     * 查询Order全部数据
     * @return
     */
    @GetMapping
    @ApiOperation("查询Order全部数据")
    public Result<List<Order>> findAll(){
        //调用OrderService实现查询所有Order
        List<Order> list = orderService.findAll();
        return new Result<List<Order>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
