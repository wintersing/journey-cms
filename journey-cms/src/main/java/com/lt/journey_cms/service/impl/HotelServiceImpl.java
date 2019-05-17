package com.lt.journey_cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.journey_cms.dao.GeoPointDao;
import com.lt.journey_cms.dao.HotelDao;
import com.lt.journey_cms.model.Hotel;
import com.lt.journey_cms.model.HotelDes;
import com.lt.journey_cms.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelDao hotelDao;
	
	@Autowired
	private GeoPointDao geoPointDao;

	@Override
	public void addHotel(List<? extends Object> hotelList) {
		// TODO Auto-generated method stub
		geoPointDao.addGeoPoint(hotelList);
		hotelDao.addHotel(hotelList);
	}

	@Override
	public int findCount() {
		// TODO Auto-generated method stub
		return hotelDao.findCount();
	}

//	@Override
//	public String findCityidByCityName(String cityName) {
//		// TODO Auto-generated method stub
//		return hotelDao.findCityidByCityName(cityName);
//	}

	@Override
	public HotelDes findHotel(String id) {
		// TODO Auto-generated method stub
		return hotelDao.findHotel(id);
	}

	@Override
	public List<HotelDes> findHotelRecommend(int i, int pagesize) {
		// TODO Auto-generated method stub
		return hotelDao.findHotelRecommend(i, pagesize);
	}

//	@Override
//	public List<Hotel> findHotelRecommend(String recommend, int offset, int pageSize) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void editHotel(HotelDes hotelDes) {
		// TODO Auto-generated method stub
		hotelDao.editHotel(hotelDes);
	}

	@Override
	public void hotelDel(String id) {
		// TODO Auto-generated method stub
		hotelDao.hotelDel(id);
	}

}
