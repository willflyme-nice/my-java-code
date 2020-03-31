package com.mypack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("com.mypack")
@EnableAspectJAutoProxy
public class AppContext {
	
	@Autowired
	private MessageService service;
	
	@Bean
	public MessagePrinter messagePrinter1() {
		
		return new MessagePrinter(service);
	}
	
	@Bean
	public MessagePrinter messagePrinter2() {
		
		return new MessagePrinter(service);
	}
	
}
