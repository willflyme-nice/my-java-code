package drawparts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * 橡皮擦
 */
public class EraserPanel {
	private JPanel eraserPanel = new JPanel(new GridLayout(2,2,4,4));
	private JLabel[] erasers = new JLabel[4]; //4种规格
	private JLabel selectedEraser = erasers[0]; //被选中的橡皮
	
	private DrawContext drawContext;
	
	//构造函数中进行颜料盒基本布局
	public EraserPanel(DrawContext drawContext) {
		this.drawContext = drawContext;
		for(int i = 0;i<4;i++) {
			erasers[i] = new JLabel();
			erasers[i].setOpaque(true);
			erasers[i].setBackground(Color.white);
			erasers[i].setPreferredSize(new Dimension(30,30)); //橡皮擦大小
			eraserPanel.add(erasers[i]);
		}
		eraserPanel.setBorder(BorderFactory.createTitledBorder("橡皮擦"));
		setErasers();
		addClickListener();
	}
	
	//为橡皮擦分配规格
	public void setErasers() {
		Font font = new Font("新宋体", Font.PLAIN, 25);
		for(int i=0;i<4;i++) {
			erasers[i].setAlignmentX(JLabel.CENTER_ALIGNMENT);
			erasers[i].setText(i*10+10+"");
		}
	}
	
	//为橡皮擦添加点击事件
	public void addClickListener() {
		MouseAdapter listener = new MouseAdapter(){  //创建内部匿名监听器类对象
			public void mouseClicked(MouseEvent e) {
				JLabel source = (JLabel)e.getSource();
				drawContext.setEraser(Integer.parseInt(source.getText()));
				drawContext.useEraser();
				//设置选中样式
				if(selectedEraser!=null) {
					selectedEraser.setBorder(null);
				}
				source.setBorder(new LineBorder(Color.gray, 1));
				selectedEraser = source;	
			}
		};
		for(JLabel eraser : erasers) {
			eraser.addMouseListener(listener);   //设置监听器
		}			
	}
	
	//添加该颜料板到父容器
	public void addTo(JPanel parent) {
		parent.add(eraserPanel);
	}
}
