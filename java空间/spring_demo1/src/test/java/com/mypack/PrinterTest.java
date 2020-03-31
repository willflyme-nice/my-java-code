package com.mypack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppContext.class)
public class PrinterTest {
	
	@Autowired
	@Qualifier("messagePrinter2")
	private MessagePrinter printer;
	
	@Test
	public void printTest() {
		try {
			printer.print("abc");
			printer.print("123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
