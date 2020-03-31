import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class RaceTextFrame extends JFrame{
	RaceTextPanel pane=new RaceTextPanel();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RaceTextFrame that=new RaceTextFrame();
		that.go();
	}
	void go() {
		setTitle("�����������Ч");
		add(pane);
		Thread thr=new Thread(pane);
		thr.start();
		
		setSize(400,350);
		setLocation(400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	class RaceTextPanel extends JPanel implements Runnable{
		Image img;
		boolean flag1=false; //״̬��־1�����������ֱ���ͼƬ�����Ƿ��Ѿ�������
		boolean flag2=false; //״̬��־2����������������������Ƿ��Ѿ���ʼ������
		String text="�ճ�������ʤ�𣬴�����ˮ��������";
		char words[]=text.toCharArray();
		int x[]=new int[words.length];//��¼ÿ���ַ��ĺ�������
		int y=50; //��������
		public void paint(Graphics g) {
			super.paint(g);
			//���Ʊ���ͼƬ
			if(!flag1) {
				try {
					img=ImageIO.read(new File("image//image3.jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				flag1=true;
			}
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			//�������������
			g.setColor(Color.GREEN);
			g.setFont(new Font("����",Font.PLAIN,20));
			for(int i=0;i<words.length;i++) {
				g.drawString(words[i]+"", x[i], y);
			}
		}
		public void run() {
			while(true) {
				if(!flag2) {  //����ǵ�һ��ִ�У���������ȱȵ���
					for(int i=0;i<words.length;i++) {
						x[i]=20*i+10;
					}
					flag2=true;
				}
				else { //������ǵ�һ��ִ��,������Ʋ�ݼ�
					for(int i=0;i<words.length;i++) {
						x[i]=x[i]-1;
						if(x[i]<10) { //����ƶ������Ե���������ұ�Ե
							x[i]=getWidth()-20;
						}
					}	
				}
				this.repaint();
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			
			
		}
	}
}
