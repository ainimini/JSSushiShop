package com.junshou.service.user.dao;

import com.junshou.user.pojo.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Menu的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface MenuMapper extends Mapper<Menu> {

    /***
     * 通过Id查询用户所有权限
     * @param username
     * @return
     */
    @Select(value = "SELECT * FROM tb_menu WHERE id in(SELECT menu_id FROM tb_permission WHERE role_id in(SELECT role_id FROM tb_user_role WHERE user_id=#{username}))")
    List<Menu> selectPermissionByUserId(@Param(value = "username") String username);
}
