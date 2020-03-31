package drawparts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * 颜料盒
 */
public class ColorPanel {
	private JPanel colorPanel = new JPanel(new GridLayout(2,10,2,2));
	private JLabel[] colors = new JLabel[20]; //20种颜色选择
	private JLabel selectedColor = colors[0]; //被选中的色块
	private JLabel enteredColor;  //鼠标经过的色块
	
	private DrawContext drawContext;
	
	//构造函数中进行颜料盒基本布局
	public ColorPanel(DrawContext drawContext) {
		this.drawContext = drawContext;
		for(int i = 0;i<20;i++) {
			colors[i] = new JLabel();
			colors[i].setOpaque(true);
			colors[i].setBackground(Color.white);
			colors[i].setPreferredSize(new Dimension(25,25)); //色块大小
			colorPanel.add(colors[i]);
		}
		colorPanel.setBorder(BorderFactory.createTitledBorder("颜色"));
		setColors();
		addClickListener();
	}
	
	//为颜料盒分配颜色
	public void setColors() {
		Random ran = new Random();
		for(JLabel color : colors) {
			color.setBackground(new Color(ran.nextInt(255),ran.nextInt(255),ran.nextInt(255)));
		}
	}
	
	//为颜料盒添加点击事件
	public void addClickListener() {
		MouseAdapter listener = new MouseAdapter(){  //创建内部匿名监听器类对象
			public void mouseClicked(MouseEvent e) {
				JLabel source = (JLabel)e.getSource();
				drawContext.setColor(source.getBackground());
				if(selectedColor!=null) {
					selectedColor.setBorder(null);
				}
				source.setBorder(new LineBorder(Color.WHITE, 2));
				selectedColor = source;	
			}
		};
		for(JLabel color : colors) {
			color.addMouseListener(listener);   //设置监听器
		}			
	}
	
	//添加该颜料板到父容器
	public void addTo(JPanel parent) {
		parent.add(colorPanel);
	}
		
}
















