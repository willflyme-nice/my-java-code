
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class PigMazeFrame extends JFrame implements ActionListener,KeyListener{
	//�����ĸ���������
	Rectangle rec1=new Rectangle(50, 50, 300, 40);
	Rectangle rec2=new Rectangle(250,90,40,200);
	Rectangle rec3=new Rectangle(100,200,300,40);
	Rectangle rec4=new Rectangle(120,240,40,150);
	//����3��������ť��1����Ϣ��ǩ
	JButton button;
	JButton b1;
	JButton b2;
	JLabel lab;
	//
	ImageIcon icon=new ImageIcon("image//pig//pig.png");
	JButton pig=new JButton(icon); //С�����
	int x; //С�������
	int y; //С��������
	int speed=2; //�ƶ��ٶ�
	BackgroundPanel pane=new BackgroundPanel(); //�������
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PigMazeFrame that=new PigMazeFrame();
		that.go();
		that.setVisible(true);
	}
	private void go() {
		setTitle("С�����Թ�");
		setSize(520,442);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		pane.setLayout(null);
		pane.setBounds(2,2,500,400);
		getContentPane().setLayout(null);
		getContentPane().add(pane);
		
		button=new JButton("���¿�ʼ");
		button.setBounds(400,10,90,30);
		pane.add(button);
		button.addActionListener(this);
		b1=new JButton("����");
		b1.setBounds(400,50,60,30);
		pane.add(b1);
		b1.addActionListener(this);
		b2=new JButton("����");
		b2.setBounds(400,90,60,30);
		pane.add(b2);
		b2.addActionListener(this);
		lab=new JLabel("�ٶȣ�"+speed);
		lab.setBounds(400,130,90,30);
		pane.add(lab);
		
		
		pig.setContentAreaFilled(false); //ȡ���������
		pig.setBorder(null); //ȡ���߿�
		pane.add(pig);
		this.addWindowListener(new WindowAdapter() { //���ڴ�ʱ���¼�
			public void windowOpened(WindowEvent e) {
				pig.requestFocus(); //��ý���
			}
		});
		
		//��Ϸ��ʼ������
		x=50;
		y=50;
		pig.setBounds(x,y,40,40);
		pig.addKeyListener(this);
		
	}
	public void init() {
		x=50;
		y=50;
		pig.setBounds(x,y,40,40);
		pig.addKeyListener(this);
		pig.requestFocus(); //��ý���
	}
	public void actionPerformed(ActionEvent e) {
		JButton source=(JButton)e.getSource();
		if(source==button) {
			x=50;
			y=50;
			pig.setBounds(x,y,40,40);
			pig.setIcon(icon);
			pig.addKeyListener(this);
			pig.requestFocus(); //��ý���
		}
		else if(source==b1) {
			speed++;
			lab.setText("�ٶȣ�"+speed);
			pig.requestFocus(); //��ý���
		}
		else if(source==b2) {
			speed--;
			lab.setText("�ٶȣ�"+speed);
			pig.requestFocus(); //��ý���
		}
		
	}
	public void keyTyped(KeyEvent e) {
		
	}
	public void keyPressed(KeyEvent e) {
		Rectangle lc=new Rectangle(x,y,20,20);
		if(y>=340) {
			pig.removeKeyListener(this); //�����Ƴ�������
			pig.setIcon(new ImageIcon("image//pig//pigOut.png")); //���óɹ�ͼƬ
			pig.setBounds(120,300,90,90);
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP) {
			if(lc.intersects(rec1)||lc.intersects(rec2)||lc.intersects(rec3)||lc.intersects(rec4)) {
				y=y-speed;
				pig.setLocation(x,y);
			}
			else{
				JOptionPane.showMessageDialog(this, "С��ײǽ�ˣ����¿�ʼ�ɡ���");
				init();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			if(lc.intersects(rec1)||lc.intersects(rec2)||lc.intersects(rec3)||lc.intersects(rec4)) {
				y=y+speed;
				pig.setLocation(x,y);
			}
			else{
				JOptionPane.showMessageDialog(this, "С��ײǽ�ˣ����¿�ʼ�ɡ���");
				init();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(lc.intersects(rec1)||lc.intersects(rec2)||lc.intersects(rec3)||lc.intersects(rec4)) {
				x=x-speed;
				pig.setLocation(x,y);
			}
			else{
				JOptionPane.showMessageDialog(this, "С��ײǽ�ˣ����¿�ʼ�ɡ���");
				init();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(lc.intersects(rec1)||lc.intersects(rec2)||lc.intersects(rec3)||lc.intersects(rec4)) {
				x=x+speed;
				pig.setLocation(x,y);
			}
			else{
				JOptionPane.showMessageDialog(this, "С��ײǽ�ˣ����¿�ʼ�ɡ���");
				init();
			}
		}
		
		
	}
	public void keyReleased(KeyEvent e) {
		
	}

}

class BackgroundPanel extends JPanel{
	Image img=null;
	Image exit=null;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			img = ImageIO.read(new File("image//pig//back.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(img,0,0,500,400,this);
		g.setColor(Color.yellow);
		g.fillRect(50, 50, 300, 40);
		g.setColor(Color.cyan);
		g.fillRect(250,90,40,200);
		g.setColor(Color.magenta);
		g.fillRect(100,200,300,40);
		g.setColor(Color.orange);
		g.fillRect(120,240,40,130);
		try {
			exit=ImageIO.read(new File("image//pig//exit.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(exit,120,370,this);
		
	}
	
}



