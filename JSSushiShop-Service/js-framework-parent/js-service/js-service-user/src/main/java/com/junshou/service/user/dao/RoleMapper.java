package com.junshou.service.user.dao;
import com.junshou.user.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author:shenkunlin
 * @Description:Role的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface RoleMapper extends Mapper<Role> {

    /***
     * 通过用户名查找用户角色
     * @param username
     * @return
     */
    @Select(value = "SELECT * FROM tb_role WHERE id in(SELECT role_id FROM tb_user_role WHERE user_id=#{username})")
    Role seleteRoleByUsername(@Param(value = "username") String username);
}
