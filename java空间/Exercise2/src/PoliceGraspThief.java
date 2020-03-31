import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PoliceGraspThief extends JFrame implements Runnable{
	JLabel thiefLab=new JLabel();
	JLabel policeLab=new JLabel();
	Thread thread=new Thread(this); //小偷运动线程
	boolean stop=false; //小偷运动暂停标志
	JButton button=new JButton();
	PoliceGraspThiefPanel pane=new PoliceGraspThiefPanel();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PoliceGraspThief that=new PoliceGraspThief();
		that.go();
		that.setVisible(true);
	}
	private void go() {
		setTitle("警察抓小偷游戏");
		setSize(700,500);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//组件布局
		add(pane);
		ImageIcon icon1=new ImageIcon("image//graspThief//小偷.png");
		thiefLab.setIcon(icon1);
		thiefLab.setBounds(300,120,icon1.getIconWidth(),icon1.getIconHeight());
		pane.add(thiefLab);
		ImageIcon icon2=new ImageIcon("image//graspThief//警察.png");
		policeLab.setIcon(icon2);
		policeLab.setBounds(10,100,icon2.getIconWidth(),icon2.getIconHeight());
		pane.add(policeLab);
		ImageIcon icon3=new ImageIcon("image//graspThief//zailai.png");
		button.setIcon(icon3);
		button.setBounds(520,400,icon3.getIconWidth(),icon3.getIconHeight());
		pane.add(button);
		
		//添加事件
		thiefLab.addMouseListener(new ThiefLabListener());
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				//重新开始
				if(!thread.isAlive()) {
					stop=false;
					thread.start();
				}
				
			}
		});
		
		//启动线程
		thread.start();
		
		
	}
	//内部监听器类
	private class ThiefLabListener extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			stop=true;
			JOptionPane.showMessageDialog(null, "恭喜。抓到小偷了");
		}
	}
	//实现线程体
	public void run() {
		boolean right=true; //运动方向
		int x=300;
		int y=120;
		thiefLab.setLocation(x,y);
		while(true) {
			if(stop) {
				thread=null;
				thread=new Thread(this);
				return; //结束线程
			}
			else {
				y=(int)(Math.random()*120+60);
				if(right) { //向右运动
					x+=10;
					thiefLab.setLocation(x,y);
					if(x>600) {
						right=false;
					}
				}
				else { //向左运动
					x-=10;
					thiefLab.setLocation(x,y);
					if(x<300) {
						right=true;
					}
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
		}	
	}

	
	
	
}

/**
 * 背景面板类
 */
class PoliceGraspThiefPanel extends JPanel{
	Image img;
	PoliceGraspThiefPanel(){
		ImageIcon icon=new ImageIcon("image//graspThief//background.png");
		img=icon.getImage();
		setLayout(null);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width=getWidth();
		int height=getHeight();
		g.drawImage(img,0, 0, width, height, this);
	}
	
}







