package com.mypack;

import org.springframework.stereotype.Component;

@Component
public class MessageService {
	private String message = "Hello World";
	
	public MessageService() {
		super();
		System.out.println("创建MessageService");
	}
	
	public String getMessage() {
		return message;
	}
}
