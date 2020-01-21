package com.junshou.service.user.service;

import com.junshou.user.pojo.User;

import java.util.List;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2020/1/21-19:05
 * @Version 1.0
 **/
public interface UserService {

    /**
     * @description: 查询所有用户
     * @author: X
     * @updateTime: 2020/1/21 19:24
     */
    List<User> findAll();
}
