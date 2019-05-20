package com.lt.journey_cms.model;

import lombok.Data;

@Data
public class HotelDes {
	private String id;					//酒店id
	private String brandName;			//品牌名称
//	private String businessDistrict;	//商圈
	private String updatetime;
	private GeoPoint geoPoint;
	private String openingHours;
//	private String commentCount;
	private String rating;
	private String[] tags;
	private String[] facilities;		//屋内设施
	private String[] infrastructures;	//酒店设施
	private String[] assistServices;	//服务
//	private List<Services> services;
	private String city;
	private String description;
	private String price;
	private String level;
//	private String minPrice;
	private String[] telephones;
//	private String url;
	private String district;			//地区
	private String address;
//	private Integer hasWifi;
	private String title;
	private String[] imageUrls;
	private String recommend = "0";
}
