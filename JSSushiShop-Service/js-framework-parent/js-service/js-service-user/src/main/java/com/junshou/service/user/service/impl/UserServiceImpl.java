package com.junshou.service.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.junshou.common.entity.Result;
import com.junshou.common.util.IdWorker;
import com.junshou.common.util.MD5Util;
import com.junshou.service.user.dao.UserMapper;
import com.junshou.service.user.service.UserService;
import com.junshou.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * @description: 添加用户积分
     * @param username
     * @param points
     * @return:
     * @author: X
     * @date: 2020/2/12
     */
    @Override
    public void addPoints(String username, Integer points) {
        userMapper.addPoints(username,points);
    }

    /**
     * @description: 查询所有用户
     * @author: X
     * @updateTime: 2020/1/21 19:24
     */
    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    /**
     * @param username
     * @description: 根据ID查询用户
     * @author: X
     * @updateTime: 2020/1/21 19:24
     */
    @Override
    public User findUserById(String username) {
        return userMapper.selectByPrimaryKey(username);
    }

    /**
     * @param user
     * @description: 添加用户信息
     * 带有Selective忽略空值
     * @author: X
     * @updateTime: 2020/1/23 20:07
     */
    @Override
    public void addUser(User user) {
        // 获取当前时间
        Date date = new Date();
        //定义user信息
        User userInfo = new User();
        userInfo.setUsername(user.getUsername());
        userInfo.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userInfo.setPhone(user.getPhone());
        userInfo.setUpdated(user.getCreated());
        userInfo.setCreated(date);
        userInfo.setNickName(user.getNickName());
        userInfo.setPoints(user.getPoints());
        userInfo.setSourceType(user.getSourceType());
        userInfo.setSex(user.getSex());
        userInfo.setStatus(user.getStatus());
        userInfo.setWeChart(user.getWeChart());
        userInfo.setUserLevel(user.getUserLevel());
        userInfo.setIsMobileCheck(user.getIsMobileCheck());
        userInfo.setIsEmailCheck(user.getIsEmailCheck());
        userInfo.setHeadPic(user.getHeadPic());
        userInfo.setExperienceValue(user.getExperienceValue());
        userInfo.setEmail(user.getEmail());
        userInfo.setBirthday(user.getBirthday());
        userMapper.insertSelective(userInfo);
    }

    /**
     * 修改
     *
     * @param user
     */
    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 条件查询
     *
     * @param searchMap
     * @return
     */
    @Override
    public List<User> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return userMapper.selectByExample(example);
    }

    /**
     * @param page 当前页
     * @param size 每页显示条数
     * @description: 分页查询信息
     * @author: X
     * @updateTime: 2020/1/27 10:44
     */
    @Override
    public PageInfo<User> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<User> pageInfo = userMapper.selectAll();
        return new PageInfo<User>(pageInfo);
    }

    /**
     * @param searchMap 搜索条件
     * @param page      当前页
     * @param size      每页显示条数
     * @description: 分页+条件查询信息
     * @author: X
     * @updateTime: 2020/1/27 10:44
     */
    @Override
    public PageInfo<User> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件查询 判断是否为空
        Example example = createExample(searchMap);
        List<User> userList = userMapper.selectByExample(example);
        return new PageInfo<User>(userList);
    }


    /**
     * 构建查询对象
     *
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // 用户名
            if (searchMap.get("username") != null && !"".equals(searchMap.get("username"))) {
                criteria.andEqualTo("userId", searchMap.get("username"));
            }
            // 密码，加密存储
            if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
                criteria.andEqualTo("password", searchMap.get("password"));
            }
            // 注册手机号
            if (searchMap.get("phone") != null && !"".equals(searchMap.get("phone"))) {
                criteria.andLike("phone", "%" + searchMap.get("phone") + "%");
            }
            // 注册邮箱
            if (searchMap.get("email") != null && !"".equals(searchMap.get("email"))) {
                criteria.andLike("email", "%" + searchMap.get("email") + "%");
            }
            // 会员来源：1:PC，2：H5，3：Android，4：IOS
            if (searchMap.get("sourceType") != null && !"".equals(searchMap.get("sourceType"))) {
                criteria.andEqualTo("sourceType", searchMap.get("sourceType"));
            }
            // 昵称
            if (searchMap.get("nickName") != null && !"".equals(searchMap.get("nickName"))) {
                criteria.andLike("nickName", "%" + searchMap.get("nickName") + "%");
            }
            // 使用状态（1正常 0非正常）
            if (searchMap.get("status") != null && !"".equals(searchMap.get("status"))) {
                criteria.andEqualTo("status", searchMap.get("status"));
            }
            // 头像地址
            if (searchMap.get("headPic") != null && !"".equals(searchMap.get("headPic"))) {
                criteria.andLike("headPic", "%" + searchMap.get("headPic") + "%");
            }
            // 微信号码
            if (searchMap.get("weChart") != null && !"".equals(searchMap.get("weChart"))) {
                criteria.andLike("weChart", "%" + searchMap.get("weChart") + "%");
            }
            // 手机是否验证 （0否  1是）
            if (searchMap.get("isMobileCheck") != null && !"".equals(searchMap.get("isMobileCheck"))) {
                criteria.andEqualTo("isMobileCheck", searchMap.get("isMobileCheck"));
            }
            // 邮箱是否检测（0否  1是）
            if (searchMap.get("isEmailCheck") != null && !"".equals(searchMap.get("isEmailCheck"))) {
                criteria.andEqualTo("isEmailCheck", searchMap.get("isEmailCheck"));
            }
            // 性别，1男，0女
            if (searchMap.get("sex") != null && !"".equals(searchMap.get("sex"))) {
                criteria.andEqualTo("sex", searchMap.get("sex"));
            }

            // 会员等级
            if (searchMap.get("userLevel") != null) {
                criteria.andEqualTo("userLevel", searchMap.get("userLevel"));
            }
            // 积分
            if (searchMap.get("points") != null) {
                criteria.andEqualTo("points", searchMap.get("points"));
            }
            // 经验值
            if (searchMap.get("experienceValue") != null) {
                criteria.andEqualTo("experienceValue", searchMap.get("experienceValue"));
            }

        }
        return example;
    }
}
