package com.lt.journey_cms.model;

import lombok.Data;

@Data
public class PlacesParam {
	private String cityid;
	private String cityName;
	private String id;
	private String kw;
	private String pageToken;
	private String sort;		//(人气最高：1，距离最近：2)
	private String lon;
	private String lat;	
	private String hasNext;		//是否有下一页(0：没有，1：有)
}
