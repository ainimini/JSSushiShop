package com.junshou.service.user.service;

import com.github.pagehelper.PageInfo;
import com.junshou.user.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

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

    /**
     * @description: 根据ID查询用户
     * @param userId
     * @author: X
     * @updateTime: 2020/1/21 19:24
     */
    User findUserById(String userId);

    /**
     * @description: 添加用户信息
     * @param user
     * @author: X
     * @updateTime: 2020/1/23 20:07
     */
    void addUser(User user);

    /***
     * 修改
     * @param user
     * @author: X
     * @updateTime: 2020/1/23 20:07
     */
    void update(User user);

    /***
     * 删除
     * @param userId
     */
    void delete(String userId);

    /***
     * 多条件搜索
     * @param searchMap
     * @author: X
     * @updateTime: 2020/1/23 20:07
     */
    List<User> findList(Map<String, Object> searchMap);

    /**
     * @description: 分页查询信息
     * @author: X
     * @updateTime: 2020/1/27 10:44
     * @param page
     * @param size
     */
    PageInfo<User> findPage(Integer page,Integer size);

    /**
     * @description: 分页+条件查询信息
     * @author: X
     * @updateTime: 2020/1/27 10:44
     * @param searchMap
     * @param page
     * @param size
     */
    PageInfo<User> findPage(Map<String, Object> searchMap,Integer page,Integer size);
}
