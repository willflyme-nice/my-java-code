import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.util.*;

public class RedirectStream{
	public static void main(String asr[]){
		try{
			int i;
			float f;
			float sum;
			String str;
			Scanner scan=new Scanner(System.in);
			System.out.println("请输入一个整型数");
			i=scan.nextInt();
			System.out.println("请输入一个浮点数");
			f=scan.nextFloat();
			System.out.println("请输入一段注释");
			str=scan.nextLine();
			str=scan.nextLine();
			sum=i+f;
			
			 PrintStream out=System.out; //保存原输出流
		     PrintStream rizhi=new PrintStream("./日志.txt"); //创建一个指向特定文件的输出流
		     System.setOut(rizhi);  // 完成重定向
		     System.out.println("执行结果："+i+" + "+f+" = "+sum);
		     System.out.println("您的注释为："+str);
		     System.setOut(out); // 重定向恢复
		     System.out.println("please look to file named 'rizhi'");
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		
		
	}
	
}