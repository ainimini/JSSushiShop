package com.junshou.user.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/****
 * @Author: X
 * @Description:User构建
 *****/
@Table(name="tb_user")
public class User implements Serializable{

	@Id
    @Column(name = "username")
	private String username;//用户名作为主键

    @Column(name = "password")
	private String password;//密码，加密存储

    @Column(name = "phone")
	private String phone;//注册手机号

    @Column(name = "email")
	private String email;//注册邮箱

    @Column(name = "created")
	private Date created;//创建时间

    @Column(name = "updated")
	private Date updated;//修改时间

    @Column(name = "source_type")
	private Integer sourceType;//会员来源：1:PC，2：H5，3：Android，4：IOS

    @Column(name = "nick_name")
	private String nickName;//昵称

    @Column(name = "status")
	private Integer status;//使用状态（1正常 0非正常）

    @Column(name = "head_pic")
	private String headPic;//头像地址

    @Column(name = "we_chart")
	private String weChart;//微信号码

    @Column(name = "is_mobile_check")
	private Integer isMobileCheck;//手机是否验证 （0否  1是）

    @Column(name = "is_email_check")
	private Integer isEmailCheck;//邮箱是否检测（0否  1是）

    @Column(name = "sex")
	private Integer sex;//性别，1男，0女

    @Column(name = "user_level")
	private Integer userLevel;//会员等级

    @Column(name = "points")
	private Integer points;//积分

    @Column(name = "experience_value")
	private Integer experienceValue;//经验值

    @Column(name = "birthday")
	private Date birthday;//出生年月日

    @Column(name = "last_login_time")
	private Date lastLoginTime;//最后登录时间



	//get方法
	public String getUsername() {
		return username;
	}

	//set方法
	public void setUsername(String username) {
		this.username = username;
	}
	//get方法
	public String getPassword() {
		return password;
	}

	//set方法
	public void setPassword(String password) {
		this.password = password;
	}
	//get方法
	public String getPhone() {
		return phone;
	}

	//set方法
	public void setPhone(String phone) {
		this.phone = phone;
	}
	//get方法
	public String getEmail() {
		return email;
	}

	//set方法
	public void setEmail(String email) {
		this.email = email;
	}
	//get方法
	public Date getCreated() {
		return created;
	}

	//set方法
	public void setCreated(Date created) {
		this.created = created;
	}
	//get方法
	public Date getUpdated() {
		return updated;
	}

	//set方法
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	//get方法
	public Integer getSourceType() {
		return sourceType;
	}

	//set方法
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	//get方法
	public String getNickName() {
		return nickName;
	}

	//set方法
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	//get方法
	public Integer getStatus() {
		return status;
	}

	//set方法
	public void setStatus(Integer status) {
		this.status = status;
	}
	//get方法
	public String getHeadPic() {
		return headPic;
	}

	//set方法
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	//get方法
	public String getWeChart() {
		return weChart;
	}

	//set方法
	public void setWeChart(String weChart) {
		this.weChart = weChart;
	}
	//get方法
	public Integer getIsMobileCheck() {
		return isMobileCheck;
	}

	//set方法
	public void setIsMobileCheck(Integer isMobileCheck) {
		this.isMobileCheck = isMobileCheck;
	}
	//get方法
	public Integer getIsEmailCheck() {
		return isEmailCheck;
	}

	//set方法
	public void setIsEmailCheck(Integer isEmailCheck) {
		this.isEmailCheck = isEmailCheck;
	}
	//get方法
	public Integer getSex() {
		return sex;
	}

	//set方法
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	//get方法
	public Integer getUserLevel() {
		return userLevel;
	}

	//set方法
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}
	//get方法
	public Integer getPoints() {
		return points;
	}

	//set方法
	public void setPoints(Integer points) {
		this.points = points;
	}
	//get方法
	public Integer getExperienceValue() {
		return experienceValue;
	}

	//set方法
	public void setExperienceValue(Integer experienceValue) {
		this.experienceValue = experienceValue;
	}
	//get方法
	public Date getBirthday() {
		return birthday;
	}

	//set方法
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	//get方法
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	//set方法
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}


}
