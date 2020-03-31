import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Calendar;
import java.util.GregorianCalendar;

class MyPanel extends JPanel{
	JFrame frame = new JFrame("模拟时钟");
	
	Point2D center; //时钟圆心
	int ram = 100; //时钟半径
	Point2D.Double scale[]=new Point2D.Double[60]; //60个时钟刻度
	double angle = Math.PI/30; //相邻两刻度间夹角
	int index=0;
	
	
	protected void go() {
		frame.getContentPane().add(this);	
		frame.setSize(300,300);
		frame.setLocation(400, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
		HandleGo t1=new HandleGo();
	    t1.start();
	    
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Rectangle rect = getBounds(); //获取边界
		Insets inset = getInsets(); //获取边框
		//确定时钟圆心位置
		double x = (rect.getWidth()-inset.left-inset.right-2*ram)/2+inset.left;
		double y = (rect.getHeight()-inset.bottom-inset.top-2*ram)/2+inset.top;
		center = new Point2D.Double(x+ram,y+ram); 
		//确定时钟刻度位置
		for(int i=0;i<60;i++) {
			scale[i]=new Point2D.Double(x+ram+ram*Math.sin(angle*i),y+ram-ram*Math.cos(angle*i));
		}
		//绘制时钟盘
		g.setColor(Color.BLUE);
		g.drawOval((int)x, (int)y, 2*ram, 2*ram);
		Graphics2D g2d = (Graphics2D)g;
		for(int i=0;i<60;i++) {
			if(i%5==0) {
				g2d.setColor(Color.red);
				g2d.fillOval((int)scale[i].x-4, (int)scale[i].y-4, 8, 8);
			}
			else {
				g2d.setColor(Color.CYAN);
				g2d.fillOval((int)scale[i].x-2, (int)scale[i].y-2, 4, 4);
			}		
		}
		//获取当前时间
		Calendar calendar= Calendar.getInstance();//日历
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		//绘制指针
		g2d.setColor(Color.red);
		g2d.draw(new Line2D.Double(center,scale[second]));
		g2d.setColor(Color.blue);
		g2d.setStroke(new BasicStroke(3));
		g2d.draw(new Line2D.Double(center,scale[minute]));
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(Color.green);
		g2d.draw(new Line2D.Double(center,scale[hour*5+minute/12]));
		
	}
	
	
	
	class HandleGo extends Thread{
		public void run() {
			while(true) {
				repaint();
			    try {
			    	Thread.sleep(1000);
			    } catch (InterruptedException e) {
			    	// TODO Auto-generated catch block
			    	e.printStackTrace();
			    }
				
			}
			
		}
	}

}


public class ModelClock{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
		MyPanel that = new MyPanel();
		
		that.go();
		
	}
	
}

