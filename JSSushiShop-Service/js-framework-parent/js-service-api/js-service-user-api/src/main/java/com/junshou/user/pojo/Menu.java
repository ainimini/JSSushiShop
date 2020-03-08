package com.junshou.user.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:shenkunlin
 * @Description:Menu构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_menu")
public class Menu implements Serializable{

	@Id
    @Column(name = "id")
	private String id;//

    @Column(name = "code")
	private String code;//菜单编码

    @Column(name = "p_id")
	private String pId;//父菜单ID

    @Column(name = "menu_name")
	private String menuName;//名称

    @Column(name = "url")
	private String url;//请求地址

    @Column(name = "is_menu")
	private String isMenu;//是否是菜单

    @Column(name = "level")
	private Integer level;//菜单层级

    @Column(name = "sort")
	private Integer sort;//菜单排序

    @Column(name = "status")
	private String status;//

    @Column(name = "icon")
	private String icon;//

    @Column(name = "create_time")
	private Date createTime;//

    @Column(name = "update_time")
	private Date updateTime;//



	//get方法
	public String getId() {
		return id;
	}

	//set方法
	public void setId(String id) {
		this.id = id;
	}
	//get方法
	public String getCode() {
		return code;
	}

	//set方法
	public void setCode(String code) {
		this.code = code;
	}
	//get方法
	public String getPId() {
		return pId;
	}

	//set方法
	public void setPId(String pId) {
		this.pId = pId;
	}
	//get方法
	public String getMenuName() {
		return menuName;
	}

	//set方法
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	//get方法
	public String getUrl() {
		return url;
	}

	//set方法
	public void setUrl(String url) {
		this.url = url;
	}
	//get方法
	public String getIsMenu() {
		return isMenu;
	}

	//set方法
	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}
	//get方法
	public Integer getLevel() {
		return level;
	}

	//set方法
	public void setLevel(Integer level) {
		this.level = level;
	}
	//get方法
	public Integer getSort() {
		return sort;
	}

	//set方法
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	//get方法
	public String getStatus() {
		return status;
	}

	//set方法
	public void setStatus(String status) {
		this.status = status;
	}
	//get方法
	public String getIcon() {
		return icon;
	}

	//set方法
	public void setIcon(String icon) {
		this.icon = icon;
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


}
