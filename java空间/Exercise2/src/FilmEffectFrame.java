import java.awt.*;
import javax.swing.*;



public class FilmEffectFrame extends JFrame{
	FilmThread thr=new FilmThread();
	//2个静态标签 5个动态标签
	JLabel labup=new JLabel();
	JLabel lab1=new JLabel();
	JLabel lab2=new JLabel();
	JLabel lab3=new JLabel();
	JLabel lab4=new JLabel();
	JLabel lab5=new JLabel();
	JLabel labdown=new JLabel();
	//5个动态标签对应的横坐标
	int x1=0;
	int x2=98;
	int x3=196;
	int x4=294;
	int x5=392;
	
	public static void main(String[] args) {
		FilmEffectFrame that=new FilmEffectFrame();
		that.go();
	}
	void go() {
		setTitle("电影胶片特效");
		JPanel pane=new JPanel();
		pane.setLayout(null);
		
		labup.setIcon(new ImageIcon("image//film//胶片.JPG"));
		labup.setText("上标签1111");
		labdown.setIcon(new ImageIcon("image//film//胶片.JPG"));
		lab1.setIcon(new ImageIcon("image//film//1.JPG"));
		lab1.setBounds(0,0,98,210);
		pane.add(lab1);
		lab2.setIcon(new ImageIcon("image//film//2.JPG"));
		lab2.setBounds(98,0,98,210);
		pane.add(lab2);
		lab3.setIcon(new ImageIcon("image//film//3.JPG"));
		lab3.setBounds(196,0,98,210);
		pane.add(lab3);
		lab4.setIcon(new ImageIcon("image//film//4.JPG"));
		lab4.setBounds(294,0,98,210);
		pane.add(lab4);
		lab5.setIcon(new ImageIcon("image//film//5.JPG"));
		lab5.setBounds(392,0,98,210);
		pane.add(lab5);
		
		getContentPane().add(pane,"Center");
		getContentPane().add(labup,"North");
		getContentPane().add(labdown,"South");
	
		thr.start();
		
		setSize(400,280);
		setLocation(400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//创建线程类 
	class FilmThread extends Thread{
		boolean flag=false;
		int speed=2;
		public void run() {
			while(true) {
				x1=x1-speed;
				x2=x2-speed;
				x3=x3-speed;
				x4=x4-speed;
				x5=x5-speed;
				if(x1==-98) {
					x1=392;
					flag=!flag;
					if(flag) {
						lab1.setIcon(new ImageIcon("image//film//6.JPG"));
					}
					else {
						lab1.setIcon(new ImageIcon("image//film//1.JPG"));
					}
				}
				if(x2==-98) {
					x2=392;
					flag=!flag;
					if(flag) {
						lab2.setIcon(new ImageIcon("image//film//7.JPG"));
					}
					else {
						lab2.setIcon(new ImageIcon("image//film//2.JPG"));
					}
				}	
				if(x3==-98) {
					x3=392;
					flag=!flag;
					if(flag) {
						lab3.setIcon(new ImageIcon("image//film//8.JPG"));
					}
					else {
						lab3.setIcon(new ImageIcon("image//film//3.JPG"));
					}
				}	
				if(x4==-98) {
					x4=392;
					flag=!flag;
					if(flag) {
						lab4.setIcon(new ImageIcon("image//film//9.JPG"));
					}
					else {
						lab4.setIcon(new ImageIcon("image//film//4.JPG"));
					}
				}	
				if(x5==-98) {
					x5=392;
					flag=!flag;
					if(flag) {
						lab5.setIcon(new ImageIcon("image//film//10.JPG"));
					}
					else {
						lab5.setIcon(new ImageIcon("image//film//5.JPG"));
					}
				}
				lab1.setBounds(x1,0,98,210);
				lab2.setBounds(x2,0,98,210);
				lab3.setBounds(x3,0,98,210);
				lab4.setBounds(x4,0,98,210);
				lab5.setBounds(x5,0,98,210);
			
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
	}

}
