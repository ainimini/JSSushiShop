package com.junshou.user.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.lang.String;
/****
 * @Author:shenkunlin
 * @Description:Role构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_role")
public class Role implements Serializable{

	@Id
    @Column(name = "id")
	private String id;//

    @Column(name = "role_name")
	private String roleName;//

    @Column(name = "role_code")
	private String roleCode;//

    @Column(name = "description")
	private String description;//

    @Column(name = "create_time")
	private Date createTime;//

    @Column(name = "update_time")
	private Date updateTime;//

    @Column(name = "status")
	private String status;//



	//get方法
	public String getId() {
		return id;
	}

	//set方法
	public void setId(String id) {
		this.id = id;
	}
	//get方法
	public String getRoleName() {
		return roleName;
	}

	//set方法
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	//get方法
	public String getRoleCode() {
		return roleCode;
	}

	//set方法
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	//get方法
	public String getDescription() {
		return description;
	}

	//set方法
	public void setDescription(String description) {
		this.description = description;
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
	public Date getUpdateTime() {
		return updateTime;
	}

	//set方法
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	//get方法
	public String getStatus() {
		return status;
	}

	//set方法
	public void setStatus(String status) {
		this.status = status;
	}


}
