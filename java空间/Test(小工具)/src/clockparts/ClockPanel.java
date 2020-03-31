package clockparts;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 时钟面板
 */
public class ClockPanel extends JPanel{
	private Image clockImage; //表盘图片
	
	private int hour;   //时
	private int minute;  //分
	private int second;  //秒
	
	private HandHour handHour = new HandHour();       //时针
	private HandMinute handMinute = new HandMinute(); //分针
	private HandSecond handSecond = new HandSecond(); //秒针
	
	//时钟线程
	private Thread clockRun = new Thread() { 
		int i=0;
		int k=0;
		public void run() {
			
			//初始化时钟
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
			String str = df.format(new Date());// new Date()为获取当前系统时间
			String time[] = str.split(":");
			int s = Integer.parseInt(time[2]);  //秒
			System.out.print(Arrays.toString(time)+"  ");
			int m = Integer.parseInt(time[1]);  //分
			int h = Integer.parseInt(time[0]);  //时
			handSecond.setAngle( Math.round(s * (360/60)) );
			handMinute.setAngle( Math.round(( m+(float)s/60) * (360/60)) );
			handHour.setAngle( Math.round(( h+(float)m/60) * (360/12)) );
			System.out.print(handSecond.getAngle()+" "+handMinute.getAngle()+ " "+handHour.getAngle());
			
			while(true) {	
				try {
					Thread.sleep(160); //160毫秒对应一角度
					handSecond.singleStepAhead(); //秒针向前一角度
					i++;
					if(i==60) {
						handMinute.singleStepAhead(); //分针向前一角度
						i = 0;
						k++;
						if(k==12) {
							handHour.singleStepAhead(); //时针向前一角度
							k = 0;
							System.out.println();
						}
					}
					ClockPanel.this.repaint(); //重绘	
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
	};
	
	//时钟走
	public void run() {
		clockRun.start();
	}
	
	//时钟暂停
	private void stop() {
		
	}
	
	//设置时间
	private void setTime(int hour,int minute,int second) {
		
	}
	
	//设置表盘图片
	private void setClockImage(Image image) {
		this.clockImage = image;
	}

	//构造函数
	public ClockPanel() {
		try {
			clockImage = ImageIO.read(new File("image/表盘.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	//绘制时钟面板
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int width = getWidth();
		int height = getHeight();
		
		g.drawImage(clockImage, (width-300)/2, 10, 300, 300, this); //在这可以设置表盘大小
		
		Graphics2D g2d = (Graphics2D)g;
		
		//画秒针
		g2d.rotate( handSecond.getAngle()*(Math.PI/180), width/2, 160); 
		g2d.drawLine(width/2, 160, width/2, 60);
		//画分针
		g2d.rotate( -handSecond.getAngle()*(Math.PI/180), width/2, 160); //先复位
		g2d.rotate( handMinute.getAngle()*(Math.PI/180), width/2, 160);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine(width/2, 160, width/2, 80);
		//画时针
		g2d.rotate( -handMinute.getAngle()*(Math.PI/180), width/2, 160); //先复位
		g2d.rotate( handHour.getAngle()*(Math.PI/180), width/2, 160);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine(width/2, 160, width/2, 100);
	}
	
	
}


































