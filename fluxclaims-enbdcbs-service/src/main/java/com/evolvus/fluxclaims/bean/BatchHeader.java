package com.evolvus.fluxclaims.bean;

import lombok.Data;

@Data
public class BatchHeader {

	private String batchHdrIdentifier;

	private String batchTxnRef;

	private String workFlowIdentifier;

	private int txnRecCount;

	private String recordSpotter;

	private int debitLevelFlg;

	private String txnTypeFlg;

	private String debitIBANnumber;

	private String debitCurency;

	private String debitActCountry;

	private String drCrAccountName;

	private String drCrAccountAdrss;

	private String drCrBICode;

	private int fixedDebit;

	private String paylaterDate;

	private int authorizationType;

	private int specialDealFlg;

	private String specialDealRef;

	private int benificiaryRegFLg;

	private String adviceFlg;

	private String h2hAdviceMode;

	private String advcingMailId;

	private String advicingFormate;

	private int invoiceRecordExist;

	private String totalInvoiceRecordCount;

	private String invoiceLabel1;

	private String invoiceLabel2;

	private String invoiceLabel3;

	private String invoiceLabel4;

	private String invoiceLabel5;

}
