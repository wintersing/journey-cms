package com.lt.journey_cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.lt.journey_cms.model.Hotel;
import com.lt.journey_cms.model.HotelDes;

@Repository
public interface HotelDao {

	public void addHotel(List<? extends Object> hotelList);
	 
	@Select("select * from hotel order by updatetime DESC limit #{offset}, #{pageSize}")
	public List<HotelDes> findHotelRecommend(@Param("offset")int offset, @Param("pageSize")int pageSize);
//
//	public List<Hotel> findHotelDes(@Param("id")String id);

	@Select("select count(*) from hotel")
	public int findCount();
//
//	@Select("select cityid from hotel_city where cityName = #{cityName }")
//	public String findCityidByCityName(@Param("cityName")String cityName);

	@Select("select * from hotel where id = #{id }")
	public HotelDes findHotel(@Param("id")String id);

	public void editHotel(HotelDes hotelDes);

	@Delete("delete FROM hotel WHERE id = #{id}")
	public void hotelDel(@Param("id")String id);

}
