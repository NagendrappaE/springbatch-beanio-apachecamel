/**
 * 
 */
package com.evolvus.fluxclaims.batch;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.evolvus.fluxclaims.FlatFileConfigurtion;
import com.evolvus.fluxclaims.bean.Emplyee;

/**
 * @author user
 *
 */
public class Reader  extends StepExecutionListenerSupport  implements ItemReader<String> {

	
    private FlatFileConfigurtion<Emplyee> configuration;

	
	
	private String FileName;
	  
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return FileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	private String[] messages = { "javainuse.com",
			"Welcome to Spring Batch Example",
			"We use H2 Database for this example" };

	private int count = 0;

	
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		System.out.println(this.FileName);
		if (count < messages.length) {
			return messages[count++];
		} else {
			count = 0;
		}
		
		return null;
	}
	public void beforeStep(StepExecution stepExecution) {
	this.FileName=stepExecution.getJobParameters().getString("fileName");
	
	
		System.out.println("before step "+stepExecution.getJobParameters().getString("fileName"));
	}
	

}
