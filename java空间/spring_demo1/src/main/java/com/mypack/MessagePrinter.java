package com.mypack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class MessagePrinter {
	
	private MessageService service ;
	
	@Autowired
	public MessagePrinter(MessageService service ) {
		super();
		this.service = service;
		System.out.println("创建MessagePrinter"+this.toString());
	}
	
	public void print(String message) throws Exception{
		System.out.println(message+" from "+this.toString() );
	}
}
