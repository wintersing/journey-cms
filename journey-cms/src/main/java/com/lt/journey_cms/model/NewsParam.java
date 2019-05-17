package com.lt.journey_cms.model;

import lombok.Data;

@Data
public class NewsParam {
	private String size="15";			//每页条数
	private String sourceType="新闻网站";	//网站分类-新闻网站
	private String sentiment="1";		//情感(1-正面，0-中立,-1负面)
	private String catLabel2;			//文章分类-旅游
	private String publishDateRange;	//
	private String createDateRange;		//
	private String pageToken_news;		//翻页值
}
