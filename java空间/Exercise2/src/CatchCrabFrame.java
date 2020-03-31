import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CatchCrabFrame extends JFrame {
	JLabel labs[]=new JLabel[6];
	int x[]= {242,190,253,335,389,362}; //洞口的x坐标
	int y[]= {425,370,318,269,312,380}; //洞口的y坐标
	ImageIcon crabSmile=new ImageIcon("image//crab//crab.png");
	ImageIcon crabCry=new ImageIcon("image//crab//crab2.png");
	Cursor released;
	Cursor catching;
	CrabBackPanel pane=new CrabBackPanel();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CatchCrabFrame that=new CatchCrabFrame();
		that.go();
		that.setVisible(true);
	}
	private void go() {
		
		setTitle("抓螃蟹游戏");
		setSize(517,540);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//创建光标
		Image releasedImg=null;
		Image catchingImg=null;
		try {
			releasedImg=ImageIO.read(new File("image//crab//hand.jpg"));
			catchingImg=ImageIO.read(new File("image//crab//hand2.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		released=getToolkit().createCustomCursor(releasedImg, new Point(), "my");
		catching=getToolkit().createCustomCursor(catchingImg, new Point(), "my");
		setCursor(released);
		
		//添加组件与布局
		getContentPane().add(pane);
		JPanel gpane=new JPanel();
		setGlassPane(gpane);
		gpane.setLayout(null);
		gpane.setVisible(true); //可见
		gpane.setOpaque(false); //透明
		crabSmile=new ImageIcon("image//crab//crab.png");
		for(int i=0;i<6;i++) {
			JLabel lab=new JLabel();
			lab.setBounds(x[i],y[i],crabSmile.getIconWidth(),crabSmile.getIconHeight());
			gpane.add(lab);
			labs[i]=lab;
		}
		
		//为标签添加事件
		for(int i=0;i<6;i++) {
			labs[i].addMouseListener(new Listener1()); //作用于标签
			labs[i].addMouseListener(new Listener2()); //作用于光标
		}	
		this.addMouseListener(new Listener2());
		
		//启动线程
		new CrabThread().start();
	}
	
	//监听器类1
	class Listener1 extends MouseAdapter{ //作用于标签
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			JLabel source=(JLabel)e.getSource();
			if(source.getIcon()!=null) {
				source.setIcon(crabCry);
			}
		}
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			JLabel source=(JLabel)e.getSource();
			source.setIcon(null);
		}	
	}
	//监听器类2
	class Listener2 extends MouseAdapter{ //作用于光标
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			setCursor(catching);
		}
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			setCursor(released);
		}	
		public void mouseExited(MouseEvent e) {
			setCursor(released);
		} 
	}
	
	//线程类
	private class CrabThread extends Thread{
		Random random=new Random();
		public void run() {
			while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int index=random.nextInt(6);
				if(labs[index].getIcon()==null) {
					labs[index].setIcon(crabSmile);
				}
					
			}
		}
	}	
	
}
/**
 * 背景面板类
 */

class CrabBackPanel extends JPanel{
	Image img=null;
	public void paint(Graphics g) {
		super.paint(g);
		try {
			img=ImageIO.read(new File("image//crab//background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(img,0, 0, this);
	}
}



