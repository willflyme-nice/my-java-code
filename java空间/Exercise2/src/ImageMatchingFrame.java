import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class ImageMatchingFrame extends JFrame{
	JLabel icon[]=new JLabel[3];
	JLabel target[]=new JLabel[3];
	ImageIcon img[]=new ImageIcon[3];
	JPanel pane; //�������
	Point pressPoint; //��¼��갴��ʱ��λ��
	
	public static void main(String[] args) {
		ImageMatchingFrame that=new ImageMatchingFrame();
		that.go();
		that.setVisible(true);
	}
	private void go() {
		setTitle("ͼƬ�����Ϸ");
		setSize(500,400);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//���ò������
		pane=new JPanel();
		setGlassPane(pane);
		pane.setLayout(null);
		pane.setVisible(true); //�ɼ�
		pane.setOpaque(false); //͸��
		
		//���ò�������ϵ�ͼƬ��ǩ
		img[0]=new ImageIcon("image//matching//bike.png");
		img[1]=new ImageIcon("image//matching//clothing.png");
		img[2]=new ImageIcon("image//matching//screen.png");
		for(int i=0;i<3;i++) {
			icon[i]=new JLabel();
			pane.add(icon[i]);
			icon[i].setIcon(img[i]);
			icon[i].setBorder(new LineBorder(Color.black));//�����߱߿�
			icon[i].setSize(50,50); //���ô�С
			int x=(int)(Math.random()*(getWidth()-50));
			int y=(int)(Math.random()*(getHeight()-200));
			icon[i].setLocation(x,y); //����λ��
			//��Ӽ�����
			icon[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e){
					pressPerform(e);
				}
				public void mouseReleased(MouseEvent e) {
					releasePerform(e);
				}
			});
			icon[i].addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e){
					dragPerform(e);
				}
			});
		}
		
		//���õײ���ǩ����
		JPanel bottomPane=new JPanel();
		getContentPane().add(bottomPane,"South");
		bottomPane.setLayout(new FlowLayout(FlowLayout.CENTER,60,5));
		for(int i=0;i<3;i++) {
			target[i]=new JLabel();
			bottomPane.add(target[i]);
			target[i].setPreferredSize(new Dimension(80,80));
			target[i].setBackground(Color.orange);
			target[i].setOpaque(true); //���ñ�����͸��
			target[i].setHorizontalAlignment(JLabel.CENTER); //���ñ�ǩ����ˮƽ����
			target[i].setVerticalTextPosition(SwingConstants.BOTTOM); //���ñ�ǩ���������ͼƬ�Ĵ�ֱλ��
			target[i].setHorizontalTextPosition(SwingConstants.CENTER);//���ñ�ǩ���������ͼƬ��ˮƽλ��
			
		}
		target[0].setText("���г�");
		target[1].setText("�·�");
		target[2].setText("��ʾ��");
		
		//���á�����һ�Ρ���ť
		JButton button=new JButton("����һ��");
		button.setBounds(400,5,90,30);
		getLayeredPane().add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<3;i++) {
					int x=(int)(Math.random()*(getWidth()-50));
					int y=(int)(Math.random()*(getHeight()-200));
					icon[i].setLocation(x,y); //����λ��
					target[i].setBackground(Color.orange);
					target[i].setIcon(null);
				}
				target[0].setText("���г�"); 
				target[1].setText("�·�");
				target[2].setText("��ʾ��");
				getGlassPane().setVisible(true);
				
			}
		});
		
	}
	private void pressPerform(MouseEvent e) { //��갴���¼�
		pressPoint=e.getPoint();
	}
	private void releasePerform(MouseEvent e) { //����ͷ��¼�
		if(isMatching()) { //���ȫ��ƥ��Ļ�
			getGlassPane().setVisible(false);
			for(int i=0;i<3;i++) {
				target[i].setIcon(img[i]);
				target[i].setText("ƥ��ɹ�");
			}
			
		}
	}
	private void dragPerform(MouseEvent e) { //����϶��¼�
		JLabel source=(JLabel)e.getSource();
		int x=source.getX()+e.getX()-pressPoint.x;
		int y=source.getY()+e.getY()-pressPoint.y;
		source.setLocation(x,y);
	}
	private boolean isMatching() { //�ж��Ƿ�ƥ��ɹ�
		boolean result=true;
		for(int i=0;i<3;i++) {
			int x=icon[i].getLocationOnScreen().x;
			int y=icon[i].getLocationOnScreen().y;
			int tx=target[i].getLocationOnScreen().x;
			int ty=target[i].getLocationOnScreen().y;
		
			Color bg=Color.green;
			if(x <tx || y < ty|| x > tx+30 || y > ty+30) {
				bg=Color.orange;
				result=false;
			}
			target[i].setBackground(bg);
			System.out.println();
		}
		return result;
	}

}
