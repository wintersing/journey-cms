package com.lt.journey_cms.model;

import lombok.Data;

@Data
public class Hotel {
	private String title;
	private String rating;
	private String price;
	private String updatetime;
	private String city;
	private String id;
	private String district;			//地区
	private String recommend = "0";
//	private String minPrice;
//	private String[] facilities;		//屋内设施
//	private String[] infrastructures;	//酒店设施
//	private DoodTagDist[] goodTagDist;	//正面标签分布
//	private String[] assistServices;	//服务
}
