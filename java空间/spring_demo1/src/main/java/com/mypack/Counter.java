package com.mypack;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Counter {
	private int sum = 0;
	
	public Counter() {
		System.out.println("创建Counter");
	}
	
	@Around("execution(* com.mypack.MessagePrinter.print(String)) and args(message)")
	public void count(ProceedingJoinPoint jp,String message) {
		try {
			sum = sum + 1;
			System.out.println("before print("+message+")");
			jp.proceed();
			System.out.println("after print("+message+")");
		}catch(Throwable e) {
			System.out.println("erro print("+message+")");
		}
		
	}
	
	public int getSum() {
		return sum;
	}
}
