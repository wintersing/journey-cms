package com.lt.journey_cms.model;

import lombok.Data;

@Data
public class BlogParam {
	private String pageToken;
	private String cityid;
	private String id;
	private String sort;
	private String DaysMin;
	private String DaysMax;
	private String Month;
	private String company;
	private String recommend;
	private String kw;
}
