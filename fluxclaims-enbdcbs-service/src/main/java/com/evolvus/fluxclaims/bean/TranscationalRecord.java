package com.evolvus.fluxclaims.bean;

import lombok.Data;

@Data
public class TranscationalRecord {

	private String txnRecordIdent;

	private String txnReference;

	private String crCode;

	private String crName;

	private String crEmailId;

	private String crMobNum;

	private String orgOrderparty;

	private String crIBANNumber;

	private String crPayMode;

	private String crAmount;

	private String crPayCurency;

	private String crAddress;

	private String crCountry;

	private String crbankName;

	private String crBankAddr;

	private String crBICode;

	private String crSortCode;

	private String crRouteCode;
	
	private String crPayableCity;


	private String crPayableCountry;

	private String paymentDetailsForBenifcr;

	private String deliveryMode;

	private String txnCharges;

	private String txnPurpuseTypes;

	private String txnPaymentType;

	private String interMediateBank1;

	private String interMediateBank1Addr;

	private String interMediate1BICCode;

	private String interMediateBank1SortCode;

	private String interMediatebank1RouteCode;

	private String interMediateBank2;

	private String interMediateBank2Addr;

	private String interMediate2BICCode;

	private String interMediateBank2SortCode;

	private String interMediatebank2RouteCode;

}
