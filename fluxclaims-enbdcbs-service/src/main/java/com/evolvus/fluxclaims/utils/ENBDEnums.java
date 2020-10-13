/**
 * 
 */
package com.evolvus.fluxclaims.utils;

/**
 * @author user
 *
 */
public enum ENBDEnums {

	CSV("csv"),

	H2H("H"), EMAIL("E"), FILEAUTHTYPE("P"), FILEHEADER("FILHDR"), BATCHDR("TXNHDR"), BATCH_WROKFLOW("BEN"),
	BATCH_REC("!<<REC>>!"), BATCH_TXNTYPE("D"), BATCH_AED("AED"), BATCH_COUNTRY_UAE("UAE"), BATCH_ADVICETYPE_D("D"),
	BATCH_ADVICEFORMAT_C("C"), TXNREC_TXNDET("TXNDET"), FILE_NAME_ENF("ENF");

	private String constValue;

	/**
	 * @return the constValue
	 */
	public String getConstValue() {
		return constValue;
	}

	/**
	 * @param constValue the constValue to set
	 */
	public void setConstValue(String constValue) {
		this.constValue = constValue;
	}

	ENBDEnums(String val) {
		this.constValue = val;
	}

}
