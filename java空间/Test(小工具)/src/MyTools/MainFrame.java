package MyTools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame{
	
	private final int frameWidth = 400;
	private final int frameHeight = 300;
	
	private ToIcon labClock;
	private ToIcon labDraw;
	private ToIcon labSnake;
	private ToIcon labFileBrows;

	public static void main(String[] args) {
		
		MainFrame that = new MainFrame();
		that.setUI();
		that.setVisible(true);
		
	}
	
	//主函数 构造函数
	public MainFrame() {
		setSize(frameWidth,frameHeight);
		setResizable(false);
		setLocation(10,10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("我的小工具集");
	}
	
	//设置界面UI
	private void setUI() {
		setLayout(null);
		
		try {
			labClock = new ToIcon("时钟",ImageIO.read(new File("image/时钟.png")));
			labClock.putAt(50, 50, this);
			
			labDraw = new ToIcon("绘图板",ImageIO.read(new File("image/绘图板.png")));
			labDraw.putAt(150, 50, this);
	
			labSnake = new ToIcon("贪吃蛇",ImageIO.read(new File("image/贪吃蛇.png")));
			labSnake.putAt(250, 50, this);
			
			labFileBrows = new ToIcon("文件浏览",ImageIO.read(new File("image/文件浏览.jpg")));
			labFileBrows.putAt(50, 150, this);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//设置图标对应的应用窗口
		labClock.setLinkWindow(new ClockFrame());
		labDraw.setLinkWindow(new DrawFrame());
		labSnake.setLinkWindow(new SnakeFrame());
		labFileBrows.setLinkWindow(new FileBrowseFrame());
	}
	

}















