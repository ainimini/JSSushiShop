package com.junshou.user.feign;
import com.github.pagehelper.PageInfo;
import com.junshou.common.entity.Result;
import com.junshou.user.pojo.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:X
 * @Description:
 * @Date 2019/6/18 13:58
 *****/
@FeignClient(name="user")
@RequestMapping("/role")
public interface RoleFeign {

    /***
     * 通过用户名查找用户角色
     * @param username
     * @return
     */
    @GetMapping(value = "/userRole")
    Result<Role> findRoleByUsername(@RequestParam(value = "username") String username);

    /***
     * Role分页条件搜索实现
     * @param role
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) Role role, @PathVariable int page, @PathVariable int size);

    /***
     * Role分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size);

    /***
     * 多条件搜索品牌数据
     * @param role
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<Role>> findList(@RequestBody(required = false) Role role);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable Integer id);

    /***
     * 修改Role数据
     * @param role
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Role role, @PathVariable Integer id);

    /***
     * 新增Role数据
     * @param role
     * @return
     */
    @PostMapping
    Result add(@RequestBody Role role);

    /***
     * 根据ID查询Role数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Role> findById(@PathVariable Integer id);

    /***
     * 查询Role全部数据
     * @return
     */
    @GetMapping
    Result<List<Role>> findAll();
}