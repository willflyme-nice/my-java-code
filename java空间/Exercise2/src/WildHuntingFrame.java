import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
/**
 * ��������
 */
public class WildHuntingFrame extends JFrame implements MouseListener,Runnable{
	private static int score=0; //����
	private static JLabel scoreLab=new JLabel("�÷֣�0");
	private int bulletNum=5; //�ӵ���
	private JLabel bulletLab=new JLabel("�ӵ���5");
	WildHuntingPanel pane=new WildHuntingPanel();//�������
	JPanel glassPane=new JPanel();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WildHuntingFrame frame=new WildHuntingFrame();
		frame.go();
		frame.setVisible(true);
	}
	private void go() {
		setTitle("��ɽ������Ϸ");
		setSize(pane.width+15,pane.height+40);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//������壺��ӱ���
		getContentPane().add(pane);
		
		//�������
		setGlassPane(glassPane);
		glassPane.setVisible(true);
		glassPane.setOpaque(false);
		glassPane.setLayout(null);
		scoreLab.setBounds(45,10,80,40);
		glassPane.add(scoreLab);
		bulletLab.setBounds(430,10,80,40);
		glassPane.add(bulletLab);
		glassPane.addMouseListener(this);
		
		//�����߳���
		new Thread(this).start();
	}
	//���¼Ʒ���
	public static synchronized void updateScore(int num) { //�÷֣������ɸ�
		score=score+num;
		scoreLab.setText("�÷֣�"+score);
	}
	//ʹ���ӵ�
	public void useBullet() {
		bulletNum--;
		bulletLab.setText("�ӵ���"+bulletNum);
		repaint();
		if(bulletNum==0) {
			new loadBullet().start(); //�ӵ����˱㿪��װ���߳�
		}
	}
	//װ���ӵ��߳���
	class loadBullet extends Thread{
		public void run() {
			JLabel lab=new JLabel("�ӵ�װ����...");
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
			bulletLab.setText("�ӵ���"+bulletNum);
		}
	}
	//�߳���
	public void run() {
		int times=0;
		while(true) {
			try {
				Thread.sleep(500);
				times++;
				if(times%4==0) { //ÿ2�� Ұ�����
					BirdLabel label=new BirdLabel();
					glassPane.add(label);
					label.addMouseListener(this); //��Ӽ�����
					repaint();
				}
				if(times%5==0) { //ÿ2.5�� Ұ�����
					PigLabel label=new PigLabel();
					glassPane.add(label);
					label.addMouseListener(this); //��Ӽ�����
					repaint();
				}	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	//�¼���Ӧ
	 public void mouseClicked(MouseEvent e) {} 
	 public void mouseEntered(MouseEvent e) {}  
	 public void mouseExited(MouseEvent e) {}
	 public void mousePressed(MouseEvent e) {
		 if(bulletNum==0) { //���ӵ����˳�
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
 * Ұ����
 */
class BirdLabel extends JLabel implements Runnable{
	ImageIcon icon; //ͼ��
	JPanel parentPane; //������
	int width;
	
	BirdLabel(){
		icon=new ImageIcon("image//hunting//bird.gif");
		setIcon(icon);
		setSize(icon.getIconWidth(),icon.getIconHeight());
		setVisible(false); //�Ȳ���ʾ�����߳���ʾ
		new Thread(this).start(); //����ñ������߳�
	}
	
	public void run() {
		parentPane=(JPanel)getParent();
		int i=0;
		while(parentPane==null) {
			parentPane=(JPanel)getParent();
			System.out.println("Ұ��ȴ�"+i++);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		width=parentPane.getWidth();
		setLocation(width-20,100);
		setVisible(true); //λ��ȷ����������ʾ
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
		if(getParent()!=null) {//��������ڸ�������
			WildHuntingFrame.updateScore(-10);
			destoy();
		}	
	}
	//����
	public void destoy() { 
		parentPane.remove(this);
	}
}
/**
 * Ұ����
 */
class PigLabel extends JLabel implements Runnable{
	ImageIcon icon; //ͼ��
	JPanel parentPane; //������
	int width;
	
	PigLabel(){
		icon=new ImageIcon("image//hunting//pig.gif");
		setIcon(icon);
		setSize(icon.getIconWidth(),icon.getIconHeight());
		setVisible(false); //�Ȳ���ʾ�����߳���ʾ
		new Thread(this).start(); //�����߳�
	}
	
	public void run() {
		parentPane=(JPanel)getParent();
		int i=0;
		while(parentPane==null) {
			parentPane=(JPanel)getParent();
			System.out.print("Ұ��ȴ�"+i++);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		width=parentPane.getWidth();
		setLocation(-40,300);
		setVisible(true); //λ��ȷ����������ʾ
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
		if(getParent()!=null) {//��������ڸ�������
			WildHuntingFrame.updateScore(-50);
			destoy();
		}
	}
	public void destoy() { 
		parentPane.remove(this);
	}
}
/**
 * ���������
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















