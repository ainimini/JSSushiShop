package com.junshou.service.user.controller;

import com.github.pagehelper.PageInfo;
import com.junshou.user.pojo.Areas;
import com.junshou.service.user.service.AreasService;
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
@RequestMapping("/areas")
@CrossOrigin
@Api(value = "地区管理接口", description = "地区管理接口，提供页面的增、删、改、查")
public class AreasController {

    @Autowired
    private AreasService areasService;

    /***
     * Areas分页条件搜索实现
     * @param areas
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("Areas分页条件搜索实现")
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Areas areas, @PathVariable  int page, @PathVariable  int size){
        //调用AreasService实现分页条件查询Areas
        PageInfo<Areas> pageInfo = areasService.findPage(areas, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Areas分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("Areas分页搜索实现")
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用AreasService实现分页查询Areas
        PageInfo<Areas> pageInfo = areasService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索地区数据
     * @param areas
     * @return
     */
    @ApiOperation("多条件搜索区数据")
    @PostMapping(value = "/search" )
    public Result<List<Areas>> findList(@RequestBody(required = false)  Areas areas){
        //调用AreasService实现条件查询Areas
        List<Areas> list = areasService.findList(areas);
        return new Result<List<Areas>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除地区数据
     * @param id
     * @return
     */
    @ApiOperation("根据ID删除区数据")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用AreasService实现根据主键删除
        areasService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Areas数据
     * @param areas
     * @param id
     * @return
     */
    @ApiOperation("修改Areas数据")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Areas areas,@PathVariable String id){
        //设置主键值
        areas.setAreaid(id);
        //调用AreasService实现修改Areas
        areasService.update(areas);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Areas数据
     * @param areas
     * @return
     */
    @ApiOperation("新增Areas数据")
    @PostMapping
    public Result add(@RequestBody   Areas areas){
        //调用AreasService实现添加Areas
        areasService.add(areas);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Areas数据
     * @param id
     * @return
     */
    @ApiOperation("根据ID查询Areas数据")
    @GetMapping("/{id}")
    public Result<Areas> findById(@PathVariable String id){
        //调用AreasService实现根据主键查询Areas
        Areas areas = areasService.findById(id);
        return new Result<Areas>(true,StatusCode.OK,"查询成功",areas);
    }

    /***
     * 查询Areas全部数据
     * @return
     */
    @ApiOperation("查询Areas全部数据")
    @GetMapping
    public Result<List<Areas>> findAll(){
        //调用AreasService实现查询所有Areas
        List<Areas> list = areasService.findAll();
        return new Result<List<Areas>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
