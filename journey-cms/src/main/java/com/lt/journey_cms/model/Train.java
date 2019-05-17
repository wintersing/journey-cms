package com.lt.journey_cms.model;

import lombok.Data;

@Data
public class Train {
	private String trainNum;			//车次
	private String trainTypeName;		
	private String departStationName;	//出发站
	private String destStationName;		//到达站
	private String departDepartTime;	//出发时间
	private String destArriveTime;		//到达时间
	private String duration;
	private String durationStr;			//运行时间
	private Price[] prices;				//价格明细
	private String departureCityCode;
	private String departureCityName;
	private String arrivalCityCode;
	private String arrivalCityName;
	private String canChooseSeat;		//是否可以选择作为
	private String sellOut;				//是否告罄
//	private String durationDay;
//	private Integer trainId;
//	private Integer trainType;
//	private Integer departStationType;
//	private Integer destStationType;
//	private Integer saleStatus;
//	private Integer departStationId;
//	private Integer destStationId;
//	private String startSaleTime;
//	private String memo;
//	private Integer upOrDown;
//	private String trainStartDate;
//	private String accessByIdcard;
//	private String departStationTypeName;
//	private String destStationTypeName;
}
