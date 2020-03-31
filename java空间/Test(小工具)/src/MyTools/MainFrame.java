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
	
	//������ ���캯��
	public MainFrame() {
		setSize(frameWidth,frameHeight);
		setResizable(false);
		setLocation(10,10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("�ҵ�С���߼�");
	}
	
	//���ý���UI
	private void setUI() {
		setLayout(null);
		
		try {
			labClock = new ToIcon("ʱ��",ImageIO.read(new File("image/ʱ��.png")));
			labClock.putAt(50, 50, this);
			
			labDraw = new ToIcon("��ͼ��",ImageIO.read(new File("image/��ͼ��.png")));
			labDraw.putAt(150, 50, this);
	
			labSnake = new ToIcon("̰����",ImageIO.read(new File("image/̰����.png")));
			labSnake.putAt(250, 50, this);
			
			labFileBrows = new ToIcon("�ļ����",ImageIO.read(new File("image/�ļ����.jpg")));
			labFileBrows.putAt(50, 150, this);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//����ͼ���Ӧ��Ӧ�ô���
		labClock.setLinkWindow(new ClockFrame());
		labDraw.setLinkWindow(new DrawFrame());
		labSnake.setLinkWindow(new SnakeFrame());
		labFileBrows.setLinkWindow(new FileBrowseFrame());
	}
	

}















