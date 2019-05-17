package com.lt.journey_cms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lt.journey_cms.model.GeoPoint;
import com.lt.journey_cms.model.PlacesDes;

@Repository
public interface GeoPointDao {
	public void addGeoPoint(List<? extends Object> journeyList);

	public void geoPointAdd(GeoPoint geoPoint);

}
