package com.lt.journey_cms.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lt.commons.utils.CommonsUtils;
import com.lt.commons.utils.HttpRequest;
import com.lt.journey_cms.model.Hotel;
import com.lt.journey_cms.model.HotelDes;
import com.lt.journey_cms.model.HotelParam;

public class HotelInfo {
	private static Properties propertiesAll = CommonsUtils.getPropertiesAll("src/main/resources/apikey.properties");
//	private static Properties propertiesAll = CommonsUtils.getPropertiesAll("apikey.properties");
	
	private static final String url = propertiesAll.getProperty("Hotel_Url");
	private static final String apikey = propertiesAll.getProperty("IDataAPI_APIKEY");

	public static <T> List<T> getHotelInfo(HotelParam hotelParam, Class<T> clazz) {
		//拼接参数
		StringBuffer param = new StringBuffer();
		param.append("apikey=").append(apikey);
		Map<String, Object> map = CommonsUtils.beantoMap(hotelParam);
		for (String key : map.keySet()) {
			if (map.get(key) != "" && map.get(key) != null) {
				param.append("&").append(key).append("=").append(map.get(key));
			}
		}
		
		//处理返回结果
		String dataStr = HttpRequest.sendGet(url, param.toString());
//		System.out.println(dataStr);
		JSONObject dataObj = JSON.parseObject(dataStr);
		String hotelListStr = dataObj.getJSONArray("data").toString();

		List<T> hotelList = JSONObject.parseArray(hotelListStr, clazz);

		for (T t : hotelList) {
			if (t instanceof HotelDes) {
				HotelDes hotelDes = (HotelDes) t;
				hotelDes.setUpdatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}
		}

		return hotelList;
	}
	
}