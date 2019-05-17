package com.lt.journey_cms.model;

import lombok.Data;

@Data
public class PlacesDes {
	private String id;
	private String location;
	private String updatetime;
	private Double rating;
	private String country;
	private String city;
	private String title;
	private String subtitle;
	private String[] imageUrls;		
	private String tipInfo;			//提示信息
	private String openingHours;
	private String description;
	private String price;
	private Integer commentCount;
	private GeoPoint geoPoint;
	private String recommend = "0";		//是否推荐(1-推荐，0-不推荐)
}
