package drawparts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class BrushPanel {
	DrawContext drawContext;
	
	private JLabel[] brushs = new JLabel[5]; //5种线宽
	private JPanel brushPanel =  new JPanel(new GridLayout(5,1,2,1)); 
	private JLabel selectedBrush = brushs[0]; //被选中的线宽
	
	public BrushPanel(DrawContext drawContext) {
		this.drawContext = drawContext;
		for(int i=0;i<5;i++) {
			brushs[i] = new JLabel();
			brushs[i].setOpaque(true);
			brushs[i].setBackground(Color.white);
			brushs[i].setPreferredSize(new Dimension(100,10)); //笔刷大小
			brushs[i].setText(i+1+"");
			brushPanel.add(brushs[i]);
		}
		brushPanel.setBorder(BorderFactory.createTitledBorder("线宽"));
		addClickListener();  //为笔刷盒添加点击事件
	}
	
	//为笔刷盒分配线宽
//	public void setBrushs() {
//		for(int i=0;i<5;i++) {
//			Graphics2D g2d = (Graphics2D)brushs[i].getGraphics();
//			System.out.println(brushs[i]);
//			g2d.setStroke(new BasicStroke(2));
//			g2d.setColor(Color.black);
//			g2d.drawLine(0, 4, 80, 4);
//		}
//	}
	
	//为笔刷盒添加点击事件
	public void addClickListener() {
		MouseAdapter listener = new MouseAdapter(){  //设置监听器
			public void mouseClicked(MouseEvent e) {
				JLabel source = (JLabel)e.getSource();
				drawContext.setBrush(Integer.parseInt(source.getText()));
				if(selectedBrush!=null) {
					selectedBrush.setBorder(null);
				}
				source.setBorder(new LineBorder(Color.gray, 1));
				selectedBrush = source;	
			}
		};
		for(JLabel brush : brushs) {
			brush.addMouseListener(listener);   
		}			
	}
	
		
	//添加该颜料板到父容器
	public void addTo(JPanel parent) {
		parent.add(brushPanel);
	}

}
