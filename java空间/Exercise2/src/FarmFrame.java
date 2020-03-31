import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FarmFrame extends JFrame{
	private FarmPanel pane=new FarmPanel();
	private ImageIcon[][] icons=new ImageIcon[5][2];
	private ImageIcon[] plantIcons=new ImageIcon[5];
	private JLabel[] labs=new JLabel[5];		   //5��ѡ��
	private PlantLabel plant=new PlantLabel(); //ֲ�����
	private int getNum=0;
	private JLabel getNumLab=new JLabel("�ջ��ʵ��"+getNum); //�ջ���ʾ
	private int growStage=0;             //�����׶Σ���ʼ��Ϊ���ޡ�
	private String[] growName=new String[]{"�ջ�","����","����","����","���"};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FarmFrame that=new FarmFrame();
		that.go();
		that.setVisible(true);
	}
	private void go() {
		setTitle("����ũ��");
		setSize(650,450);
		setResizable(false);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//�������
		String filename="";
		for(int i=0;i<10;i++) {
			switch(i) {
			case 0: filename="����.png";break;
			case 1: filename="����1.png";break;
			case 2: filename="����.png";break;
			case 3: filename="����1.png";break;
			case 4: filename="����.png";break;
			case 5: filename="����1.png";break;
			case 6: filename="���.png";break;
			case 7: filename="���1.png";break;
			case 8: filename="�ջ�.png";break;
			case 9: filename="�ջ�1.png";break;
			}
			icons[i/2][i%2]=new ImageIcon("image//farm//"+filename);
		}
		for(int i=0;i<5;i++) {
			labs[i]=new JLabel();
			labs[i].setIcon(icons[i][0]);
			labs[i].setBounds(50+i*100,340,60,60);
		}
		filename="";
		for(int i=0;i<4;i++) {
			switch(i) {
			case 0: filename="seed.png";break;
			case 1: filename="grow.png";break;
			case 2: filename="bloom.png";break;
			case 3: filename="fruit.png";break;
			}
			plantIcons[i]=new ImageIcon("image//farm//"+filename);
		}
		plantIcons[4]=null;  //���һ��ͼƬΪ��
		
		
		
		//�������
		add(pane);
		pane.add(labs[0]);
		pane.add(labs[1]);
		pane.add(labs[2]);
		pane.add(labs[3]);
		pane.add(labs[4]);
		pane.add(plant);
		getNumLab.setBounds(150,25,400,50);
		getNumLab.setFont(new Font("΢���ź�",Font.PLAIN,25));
		pane.add(getNumLab);
		
		//����¼�
		LabelListener labLis=new LabelListener();
		for(int i=0;i<5;i++) {
			labs[i].addMouseListener(labLis);
		}
		
		
		
	}
	//�¼�������
	class LabelListener extends MouseAdapter{
		private Point[] p=new Point[5];
		LabelListener() {
			super();
			p[0]=new Point(49,61);
			p[1]=new Point(50,65);
			p[2]=new Point(53,83);
			p[3]=new Point(57,82);
			p[4]=new Point(10,10);
		}
		public void mousePressed(MouseEvent e){
			JLabel lab=(JLabel)e.getSource();
			for(int i=0;i<5;i++) {
				if(lab==labs[i]) {
					if(growStage==i) {
						if(i<4)
							plant.setIcon(plantIcons[i]);
						else
							plant.setIcon(null);
						plant.setLocation(315, 205, p[i]);
						for(int j=0;j<5;j++) {
							labs[j].setIcon(icons[j][0]);
						}
						labs[i].setIcon(icons[i][1]);
						growStage=(i+1)%5;
						if(growStage==0) { //���ջ��ʵʱ������ʾ��Ϣ
							getNum+=10;
							getNumLab.setText("�ջ��ʵ��"+getNum);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "ֲ�ﵱǰ����"+growName[growStage]+"�׶Σ�Ӧ�Ƚ���"+growName[(growStage+1)%5]);
					}
				}
			}	 
		}
	}

	
}

class PlantLabel extends JLabel{
	private ImageIcon icon;
//	private int width=10;
//	private int height=10;
	public void setIcon(ImageIcon icon) {
		this.icon=icon;
		if(icon!=null) {
			setSize(icon.getIconWidth(),icon.getIconHeight());
		}
		super.setIcon(icon);
	}
	public void setLocation(int x,int y,Point p) { //pΪ��ǩ�ڵĻ���
		x=x-p.x;
		y=y-p.y;
		super.setLocation(x,y);
	}
	
	
}

class FarmPanel extends JPanel{
	private Image img;
	private int width;
	private int height;
	FarmPanel(){
		ImageIcon icon=new ImageIcon("image//farm//plowland.jpg");
		img=icon.getImage();
		setLayout(null);
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		width=getWidth();
		height=getHeight();
		g.drawImage(img, 0, 0, width, height, this);
	}
}







