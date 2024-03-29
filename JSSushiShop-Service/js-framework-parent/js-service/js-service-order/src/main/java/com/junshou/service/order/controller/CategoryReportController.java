package com.junshou.service.order.controller;

import com.github.pagehelper.PageInfo;
import com.junshou.common.entity.Result;
import com.junshou.order.pojo.CategoryReport;
import com.junshou.service.order.service.CategoryReportService;
import com.junshou.common.entity.Result;
import com.junshou.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.ApiListing;

import java.util.Date;
import java.util.List;

/****
 * @Author: X
 * @Description:
 *****/

@RestController
@RequestMapping("/categoryReport")
@CrossOrigin
public class CategoryReportController {

    @Autowired
    private CategoryReportService categoryReportService;

    /***
     * CategoryReport分页条件搜索实现
     * @param categoryReport
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  CategoryReport categoryReport, @PathVariable  int page, @PathVariable  int size){
        //调用CategoryReportService实现分页条件查询CategoryReport
        PageInfo<CategoryReport> pageInfo = categoryReportService.findPage(categoryReport, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * CategoryReport分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用CategoryReportService实现分页查询CategoryReport
        PageInfo<CategoryReport> pageInfo = categoryReportService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param categoryReport
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<CategoryReport>> findList(@RequestBody(required = false)  CategoryReport categoryReport){
        //调用CategoryReportService实现条件查询CategoryReport
        List<CategoryReport> list = categoryReportService.findList(categoryReport);
        return new Result<List<CategoryReport>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用CategoryReportService实现根据主键删除
        categoryReportService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改CategoryReport数据
     * @param categoryReport
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  CategoryReport categoryReport,@PathVariable Date id){
        //设置主键值
        categoryReport.setCountDate(id);
        //调用CategoryReportService实现修改CategoryReport
        categoryReportService.update(categoryReport);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增CategoryReport数据
     * @param categoryReport
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   CategoryReport categoryReport){
        //调用CategoryReportService实现添加CategoryReport
        categoryReportService.add(categoryReport);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询CategoryReport数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<CategoryReport> findById(@PathVariable String id){
        //调用CategoryReportService实现根据主键查询CategoryReport
        CategoryReport categoryReport = categoryReportService.findById(id);
        return new Result<CategoryReport>(true,StatusCode.OK,"查询成功",categoryReport);
    }

    /***
     * 查询CategoryReport全部数据
     * @return
     */
    @GetMapping
    public Result<List<CategoryReport>> findAll(){
        //调用CategoryReportService实现查询所有CategoryReport
        List<CategoryReport> list = categoryReportService.findAll();
        return new Result<List<CategoryReport>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
