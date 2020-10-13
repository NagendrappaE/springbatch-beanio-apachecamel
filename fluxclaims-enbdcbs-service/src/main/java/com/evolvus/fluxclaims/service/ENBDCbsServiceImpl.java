/**
 * 
 */
package com.evolvus.fluxclaims.service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.evolvus.fluxclaims.api.CBSInterfaceRequest;
import com.evolvus.fluxclaims.api.CBSInterfaceResponse;
import com.evolvus.fluxclaims.bean.BatchHeader;
import com.evolvus.fluxclaims.bean.FileHeader;
import com.evolvus.fluxclaims.bean.TranscationalRecord;
import com.evolvus.fluxclaims.constants.FluxClaimsEnum;
import com.evolvus.fluxclaims.utils.ENBDEnums;

/**
 * @author user
 *
 */
@Service
public class ENBDCbsServiceImpl implements ENBDCbsService {

	static Logger log = LoggerFactory.getLogger(ENBDCbsServiceImpl.class);

	@Value("${flux.claim.path}")
	private String fluxClaimPath;

	@Override
	public CBSInterfaceResponse processPayemntRequest(CBSInterfaceRequest cbsInterfaceRequest) {

		log.info("processPayemntRequest START ");
		CBSInterfaceResponse cbsInterfaceResponse = new CBSInterfaceResponse();
		StreamFactory factory = null;

		String subscriptionId = "DHA";
		try {

			factory = StreamFactory.newInstance();

			// load the mapping file
			File file = new ClassPathResource("beanio-enbd-payment.xml").getFile();

			factory.load(file);

			FileHeader fileHeader = new FileHeader();
			fileHeader.setFileHeaderIdentifier(ENBDEnums.FILEHEADER.getConstValue());
			fileHeader.setFilereference(cbsInterfaceRequest.getRemittanceRefNumber());
			fileHeader.setSubscriberID(subscriptionId);
			fileHeader.setTotalNumOfBatches(1);
			fileHeader.setTotalNumOfBatches(1);
			fileHeader.setFileCreationDt(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-YYYY")));
			fileHeader.setFileCreationTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
			fileHeader.setFileAutherizatioType(ENBDEnums.FILEAUTHTYPE.getConstValue());
			fileHeader.setAckMode(ENBDEnums.H2H.getConstValue());
			fileHeader.setAckReceivngMailIds("");
			fileHeader.setAckFormat(ENBDEnums.CSV.getConstValue());
			fileHeader.setFileCreationDt(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-YYYY")));
			fileHeader.setFileCreationTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
			fileHeader.setFileAutherizatioType(ENBDEnums.FILEAUTHTYPE.getConstValue());
			fileHeader.setAckMode(ENBDEnums.H2H.getConstValue());
			fileHeader.setAckReceivngMailIds("");
			fileHeader.setAckFormat(ENBDEnums.CSV.getConstValue());

			BatchHeader batchHeader = new BatchHeader();
			batchHeader.setBatchHdrIdentifier(ENBDEnums.BATCHDR.getConstValue());
			batchHeader.setBatchTxnRef(cbsInterfaceRequest.getRemittanceRefNumber());
			batchHeader.setWorkFlowIdentifier(ENBDEnums.BATCH_WROKFLOW.getConstValue());
			batchHeader.setTxnRecCount(1);
			batchHeader.setRecordSpotter(ENBDEnums.BATCH_REC.getConstValue());
			batchHeader.setDebitLevelFlg(1);
			batchHeader.setTxnTypeFlg(ENBDEnums.BATCH_TXNTYPE.getConstValue());
			batchHeader.setDebitIBANnumber(cbsInterfaceRequest.getDrIBANNumber());
			batchHeader.setDebitCurency(ENBDEnums.BATCH_AED.getConstValue());
			batchHeader.setDebitActCountry(ENBDEnums.BATCH_COUNTRY_UAE.getConstValue());
			batchHeader.setDrCrAccountName("");
			batchHeader.setDrCrAccountAdrss("");
			batchHeader.setDrCrBICode("");
			batchHeader.setFixedDebit(0);
			batchHeader.setPaylaterDate("");
			batchHeader.setAuthorizationType(1);
			batchHeader.setSpecialDealFlg(0);
			batchHeader.setSpecialDealRef("");
			batchHeader.setBenificiaryRegFLg(0);
			batchHeader.setAdviceFlg(ENBDEnums.BATCH_ADVICETYPE_D.getConstValue());
			batchHeader.setH2hAdviceMode(ENBDEnums.H2H.getConstValue());
			batchHeader.setAdvcingMailId("");
			batchHeader.setAdvicingFormate(ENBDEnums.BATCH_ADVICEFORMAT_C.getConstValue());
			batchHeader.setInvoiceRecordExist(0);
			batchHeader.setTotalInvoiceRecordCount("");
			batchHeader.setInvoiceLabel1("");
			batchHeader.setInvoiceLabel2("");
			batchHeader.setInvoiceLabel3("");
			batchHeader.setInvoiceLabel4("");
			batchHeader.setInvoiceLabel5("");

			TranscationalRecord transcationalRecord = new TranscationalRecord();
			transcationalRecord.setTxnRecordIdent(ENBDEnums.TXNREC_TXNDET.getConstValue());
			transcationalRecord.setTxnReference(cbsInterfaceRequest.getRemittanceRefNumber());
			transcationalRecord.setCrCode("");
			transcationalRecord.setCrName(cbsInterfaceRequest.getCrAccName());
			transcationalRecord.setCrEmailId("");
			transcationalRecord.setCrMobNum("");
			transcationalRecord.setOrgOrderparty("");
			transcationalRecord.setCrIBANNumber(cbsInterfaceRequest.getCrIBANNumber());
			// this will vary
			transcationalRecord.setCrPayMode("TB");
			transcationalRecord.setCrAmount(String.valueOf(cbsInterfaceRequest.getApprovedAmount()));

			transcationalRecord.setCrPayCurency(ENBDEnums.BATCH_AED.getConstValue());
			transcationalRecord.setCrbankName("");
			transcationalRecord.setCrBankAddr("");
			transcationalRecord.setCrBICode("");
			transcationalRecord.setCrSortCode("");
			transcationalRecord.setCrRouteCode("");
			transcationalRecord.setCrPayableCity("");
			transcationalRecord.setCrPayableCountry("");
			transcationalRecord.setPaymentDetailsForBenifcr(cbsInterfaceRequest.getCrNarration());
			transcationalRecord.setDeliveryMode("");
			// this need to get fromDHA
			transcationalRecord.setTxnCharges("");
			transcationalRecord.setTxnPurpuseTypes("");
			// above fields values need to check
			transcationalRecord.setInterMediateBank1("");
			transcationalRecord.setInterMediateBank1Addr("");
			transcationalRecord.setInterMediate1BICCode("");
			transcationalRecord.setInterMediateBank1SortCode("");
			transcationalRecord.setInterMediatebank1RouteCode("");

			transcationalRecord.setInterMediateBank2("");
			transcationalRecord.setInterMediateBank2Addr("");
			transcationalRecord.setInterMediate2BICCode("");
			transcationalRecord.setInterMediateBank2SortCode("");
			transcationalRecord.setInterMediatebank2RouteCode("");

			String paymentFileName = this.paymentFileName(this.paymentRefNum(subscriptionId,subscriptionId), subscriptionId);

			File paymentFile = new File(fluxClaimPath + File.separator+"PAYMENTOUT"+File.separator + paymentFileName);

			// use a StreamFactory to create a BeanWriter
			BeanWriter out = factory.createWriter("enbdpaymentStream", paymentFile);
			// write an Employee object directly to the BeanWriter

			out.write(fileHeader);
			// out.flush();

			out.write(batchHeader);

			// out.flush();

			out.write(transcationalRecord);

			out.flush();
			out.close();

			cbsInterfaceResponse.setBankRefNumber(paymentFileName);
			cbsInterfaceResponse.setRemittanceRefNumber(cbsInterfaceRequest.getRemittanceRefNumber());
			cbsInterfaceResponse.setBankRespCode("0000");
			cbsInterfaceResponse.setBankRespDesc("SENT TO BANK");
			cbsInterfaceResponse.setValueDate(LocalDate.now());

		} catch (Exception e) {
			cbsInterfaceResponse.setBankRespCode(FluxClaimsEnum.ERROR.getConstValue());

			log.error(e.getMessage());
		}
		log.info("processPayemntRequest ENDS ");
		return cbsInterfaceResponse;
	}

	@Override
	public String paymentFileName(String ref1, String ref2) {
		StringBuilder fileName = new StringBuilder(ref1).append(ref2).append(ENBDEnums.FILE_NAME_ENF.getConstValue())
				.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMDDHHmmss"))).append(".csv");

		log.info("payment File Name  {}", fileName.toString());
		return fileName.toString();
	}

//	@Override
	public String paymentRefNum(String ref1, String ref2) {
		StringBuilder fileName = new StringBuilder(ref1).append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMDDHHmmss")));

		log.info("payment File Name  {}", fileName.toString());
		return fileName.toString();
	}

}
