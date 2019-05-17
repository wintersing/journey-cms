package com.lt.journey_cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.lt.journey_cms.model.Places;
import com.lt.journey_cms.model.PlacesDes;

@Repository
public interface PlacesDao {
	public void addPlaces(List<? extends Object> placesList);
	
	@Select("select cityid from places_city where cityName = #{cityName}")
	public String findCityidByCityName(@Param("cityName")String cityName);

	@Select("select * from places ORDER BY updatetime DESC limit #{offset}, #{pageSize}")
	public List<Places> findPlacesRecommend(@Param("offset")int offset, @Param("pageSize")int pageSize);

	@Select("select count(*) from places")
	public int findCount();

//	@Select("select cityid from hotel_city where cityName = #{cityName}")
//	public String findCityidByCityName_hotel(@Param("cityName")String cityName);

	public PlacesDes findPlaces(String id);

	public void editPlaces(PlacesDes placesDes);

	@Delete("delete FROM places WHERE id = #{id}")
	public void placesDel(@Param("id")String id);

	public void placesAdd(PlacesDes places);

	public void placessDel(String[] ids);
}
