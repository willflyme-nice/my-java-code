import java.awt.*;
import javax.swing.*;

public class CaptionSpecificFrame extends JFrame{
	CaptionSpecificPanel pane=new CaptionSpecificPanel();
	public static void main(String[] args) {
		CaptionSpecificFrame that=new CaptionSpecificFrame();
		that.go();
	}
	void go() {
		setTitle("��Ļ��Ч");
		add(pane);
		Thread thr=new Thread(pane);
		thr.start();
		
		setSize(400,350);
		setLocation(400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
    class CaptionSpecificPanel extends JPanel implements Runnable{
    	Image img;
    	boolean flag=false;//��¼ͼƬ�Ƿ񴴽���
    	int y=300;
    	int x=100;
    	String text="���տƼ���̴ʵ�";
    	
    	public void paint(Graphics g) {
    		super.paint(g);
    		if(!flag) {
    			 img=Toolkit.getDefaultToolkit().getImage("image//image3.jpg"); // ��ȡͼ����Դ
    		}
    		g.drawImage(img, 0, 0, getWidth(), getHeight(),this);
    		//���ַ���
    		g.setColor(Color.blue);
    		g.setFont(new Font("��Բ",Font.BOLD,20));
    		g.drawString(text, x, y);
    		
    	}
    	public void run() {
    		while(true) {
    			try {
    				if(y<getHeight()-50) {
    					y=getHeight()-20;
    					if(text.equals("���տƼ���̴ʵ�")) {
    						text="��ַ��www.mr.cn.com";
    					}
    					else {
    						text="���տƼ���̴ʵ�";
    					}
    				}
    				y=y-2; //��Ļÿ300ms�����ƶ�2����
    				repaint();
					Thread.sleep(300);	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    	}
    }
}
