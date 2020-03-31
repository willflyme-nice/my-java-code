import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class ImageMatchingFrame extends JFrame{
	JLabel icon[]=new JLabel[3];
	JLabel target[]=new JLabel[3];
	ImageIcon img[]=new ImageIcon[3];
	JPanel pane; //玻璃面板
	Point pressPoint; //记录鼠标按下时的位置
	
	public static void main(String[] args) {
		ImageMatchingFrame that=new ImageMatchingFrame();
		that.go();
		that.setVisible(true);
	}
	private void go() {
		setTitle("图片配对游戏");
		setSize(500,400);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//设置玻璃面板
		pane=new JPanel();
		setGlassPane(pane);
		pane.setLayout(null);
		pane.setVisible(true); //可见
		pane.setOpaque(false); //透明
		
		//设置玻璃面板上的图片标签
		img[0]=new ImageIcon("image//matching//bike.png");
		img[1]=new ImageIcon("image//matching//clothing.png");
		img[2]=new ImageIcon("image//matching//screen.png");
		for(int i=0;i<3;i++) {
			icon[i]=new JLabel();
			pane.add(icon[i]);
			icon[i].setIcon(img[i]);
			icon[i].setBorder(new LineBorder(Color.black));//设置线边框
			icon[i].setSize(50,50); //设置大小
			int x=(int)(Math.random()*(getWidth()-50));
			int y=(int)(Math.random()*(getHeight()-200));
			icon[i].setLocation(x,y); //设置位置
			//添加监听器
			icon[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e){
					pressPerform(e);
				}
				public void mouseReleased(MouseEvent e) {
					releasePerform(e);
				}
			});
			icon[i].addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e){
					dragPerform(e);
				}
			});
		}
		
		//设置底部标签方块
		JPanel bottomPane=new JPanel();
		getContentPane().add(bottomPane,"South");
		bottomPane.setLayout(new FlowLayout(FlowLayout.CENTER,60,5));
		for(int i=0;i<3;i++) {
			target[i]=new JLabel();
			bottomPane.add(target[i]);
			target[i].setPreferredSize(new Dimension(80,80));
			target[i].setBackground(Color.orange);
			target[i].setOpaque(true); //设置背景不透明
			target[i].setHorizontalAlignment(JLabel.CENTER); //设置标签内容水平居中
			target[i].setVerticalTextPosition(SwingConstants.BOTTOM); //设置标签文字相对于图片的垂直位置
			target[i].setHorizontalTextPosition(SwingConstants.CENTER);//设置标签文字相对于图片的水平位置
			
		}
		target[0].setText("自行车");
		target[1].setText("衣服");
		target[2].setText("显示器");
		
		//设置“再玩一次”按钮
		JButton button=new JButton("再玩一次");
		button.setBounds(400,5,90,30);
		getLayeredPane().add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<3;i++) {
					int x=(int)(Math.random()*(getWidth()-50));
					int y=(int)(Math.random()*(getHeight()-200));
					icon[i].setLocation(x,y); //设置位置
					target[i].setBackground(Color.orange);
					target[i].setIcon(null);
				}
				target[0].setText("自行车"); 
				target[1].setText("衣服");
				target[2].setText("显示器");
				getGlassPane().setVisible(true);
				
			}
		});
		
	}
	private void pressPerform(MouseEvent e) { //鼠标按下事件
		pressPoint=e.getPoint();
	}
	private void releasePerform(MouseEvent e) { //鼠标释放事件
		if(isMatching()) { //如果全部匹配的话
			getGlassPane().setVisible(false);
			for(int i=0;i<3;i++) {
				target[i].setIcon(img[i]);
				target[i].setText("匹配成功");
			}
			
		}
	}
	private void dragPerform(MouseEvent e) { //鼠标拖动事件
		JLabel source=(JLabel)e.getSource();
		int x=source.getX()+e.getX()-pressPoint.x;
		int y=source.getY()+e.getY()-pressPoint.y;
		source.setLocation(x,y);
	}
	private boolean isMatching() { //判断是否匹配成功
		boolean result=true;
		for(int i=0;i<3;i++) {
			int x=icon[i].getLocationOnScreen().x;
			int y=icon[i].getLocationOnScreen().y;
			int tx=target[i].getLocationOnScreen().x;
			int ty=target[i].getLocationOnScreen().y;
		
			Color bg=Color.green;
			if(x <tx || y < ty|| x > tx+30 || y > ty+30) {
				bg=Color.orange;
				result=false;
			}
			target[i].setBackground(bg);
			System.out.println();
		}
		return result;
	}

}
