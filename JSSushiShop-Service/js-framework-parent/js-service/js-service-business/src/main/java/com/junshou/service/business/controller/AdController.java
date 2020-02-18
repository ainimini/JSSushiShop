package com.junshou.service.business.controller;

import com.github.pagehelper.PageInfo;
import com.junshou.business.pojo.Ad;
import com.junshou.service.business.service.AdService;
import com.junshou.common.entity.Result;
import com.junshou.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author: X
 * @Description: 广告控制层
 *****/

@RestController
@RequestMapping("/ad")
@CrossOrigin
public class AdController {

    @Autowired
    private AdService adService;

    /***
     * Ad分页条件搜索实现
     * @param ad
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Ad ad, @PathVariable  int page, @PathVariable  int size){
        //调用AdService实现分页条件查询Ad
        PageInfo<Ad> pageInfo = adService.findPage(ad, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Ad分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用AdService实现分页查询Ad
        PageInfo<Ad> pageInfo = adService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param ad
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Ad>> findList(@RequestBody(required = false)  Ad ad){
        //调用AdService实现条件查询Ad
        List<Ad> list = adService.findList(ad);
        return new Result<List<Ad>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用AdService实现根据主键删除
        adService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Ad数据
     * @param ad
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Ad ad,@PathVariable Integer id){
        //设置主键值
        ad.setId(id);
        //调用AdService实现修改Ad
        adService.update(ad);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Ad数据
     * @param ad
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   Ad ad){
        //调用AdService实现添加Ad
        adService.add(ad);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Ad数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Ad> findById(@PathVariable Integer id){
        //调用AdService实现根据主键查询Ad
        Ad ad = adService.findById(id);
        return new Result<Ad>(true,StatusCode.OK,"查询成功",ad);
    }

    /***
     * 查询Ad全部数据
     * @return
     */
    @GetMapping
    public Result<List<Ad>> findAll(){
        //调用AdService实现查询所有Ad
        List<Ad> list = adService.findAll();
        return new Result<List<Ad>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
