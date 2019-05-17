package com.lt.journey_cms.model;

import lombok.Data;

@Data
public class BlogDes {
	private String content;
	private String commentCount;
	private String id;
	private String cityid;
	private String imageUrls;
	private String likeCount;
	private String updatetime;
	private String avatarUrl;
	private String viewCount;
	private String city;
	private String title;
	private String publishDateStr;
	private String posterScreenName;
	private String recommend = "0";
}
