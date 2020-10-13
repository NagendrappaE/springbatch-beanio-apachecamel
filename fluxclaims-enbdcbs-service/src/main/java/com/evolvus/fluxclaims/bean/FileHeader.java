package com.evolvus.fluxclaims.bean;

import lombok.Data;

@Data
public class FileHeader {

	private String fileHeaderIdentifier;


	private String filereference;

	private String subscriberID;

	private int totalNumOfBatches;

	private int totalNumberOfLines;

	private String fileCreationDt;

	private String fileCreationTime;

	private String fileAutherizatioType;

	private String ackMode;

	private String ackReceivngMailIds;

	private String ackFormat;

}
