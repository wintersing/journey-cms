package com.lt.journey_cms.model;

public class DoodTagDist {
	private String key;
	private Integer value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "DoodTagDist [key=" + key + ", value=" + value + "]";
	}
}
