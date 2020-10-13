package com.evolvus.fluxclaims;

import org.beanio.spring.BeanIOFlatFileItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.evolvus.fluxclaims.batch.EmplyProcessor;
import com.evolvus.fluxclaims.batch.JobCompletionListener;
import com.evolvus.fluxclaims.batch.Processor;
import com.evolvus.fluxclaims.batch.Reader;
import com.evolvus.fluxclaims.batch.Writer;
import com.evolvus.fluxclaims.bean.Emplyee;

@SpringBootApplication
@EnableBatchProcessing

public class FluxclaimsEnbdcbsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FluxclaimsEnbdcbsServiceApplication.class, args);

	}

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob").incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep2()).end().build();
	}

	@Bean
	public Step orderStep1() {
		return stepBuilderFactory.get("orderStep1").<String, String>chunk(1).reader(new Reader())
				.processor(new Processor()).writer(new Writer()).build();
	}

	@Bean
	public Step orderStep2() {
		return stepBuilderFactory.get("orderStep2").<Emplyee, String>chunk(1).reader(readerBean(null))
				.processor(new EmplyProcessor()).writer(new Writer()).build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}

	@Bean
	@StepScope

	public BeanIOFlatFileItemReader<Emplyee> readerBean(@Value("#{jobParameters['fileName']}") String fileName) {

		BeanIOFlatFileItemReader<Emplyee> read = new BeanIOFlatFileItemReader<Emplyee>();
		read.setStreamMapping(new ClassPathResource("beanio-employee.xml"));
		read.setStreamName("employeeFile");
		read.setResource(new FileSystemResource(fileName));
		return read;
	}

}
