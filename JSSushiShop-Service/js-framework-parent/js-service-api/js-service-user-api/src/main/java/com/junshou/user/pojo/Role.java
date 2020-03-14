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
	private Integer id;//

    @Column(name = "name")
	private String name;//

    @Column(name = "code")
	private String code;//

    @Column(name = "description")
	private String description;//

    @Column(name = "status")
	private String status;//


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
