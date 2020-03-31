package com.mypack.mvn_demo2;

import com.mypack.MavenDemo;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println(new MavenDemo().sayHello());
        new AppTest("a");
    }
}
