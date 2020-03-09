package com.junshou.service.user.controller;

import com.github.pagehelper.PageInfo;
import com.junshou.user.pojo.Provinces;
import com.junshou.service.user.service.ProvincesService;
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
@RequestMapping("/provinces")
@CrossOrigin
@Api(value = "省会管理接口", description = "省会管理接口，提供页面的增、删、改、查")
public class ProvincesController {

    @Autowired
    private ProvincesService provincesService;

    /***
     * Provinces分页条件搜索实现
     * @param provinces
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("Provinces分页条件搜索实现")
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Provinces provinces, @PathVariable  int page, @PathVariable  int size){
        //调用ProvincesService实现分页条件查询Provinces
        PageInfo<Provinces> pageInfo = provincesService.findPage(provinces, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Provinces分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("Provinces分页搜索实现")
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用ProvincesService实现分页查询Provinces
        PageInfo<Provinces> pageInfo = provincesService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索省会数据
     * @param provinces
     * @return
     */
    @ApiOperation("多条件搜索省会数据")
    @PostMapping(value = "/search" )
    public Result<List<Provinces>> findList(@RequestBody(required = false)  Provinces provinces){
        //调用ProvincesService实现条件查询Provinces
        List<Provinces> list = provincesService.findList(provinces);
        return new Result<List<Provinces>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除省会数据
     * @param id
     * @return
     */
    @ApiOperation("根据ID删除省会数据")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用ProvincesService实现根据主键删除
        provincesService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Provinces数据
     * @param provinces
     * @param id
     * @return
     */
    @ApiOperation("修改Provinces数据")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Provinces provinces,@PathVariable String id){
        //设置主键值
        provinces.setProvinceid(id);
        //调用ProvincesService实现修改Provinces
        provincesService.update(provinces);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Provinces数据
     * @param provinces
     * @return
     */
    @ApiOperation("新增Provinces数据")
    @PostMapping
    public Result add(@RequestBody   Provinces provinces){
        //调用ProvincesService实现添加Provinces
        provincesService.add(provinces);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Provinces数据
     * @param id
     * @return
     */
    @ApiOperation("根据ID查询Provinces数据")
    @GetMapping("/{id}")
    public Result<Provinces> findById(@PathVariable String id){
        //调用ProvincesService实现根据主键查询Provinces
        Provinces provinces = provincesService.findById(id);
        return new Result<Provinces>(true,StatusCode.OK,"查询成功",provinces);
    }

    /***
     * 查询Provinces全部数据
     * @return
     */
    @ApiOperation("查询Provinces全部数据")
    @GetMapping
    public Result<List<Provinces>> findAll(){
        //调用ProvincesService实现查询所有Provinces
        List<Provinces> list = provincesService.findAll();
        return new Result<List<Provinces>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
