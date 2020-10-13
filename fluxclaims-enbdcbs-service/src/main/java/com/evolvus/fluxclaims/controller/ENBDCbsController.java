package com.evolvus.fluxclaims.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evolvus.fluxclaims.api.CBSInterfaceRequest;
import com.evolvus.fluxclaims.api.CBSInterfaceResponse;
import com.evolvus.fluxclaims.service.ENBDCbsService;

@RestController
@RequestMapping("enbdapi/1.0.0")
public class ENBDCbsController {

	static Logger log = LoggerFactory.getLogger(ENBDCbsController.class);

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job processJob;

	@Autowired
	private ENBDCbsService enbdCbsService;

	@PostMapping("paymentreq")
	public CBSInterfaceResponse processPayemntRequest(@RequestBody CBSInterfaceRequest cbsInterfaceRequest) {

		JobParameters jobParameters = new JobParametersBuilder().addLong("fileNamennnn", System.currentTimeMillis()).addString("fileName", "emp.txt")
				.toJobParameters();
		try {
			jobLauncher.run(processJob, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}

		log.info(String.format("processPayemntRequest START :::::::::: %s", cbsInterfaceRequest.toString()));

		CBSInterfaceResponse cbsInterfaceResponse = enbdCbsService.processPayemntRequest(cbsInterfaceRequest);
		log.info("processPayemntRequest ENDS ::::::::::{} ", cbsInterfaceRequest.toString());

		return cbsInterfaceResponse;

	}

}
