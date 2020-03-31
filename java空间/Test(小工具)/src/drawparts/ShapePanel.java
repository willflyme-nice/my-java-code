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
 * ��ͼ��״ѡ�����
 */
public class ShapePanel {
	private JPanel shapePanel = new JPanel(new GridLayout(2,3,4,4));
	private JLabel[] shapes = new JLabel[5]; //5�ֻ�ͼ��״
	private String[] shapesText = {"����״","ֱ��","����","Բ��","��Բ"}; //ѡ������
	private JLabel selectedShape = shapes[0]; //��ѡ�е���״
	
	private DrawContext drawContext;
	
	//���캯���н������Ϻл�������
	public ShapePanel(DrawContext drawContext) {
		this.drawContext = drawContext;
		for(int i = 0;i<5;i++) {
			shapes[i] = new JLabel();
			shapes[i].setOpaque(true);
			shapes[i].setPreferredSize(new Dimension(40,20)); //ѡ���С
			shapePanel.add(shapes[i]);
		}
		shapePanel.setBorder(BorderFactory.createTitledBorder("��ͼ��״"));
		setShapes();
		addClickListener();
	}
	
	//Ϊѡ�����������Ϣ
	public void setShapes() {
		Font font = new Font("������", Font.PLAIN, 25);
		for(int i=0;i<5;i++) {
			shapes[i].setText(shapesText[i]);
		}
	}
	
	//Ϊѡ����ӵ���¼�
	public void addClickListener() {
		MouseAdapter listener = new MouseAdapter(){  //�����ڲ����������������
			public void mouseClicked(MouseEvent e) {
				JLabel source = (JLabel)e.getSource();
				switch (source.getText()) {
				case "����״":
					drawContext.useNone();
					break;
				case "ֱ��":
					drawContext.useLine();
					break;
				case "����":
					drawContext.useRect();
					break;
				case "Բ��":
					drawContext.useCircle();
					break;
				case "��Բ":
					drawContext.useArc();
					break;
				default:
					System.out.println("����û��ѡ���κλ�ͼ��״..................");
					break;
				}
				//����ѡ����ʽ
				if(selectedShape!=null) {
					selectedShape.setBorder(null);
				}
				source.setBorder(new LineBorder(Color.gray, 1));
				selectedShape = source;	
			}
			
		};
		for(JLabel shape : shapes) {
			shape.addMouseListener(listener);   //���ü�����
		}			
	}
	
	//��Ӹ����ϰ嵽������
	public void addTo(JPanel parent) {
		parent.add(shapePanel);
	}
}
