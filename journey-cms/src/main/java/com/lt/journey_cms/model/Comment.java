package com.lt.journey_cms.model;

import lombok.Data;

@Data
public class Comment {
	private int id;
	private String parent;
	private String parentID;
	private String avatarUrl;
	private String commenterScreenName;
	private long publishDate;
	private String content;
}
