package com.junshou.service.user.controller;

import com.github.pagehelper.PageInfo;
import com.junshou.user.pojo.Cities;
import com.junshou.service.user.service.CitiesService;
import com.junshou.common.entity.Result;
import com.junshou.common.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author: X
 * @Description:
 *****/

@RestController
@RequestMapping("/cities")
@CrossOrigin
@Api(value = "城市管理接口", description = "城市管理接口，提供页面的增、删、改、查")
public class CitiesController {

    @Autowired
    private CitiesService citiesService;

    /***
     * Cities分页条件搜索实现
     * @param cities
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("Cities分页条件搜索实现")
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) Cities cities, @PathVariable  int page, @PathVariable  int size){
        //调用CitiesService实现分页条件查询Cities
        PageInfo<Cities> pageInfo = citiesService.findPage(cities, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Cities分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("Cities分页搜索实现")
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用CitiesService实现分页查询Cities
        PageInfo<Cities> pageInfo = citiesService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索城市数据
     * @param cities
     * @return
     */
    @ApiOperation("多条件搜索城市数据")
    @PostMapping(value = "/search" )
    public Result<List<Cities>> findList(@RequestBody(required = false)  Cities cities){
        //调用CitiesService实现条件查询Cities
        List<Cities> list = citiesService.findList(cities);
        return new Result<List<Cities>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除城市数据
     * @param id
     * @return
     */
    @ApiOperation("根据ID删除城市数据")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用CitiesService实现根据主键删除
        citiesService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Cities数据
     * @param cities
     * @param id
     * @return
     */
    @ApiOperation("修改Cities数据")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Cities cities,@PathVariable String id){
        //设置主键值
        cities.setCityid(id);
        //调用CitiesService实现修改Cities
        citiesService.update(cities);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Cities数据
     * @param cities
     * @return
     */
    @ApiOperation("新增Cities数据")
    @PostMapping
    public Result add(@RequestBody   Cities cities){
        //调用CitiesService实现添加Cities
        citiesService.add(cities);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Cities数据
     * @param id
     * @return
     */
    @ApiOperation("根据ID查询Cities数据")
    @GetMapping("/{id}")
    public Result<Cities> findById(@PathVariable String id){
        //调用CitiesService实现根据主键查询Cities
        Cities cities = citiesService.findById(id);
        return new Result<Cities>(true,StatusCode.OK,"查询成功",cities);
    }

    /***
     * 查询Cities全部数据
     * @return
     */
    @ApiOperation("查询Cities全部数据")
    @GetMapping
    public Result<List<Cities>> findAll(){
        //调用CitiesService实现查询所有Cities
        List<Cities> list = citiesService.findAll();
        return new Result<List<Cities>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
