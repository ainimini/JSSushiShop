package com.junshou.user.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.lang.String;
/****
 * @Author:shenkunlin
 * @Description:Permission构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_permission")
public class Permission implements Serializable{

	@Id
    @Column(name = "id")
	private String id;//

    @Column(name = "role_id")
	private String roleId;//

    @Column(name = "menu_id")
	private String menuId;//

    @Column(name = "create_time")
	private Date createTime;//



	//get方法
	public String getId() {
		return id;
	}

	//set方法
	public void setId(String id) {
		this.id = id;
	}
	//get方法
	public String getRoleId() {
		return roleId;
	}

	//set方法
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	//get方法
	public String getMenuId() {
		return menuId;
	}

	//set方法
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	//get方法
	public Date getCreateTime() {
		return createTime;
	}

	//set方法
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
