import java.awt.*;
import javax.swing.*;

public class CaptionSpecificFrame extends JFrame{
	CaptionSpecificPanel pane=new CaptionSpecificPanel();
	public static void main(String[] args) {
		CaptionSpecificFrame that=new CaptionSpecificFrame();
		that.go();
	}
	void go() {
		setTitle("字幕特效");
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
    	boolean flag=false;//记录图片是否创建了
    	int y=300;
    	int x=100;
    	String text="明日科技编程词典";
    	
    	public void paint(Graphics g) {
    		super.paint(g);
    		if(!flag) {
    			 img=Toolkit.getDefaultToolkit().getImage("image//image3.jpg"); // 获取图像资源
    		}
    		g.drawImage(img, 0, 0, getWidth(), getHeight(),this);
    		//画字符串
    		g.setColor(Color.blue);
    		g.setFont(new Font("幼圆",Font.BOLD,20));
    		g.drawString(text, x, y);
    		
    	}
    	public void run() {
    		while(true) {
    			try {
    				if(y<getHeight()-50) {
    					y=getHeight()-20;
    					if(text.equals("明日科技编程词典")) {
    						text="网址：www.mr.cn.com";
    					}
    					else {
    						text="明日科技编程词典";
    					}
    				}
    				y=y-2; //字幕每300ms向上移动2像素
    				repaint();
					Thread.sleep(300);	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    	}
    }
}
