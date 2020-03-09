package com.junshou.service.seckill.controller;

import com.github.pagehelper.PageInfo;
import com.junshou.common.util.DateUtil;
import com.junshou.seckill.pojo.SeckillGoods;
import com.junshou.service.seckill.service.SeckillGoodsService;
import com.junshou.common.entity.Result;
import com.junshou.common.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

/****
 * @Author: X
 * @Description:
 *****/

@RestController
@RequestMapping("/seckillGoods")
@CrossOrigin
@Api(value = "秒杀管理接口", description = "秒杀商品管理接口，提供页面的增、删、改、查")
public class SeckillGoodsController {

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    /**
     * @param time
     * @param id
     * @description: 根据时间和ID查询秒杀商品详情
     * @return:
     * @author: X
     * @date: 2020/2/15
     */
    @GetMapping(value = "/details")
    @ApiOperation("根据时间和ID查询秒杀商品详情")
    public Result<SeckillGoods> details(@PathParam(value = "time") String time,
                                        @PathParam(value = "id") Long id) {
        SeckillGoods details = seckillGoodsService.details(time, id);
        return new Result<SeckillGoods>(true, StatusCode.OK, "成功查询秒杀商品详情", details);
    }

    /**
     * @description: 查询秒杀列表菜单
     * @return:
     * @author: X
     * @date: 2020/2/15
     */
    @GetMapping(value = "/menus")
    @ApiOperation("查询秒杀列表菜单")
    public Result<List<Date>> menus() {
        List<Date> dateMenus = DateUtil.getDateMenus();
        return new Result<List<Date>>(true, StatusCode.OK, "成功查询秒杀列表菜单", dateMenus);
    }

    /**
     * @param time
     * @description: 根据时间区间查询秒杀频道列表数据
     * @return:
     * @author: X
     * @date: 2020/2/14
     */
    @GetMapping(value = "/list")
    @ApiOperation("根据时间区间查询秒杀频道列表数据")
    public Result<List<SeckillGoods>> list(@PathParam(value = "time") String time) {

        List<SeckillGoods> list = seckillGoodsService.list(time);
        return new Result<List<SeckillGoods>>(true, StatusCode.OK, "成功查询秒杀频道列表数据", list);
    }

    /***
     * SeckillGoods分页条件搜索实现
     * @param seckillGoods
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    @ApiOperation("SeckillGoods分页条件搜索实现")
    public Result<PageInfo> findPage(@RequestBody(required = false) SeckillGoods seckillGoods, @PathVariable int page, @PathVariable int size) {
        //调用SeckillGoodsService实现分页条件查询SeckillGoods
        PageInfo<SeckillGoods> pageInfo = seckillGoodsService.findPage(seckillGoods, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * SeckillGoods分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    @ApiOperation("SeckillGoods分页搜索实现")
    public Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size) {
        //调用SeckillGoodsService实现分页查询SeckillGoods
        PageInfo<SeckillGoods> pageInfo = seckillGoodsService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索秒杀商品数据
     * @param seckillGoods
     * @return
     */
    @PostMapping(value = "/search")
    @ApiOperation("多条件搜索秒杀商品数据")
    public Result<List<SeckillGoods>> findList(@RequestBody(required = false) SeckillGoods seckillGoods) {
        //调用SeckillGoodsService实现条件查询SeckillGoods
        List<SeckillGoods> list = seckillGoodsService.findList(seckillGoods);
        return new Result<List<SeckillGoods>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除秒杀商品数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    @ApiOperation("根据ID删除秒杀商品数据")
    public Result delete(@PathVariable Long id) {
        //调用SeckillGoodsService实现根据主键删除
        seckillGoodsService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改SeckillGoods数据
     * @param seckillGoods
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    @ApiOperation("修改SeckillGoods数据")
    public Result update(@RequestBody SeckillGoods seckillGoods, @PathVariable Long id) {
        //设置主键值
        seckillGoods.setId(id);
        //调用SeckillGoodsService实现修改SeckillGoods
        seckillGoodsService.update(seckillGoods);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增SeckillGoods数据
     * @param seckillGoods
     * @return
     */
    @PostMapping
    @ApiOperation("新增SeckillGoods数据")
    public Result add(@RequestBody SeckillGoods seckillGoods) {
        //调用SeckillGoodsService实现添加SeckillGoods
        seckillGoodsService.add(seckillGoods);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询SeckillGoods数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询SeckillGoods数据")
    public Result<SeckillGoods> findById(@PathVariable Long id) {
        //调用SeckillGoodsService实现根据主键查询SeckillGoods
        SeckillGoods seckillGoods = seckillGoodsService.findById(id);
        return new Result<SeckillGoods>(true, StatusCode.OK, "查询成功", seckillGoods);
    }

    /***
     * 查询SeckillGoods全部数据
     * @return
     */
    @GetMapping
    @ApiOperation("查询SeckillGoods全部数据")
    public Result<List<SeckillGoods>> findAll() {
        //调用SeckillGoodsService实现查询所有SeckillGoods
        List<SeckillGoods> list = seckillGoodsService.findAll();
        return new Result<List<SeckillGoods>>(true, StatusCode.OK, "查询成功", list);
    }
}
