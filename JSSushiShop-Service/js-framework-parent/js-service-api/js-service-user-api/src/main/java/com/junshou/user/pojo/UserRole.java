package com.junshou.user.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.lang.String;
/****
 * @Author:shenkunlin
 * @Description:UserRole构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_user_role")
public class UserRole implements Serializable{

	@Id
    @Column(name = "id")
	private String id;//

    @Column(name = "user_id")
	private String userId;//

    @Column(name = "role_id")
	private String roleId;//

    @Column(name = "create_time")
	private Date createTime;//

    @Column(name = "creator")
	private String creator;//



	//get方法
	public String getId() {
		return id;
	}

	//set方法
	public void setId(String id) {
		this.id = id;
	}
	//get方法
	public String getUserId() {
		return userId;
	}

	//set方法
	public void setUserId(String userId) {
		this.userId = userId;
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
	public Date getCreateTime() {
		return createTime;
	}

	//set方法
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	//get方法
	public String getCreator() {
		return creator;
	}

	//set方法
	public void setCreator(String creator) {
		this.creator = creator;
	}


}
