package com.evolvus.fluxclaims.batch;

import org.springframework.batch.item.ItemProcessor;

import com.evolvus.fluxclaims.bean.Emplyee;

public class EmplyProcessor  implements ItemProcessor<Emplyee, String> {

	@Override
	public String process(Emplyee data) throws Exception {

		return data.getFirstName().toUpperCase();
	}

}
