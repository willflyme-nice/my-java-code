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
 * ���Ϻ�
 */
public class ColorPanel {
	private JPanel colorPanel = new JPanel(new GridLayout(2,10,2,2));
	private JLabel[] colors = new JLabel[20]; //20����ɫѡ��
	private JLabel selectedColor = colors[0]; //��ѡ�е�ɫ��
	private JLabel enteredColor;  //��꾭����ɫ��
	
	private DrawContext drawContext;
	
	//���캯���н������Ϻл�������
	public ColorPanel(DrawContext drawContext) {
		this.drawContext = drawContext;
		for(int i = 0;i<20;i++) {
			colors[i] = new JLabel();
			colors[i].setOpaque(true);
			colors[i].setBackground(Color.white);
			colors[i].setPreferredSize(new Dimension(25,25)); //ɫ���С
			colorPanel.add(colors[i]);
		}
		colorPanel.setBorder(BorderFactory.createTitledBorder("��ɫ"));
		setColors();
		addClickListener();
	}
	
	//Ϊ���Ϻз�����ɫ
	public void setColors() {
		Random ran = new Random();
		for(JLabel color : colors) {
			color.setBackground(new Color(ran.nextInt(255),ran.nextInt(255),ran.nextInt(255)));
		}
	}
	
	//Ϊ���Ϻ���ӵ���¼�
	public void addClickListener() {
		MouseAdapter listener = new MouseAdapter(){  //�����ڲ����������������
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
			color.addMouseListener(listener);   //���ü�����
		}			
	}
	
	//��Ӹ����ϰ嵽������
	public void addTo(JPanel parent) {
		parent.add(colorPanel);
	}
		
}
















