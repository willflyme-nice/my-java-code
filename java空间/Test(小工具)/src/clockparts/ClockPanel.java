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
 * ʱ�����
 */
public class ClockPanel extends JPanel{
	private Image clockImage; //����ͼƬ
	
	private int hour;   //ʱ
	private int minute;  //��
	private int second;  //��
	
	private HandHour handHour = new HandHour();       //ʱ��
	private HandMinute handMinute = new HandMinute(); //����
	private HandSecond handSecond = new HandSecond(); //����
	
	//ʱ���߳�
	private Thread clockRun = new Thread() { 
		int i=0;
		int k=0;
		public void run() {
			
			//��ʼ��ʱ��
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//�������ڸ�ʽ
			String str = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
			String time[] = str.split(":");
			int s = Integer.parseInt(time[2]);  //��
			System.out.print(Arrays.toString(time)+"  ");
			int m = Integer.parseInt(time[1]);  //��
			int h = Integer.parseInt(time[0]);  //ʱ
			handSecond.setAngle( Math.round(s * (360/60)) );
			handMinute.setAngle( Math.round(( m+(float)s/60) * (360/60)) );
			handHour.setAngle( Math.round(( h+(float)m/60) * (360/12)) );
			System.out.print(handSecond.getAngle()+" "+handMinute.getAngle()+ " "+handHour.getAngle());
			
			while(true) {	
				try {
					Thread.sleep(160); //160�����Ӧһ�Ƕ�
					handSecond.singleStepAhead(); //������ǰһ�Ƕ�
					i++;
					if(i==60) {
						handMinute.singleStepAhead(); //������ǰһ�Ƕ�
						i = 0;
						k++;
						if(k==12) {
							handHour.singleStepAhead(); //ʱ����ǰһ�Ƕ�
							k = 0;
							System.out.println();
						}
					}
					ClockPanel.this.repaint(); //�ػ�	
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
	};
	
	//ʱ����
	public void run() {
		clockRun.start();
	}
	
	//ʱ����ͣ
	private void stop() {
		
	}
	
	//����ʱ��
	private void setTime(int hour,int minute,int second) {
		
	}
	
	//���ñ���ͼƬ
	private void setClockImage(Image image) {
		this.clockImage = image;
	}

	//���캯��
	public ClockPanel() {
		try {
			clockImage = ImageIO.read(new File("image/����.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	//����ʱ�����
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int width = getWidth();
		int height = getHeight();
		
		g.drawImage(clockImage, (width-300)/2, 10, 300, 300, this); //����������ñ��̴�С
		
		Graphics2D g2d = (Graphics2D)g;
		
		//������
		g2d.rotate( handSecond.getAngle()*(Math.PI/180), width/2, 160); 
		g2d.drawLine(width/2, 160, width/2, 60);
		//������
		g2d.rotate( -handSecond.getAngle()*(Math.PI/180), width/2, 160); //�ȸ�λ
		g2d.rotate( handMinute.getAngle()*(Math.PI/180), width/2, 160);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine(width/2, 160, width/2, 80);
		//��ʱ��
		g2d.rotate( -handMinute.getAngle()*(Math.PI/180), width/2, 160); //�ȸ�λ
		g2d.rotate( handHour.getAngle()*(Math.PI/180), width/2, 160);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine(width/2, 160, width/2, 100);
	}
	
	
}


































