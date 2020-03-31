package MyTools;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ToIcon extends JPanel{
	private final int lineSize = 60;  //图框的大小
	private final int imgSize = 55;  //图案的大小
	private final int fontSize = 14; //字体的大小
	private final int lineHeiht = 30; //字体行高
	
	private Color color = new Color(153,217,234,0);
	private Color bcolor = new Color(153,217,234,200);
	
	private Image img;
	private JLabel nameLabel;
	
	public ToIcon(String name,Image img) {
		this.img = img;
	
		setSize(lineSize,lineSize+fontSize+20);
		setLayout(null);
		
		nameLabel = new JLabel(name,JLabel.CENTER);
		nameLabel.setFont(new Font("新宋体",Font.PLAIN,fontSize));
		nameLabel.setBounds(0,lineSize,lineSize,lineHeiht); 
		add(nameLabel);
		
		setMouseOver();  //设置鼠标经过时的样式
	}
	
	//绘图
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		RoundRectangle2D.Double rect = new RoundRectangle2D.Double(0,0,lineSize-1,lineSize-1,10,10);
		g2d.setColor(color);
		g2d.fill(rect);
		g2d.setColor(bcolor);
		g2d.draw(rect);
		
		g2d.drawImage(img, (lineSize-imgSize)/2, (lineSize-imgSize)/2, imgSize, imgSize, this);
		
	}
	
	//设置鼠标经过时的转态
	private void setMouseOver() {
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				try {
					Image img = ImageIO.read(new File("image/cursor.png"));
					Cursor cursor = getToolkit().createCustomCursor(img, new Point(), "mouseOver");
					setCursor(cursor);
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
				color = new Color(153,217,234,80);
				repaint();
			}
			public void mouseExited(MouseEvent e) {
				color = new Color(153,217,234,0);
				repaint();
			}
		});
	}
	
	
	//设置边框
	private void setTBorder() {
		
	}
	
	//设置图标阴影
	private void setShadow() {
		
	}
	
	//放置图标
	public void putAt(int x, int y, JFrame parent) {
		setLocation(x,y);
		parent.add(this);
	}
	
	//设置图标链接到响应窗口
	public void setLinkWindow(JFrame window) {
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				window.setVisible(true);
			}
		});
	}
	
	
	
}





















