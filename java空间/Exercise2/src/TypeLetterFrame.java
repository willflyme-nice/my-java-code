import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.util.Random;
import java.util.Vector;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TypeLetterFrame extends JFrame implements KeyListener,Runnable {
	private TypeLetterPanel pane=new TypeLetterPanel();
	static Vector<LetterLab> letters=new Vector<LetterLab>(); //用于管理所有标签
	static int score=0; //得分
	static JLabel scoreLab=new JLabel("当前得分：0"); //得分标签
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TypeLetterFrame that=new TypeLetterFrame();
		that.go();
	}
	private void go() {
		setTitle("打字母游戏");
		setSize(600,500);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setVisible(true);
		
		pane.setLayout(null);
		add(pane);
		scoreLab.setBounds(230,0,150,40);
		pane.add(scoreLab);
		
		//添加键盘监听器并启动线程
		addKeyListener(this);
		new Thread(this).start();
		
	}
	//更新得分标签
	static void updateScorelab(int num) {
		score=score+num;
		scoreLab.setText("当前得分："+score);
	}
	//run里实现字母标签的生成
	public void run() {
		int x; 
		int y=-20;
		while(true) {
			for(int i=0;i<5;i++) {
				x=(int)(i*100+60*Math.random());
				LetterLab lab=new LetterLab();
				lab.setLocation(x,y);
				pane.add(lab);
				letters.add(lab); //将标签的键值加入数组
				
				int sleepTime=(int)(500+Math.random()*1000);
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
			
	}
	//键盘事件
	public void keyTyped(KeyEvent e) {
		char code=e.getKeyChar();//获取键码
		for(int i=0;i<letters.size();i++) { //遍历向量
			LetterLab lab=letters.elementAt(i);
			if(lab.getLetterCode()==code||lab.getLetterCode()==code-32)
			{
				letters.remove(i); //从向量中清除
				pane.remove(lab); //清除该标签
				updateScorelab(10);//更新得分
				System.out.println(letters.size()); //打印成员个数
				pane.repaint();
				return;
			}
		}
	}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
		
	
}

/**
*标签类
 */
class LetterLab extends JLabel implements Runnable {
	String letter;
	char code;
	ImageIcon icon;
	Container parent; //父容器
	LetterLab(){
		Random ran=new Random();
		code=(char)('A'+ran.nextInt(26));
		letter=code+""; //随机产生一个字母
		icon=new ImageIcon("image//typeLetter//icon//"+letter+".png");
		setIcon(icon);
		setSize(icon.getIconWidth(),icon.getIconHeight());
		new Thread(this).start(); //启动下坠线程
	}
	public char getLetterCode() {
		return code;
	}
	//run里实现字母标签的下坠
	public void run() {
		while(parent==null) {
			parent=this.getParent();
		}
		int height=parent.getHeight();
		int x=getLocation().x;
		int y=getLocation().y;
		int sleepTime=(int)(50+100*Math.random()); //调整下坠速度时间间隔
		int sy=(int)(3+10*Math.random()); //调整下坠速度距离间隔
		while(y<height-20) {
			y+=sy;
			setLocation(x,y);
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(getParent()!=null) {//如果标签还在父容器内
			TypeLetterFrame.updateScorelab(-20); //扣分
			TypeLetterFrame.letters.remove(this); //从向量中清除
			parent.remove(this);
			parent.repaint();
		}
	
	}
}
/**
 * 背景面板类
 */
class TypeLetterPanel extends JPanel{
	Image img;
	TypeLetterPanel(){
		ImageIcon icon=new ImageIcon("image//typeLetter//background.jpg");
		img=icon.getImage();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width=getWidth();
		int height=getHeight();
		g.drawImage(img,0, 0, width, height, this);
	}
	
}








