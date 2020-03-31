package com.mypack;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MavenDemoTest {
	@Test
	public void testSayHello(){
		MavenDemo demo = new MavenDemo();
		String result = demo.sayHello();
		assertEquals("Hello Maven", result);
	}
}
