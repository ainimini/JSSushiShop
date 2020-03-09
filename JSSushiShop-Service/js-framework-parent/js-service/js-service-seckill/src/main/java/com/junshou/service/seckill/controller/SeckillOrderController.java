package com.junshou.service.seckill.controller;

import com.github.pagehelper.PageInfo;
import com.junshou.common.entity.SeckillStatus;
import com.junshou.common.util.TokenDecodeUtil;
import com.junshou.seckill.pojo.SeckillOrder;
import com.junshou.service.seckill.service.SeckillOrderService;
import com.junshou.common.entity.Result;
import com.junshou.common.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/****
 * @Author: X
 * @Description:
 *****/

@RestController
@RequestMapping("/seckillOrder")
@CrossOrigin
@Api(value = "秒杀管理接口", description = "秒杀订单管理接口，提供页面的增、删、改、查")
public class SeckillOrderController {

    @Autowired
    private SeckillOrderService seckillOrderService;

    /**
     * @description: 抢单状态查询
     * @return:
     * @author: X
     * @date: 2020/2/15
     */
    @GetMapping(value = "/queryStatus")
    @ApiOperation("抢单状态查询")
    public Result queryStatus() {
        //String username = "junshou";
        String username = TokenDecodeUtil.getUserInfo().get("username");
        SeckillStatus seckillStatus = seckillOrderService.queryStatus(username);
        if (null != seckillStatus) {
            return new Result(true, StatusCode.OK, "查询状态成功", seckillStatus);
        }
        return new Result(false, StatusCode.ERROR, "抢单失败");
    }

    /**
     * @param time
     * @param id
     * @description: 添加秒杀订单
     * @return:
     * @author: X
     * @date: 2020/2/15
     */
    @GetMapping(value = "/add/seckillOrder")
    @ApiOperation("添加秒杀订单")
    public Result addSeckillOrder(@PathParam(value = "time") String time,
                                  @PathParam(value = "id") Long id) {
        //String username = "junshou";
        String username = TokenDecodeUtil.getUserInfo().get("username");
        seckillOrderService.addSeckillOrder(time, id, username);
        return new Result(true, StatusCode.OK, "正在抢单中。。。");
    }

    /***
     * SeckillOrder分页条件搜索实现
     * @param seckillOrder
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    @ApiOperation("SeckillOrder分页条件搜索实现")
    public Result<PageInfo> findPage(@RequestBody(required = false) SeckillOrder seckillOrder, @PathVariable int page, @PathVariable int size) {
        //调用SeckillOrderService实现分页条件查询SeckillOrder
        PageInfo<SeckillOrder> pageInfo = seckillOrderService.findPage(seckillOrder, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * SeckillOrder分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    @ApiOperation("SeckillOrder分页搜索实现")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用SeckillOrderService实现分页查询SeckillOrder
        PageInfo<SeckillOrder> pageInfo = seckillOrderService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索秒杀订单数据
     * @param seckillOrder
     * @return
     */
    @PostMapping(value = "/search")
    @ApiOperation("多条件搜索秒杀订单数据")
    public Result<List<SeckillOrder>> findList(@RequestBody(required = false) SeckillOrder seckillOrder) {
        //调用SeckillOrderService实现条件查询SeckillOrder
        List<SeckillOrder> list = seckillOrderService.findList(seckillOrder);
        return new Result<List<SeckillOrder>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除秒杀订单数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    @ApiOperation("根据ID删除秒杀订单数据")
    public Result delete(@PathVariable Long id) {
        //调用SeckillOrderService实现根据主键删除
        seckillOrderService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改SeckillOrder数据
     * @param seckillOrder
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    @ApiOperation("修改SeckillOrder数据")
    public Result update(@RequestBody SeckillOrder seckillOrder, @PathVariable Long id) {
        //设置主键值
        seckillOrder.setId(id);
        //调用SeckillOrderService实现修改SeckillOrder
        seckillOrderService.update(seckillOrder);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增SeckillOrder数据
     * @param seckillOrder
     * @return
     */
    @PostMapping
    @ApiOperation("新增SeckillOrder数据")
    public Result add(@RequestBody SeckillOrder seckillOrder) {
        //调用SeckillOrderService实现添加SeckillOrder
        seckillOrderService.add(seckillOrder);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询SeckillOrder数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询SeckillOrder数据")
    public Result<SeckillOrder> findById(@PathVariable Long id) {
        //调用SeckillOrderService实现根据主键查询SeckillOrder
        SeckillOrder seckillOrder = seckillOrderService.findById(id);
        return new Result<SeckillOrder>(true, StatusCode.OK, "查询成功", seckillOrder);
    }

    /***
     * 查询SeckillOrder全部数据
     * @return
     */
    @GetMapping
    @ApiOperation("查询SeckillOrder全部数据")
    public Result<List<SeckillOrder>> findAll() {
        //调用SeckillOrderService实现查询所有SeckillOrder
        List<SeckillOrder> list = seckillOrderService.findAll();
        return new Result<List<SeckillOrder>>(true, StatusCode.OK, "查询成功", list);
    }
}
