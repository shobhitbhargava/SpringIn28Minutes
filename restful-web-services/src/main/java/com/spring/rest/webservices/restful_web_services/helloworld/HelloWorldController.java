package com.spring.rest.webservices.restful_web_services.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Rest API
@RestController
public class HelloWorldController {

	// /hello-world will be shown in the address
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path = "/hello-world-infinity")
	public String helloWorldInfinity() {
		StringBuffer buffer = new StringBuffer();
		for(int i = 1; i <= 1000; i++) {
			System.out.println("Test "+ i);
			buffer.append("Test "+i);
		}
		return buffer.toString();
	}
}
