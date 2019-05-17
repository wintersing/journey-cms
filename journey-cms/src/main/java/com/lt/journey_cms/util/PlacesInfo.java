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
import com.lt.journey_cms.exception.MessageException;
import com.lt.journey_cms.model.Places;
import com.lt.journey_cms.model.PlacesDes;
import com.lt.journey_cms.model.PlacesParam;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:spring-mybatis.xml" })
public class PlacesInfo {

	private static Properties propertiesAll = CommonsUtils.getPropertiesAll("src/main/resources/apikey.properties");
//	private static Properties propertiesAll = CommonsUtils.getPropertiesAll("apikey.properties");

	private static final String url = propertiesAll.getProperty("Journey_Url");
	private static final String apikey = propertiesAll.getProperty("IDataAPI_APIKEY");

	
	public static <T> List<T> getPlacesInfo(PlacesParam placesParam, Class<T> clazz) throws MessageException {
		StringBuffer param = new StringBuffer();
		param.append("apikey=" + apikey);
		Map<String, Object> map = CommonsUtils.beantoMap(placesParam);
		for (String key : map.keySet()) {
			if (map.get(key) != "" && map.get(key) != null) {
				param.append("&" + key + "=" + map.get(key));
			}
		}
		String ret = HttpRequest.sendGet(url, param.toString());
//		String dataStr = CommonsUtils.unicodeToString(ret);
		JSONObject dataObj = JSON.parseObject(ret);
//		System.out.println(dataObj);
		String retcode = dataObj.getString("retcode");
		if (retcode.equals("100002")) {
			throw new MessageException("Search No Result");
		}
		String placesListStr = dataObj.getJSONArray("data").toJSONString();
		List<T> placesList = JSONObject.parseArray(placesListStr, clazz);
		
		for (T t : placesList) {
			if (t instanceof PlacesDes) {
				PlacesDes places = (PlacesDes) t;
				places.setUpdatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}
		}
		
		return placesList;
	}
	
	public static PlacesDes getPlacesSingleInfo(PlacesParam placesParam) {
		//拼接参数
		StringBuffer param = new StringBuffer();
		param.append("apikey=").append(apikey);
		Map<String, Object> map = CommonsUtils.beantoMap(placesParam);
		for (String key : map.keySet()) {
			if (map.get(key) != "" && map.get(key) != null) {
				param.append("&").append(key).append("=").append(map.get(key));
//				param.append("&" + key + "=" + map.get(key));
			}
		}
		//发送请求
		String ret = HttpRequest.sendGet(url, param.toString());
		JSONObject dataObj = JSON.parseObject(ret);
		String retcode = dataObj.getString("retcode");
		
		if (retcode.equals("100002")) return null;
		
		JSONArray placesList = dataObj.getJSONArray("data");

		PlacesDes placesDes = null;
		for (Object obj_ : placesList) {
			if (obj_ instanceof JSONObject) {
				JSONObject obj = (JSONObject) obj_;
				String id = obj.getString("id");
				if (placesParam.getId().equals(id)) {
					placesDes = JSONObject.parseObject(obj+"", PlacesDes.class);
					break;
				}
			}
		}
		
//		model.addAttribute("pageToken", dataObj.getString("pageToken"));
//		model.addAttribute("retcode", dataObj.getString("retcode"));
//		model.addAttribute(placesParam);
//		if (dataObj.getBooleanValue("hasNext") == true) {
//			model.addAttribute("hasNext", "1");
//		} else {
//			model.addAttribute("hasNext", "0");
//		}
		
		return placesDes;
	}
	
	@Test
	public void name() {
		PlacesParam placesParam = new PlacesParam();
		placesParam.setCityid("405");
		placesParam.setSort("1");
//		getPlacesInfo(placesParam);
//		System.out.println(placesList);
//		placesService.addJourney(placesInfo);
	}

}