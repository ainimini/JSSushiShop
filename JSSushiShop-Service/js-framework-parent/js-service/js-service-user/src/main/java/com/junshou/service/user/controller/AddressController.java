package com.junshou.service.user.controller;

import com.github.pagehelper.PageInfo;
import com.junshou.common.util.TokenDecodeUtil;
import com.junshou.user.pojo.Address;
import com.junshou.service.user.service.AddressService;
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
@RequestMapping("/address")
@CrossOrigin
@Api(value = "用户收件地址管理接口", description = "用户收件地址管理接口，提供页面的增、删、改、查")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * @description: 根据用户登录名字查询用户收件地址列表信息
     * @return:
     * @author: X
     * @date: 2020/2/11
     */
    @GetMapping(value = "/user/list")
    @ApiOperation("根据用户登录名字查询用户收件地址列表信息")
    public Result<List<Address>> list(){
        //获取用户信息
        String username = TokenDecodeUtil.getUserInfo().get("username");
        //查询用户地址列表信息
        List<Address> addressList = addressService.list(username);
        return new Result<List<Address>>(true,StatusCode.OK,"成功查询用户地址列表",addressList);
    }

    /***
     * Address分页条件搜索实现
     * @param address
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    @ApiOperation("Address分页条件搜索实现")
    public Result<PageInfo> findPage(@RequestBody(required = false)  Address address, @PathVariable  int page, @PathVariable  int size){
        //调用AddressService实现分页条件查询Address
        PageInfo<Address> pageInfo = addressService.findPage(address, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Address分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    @ApiOperation("Address分页搜索实现")
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用AddressService实现分页查询Address
        PageInfo<Address> pageInfo = addressService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索用户地址数据
     * @param address
     * @return
     */
    @PostMapping(value = "/search")
    @ApiOperation("多条件搜索用户地址数据")
    public Result<List<Address>> findList(@RequestBody(required = false)  Address address){
        //调用AddressService实现条件查询Address
        List<Address> list = addressService.findList(address);
        return new Result<List<Address>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除用户地址数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    @ApiOperation("根据ID删除用户地址数据")
    public Result delete(@PathVariable Integer id){
        //调用AddressService实现根据主键删除
        addressService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Address数据
     * @param address
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    @ApiOperation("修改Address数据")
    public Result update(@RequestBody  Address address,@PathVariable Integer id){
        //设置主键值
        address.setId(id);
        //调用AddressService实现修改Address
        addressService.update(address);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Address数据
     * @param address
     * @return
     */
    @PostMapping
    @ApiOperation("新增Address数据")
    public Result add(@RequestBody   Address address){
        //调用AddressService实现添加Address
        addressService.add(address);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Address数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询Address数据")
    public Result<Address> findById(@PathVariable Integer id){
        //调用AddressService实现根据主键查询Address
        Address address = addressService.findById(id);
        return new Result<Address>(true,StatusCode.OK,"查询成功",address);
    }

    /***
     * 查询Address全部数据
     * @return
     */
    @GetMapping
    @ApiOperation("查询Address全部数据")
    public Result<List<Address>> findAll(){
        //调用AddressService实现查询所有Address
        List<Address> list = addressService.findAll();
        return new Result<List<Address>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
