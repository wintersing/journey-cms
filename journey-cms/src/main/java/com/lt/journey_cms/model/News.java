package com.lt.journey_cms.model;

import lombok.Data;

@Data
public class News {
	private String id;
	private String title;
	private String url;
	private String catLabel2;
	private String updatetime;
	private String recommend="0";	//1-酒店列表推荐,2-酒店查找,3-酒店详情推荐,4-景点详情推荐
}
