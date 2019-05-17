package com.lt.journey_cms.service;

import java.util.List;

import com.lt.journey_cms.model.Hotel;
import com.lt.journey_cms.model.HotelDes;

public interface HotelService {

	public void addHotel(List<? extends Object> hotelList);
//
//	public List<Hotel> findHotelRecommend(String recommend, int offset, int pageSize);
//
//	public String findCityidByCityName(String cityName);
//
	public HotelDes findHotel(String id);
//
	public List<HotelDes> findHotelRecommend(int i, int pagesize);
//
	public int findCount();
//
	public void editHotel(HotelDes hotelDes);
//
	public void hotelDel(String id);


}
