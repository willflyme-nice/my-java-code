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
 * ��ͼ���ڳ���
 */
public class DrawFrame extends JFrame{
	
	private final int frameWidth = 700;
	private final int frameHeight = 500;
	
	private DrawContext drawContext = new DrawContext();            //��ͼ������
	private EraserPanel eraserPanel = new EraserPanel(drawContext); //��Ƥ��
	private BrushPanel brushPanel = new BrushPanel(drawContext);    //��ˢ��
	private ColorPanel colorPanel = new ColorPanel(drawContext);    //���Ϻ�
	private DrawPanel drawPanel = new DrawPanel(drawContext);       //��ͼ��
	private ShapePanel shapePanel = new ShapePanel(drawContext);    //��״��
	
	public DrawFrame() {
		setSize(frameWidth,frameHeight);
		setLocation(430,50);
		setTitle("�滭��");
		setResizable(false);
		
		setLayout(new BorderLayout());
		add(drawPanel); //�����ͼ��
		
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 5)); 
		eraserPanel.addTo(topPanel);//�����Ƥ����������
		brushPanel.addTo(topPanel); //��ӱ�ˢ�е�������
		colorPanel.addTo(topPanel); //������Ϻе�������
		shapePanel.addTo(topPanel); //�����״�е�������
		add(topPanel,"North");   //���빤����
		((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(5,5,5,5)); //���ô��ڱ߿�
		
	}
	
	public DrawPanel getDrawPanel() {
		return drawPanel;
	}
	
	
}










