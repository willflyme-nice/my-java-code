package MyTools;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import drawparts.BrushPanel;
import drawparts.ColorPanel;
import drawparts.DrawContext;
import drawparts.DrawPanel;
import drawparts.EraserPanel;
import drawparts.ShapePanel;

/**
 * 绘图窗口程序
 */
public class DrawFrame extends JFrame{
	
	private final int frameWidth = 700;
	private final int frameHeight = 500;
	
	private DrawContext drawContext = new DrawContext();            //绘图上下文
	private EraserPanel eraserPanel = new EraserPanel(drawContext); //橡皮擦
	private BrushPanel brushPanel = new BrushPanel(drawContext);    //笔刷盒
	private ColorPanel colorPanel = new ColorPanel(drawContext);    //颜料盒
	private DrawPanel drawPanel = new DrawPanel(drawContext);       //绘图区
	private ShapePanel shapePanel = new ShapePanel(drawContext);    //形状盒
	
	public DrawFrame() {
		setSize(frameWidth,frameHeight);
		setLocation(430,50);
		setTitle("绘画板");
		setResizable(false);
		
		setLayout(new BorderLayout());
		add(drawPanel); //加入绘图区
		
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 5)); 
		eraserPanel.addTo(topPanel);//添加橡皮擦到工具栏
		brushPanel.addTo(topPanel); //添加笔刷盒到工具栏
		colorPanel.addTo(topPanel); //添加颜料盒到工具栏
		shapePanel.addTo(topPanel); //添加形状盒到工具栏
		add(topPanel,"North");   //加入工具栏
		((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(5,5,5,5)); //设置窗口边框
		
	}
	
	public DrawPanel getDrawPanel() {
		return drawPanel;
	}
	
	
}










