/**
 * 
 * 数字类演示
 * 
 * @author Administrator
 *
 */
import java.text.DecimalFormat;
import java.lang.StringBuffer;
import java.util.Scanner;
import java.lang.Exception;

public class NumTest {
	private final static String NUM[]= {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
	private final static String UNIT[]= { "", "拾", "佰", "仟", "万", "拾","佰", "仟", "亿", "拾", "佰", "仟" };
	private final static String DUNIT[]= {"角","分","厘"};
	public static void main(String arg[]) throws Exception{
		Scanner scan=new Scanner(System.in);
		
		
			System.out.println("请输入金额:");
			double num=scan.nextDouble();
			String resultStr=convert(num);
			System.out.println("大写为:"+resultStr);
			
		
		
	
		
	}
	
	//转换函数，传递一个浮点数，转换为大写字符串
	public static String convert(double num) throws Exception {
		
		
		DecimalFormat df=new DecimalFormat("##0.###");
		String numberStr=df.format(num);
		String interNumberStr= numberStr.substring(0,numberStr.indexOf("."));//取整数部分
		String deciNumberStr= numberStr.substring(numberStr.indexOf(".")+1,numberStr.length());//取小数部分
		
		System.out.println(interNumberStr);
		System.out.println(deciNumberStr);
		
		interNumberStr=new StringBuffer(numberStr).reverse().toString();
		StringBuffer buf=new StringBuffer();   //创建一个字符串缓冲区
		for(int i=0;i<interNumberStr.length();i++) {
			buf.append(UNIT[i]);               //加单位
			buf.append(NUM[interNumberStr.charAt(i)-'0']);//加大写数字
		}
		interNumberStr=buf.reverse().toString();   
		interNumberStr.replaceAll("零拾","零");
		interNumberStr.replaceAll("零佰","零");
		interNumberStr.replaceAll("零仟","零");
		interNumberStr.replaceAll("零万","万");
		interNumberStr.replaceAll("零亿","亿");
		interNumberStr.replaceAll("零零","零");
		interNumberStr.replaceAll("亿万","亿");  //得到整数部分大写字符串
		
		buf=new StringBuffer();
		for(int i=0;i<deciNumberStr.length();i++) {
			buf.append(NUM[deciNumberStr.charAt(i)-'0']);//加大写数字
			buf.append(DUNIT[i]);               //加单位
		}
		deciNumberStr=buf.toString();    //得到小数部分大写字符串
		
		String resultStr=interNumberStr+"元"+deciNumberStr;
		return  resultStr;
		
		}
	}
