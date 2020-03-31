import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EtchedBorder;

public class DiceGameFrame extends JFrame implements Runnable{
	private JLabel lab1=new JLabel(); //骰子1
	private JLabel lab2=new JLabel(); //骰子2
	private JLabel lab3=new JLabel(); //骰子3
	private ImageIcon icon[]=new ImageIcon[6]; //骰子六面的图片
	Thread thread=new Thread(this); //投骰子线程
	
	private int sum=0;
	private MyLabel sumlab=new MyLabel("点数和：0"); //点数和标签
	private int coin=1000; 
	private MyLabel coinlab=new MyLabel("钱币：1000"); //玩家钱币标签
	private int wager=10;
	private MyLabel wagerlab=new MyLabel("10"); //赌注标签
	boolean selectBig=true;  //玩家选择大还是小
	JLabel tip=new JLabel(); //提示信息
	
	private MyButton button1=new MyButton("大.png");
	private MyButton button2=new MyButton("小.png");
	private JButton put10=new JButton("+10元 ");
	private JButton put50=new JButton("+50元 ");
	private JButton put100=new JButton("+100元");
	private JButton put200=new JButton("+200元");
	private JButton redouble=new JButton("加倍  ");
	
	private DiceGamePanel pane=new DiceGamePanel(); //背景面板
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DiceGameFrame that=new DiceGameFrame();
		that.go();
		that.setVisible(true);
	}
	private void go() {
		setTitle("掷骰子游戏");
		setSize(700,600);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//初始化骰子
		for(int i=0;i<6;i++) {
			icon[i]=new ImageIcon("image//diceGame//"+(i+1)+".png");
		}
		int index1=(int)(Math.random()*6);
		int index2=(int)(Math.random()*6);
		int index3=(int)(Math.random()*6);
		lab1.setIcon(icon[index1]);
		lab2.setIcon(icon[index2]);
		lab3.setIcon(icon[index3]);
		
		//组件布局
		getContentPane().add(pane);
		lab1.setBounds(260,170,50,50);
		pane.add(lab1);
		lab2.setBounds(370,170,50,50);
		pane.add(lab2);
		lab3.setBounds(310,250,50,50);
		pane.add(lab3);
		button1.setBounds(195,350,120,50);
		pane.add(button1);
		button2.setBounds(370,350,120,50);
		pane.add(button2);
		sumlab.setBounds(275,10,130,30);
		pane.add(sumlab);
		coinlab.setBounds(530,470,150,30);
		pane.add(coinlab);
		put10.setBounds(200,400,80,30);
		pane.add(put10);
		wagerlab.setBounds(290,400,100,30);
		pane.add(wagerlab);
		put50.setBounds(400,400,80,30);
		pane.add(put50);
		put100.setBounds(200,450,80,30);
		pane.add(put100);
		put200.setBounds(300,450,80,30);
		pane.add(put200);
		redouble.setBounds(400,450,80,30);
		pane.add(redouble);
		
		//添加事件
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPerform(e);
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPerform(e);
			}
		});
		put10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putPerform(e);
			}
		});
		put50.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putPerform(e);
			}
		});
		put100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putPerform(e);
			}
		});
		put200.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putPerform(e);
			}
		});
		redouble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				putPerform(e);
			}
		});
		
	}
	//求点数和
	public int getSum() {
		int num1=0,num2=0,num3=0;
		for(int i=0;i<6;i++) {
			if(num1==0) {
				if(lab1.getIcon()==icon[i]) {num1=i+1;}
			}
			if(num2==0) {
				if(lab2.getIcon()==icon[i]) {num2=i+1;}
			}
			if(num3==0) {
				if(lab3.getIcon()==icon[i]) {num3=i+1;}
			}
		}
		return num1+num2+num3;	
	}
	//线程体
	public void run() {
		for(int i=0;i<50;i++) {
			int index1=(int)(Math.random()*6);
			int index2=(int)(Math.random()*6);
			int index3=(int)(Math.random()*6);
			lab1.setIcon(icon[index1]);
			lab2.setIcon(icon[index2]);
			lab3.setIcon(icon[index3]);	
			repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int precoin=coin;
		sum=getSum(); //获得点数和
		if(sum>10) {
			sumlab.setText("点数和："+sum+"大");
			coin=selectBig?(coin+wager):(coin-wager);
		}
		else {
			sumlab.setText("点数和："+sum+"小");
			coin=!selectBig?(coin+wager):(coin-wager);
		}
		coinlab.setText("钱币："+coin);
		
		//设置提示信息
		tip.setFont(new Font("楷体",Font.BOLD,25));
		tip.setForeground(Color.YELLOW);
		tip.setText((coin-precoin)+"");
		tip.setBounds(530,400,150,30);
		pane.add(tip);
		
		wager=10;
		wagerlab.setText("10");
		
	}
	//按键button响应事件
	public void buttonPerform(ActionEvent e) {
		if(thread.isAlive()) {
			return;		//若正在投掷骰子，则按键无效
		}
		if(e.getSource()==button1) {
			selectBig=true;
		}
		else {
			selectBig=false;
		}
		thread=new Thread(this);
		thread.start(); //启动线程
		
	}
	//按键put响应事件
	public void putPerform(ActionEvent e) {
		if(thread.isAlive()) { 
			return;		//若正在投掷骰子，则按键无效
		}
		JButton source=(JButton)e.getSource();
		if(source==put10) {wager+=10;}
		else if(source==put50) {wager+=50;}
		else if(source==put100) {wager+=100;}
		else if(source==put200) {wager+=200;}
		else if(source==redouble) {wager*=2;}
		wagerlab.setText(wager+"");
	}
	//内部类
	private class MyButton extends JButton{
		public MyButton(String filename) {
			super();
			ImageIcon icon=new ImageIcon("image//diceGame//"+filename);
			this.setIcon(icon);
			this.setContentAreaFilled(false); //取消填充区域
			this.setBorder(null); //取消边框
		}
	}
	//内部类
	private class MyLabel extends JLabel{
		MyLabel(String str){
			super(str);
			this.setFont(new Font("微软雅黑",Font.PLAIN,20));
			this.setBackground(Color.WHITE);
			this.setOpaque(true);
			this.setBorder(new EtchedBorder());
			this.setHorizontalAlignment(JLabel.CENTER); //居中对齐
		}
	}

}

class DiceGamePanel extends JPanel{
	Image img;
	DiceGamePanel(){
		ImageIcon icon=new ImageIcon("image//diceGame//background.jpg");
		img=icon.getImage();
		setLayout(null);
	}
	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
		g.drawImage(img,0, 0, getWidth(), getHeight(),this);	
	}
}






