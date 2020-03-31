import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

public class DrawFlower extends JFrame{
	private DrawFlowerPanel pane=new DrawFlowerPanel();
	private FlowerLabel flower1=new FlowerLabel(new ImageIcon("image//drawFlower//flower1.png"),FlowerLabel.RIGHT_BOTTOM);
	private FlowerLabel flower2=new FlowerLabel(new ImageIcon("image//drawFlower//flower2.png"),FlowerLabel.CENTER);
	private FlowerLabel flower3=new FlowerLabel(new ImageIcon("image//drawFlower//flower3.png"),FlowerLabel.RIGHT_TOP);
	private FlowerLabel flower4=new FlowerLabel(new ImageIcon("image//drawFlower//flower4.png"),FlowerLabel.CENTER);
	
	private FlowerLabel brush=flower2; //画笔样式，默认为flawer2
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrawFlower that=new DrawFlower();
		that.go();
		that.setVisible(true);
	}
	private void go() {
		setTitle("画梅花");
		setSize(800,560);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//组件布局
		flower2.setSelected(true);
		add(pane);
		flower1.setLocation(30,210);
		pane.add(flower1);
		flower2.setLocation(80,200);
		pane.add(flower2);
		flower3.setLocation(30,270);
		pane.add(flower3);
		flower4.setLocation(80,260);
		pane.add(flower4);
		
		//添加事件
		LabelListener labListener=new LabelListener();
		flower1.addMouseListener(labListener);
		flower2.addMouseListener(labListener);
		flower3.addMouseListener(labListener);
		flower4.addMouseListener(labListener);
		PaneListener paneListener=new PaneListener();
		pane.addMouseListener(paneListener);
		
	}
	//标签监听类
	class LabelListener extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			FlowerLabel source=(FlowerLabel)e.getSource();
			flower1.setSelected(false);
			flower2.setSelected(false);
			flower3.setSelected(false);
			flower4.setSelected(false);
			source.setSelected(true);
			brush=source;
		}
	}
	//面板监听类
	class PaneListener extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			int mousebutton=e.getButton();
			Point p=e.getPoint();
			if(mousebutton==e.BUTTON1) {
				FlowerLabel flower=brush.creat();
				flower.drawAt(p);
				pane.add(flower);
				pane.repaint();  //调用面板的repaint才能够显示
			}
			else if(mousebutton==e.BUTTON3) {
				Component comp=pane.getComponentAt(p);
				if(comp instanceof FlowerLabel) {
					pane.remove(comp);
					pane.repaint();  //调用面板的repaint才能够显示
				}
			}
			
		}
	}
	
}
class FlowerLabel extends JLabel{
	public static final int CENTER=0;      //指定中心为基点
	public static final int RIGHT_TOP=1;	 //指定右上为基点
	public static final int RIGHT_BOTTOM=2;//指定右下为基点
	
	private ImageIcon icon;
	private int width,height;
	private int basePoint; //基点
	private LineBorder border=new LineBorder(Color.black);
	
	FlowerLabel(ImageIcon icon,int basePoint){
		this.icon=icon;
		this.basePoint=basePoint;
		setIcon(icon);
		width=icon.getIconWidth();
		height=icon.getIconHeight();
		setSize(width,height);
	}
	public void setSelected(boolean f) {
		if(f==true)
			setBorder(border);
		else
			setBorder(null);
	}
	public FlowerLabel creat() {
		return new FlowerLabel(icon,basePoint);
	}
	public void drawAt(Point p) {
		int x=0,y=0;
		switch(basePoint) {
			case CENTER:
				x=p.x-width/2;
				y=p.y-height/2;
				break;
			case RIGHT_TOP:
				x=p.x-width;
				y=p.y;
				break;
			case RIGHT_BOTTOM:
				x=p.x-width;
				y=p.y-height;
				break;
		}
		setLocation(x,y);	
	}
}
class DrawFlowerPanel extends JPanel{
	private Image img;
	DrawFlowerPanel(){
		ImageIcon icon=new ImageIcon("image//drawFlower//background.jpg");
		img=icon.getImage();
		setLayout(null);
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}