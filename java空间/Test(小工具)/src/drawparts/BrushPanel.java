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
	
	private JLabel[] brushs = new JLabel[5]; //5���߿�
	private JPanel brushPanel =  new JPanel(new GridLayout(5,1,2,1)); 
	private JLabel selectedBrush = brushs[0]; //��ѡ�е��߿�
	
	public BrushPanel(DrawContext drawContext) {
		this.drawContext = drawContext;
		for(int i=0;i<5;i++) {
			brushs[i] = new JLabel();
			brushs[i].setOpaque(true);
			brushs[i].setBackground(Color.white);
			brushs[i].setPreferredSize(new Dimension(100,10)); //��ˢ��С
			brushs[i].setText(i+1+"");
			brushPanel.add(brushs[i]);
		}
		brushPanel.setBorder(BorderFactory.createTitledBorder("�߿�"));
		addClickListener();  //Ϊ��ˢ����ӵ���¼�
	}
	
	//Ϊ��ˢ�з����߿�
//	public void setBrushs() {
//		for(int i=0;i<5;i++) {
//			Graphics2D g2d = (Graphics2D)brushs[i].getGraphics();
//			System.out.println(brushs[i]);
//			g2d.setStroke(new BasicStroke(2));
//			g2d.setColor(Color.black);
//			g2d.drawLine(0, 4, 80, 4);
//		}
//	}
	
	//Ϊ��ˢ����ӵ���¼�
	public void addClickListener() {
		MouseAdapter listener = new MouseAdapter(){  //���ü�����
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
	
		
	//��Ӹ����ϰ嵽������
	public void addTo(JPanel parent) {
		parent.add(brushPanel);
	}

}
