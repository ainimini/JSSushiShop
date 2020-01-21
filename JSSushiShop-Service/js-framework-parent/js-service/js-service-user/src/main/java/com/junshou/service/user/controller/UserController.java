package com.junshou.service.user.controller;

import com.junshou.common.entity.Result;
import com.junshou.common.entity.StatusCode;
import com.junshou.service.user.service.UserService;
import com.junshou.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "findAll")
    public Result findAll(){
        //查询所有用户
        List<User> userList = userService.findAll();
        //响应结果封装
        return new Result(true, StatusCode.OK,"查询成功",userList);
    }
}
