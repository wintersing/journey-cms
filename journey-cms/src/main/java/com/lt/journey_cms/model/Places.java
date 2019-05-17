package com.lt.journey_cms.model;

import lombok.Data;

@Data
public class Places {
	private String id;
	private String updatetime;
	private String location;
	private Double rating;
	private String city;
	private String country;
	private String title;
	private String subtitle;
	private Double price;
	private String openingHours;
	private String recommend;		//是否推荐(1-推荐，0-不推荐)
}
