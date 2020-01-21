package com.junshou.service.user.service.impl;

import com.junshou.service.user.dao.UserMapper;
import com.junshou.service.user.service.UserService;
import com.junshou.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2020/1/21
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @description: 查询所有用户
     * @author: X
     * @updateTime: 2020/1/21 19:24
     */
    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }
}
