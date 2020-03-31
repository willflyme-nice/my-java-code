import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ImageCodeFrame extends JFrame{
	JLabel lab1=new JLabel("�û�����");
	JLabel lab2=new JLabel("���룺");
	JLabel lab3=new JLabel("��֤�룺");
	JTextField tf1=new JTextField();
	JTextField tf2=new JTextField();
	JTextField tf3=new JTextField();
	JButton button1=new JButton("��һ��");
	JButton button2=new JButton("��¼");
	JButton button3=new JButton("����");
	ImageCodePanel codePane=new ImageCodePanel();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImageCodeFrame that=new ImageCodeFrame();
		that.go();
	}
	void go() {
		setTitle("ͼƬ��֤��");
		setResizable(false);
		
		JPanel pane=new JPanel();
		pane.setLayout(null);
		lab1.setBounds(30,20,60,20);
		pane.add(lab1);
		lab2.setBounds(30,70,60,20);
		pane.add(lab2);
		lab3.setBounds(30,120,60,20);
		pane.add(lab3);
		tf1.setBounds(120,20,200,20);
		pane.add(tf1);
		tf2.setBounds(120,70,200,20);
		pane.add(tf2);
		tf3.setBounds(120,120,70,20);
		pane.add(tf3);
		codePane.setBounds(195,115,84,30);
		pane.add(codePane);
		button1.setBounds(285,120,80,25);
		pane.add(button1);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				codePane.drawAgain();
			}
		});
		button2.setBounds(110,170,80,25);
		pane.add(button2);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ifo;
				if(tf1.getText().isEmpty()||tf2.getText().isEmpty()) {
					ifo="�û��������벻��Ϊ�գ�";
				}
				else if(!tf3.getText().equals(codePane.getCode())) {
					ifo="��֤�����";
				}
				else if(tf1.getText().equals("1234")&&tf2.getText().equals("1234")) {
					ifo="��¼�ɹ�!";					
				}
				else {
					ifo="�û������������!";
				}
				JOptionPane.showMessageDialog(null, ifo,"��ʾ",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		button3.setBounds(220,170,80,25);
		pane.add(button3);
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
			}
		});
		
		
		add(pane);
		
		setSize(400,250);
		setLocation(400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	class ImageCodePanel extends JPanel{
		BufferedImage img;
		Image image;
		StringBuffer code;//��¼��֤��
		Random ran=new Random();
		public void paint(Graphics g) {
			super.paint(g);
			//�汳��ͼƬ
			img=new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_BGR);
			Graphics gs= img.getGraphics();
			try {
				image=ImageIO.read(new File("image//image3.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gs.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
			//�������
			int x1=ran.nextInt(10)+4;
			int y1=ran.nextInt(10)+3;
			int x2=ran.nextInt(20)+30;
			int y2=ran.nextInt(10)+15;
			int x3=ran.nextInt(10)+70;
			int y3=ran.nextInt(10)+5;
			gs.setColor(Color.blue);
			gs.drawLine(x1, y1, x2, y2);
			gs.setColor(Color.RED);
			gs.drawLine(x2, y2,x3,y3);
			//���������ĸ
			Graphics2D gs2d=(Graphics2D)gs;
			Font font=new Font("����",Font.BOLD,15);
			code=new StringBuffer();
			for(int i=0;i<4;i++) {
				Color color=new Color(ran.nextInt(120),ran.nextInt(120),ran.nextInt(120));
				gs2d.setColor(color);
				AffineTransform trans=new  AffineTransform();
				trans.rotate(Math.toRadians(ran.nextInt(45)),i*16+8,7);//��ת
				float scaleSize=ran.nextFloat()+0.8f;
				if(scaleSize>1.2f) {
					scaleSize=1.2f;
				}
				trans.scale(scaleSize, scaleSize); //����
				gs2d.setTransform(trans);
				String word=""+(char)(ran.nextInt(26)+65);
				code.append(word);
				gs2d.drawString(word,this.getWidth()*i/5+5,this.getHeight()/2);
			}
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
		//Ҫ�õ��ķ���
		public void drawAgain() {
			this.repaint();
		}
		public String getCode() {
			return code.toString();
		}
	}
	

}










