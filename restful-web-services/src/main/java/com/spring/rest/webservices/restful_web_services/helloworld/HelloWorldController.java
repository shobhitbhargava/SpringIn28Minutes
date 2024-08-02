package com.spring.rest.webservices.restful_web_services.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Rest API
@RestController
public class HelloWorldController {
	
	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	// /hello-world will be shown in the address
	@RequestMapping(method = RequestMethod.GET, path = "/hello-world-old")
	public String helloWorldOld() {
		return "Hello World";
	}
	
	// /hello-world will be shown in the address
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	// /hello-world will be shown in the address
	@GetMapping(path = "/hello-world-internationalization")
	public String helloWorldInternationalization() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("greetings.questions.message", null, "Default Message", locale);
	}
	
	// This will return the JSON of an hello world bean
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	// Path parameters /hello-world/path-variable/{name}
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	
}
