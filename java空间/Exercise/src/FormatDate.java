import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class FormatDate {
		static Date date=new Date();
		static DateFormat format;

	public static void main(String[]arg) {
		Scanner select=new Scanner(System.in);
		int i;
		System.out.println("请输入要显示的国家时间：");
		while(true) {
			i=select.nextInt();
			switch(i) {
				case 1: format=DateFormat.getDateInstance(DateFormat.FULL,Locale.CHINA);
		                System.out.println("中国日期："+format.format(date));break;
				case 2:  format=DateFormat.getDateInstance(DateFormat.FULL,Locale.US);
		                  System.out.println("美国日期："+format.format(date));break;
				case 3: format=DateFormat.getDateInstance(DateFormat.FULL,Locale.JAPAN);
		                 System.out.println("日本日期："+format.format(date));break;
				case 4:   format=DateFormat.getDateInstance(DateFormat.FULL,Locale.ENGLISH);
			             System.out.println("英国日期："+format.format(date)); break;   
				case 5: format=DateFormat.getDateInstance(DateFormat.FULL,Locale.CANADA);
			             System.out.println("加拿大日期："+format.format(date));break;  
			    default: System.out.println("只能输入1，2，3，4, 5");      
		}
	                            
		}
		
		
	    
    }
}
	
