package com.evolvus.fluxclaims.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CustomRouteBuilder extends RouteBuilder {

	
	@Override
	public void configure() throws Exception {

	
		
		
		fromF("file://%s?move=backup/${date:now:yyyyMMdd}/${file:name}", filePath).
		from("timer://foo?period=5").routeId("paymentpostingroute").log("sending.......").setHeader("fileName", constant("/home/user/JAVA_WORKSPACE/FLUXCLAIM/08-10-2020/fl/fluxclaims-enbdcbs-service/emp.txt")).to("spring-batch:processJob");

	}

}
