package com.lt.journey_cms.model;

import lombok.Data;

@Data
public class Admin {
	private String id;
	private String adminname;
	private String sex;			//0-女，1-男
	private String password;
	private String password2;
	private String phone;
	private String role;		//0-普通管理员，1-超级管理员
	private String createtime;
}
