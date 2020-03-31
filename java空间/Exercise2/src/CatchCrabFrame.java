import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CatchCrabFrame extends JFrame {
	JLabel labs[]=new JLabel[6];
	int x[]= {242,190,253,335,389,362}; //���ڵ�x����
	int y[]= {425,370,318,269,312,380}; //���ڵ�y����
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
		
		setTitle("ץ�з��Ϸ");
		setSize(517,540);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//�������
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
		
		//�������벼��
		getContentPane().add(pane);
		JPanel gpane=new JPanel();
		setGlassPane(gpane);
		gpane.setLayout(null);
		gpane.setVisible(true); //�ɼ�
		gpane.setOpaque(false); //͸��
		crabSmile=new ImageIcon("image//crab//crab.png");
		for(int i=0;i<6;i++) {
			JLabel lab=new JLabel();
			lab.setBounds(x[i],y[i],crabSmile.getIconWidth(),crabSmile.getIconHeight());
			gpane.add(lab);
			labs[i]=lab;
		}
		
		//Ϊ��ǩ����¼�
		for(int i=0;i<6;i++) {
			labs[i].addMouseListener(new Listener1()); //�����ڱ�ǩ
			labs[i].addMouseListener(new Listener2()); //�����ڹ��
		}	
		this.addMouseListener(new Listener2());
		
		//�����߳�
		new CrabThread().start();
	}
	
	//��������1
	class Listener1 extends MouseAdapter{ //�����ڱ�ǩ
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
	//��������2
	class Listener2 extends MouseAdapter{ //�����ڹ��
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
	
	//�߳���
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
 * ���������
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



