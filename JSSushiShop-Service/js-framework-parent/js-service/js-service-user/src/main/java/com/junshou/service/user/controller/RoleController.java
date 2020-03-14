package com.junshou.service.user.controller;
import com.junshou.user.pojo.Role;
import com.junshou.service.user.service.RoleService;
import com.github.pagehelper.PageInfo;
import com.junshou.common.entity.Result;
import com.junshou.common.entity.StatusCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/role")
@CrossOrigin
@Api(value = "用户角色管理接口", description = "用户角色管理接口，提供页面的增、删、改、查")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /***
     * 通过用户名查找用户角色
     * @param username
     * @return
     */
    @GetMapping(value = "/userRole")
    @PreAuthorize("hasAnyAuthority('oauth')")
    @ApiOperation("通过用户名查找用户角色")
    public Result<Role> findRoleByUsername(@RequestParam(value = "username") String username){
        Role roleByUsername = roleService.findRoleByUsername(username);
        if (null != roleByUsername) {
            return new Result<Role>(true,StatusCode.OK,"成功查询用户角色",roleByUsername);
        }
        return new Result<Role>(false,StatusCode.ERROR,"该用户没有角色");
    }

    /***
     * Role分页条件搜索实现
     * @param role
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    @ApiOperation("Role分页条件搜索实现")
    public Result<PageInfo> findPage(@RequestBody(required = false)  Role role, @PathVariable  int page, @PathVariable  int size){
        //调用RoleService实现分页条件查询Role
        PageInfo<Role> pageInfo = roleService.findPage(role, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Role分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    @ApiOperation("Role分页搜索实现")
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用RoleService实现分页查询Role
        PageInfo<Role> pageInfo = roleService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索用户角色数据
     * @param role
     * @return
     */
    @PostMapping(value = "/search" )
    @ApiOperation("多条件搜索用户角色数据")
    public Result<List<Role>> findList(@RequestBody(required = false)  Role role){
        //调用RoleService实现条件查询Role
        List<Role> list = roleService.findList(role);
        return new Result<List<Role>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除用户角色数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    @ApiOperation("根据ID删除用户角色数据")
    public Result delete(@PathVariable Integer id){
        //调用RoleService实现根据主键删除
        roleService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Role数据
     * @param role
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    @ApiOperation("修改Role数据")
    public Result update(@RequestBody  Role role,@PathVariable Integer id){
        //设置主键值
        role.setId(id);
        //调用RoleService实现修改Role
        roleService.update(role);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Role数据
     * @param role
     * @return
     */
    @PostMapping
    @ApiOperation("新增Role数据")
    public Result add(@RequestBody   Role role){
        //调用RoleService实现添加Role
        roleService.add(role);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Role数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询Role数据")
    public Result<Role> findById(@PathVariable Integer id){
        //调用RoleService实现根据主键查询Role
        Role role = roleService.findById(id);
        return new Result<Role>(true,StatusCode.OK,"查询成功",role);
    }

    /***
     * 查询Role全部数据
     * @return
     */
    @GetMapping
    @ApiOperation("查询Role全部数据")
    public Result<List<Role>> findAll(){
        //调用RoleService实现查询所有Role
        List<Role> list = roleService.findAll();
        return new Result<List<Role>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
