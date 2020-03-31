package drawparts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


/**
 * 绘图形状选择面板
 */
public class ShapePanel {
	private JPanel shapePanel = new JPanel(new GridLayout(2,3,4,4));
	private JLabel[] shapes = new JLabel[5]; //5种绘图形状
	private String[] shapesText = {"无形状","直线","矩形","圆形","椭圆"}; //选项文字
	private JLabel selectedShape = shapes[0]; //被选中的形状
	
	private DrawContext drawContext;
	
	//构造函数中进行颜料盒基本布局
	public ShapePanel(DrawContext drawContext) {
		this.drawContext = drawContext;
		for(int i = 0;i<5;i++) {
			shapes[i] = new JLabel();
			shapes[i].setOpaque(true);
			shapes[i].setPreferredSize(new Dimension(40,20)); //选项大小
			shapePanel.add(shapes[i]);
		}
		shapePanel.setBorder(BorderFactory.createTitledBorder("绘图形状"));
		setShapes();
		addClickListener();
	}
	
	//为选项分配描述信息
	public void setShapes() {
		Font font = new Font("新宋体", Font.PLAIN, 25);
		for(int i=0;i<5;i++) {
			shapes[i].setText(shapesText[i]);
		}
	}
	
	//为选项添加点击事件
	public void addClickListener() {
		MouseAdapter listener = new MouseAdapter(){  //创建内部匿名监听器类对象
			public void mouseClicked(MouseEvent e) {
				JLabel source = (JLabel)e.getSource();
				switch (source.getText()) {
				case "无形状":
					drawContext.useNone();
					break;
				case "直线":
					drawContext.useLine();
					break;
				case "矩形":
					drawContext.useRect();
					break;
				case "圆形":
					drawContext.useCircle();
					break;
				case "椭圆":
					drawContext.useArc();
					break;
				default:
					System.out.println("错误：没有选择任何绘图形状..................");
					break;
				}
				//设置选中样式
				if(selectedShape!=null) {
					selectedShape.setBorder(null);
				}
				source.setBorder(new LineBorder(Color.gray, 1));
				selectedShape = source;	
			}
			
		};
		for(JLabel shape : shapes) {
			shape.addMouseListener(listener);   //设置监听器
		}			
	}
	
	//添加该颜料板到父容器
	public void addTo(JPanel parent) {
		parent.add(shapePanel);
	}
}
