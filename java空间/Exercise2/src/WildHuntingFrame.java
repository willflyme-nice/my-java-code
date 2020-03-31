import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
/**
 * 主窗体类
 */
public class WildHuntingFrame extends JFrame implements MouseListener,Runnable{
	private static int score=0; //分数
	private static JLabel scoreLab=new JLabel("得分：0");
	private int bulletNum=5; //子弹数
	private JLabel bulletLab=new JLabel("子弹：5");
	WildHuntingPanel pane=new WildHuntingPanel();//背景面板
	JPanel glassPane=new JPanel();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WildHuntingFrame frame=new WildHuntingFrame();
		frame.go();
		frame.setVisible(true);
	}
	private void go() {
		setTitle("荒山打猎游戏");
		setSize(pane.width+15,pane.height+40);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//内容面板：添加背景
		getContentPane().add(pane);
		
		//玻璃面板
		setGlassPane(glassPane);
		glassPane.setVisible(true);
		glassPane.setOpaque(false);
		glassPane.setLayout(null);
		scoreLab.setBounds(45,10,80,40);
		glassPane.add(scoreLab);
		bulletLab.setBounds(430,10,80,40);
		glassPane.add(bulletLab);
		glassPane.addMouseListener(this);
		
		//开启线程体
		new Thread(this).start();
	}
	//更新计分牌
	public static synchronized void updateScore(int num) { //得分，可正可负
		score=score+num;
		scoreLab.setText("得分："+score);
	}
	//使用子弹
	public void useBullet() {
		bulletNum--;
		bulletLab.setText("子弹："+bulletNum);
		repaint();
		if(bulletNum==0) {
			new loadBullet().start(); //子弹完了便开启装载线程
		}
	}
	//装载子弹线程类
	class loadBullet extends Thread{
		public void run() {
			JLabel lab=new JLabel("子弹装载中...");
			lab.setBounds(200,150,100,50);
			glassPane.add(lab);
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bulletNum=10;
			glassPane.remove(lab);
			repaint();
			bulletLab.setText("子弹："+bulletNum);
		}
	}
	//线程体
	public void run() {
		int times=0;
		while(true) {
			try {
				Thread.sleep(500);
				times++;
				if(times%4==0) { //每2秒 野鸟出现
					BirdLabel label=new BirdLabel();
					glassPane.add(label);
					label.addMouseListener(this); //添加监听器
					repaint();
				}
				if(times%5==0) { //每2.5秒 野猪出现
					PigLabel label=new PigLabel();
					glassPane.add(label);
					label.addMouseListener(this); //添加监听器
					repaint();
				}	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	//事件响应
	 public void mouseClicked(MouseEvent e) {} 
	 public void mouseEntered(MouseEvent e) {}  
	 public void mouseExited(MouseEvent e) {}
	 public void mousePressed(MouseEvent e) {
		 if(bulletNum==0) { //无子弹则退出
			 return;
		 } 
		 Object o=e.getSource();
		
		 repaint();
		 if(o instanceof BirdLabel) {
			 glassPane.remove((BirdLabel)o);
			 updateScore(10);
		 }
		 else if(o instanceof PigLabel) {
			 glassPane.remove((PigLabel)o);
			 updateScore(50);
		 }	 
		 useBullet();
	 }
	 public void mouseReleased(MouseEvent e) {}
}

/**
 * 野鸟类
 */
class BirdLabel extends JLabel implements Runnable{
	ImageIcon icon; //图标
	JPanel parentPane; //父容器
	int width;
	
	BirdLabel(){
		icon=new ImageIcon("image//hunting//bird.gif");
		setIcon(icon);
		setSize(icon.getIconWidth(),icon.getIconHeight());
		setVisible(false); //先不显示，待线程显示
		new Thread(this).start(); //构造好便启动线程
	}
	
	public void run() {
		parentPane=(JPanel)getParent();
		int i=0;
		while(parentPane==null) {
			parentPane=(JPanel)getParent();
			System.out.println("野鸟等待"+i++);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		width=parentPane.getWidth();
		setLocation(width-20,100);
		setVisible(true); //位置确定好了再显示
 		int speed=(int)(Math.random()*100+10);
		int x=getX();
		int y=getY();
		while(x>-10) {
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			x=x-5;
			setLocation(x,y);
		}
		if(getParent()!=null) {//如果对象还在父容器内
			WildHuntingFrame.updateScore(-10);
			destoy();
		}	
	}
	//销毁
	public void destoy() { 
		parentPane.remove(this);
	}
}
/**
 * 野猪类
 */
class PigLabel extends JLabel implements Runnable{
	ImageIcon icon; //图标
	JPanel parentPane; //父容器
	int width;
	
	PigLabel(){
		icon=new ImageIcon("image//hunting//pig.gif");
		setIcon(icon);
		setSize(icon.getIconWidth(),icon.getIconHeight());
		setVisible(false); //先不显示，待线程显示
		new Thread(this).start(); //启动线程
	}
	
	public void run() {
		parentPane=(JPanel)getParent();
		int i=0;
		while(parentPane==null) {
			parentPane=(JPanel)getParent();
			System.out.print("野猪等待"+i++);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		width=parentPane.getWidth();
		setLocation(-40,300);
		setVisible(true); //位置确定好了再显示
		int speed=(int)(Math.random()*50+10);
		int x=getX();
		int y=getY();
		while(x<width-40) {
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			x=x+2;
			setLocation(x,y);
		}
		if(getParent()!=null) {//如果对象还在父容器内
			WildHuntingFrame.updateScore(-50);
			destoy();
		}
	}
	public void destoy() { 
		parentPane.remove(this);
	}
}
/**
 * 背景面板类
 */
class WildHuntingPanel extends JPanel{
	private Image img;
	int width;
	int height;
	WildHuntingPanel(){
		super();
		try {
			img=ImageIO.read(new File("image//hunting//background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		width=img.getWidth(this);
		height=img.getHeight(this);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
	
}















