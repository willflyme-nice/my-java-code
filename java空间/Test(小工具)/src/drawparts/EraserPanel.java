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
 * ��Ƥ��
 */
public class EraserPanel {
	private JPanel eraserPanel = new JPanel(new GridLayout(2,2,4,4));
	private JLabel[] erasers = new JLabel[4]; //4�ֹ��
	private JLabel selectedEraser = erasers[0]; //��ѡ�е���Ƥ
	
	private DrawContext drawContext;
	
	//���캯���н������Ϻл�������
	public EraserPanel(DrawContext drawContext) {
		this.drawContext = drawContext;
		for(int i = 0;i<4;i++) {
			erasers[i] = new JLabel();
			erasers[i].setOpaque(true);
			erasers[i].setBackground(Color.white);
			erasers[i].setPreferredSize(new Dimension(30,30)); //��Ƥ����С
			eraserPanel.add(erasers[i]);
		}
		eraserPanel.setBorder(BorderFactory.createTitledBorder("��Ƥ��"));
		setErasers();
		addClickListener();
	}
	
	//Ϊ��Ƥ��������
	public void setErasers() {
		Font font = new Font("������", Font.PLAIN, 25);
		for(int i=0;i<4;i++) {
			erasers[i].setAlignmentX(JLabel.CENTER_ALIGNMENT);
			erasers[i].setText(i*10+10+"");
		}
	}
	
	//Ϊ��Ƥ����ӵ���¼�
	public void addClickListener() {
		MouseAdapter listener = new MouseAdapter(){  //�����ڲ����������������
			public void mouseClicked(MouseEvent e) {
				JLabel source = (JLabel)e.getSource();
				drawContext.setEraser(Integer.parseInt(source.getText()));
				drawContext.useEraser();
				//����ѡ����ʽ
				if(selectedEraser!=null) {
					selectedEraser.setBorder(null);
				}
				source.setBorder(new LineBorder(Color.gray, 1));
				selectedEraser = source;	
			}
		};
		for(JLabel eraser : erasers) {
			eraser.addMouseListener(listener);   //���ü�����
		}			
	}
	
	//��Ӹ����ϰ嵽������
	public void addTo(JPanel parent) {
		parent.add(eraserPanel);
	}
}
