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
		System.out.println("������Ҫ��ʾ�Ĺ���ʱ�䣺");
		while(true) {
			i=select.nextInt();
			switch(i) {
				case 1: format=DateFormat.getDateInstance(DateFormat.FULL,Locale.CHINA);
		                System.out.println("�й����ڣ�"+format.format(date));break;
				case 2:  format=DateFormat.getDateInstance(DateFormat.FULL,Locale.US);
		                  System.out.println("�������ڣ�"+format.format(date));break;
				case 3: format=DateFormat.getDateInstance(DateFormat.FULL,Locale.JAPAN);
		                 System.out.println("�ձ����ڣ�"+format.format(date));break;
				case 4:   format=DateFormat.getDateInstance(DateFormat.FULL,Locale.ENGLISH);
			             System.out.println("Ӣ�����ڣ�"+format.format(date)); break;   
				case 5: format=DateFormat.getDateInstance(DateFormat.FULL,Locale.CANADA);
			             System.out.println("���ô����ڣ�"+format.format(date));break;  
			    default: System.out.println("ֻ������1��2��3��4, 5");      
		}
	                            
		}
		
		
	    
    }
}
	
