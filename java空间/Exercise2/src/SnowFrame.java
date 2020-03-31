import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseEvent;

public class SnowFrame extends JFrame{
	BackPanel backGroundPane=new BackPanel();
	public static void main(String[] args) {
		SnowFrame that=new SnowFrame();
		that.go();
		that.setVisible(true);
	}
	void go() {
		setTitle("雪花飘落");
		setSize(700,500);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		getContentPane().add(backGroundPane);
		
		//设置背景图片并添加鼠标事件
		Image img=null;	
		try {
			img = ImageIO.read(new File("image//snow//bg.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backGroundPane.setImage(img);
		backGroundPane.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {//鼠标移动事件
				Point point=e.getPoint(); //获取鼠标的相对位置
				SnowLabel snowlab=new SnowLabel(point,backGroundPane);
				snowlab.setLocation(point);
				backGroundPane.add(snowlab);	
			}
		});
		
		//设置光标图片
		try {
			img = ImageIO.read(new File("image//snow//cursor.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Cursor cursor=getToolkit().createCustomCursor(img, new Point(), "my");
		setCursor(cursor);
	}

}

class SnowLabel extends JLabel implements Runnable{
	private final static ImageIcon snow=new ImageIcon("image\\snow\\snowflake.png");
	JPanel parent; //父容器
	int x;
	int y;
	SnowLabel(Point point,JPanel paren){
		super();
		setOpaque(false);
		setIcon(snow);
		setSize(new Dimension(snow.getIconWidth(),snow.getIconHeight()));
		parent=paren;
		x=point.x;
		y=point.y;
		new Thread(this).start();//启动线程
	}
	public void run() {
		int speed=(int)(Math.random()*30+10);
		while(parent.isVisible()&&y<parent.getHeight()-20) {
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			y=y+2;
			setLocation(x,y);
		}
		
	}
}

class BackPanel extends JPanel{
	Image img=null;
	BackPanel(){
		setLayout(null);
		setOpaque(false);
	}
	public void setImage(Image image) {
		img=image;
	}
	public void paint(Graphics g) {
		if(img!=null) {
			g.drawImage(img, 0, 0,this.getWidth(),this.getHeight(),this); //要不停的刷新背景图片
		}
		super.paint(g);
	}
}
















