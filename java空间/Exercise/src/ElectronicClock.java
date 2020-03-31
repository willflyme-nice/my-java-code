import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

public class ElectronicClock {
	//创建一个图片数组
	ImageIcon[] icons= {new ImageIcon("images//0.png"),
			new ImageIcon("images//1.png"),new ImageIcon("images//2.png"),
			new ImageIcon("images//3.png"),new ImageIcon("images//4.png"),
			new ImageIcon("images//5.png"),new ImageIcon("images//6.png"),
			new ImageIcon("images//7.png"),new ImageIcon("images//8.png"),
			new ImageIcon("images//9.png"),new ImageIcon("images//colon.png"),
			};
	JFrame frame=new JFrame("电子时钟");
	JLabel hourLab1=new JLabel(icons[0]);
	JLabel hourLab2=new JLabel(icons[0]);
	JLabel colonLab1=new JLabel(icons[10]);
	JLabel minutLab1=new JLabel(icons[0]);
	JLabel minutLab2=new JLabel(icons[0]);
	JLabel colonLab2=new JLabel(icons[10]);
	JLabel secondLab1=new JLabel(icons[0]);
	JLabel secondLab2=new JLabel(icons[0]);
	JButton button;
	
	JPanel cp;
	
	//初始化函数
	protected void init() {
		cp=new JPanel();
		cp.setBorder(new EmptyBorder(10, 10, 10, 10));
		cp.setLayout(new FlowLayout());
		cp.add(hourLab1);
		cp.add(hourLab2);
		cp.add(colonLab1);
		cp.add(minutLab1);
		cp.add(minutLab2);
		cp.add(colonLab2);
		cp.add(secondLab1);
		cp.add(secondLab2);	
		frame.getContentPane().add(cp);
	}
	//窗口显示
	protected void setVisual() {
		frame.pack();
		frame.setLocation(400, 300);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     
	    new Thread(new TimeRun()).start();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ElectronicClock that =new ElectronicClock();
		that.init();
		that.setVisual();
	}
	
	public void setTime() {
		Calendar time=new GregorianCalendar();
		int hour;
		int minut;
		int second;
		hour=time.get(Calendar.HOUR_OF_DAY);
		minut=time.get(Calendar.MINUTE);
		second=time.get(Calendar.SECOND);
		hourLab1.setIcon(icons[hour/10]);
		hourLab2.setIcon(icons[hour%10]);
		minutLab1.setIcon(icons[minut/10]);
		minutLab2.setIcon(icons[minut%10]);
		secondLab1.setIcon(icons[second/10]);
		secondLab2.setIcon(icons[second%10]);
		frame.repaint();
	}
	
	
	class TimeRun implements Runnable{
		
		public void run() {
			while(true) {
				setTime();
			    try {
			    	 Thread.sleep(1000);//延时1秒
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		}
	}



}











