import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class RaceTextFrame extends JFrame{
	RaceTextPanel pane=new RaceTextPanel();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RaceTextFrame that=new RaceTextFrame();
		that.go();
	}
	void go() {
		setTitle("文字跑马灯特效");
		add(pane);
		Thread thr=new Thread(pane);
		thr.start();
		
		setSize(400,350);
		setLocation(400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	class RaceTextPanel extends JPanel implements Runnable{
		Image img;
		boolean flag1=false; //状态标志1用来用来区分背景图片对象是否已经创建过
		boolean flag2=false; //状态标志2用来用来区分文字跑马灯是否已经初始化过了
		String text="日出江花红胜火，春来江水绿如蓝。";
		char words[]=text.toCharArray();
		int x[]=new int[words.length];//记录每个字符的横轴坐标
		int y=50; //纵轴坐标
		public void paint(Graphics g) {
			super.paint(g);
			//绘制背景图片
			if(!flag1) {
				try {
					img=ImageIO.read(new File("image//image3.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				flag1=true;
			}
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			//绘制文字跑马灯
			g.setColor(Color.GREEN);
			g.setFont(new Font("宋体",Font.PLAIN,20));
			for(int i=0;i<words.length;i++) {
				g.drawString(words[i]+"", x[i], y);
			}
		}
		public void run() {
			while(true) {
				if(!flag2) {  //如果是第一次执行，横轴坐标等比递增
					for(int i=0;i<words.length;i++) {
						x[i]=20*i+10;
					}
					flag2=true;
				}
				else { //如果不是第一次执行,横坐标灯差递减
					for(int i=0;i<words.length;i++) {
						x[i]=x[i]-1;
						if(x[i]<10) { //如果移动到左边缘，则跳到右边缘
							x[i]=getWidth()-20;
						}
					}	
				}
				this.repaint();
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			
			
		}
	}
}
