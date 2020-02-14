package com.junshou.service.user.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.junshou.common.entity.Result;
import com.junshou.common.entity.StatusCode;
import com.junshou.common.util.JwtUtil;
import com.junshou.common.util.TokenDecodeUtil;
import com.junshou.service.user.service.UserService;
import com.junshou.user.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
     * @description: 添加用户积分
     * @param:
     * @return:
     * @author: X
     * @date: 2020/2/12
     */
    @GetMapping(value = "/add/point")
    public Result addPoints(Integer points) {
        //获取用户名
        String username = TokenDecodeUtil.getUserInfo().get("username");
        //调用userService增加用户积分
        userService.addPoints(username, points);
        return new Result(true,StatusCode.OK,"用户积分增加成功");
    }

    /**
     * @param username
     * @param password
     * @title: 用户登录
     * @description:
     * @author: X
     * @updateTime: 2020/2/4 17:26
     * @return:
     * @throws:
     */
    @GetMapping(value = "/login")
    public Result login(@PathParam(value = "username") String username,
                        @PathParam(value = "password") String password,
                        HttpServletResponse response) {
        //通过username查询信息
        User user = userService.findUserById(username);
        //对比密码
        if (BCrypt.checkpw(password, user.getPassword())) {
            //登录成功生成令牌
            Map<String, Object> tokenMap = new HashMap<String, Object>();
            tokenMap.put("role", "USER");
            tokenMap.put("success", "SUCCESS");
            tokenMap.put("username", username);
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(tokenMap), null);
            //令牌存放在cookie
            Cookie cookie = new Cookie("Authorization", token);
            //所属的域名
            cookie.setDomain("localhost");
            cookie.setPath("/");
            response.addCookie(cookie);
            //令牌作位参数
            return new Result(true, StatusCode.OK, "登陆成功", token);
        }
        //登录失败
        return new Result(false, StatusCode.LOGINERROR, "账号或密码有误");
    }

    /**
     * @return List<User>
     * @description: 查询所有用户
     * PreAuthorize只允许admin（管理员）角色来访问
     * @author: X
     * @updateTime: 2020/1/21 19:24
     */
    @GetMapping(value = "/findAll")
    @ApiOperation("查询所有用户")
    @PreAuthorize("hasAnyRole('user')")
    public Result<List<User>> findAll() {
        //查询所有用户
        List<User> userList = userService.findAll();
        //响应结果封装
        return new Result<List<User>>(true, StatusCode.OK, "成功查询所有用户", userList);
    }

    /**
     * @param username
     * @return userById
     * @description: 根据ID查询用户
     * @author: X
     * @updateTime: 2020/1/21 19:24
     */
    @GetMapping(value = "/find/{username}")
    @ApiOperation("根据ID查询用户")
    @PreAuthorize("hasAnyRole('user')")
    public Result<User> findUserById(@PathVariable("username") String username) {
        //通过用户id查询用户信息
        User userById = userService.findUserById(username);
        //响应结果封装
        return new Result<User>(true, StatusCode.OK, "成功根据ID查询用户", userById);
    }

    @GetMapping("/load/{username}")
    public Result<User> findUserInfo(@PathVariable("username") String username) {
        User user = userService.findUserById(username);
        return new Result<User>(true, StatusCode.OK, "成功根据ID查询用户", user);
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
     * @param id
     * @return Result
     */
    @PutMapping(value = "/update/{id}")
    @ApiOperation("修改用户信息")
    public Result update(@RequestBody User user,
                         @PathVariable(value = "id") String id) {
        user.setUsername(id);
        userService.update(user);
        return new Result(true, StatusCode.OK, "成功修改用户信息");
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return Result
     */
    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation("删除用户信息")
    public Result delete(@PathVariable(value = "id") String id) {
        userService.delete(id);
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
     * @RequestParam 注解传入参数的形式为从url中写入参数
     * @RequestBody 注解传入参数的形式为json
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
