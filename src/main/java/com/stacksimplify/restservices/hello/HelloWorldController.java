package com.stacksimplify.restservices.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	//Simple method
	//URI - /helloworld
	//GET 
	//@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	@GetMapping("/helloworld")
	public String hellowWorld() {
		return "Hello World";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Samuel", "Reis" ,"RJ");
	}
	
	@GetMapping("hello-int")
	public String getMessagesInI18NFormat(@RequestHeader(name = "Accept-Language", required = true) String locale){
		return messageSource.getMessage("label.hello", null, new Locale (locale));
	}
	
	@GetMapping("hello-int2")
	public String getMessagesInI18NFormat2(){
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}
}
