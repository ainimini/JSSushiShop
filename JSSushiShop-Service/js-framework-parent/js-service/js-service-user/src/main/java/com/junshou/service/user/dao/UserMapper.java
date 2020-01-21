package com.junshou.service.user.dao;

import com.junshou.user.pojo.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName dell
 * @Description 通用Mapper
 *              增加数据 调用Mapper.insert()
 *              增加数据 调用Mapper.insertSelective()
 *
 *              修改数据 调用Mapper.update(T)
 *              修改数据 调用Mapper.updateByPrimaryKey(T)
 *
 *              查询数据 调用Mapper.selectByPrimaryKey(id)
 *              查询数据 调用Mapper.select(T)
 * @Author X
 * @Data 2020/1/21
 * @Version 1.0
 **/
public interface UserMapper extends Mapper<User> {
}
