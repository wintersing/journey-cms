package com.lt.journey_cms.model;

import lombok.Data;

@Data
public class HotelParam {
	private String id;
	private String lon;
	private String lat;
	private String distance;			//
	private String kw;					//
	private String pageToken;			//
	private String city;				//城市
	private String level;				//星级
	private String brandName;			//品牌
	private String address;				//地址
	private String businessDistrict;	//商圈
	private String sortByField;			//relevant|rating|price|ratingCount 不填为relevant
//	private String appCode;
}
