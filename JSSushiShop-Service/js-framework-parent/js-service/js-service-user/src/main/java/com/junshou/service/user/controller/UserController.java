package com.junshou.service.user.controller;

import com.github.pagehelper.PageInfo;
import com.junshou.common.entity.Result;
import com.junshou.common.entity.StatusCode;
import com.junshou.service.user.service.UserService;
import com.junshou.user.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description 用户Controller层
 * @Author X
 * @Data 2020/1/21
 * @Version 1.0
 **/
@RestController
/**
 * @title: @CrossOrigin
 * @description: 解决跨域
 */
@CrossOrigin
@RequestMapping(value = "/user")
@Api(value = "用户管理接口", description = "用户管理接口，提供页面的增、删、改、查")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @return List<User>
     * @description: 查询所有用户
     * @author: X
     * @updateTime: 2020/1/21 19:24
     */
    @GetMapping(value = "/findAll")
    @ApiOperation("查询所有用户")
    public Result<List<User>> findAll() {
        //查询所有用户
        List<User> userList = userService.findAll();
        //响应结果封装
        return new Result<List<User>>(true, StatusCode.OK, "成功查询所有用户", userList);
    }

    /**
     * @param userId
     * @return User
     * @description: 根据ID查询用户
     * @author: X
     * @updateTime: 2020/1/21 19:24
     */
    @GetMapping(value = "/find/{userId}")
    @ApiOperation("根据ID查询用户")
    public Result<User> findUserById(@PathVariable("userId") String userId) {
        //通过用户id查询用户信息
        User userById = userService.findUserById(userId);
        //响应结果封装
        return new Result<User>(true, StatusCode.OK, "成功根据ID查询用户", userById);
    }

    /**
     * @param user
     * @description: 添加用户信息
     * @author: X
     * @updateTime: 2020/1/23 20:07
     */
    @PostMapping(value = "/add")
    @ApiOperation("添加用户信息")
    public Result addUser(@RequestBody User user) {
        userService.addUser(user);
        return new Result(true, StatusCode.OK, "成功添加用户信息");
    }

    /***
     * 修改数据
     * @param user
     * @param userId
     * @return Result
     */
    @PutMapping(value = "/update/{userId}")
    @ApiOperation("修改用户信息")
    public Result update(@RequestBody User user,
                         @PathVariable(value = "userId") String userId) {
        user.setUserId(userId);
        userService.update(user);
        return new Result(true, StatusCode.OK, "成功修改用户信息");
    }

    /***
     * 根据ID删除品牌数据
     * @param userId
     * @return Result
     */
    @DeleteMapping(value = "/delete/{userId}")
    @ApiOperation("删除用户信息")
    public Result delete(@PathVariable(value = "userId") String userId) {
        userService.delete(userId);
        return new Result(true, StatusCode.OK, "成功删除用户信息");
    }

    /***
     * 多条件搜索品牌数据
     * @param searchMap
     * @return Result<List < User>>
     */
    @GetMapping(value = "/search")
    @ApiOperation("多条件搜索")
    public Result<List<User>> findList(@RequestParam Map searchMap) {
        List<User> list = userService.findList(searchMap);
        return new Result<List<User>>(true, StatusCode.OK, "成功实现条件查询", list);
    }

    /**
     * @param page 当前页
     * @param size 每页显示条数
     * @description: 分页查询信息
     * @author: X
     * @updateTime: 2020/1/27 10:44
     */
    @GetMapping(value = "/search/{page}/{size}")
    @ApiOperation("分页查询信息")
    public Result<PageInfo<User>> findPage(@PathVariable(value = "page") Integer page,
                                           @PathVariable(value = "size") Integer size) {
        PageInfo<User> pageInfo = userService.findPage(page, size);
        return new Result<PageInfo<User>>(true, StatusCode.OK, "成功实现分页查询", pageInfo);
    }

    /**
     * @param searchMap 搜索条件
     * @param page      当前页
     * @param size      每页显示条数
     * @description: 分页+条件查询信息
     *              @RequestParam 注解传入参数的形式为从url中写入参数
     *              @RequestBody 注解传入参数的形式为json
     * @author: X
     * @updateTime: 2020/1/27 10:44
     */
    @PostMapping(value = "/search/page/{page}/{size}")
    @ApiOperation("分页+条件查询信息")
    public Result<PageInfo<User>> findPage(@RequestParam Map searchMap,
                                           @PathVariable(value = "page") Integer page,
                                           @PathVariable(value = "size") Integer size) {
        PageInfo pageList = userService.findPage(searchMap, page, size);
        return new Result<PageInfo<User>>(true, StatusCode.OK, "成功实现分页查询", pageList);
    }
}
