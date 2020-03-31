import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.util.Random;
import java.util.Vector;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TypeLetterFrame extends JFrame implements KeyListener,Runnable {
	private TypeLetterPanel pane=new TypeLetterPanel();
	static Vector<LetterLab> letters=new Vector<LetterLab>(); //���ڹ������б�ǩ
	static int score=0; //�÷�
	static JLabel scoreLab=new JLabel("��ǰ�÷֣�0"); //�÷ֱ�ǩ
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TypeLetterFrame that=new TypeLetterFrame();
		that.go();
	}
	private void go() {
		setTitle("����ĸ��Ϸ");
		setSize(600,500);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setVisible(true);
		
		pane.setLayout(null);
		add(pane);
		scoreLab.setBounds(230,0,150,40);
		pane.add(scoreLab);
		
		//��Ӽ��̼������������߳�
		addKeyListener(this);
		new Thread(this).start();
		
	}
	//���µ÷ֱ�ǩ
	static void updateScorelab(int num) {
		score=score+num;
		scoreLab.setText("��ǰ�÷֣�"+score);
	}
	//run��ʵ����ĸ��ǩ������
	public void run() {
		int x; 
		int y=-20;
		while(true) {
			for(int i=0;i<5;i++) {
				x=(int)(i*100+60*Math.random());
				LetterLab lab=new LetterLab();
				lab.setLocation(x,y);
				pane.add(lab);
				letters.add(lab); //����ǩ�ļ�ֵ��������
				
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
	//�����¼�
	public void keyTyped(KeyEvent e) {
		char code=e.getKeyChar();//��ȡ����
		for(int i=0;i<letters.size();i++) { //��������
			LetterLab lab=letters.elementAt(i);
			if(lab.getLetterCode()==code||lab.getLetterCode()==code-32)
			{
				letters.remove(i); //�����������
				pane.remove(lab); //����ñ�ǩ
				updateScorelab(10);//���µ÷�
				System.out.println(letters.size()); //��ӡ��Ա����
				pane.repaint();
				return;
			}
		}
	}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
		
	
}

/**
*��ǩ��
 */
class LetterLab extends JLabel implements Runnable {
	String letter;
	char code;
	ImageIcon icon;
	Container parent; //������
	LetterLab(){
		Random ran=new Random();
		code=(char)('A'+ran.nextInt(26));
		letter=code+""; //�������һ����ĸ
		icon=new ImageIcon("image//typeLetter//icon//"+letter+".png");
		setIcon(icon);
		setSize(icon.getIconWidth(),icon.getIconHeight());
		new Thread(this).start(); //������׹�߳�
	}
	public char getLetterCode() {
		return code;
	}
	//run��ʵ����ĸ��ǩ����׹
	public void run() {
		while(parent==null) {
			parent=this.getParent();
		}
		int height=parent.getHeight();
		int x=getLocation().x;
		int y=getLocation().y;
		int sleepTime=(int)(50+100*Math.random()); //������׹�ٶ�ʱ����
		int sy=(int)(3+10*Math.random()); //������׹�ٶȾ�����
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
		if(getParent()!=null) {//�����ǩ���ڸ�������
			TypeLetterFrame.updateScorelab(-20); //�۷�
			TypeLetterFrame.letters.remove(this); //�����������
			parent.remove(this);
			parent.repaint();
		}
	
	}
}
/**
 * ���������
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








