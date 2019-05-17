package com.lt.journey_cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.journey_cms.dao.GeoPointDao;
import com.lt.journey_cms.dao.PlacesDao;
import com.lt.journey_cms.model.Places;
import com.lt.journey_cms.model.PlacesDes;
import com.lt.journey_cms.service.PlacesService;


@Service("placesService")
public class PlacesServiceImpl implements PlacesService {
	
	@Autowired
	private GeoPointDao geoPointDao;

	@Autowired
	private PlacesDao placesDao;
	
	public void addPlaces(List<? extends Object> placesList) {
		geoPointDao.addGeoPoint(placesList);
		placesDao.addPlaces(placesList);
	}
	
	public String findCityidByCityName(String cityName) {
		String cityid = placesDao.findCityidByCityName(cityName);
		return cityid;
	}

	@Override
	public List<Places> findPlacesRecommend(int offset, int pageSize) {
		return placesDao.findPlacesRecommend(offset, pageSize);
	}

	@Override
	public int findCount() {
		// TODO Auto-generated method stub
		return placesDao.findCount();
	}

//	@Override
//	public String findCityidByCityName_hotel(String cityName) {
//		// TODO Auto-generated method stub
//		return placesDao.findCityidByCityName_hotel(cityName);
//	}

	@Override
	public PlacesDes findPlaces(String id) {
		// TODO Auto-generated method stub
		return placesDao.findPlaces(id);
	}

	@Override
	public void editPlaces(PlacesDes places) {
		// TODO Auto-generated method stub
		placesDao.editPlaces(places);
	}

	@Override
	public void placesDel(String id) {
		// TODO Auto-generated method stub
		placesDao.placesDel(id);
	}

	@Override
	public void placesAdd(PlacesDes places) {
		// TODO Auto-generated method stub
		geoPointDao.geoPointAdd(places.getGeoPoint());
		placesDao.placesAdd(places);
	}

	@Override
	public void placessDel(String[] ids) {
		// TODO Auto-generated method stub
		placesDao.placessDel(ids);
	}

}
