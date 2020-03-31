import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PoliceGraspThief extends JFrame implements Runnable{
	JLabel thiefLab=new JLabel();
	JLabel policeLab=new JLabel();
	Thread thread=new Thread(this); //С͵�˶��߳�
	boolean stop=false; //С͵�˶���ͣ��־
	JButton button=new JButton();
	PoliceGraspThiefPanel pane=new PoliceGraspThiefPanel();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PoliceGraspThief that=new PoliceGraspThief();
		that.go();
		that.setVisible(true);
	}
	private void go() {
		setTitle("����ץС͵��Ϸ");
		setSize(700,500);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//�������
		add(pane);
		ImageIcon icon1=new ImageIcon("image//graspThief//С͵.png");
		thiefLab.setIcon(icon1);
		thiefLab.setBounds(300,120,icon1.getIconWidth(),icon1.getIconHeight());
		pane.add(thiefLab);
		ImageIcon icon2=new ImageIcon("image//graspThief//����.png");
		policeLab.setIcon(icon2);
		policeLab.setBounds(10,100,icon2.getIconWidth(),icon2.getIconHeight());
		pane.add(policeLab);
		ImageIcon icon3=new ImageIcon("image//graspThief//zailai.png");
		button.setIcon(icon3);
		button.setBounds(520,400,icon3.getIconWidth(),icon3.getIconHeight());
		pane.add(button);
		
		//����¼�
		thiefLab.addMouseListener(new ThiefLabListener());
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				//���¿�ʼ
				if(!thread.isAlive()) {
					stop=false;
					thread.start();
				}
				
			}
		});
		
		//�����߳�
		thread.start();
		
		
	}
	//�ڲ���������
	private class ThiefLabListener extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			stop=true;
			JOptionPane.showMessageDialog(null, "��ϲ��ץ��С͵��");
		}
	}
	//ʵ���߳���
	public void run() {
		boolean right=true; //�˶�����
		int x=300;
		int y=120;
		thiefLab.setLocation(x,y);
		while(true) {
			if(stop) {
				thread=null;
				thread=new Thread(this);
				return; //�����߳�
			}
			else {
				y=(int)(Math.random()*120+60);
				if(right) { //�����˶�
					x+=10;
					thiefLab.setLocation(x,y);
					if(x>600) {
						right=false;
					}
				}
				else { //�����˶�
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
 * ���������
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







