package com.lt.journey_cms.model;

import lombok.Data;

@Data
public class Price {
	private String leftNumber;
	private String seatStatus;
	private String price;
	private String stuPrice;
	private String seatName;
	private Detail[] detail;
//	private Integer seat;
//	private String promotionPrice;
//	private Integer resId;
//	private String priceMemo;
}
