package com.lt.journey_cms.util;

import java.util.List;
import java.util.Properties;

import org.springframework.ui.Model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lt.commons.utils.CommonsUtils;
import com.lt.commons.utils.HttpRequest;
import com.lt.journey_cms.model.Train;
import com.lt.journey_cms.model.TrainParam;

public class TrainInfo {

	private static Properties propertiesAll = CommonsUtils.getPropertiesAll("src/main/resources/apikey.properties");
	private static final String url = propertiesAll.getProperty("Train_Url");

	/**
	 * @param trainParam
	 * @param isCount
	 * @return
	 */
	public static List<Train> getTrainInfo(TrainParam trainParam, Model model) {
		StringBuffer param = new StringBuffer();
		param.append("r=train/trainTicket/getTickets");
		param.append("&primary[departureDate]=").append(trainParam.getDepartureDate());
		param.append("&primary[departureCityCode]=").append(trainParam.getDepartureCityCode());
		param.append("&primary[departureCityName]=").append(trainParam.getDepartureCityName());
		param.append("&primary[arrivalCityCode]=").append(trainParam.getArrivalCityCode());
		param.append("&primary[arrivalCityName]=").append(trainParam.getArrivalCityName());
		param.append("&start=").append(trainParam.getStart());
		param.append("&limit=").append(trainParam.getLimit());
		String dataStr = HttpRequest.sendGet(url, param.toString());
		
		JSONObject dataObj = JSON.parseObject(dataStr);
		
		JSONObject trainObj = dataObj.getJSONObject("data");
		
		if (trainParam.getLimit() == 0) {
			Integer count = trainObj.getInteger("count");
			Integer maxPage = (int) Math.ceil(count/10.0);
			trainParam.setMaxPage(maxPage);
//			model.addAttribute(trainParam);
			if (count == 0) {
				return null;
			}
		}
		JSONArray trainObjList = trainObj.getJSONArray("list");
		JSONArray trainObjList_ = new JSONArray();
		List<Train> trainList = null;
		if (trainParam.getLimit() == 0) {
			int i = 0;
			for (Object trainObj1 : trainObjList) {
				trainObjList_.add(trainObj1);
				++i;
				if (i == 10) {
					break;
				}
			}
			trainList = JSONObject.parseArray(trainObjList_+"", Train.class);
			trainParam.setLimit(10);
		} else {
			trainList = JSONObject.parseArray(trainObjList+"", Train.class);
		}
		model.addAttribute("trainList", trainList);
		model.addAttribute("trainParam", trainParam);
//		resObj.setDataList(trainList);
//		resObj.setTrainParam(trainParam);
		return trainList;
	}

}
