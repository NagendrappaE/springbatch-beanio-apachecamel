package com.evolvus.fluxclaims;

import org.beanio.spring.BeanIOFlatFileItemReader;
import org.beanio.spring.BeanIOFlatFileItemWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class FlatFileConfigurtion<Emplyee> {

	/**
	 * @param reader the reader to set
	 */
	


	@Getter
	private BeanIOFlatFileItemWriter<Emplyee> writer;
	
	private BeanIOFlatFileItemReader<Emplyee> reader;
	
	
	  public void initializeReader(String stream, String path) {
	        try {
	        	this.reader=	new BeanIOFlatFileItemReader();
	        	this.reader.setStreamMapping(new ClassPathResource("beanio-employee.xml"));
	            this.reader.setStreamName("employeeFile");
	            this.reader.setResource(new ClassPathResource("emp.txt"));
	    	
	    		
	            this.reader.afterPropertiesSet();
	        } catch (Exception e) {
	            throw new RuntimeException("Ocorreu um erro ao inicializar o initializeReader!");
	        }
	    }


	/**
	 * @return the reader
	 */
	public BeanIOFlatFileItemReader<Emplyee> getReader() {
		return  new BeanIOFlatFileItemReader<Emplyee>();
	}
	 

}
