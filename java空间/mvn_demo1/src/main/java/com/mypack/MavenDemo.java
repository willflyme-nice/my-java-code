package com.mypack;

public class MavenDemo {
	public String sayHello() {
		return "Hello Maven";
	}
	public static void main(String a[]) {
		System.out.println(new MavenDemo().sayHello());
	}
}
