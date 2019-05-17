package com.lt.journey_cms.service;

import java.util.List;

import com.lt.journey_cms.model.Places;
import com.lt.journey_cms.model.PlacesDes;

public interface PlacesService {

	public void placesAdd(PlacesDes places);

	public void addPlaces(List<? extends Object> placesList);

	public String findCityidByCityName(String cityName);

	public List<Places> findPlacesRecommend(int offset, int pageSize);

	public int findCount();

//	public String findCityidByCityName_hotel(String string);

	public PlacesDes findPlaces(String id);

	public void editPlaces(PlacesDes places);

	public void placesDel(String id);

	public void placessDel(String[] ids);

}
