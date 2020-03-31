import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PuzzleGameFrame extends JFrame{
	GamePane pane=new GamePane();
	JPanel upPane=new JPanel();
	JButton reStart=new JButton("��ʼ");
	JButton change=new JButton("��ͼ");
	JButton mode=new JButton("��"); //��3x3��4x4���л�
	JButton plug=new JButton("���");
	JTextField tf=new JTextField("�滻��Ԫ��",6);
	int flag=0;  //0Ϊ��ģʽ��1Ϊ�߼�ģʽ
	int index=1; //ͼƬԴ������
	final int sum1=3; //��ģʽͼƬԴ����
	final int sum2=2; //�߼�ģʽͼƬԴ����
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PuzzleGameFrame that=new PuzzleGameFrame();
		that.go();
		that.setVisible(true);
	}
	private void go() {
		setTitle("ƴͼ��Ϸ");
//		pack();
		setSize(366,428);
		setLocation(400,180);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		//����
		getContentPane().add(pane);
		upPane.add(reStart);
		upPane.add(change);
		upPane.add(mode);
		upPane.add(plug);
		tf.selectAll();
		upPane.add(tf);
		getContentPane().add(upPane,"North");
		
		//����¼�
		reStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.random();
			}
		});
		change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				if(flag==0) {
					if(index>sum1) {
						index=1;
					}
					pane.setCellIcon("images_"+index); //�л�ͼƬԴ	
				}
				if(flag==1) {
					if(index>sum2) {
						index=1;
					}
					pane.setCellIcon("images16_"+index); //�л�ͼƬԴ	
				}
				
			}
		});
		mode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag==0) { //�Ӽ򵥵��߼�
					flag=1; 
					mode.setText("�߼�");
					setSize(715,780);
					pane.changeMode(1);	
					index=1;
				}
				else if(flag==1){ //�Ӹ߼�����
					flag=0;
					mode.setText("��");
					setSize(366,428);
					pane.changeMode(0);
					index=1;
				}
			}
		});
		plug.addActionListener(new ActionListener() { //ʹ�����
			public void actionPerformed(ActionEvent e) {
				String text=tf.getText();
				try {
						pane.usePlug(Integer.parseInt(text)-1);
				}catch(NumberFormatException ne) {
					JOptionPane.showMessageDialog(pane,"������������������");
				}
				tf.requestFocus();
				tf.selectAll();
//				tf.setText("");
			} 
		});
		
		
	}
}
/**
 * ����ö������
 */
enum Direction{
	Up,
	Down,
	Left,
	Right
}
/**
 * ����ƴͼ��Ԫ��
 */
class Cell extends JButton{
	private int WIGHT; //��Ԫ���
	private int place; //λ��
	private int ROWS; //ƴͼ������
	Cell(ImageIcon icon,int place,int wight,int rows){
		super();
		WIGHT=wight;
		ROWS=rows;
		setSize(WIGHT,WIGHT);
		setIcon(icon);
		this.place=place;
		setLocation(place%rows*WIGHT,place/rows*WIGHT);
	}
	public void move(Direction dr) {
		int x=getX();
		int y=getY();
		switch(dr) {
		case Up:setLocation(x,y-WIGHT);
				place=place-ROWS;
				break;
		case Down:setLocation(x,y+WIGHT);
				place=place+ROWS;
				break;
		case Left:setLocation(x-WIGHT,y);
				place=place-1;
				break;
		case Right:setLocation(x+WIGHT,y);
				place=place+1;
				break;
		}
	}
	public int getX() {
		return this.getLocation().x;
	}
	public int getY() {
		return this.getLocation().y;
	}
	public int getWIGHT() {
		return WIGHT;
	}
	public void setplace(int i) {
		place=i;
	}
	public int getplace() {
		return place;
	}	
}
/**
 * ����ƴͼ�����
 */
class GamePane extends JPanel implements ActionListener{
	private Cell cells[];
	private int rows;//����
	private Cell blank;
	
	GamePane() {
		super();
		cells=new Cell[9];//��ʼ��Ϊ3x3ģʽ
		rows=3;
		setLayout(null);
		for(int i=0;i<cells.length;i++) {
			ImageIcon img=new ImageIcon("image\\puzzle\\images_1\\"+(i+1)+".jpg");
			cells[i]=new Cell(img,i,117,rows);
			add(cells[i]);
		}
		blank=cells[cells.length-1];
	}
	//���õ�ԪͼƬ����
	public void setCellIcon(String filename) {
		removeAll();
		for(int i=0;i<cells.length;i++) {
			ImageIcon img=new ImageIcon("image\\puzzle\\"+filename+"\\"+(i+1)+".jpg");
			cells[i]=new Cell(img,i,img.getIconWidth(),rows);
			add(cells[i]);
		}
		repaint();
		blank=cells[cells.length-1];
	}
	//�л�ģʽ����
	public void changeMode(int flag) { //=0Ϊ�򵥣�=1Ϊ�߼�
		if (flag==0) {
			cells=new Cell[9];//��Ϊ3x3ģʽ
			rows=3;
			removeAll();
			for(int i=0;i<cells.length;i++) {
				ImageIcon img=new ImageIcon("image\\puzzle\\images_1\\"+(i+1)+".jpg");
				cells[i]=new Cell(img,i,117,rows);
				add(cells[i]);
			}	
			blank=cells[cells.length-1];
		}
		else if(flag==1) {
			cells=new Cell[16];//��Ϊ4x4ģʽ
			rows=4;
			removeAll();
			for(int i=0;i<cells.length;i++) {
				ImageIcon img=new ImageIcon("image\\puzzle\\images16_1\\"+(i+1)+".jpg");
				cells[i]=new Cell(img,i,175,rows);
				add(cells[i]);
			}	
			blank=cells[cells.length-1];
		}
	}
	//ʹ����Һ���
	public void usePlug(int i) {
		if(i>=0&&i<cells.length) {
			Cell source=getCellAt(i);//��ȡ��Ԫ
			Point p=source.getLocation();
			source.setLocation(blank.getLocation());
			source.setplace(blank.getplace());
			blank.setLocation(p);
			blank.setplace(i);
		}
		else {
			JOptionPane.showMessageDialog(this, "�����뵥Ԫ��1~"+cells.length);
		}
		
	}
	//��λ�÷��ص�Ԫ��
	public Cell getCellAt(int place) {
		Cell cell=null;
		for(int i=0;i<cells.length;i++) {
			if(cells[i].getplace()==place) {
				cell=cells[i];
				break;
			}	
		}
		return cell;
	}
	//�¼���Ӧ����
	public void actionPerformed(ActionEvent e) {
//		System.out.println("������Ӧ");
		Cell source=(Cell)e.getSource();
		int x=source.getX();
		int y=source.getY();
		int place=source.getplace();
		if(x==blank.getX()&&y-blank.getY()==source.getWIGHT()) {
			source.move(Direction.Up);
			blank.move(Direction.Down);
		}
		else if(x==blank.getX()&&y-blank.getY()==-source.getWIGHT()) {
			source.move(Direction.Down);
			blank.move(Direction.Up);
		}
		else if(x-blank.getX()==source.getWIGHT()&&y==blank.getY()) {
			source.move(Direction.Left);
			blank.move(Direction.Right);
		}
			else if(x-blank.getX()==-source.getWIGHT()&&y==blank.getY()) {
			source.move(Direction.Right);
			blank.move(Direction.Left);
		}
		if(checkFinish()) {
			FinishWindow();
		}
	}
	//������к���
	public void random() {
		Random randon=new Random();
		for(int i=0;i<cells.length;i++) {
			int n=randon.nextInt(cells.length);
			int m=randon.nextInt(cells.length);
			int x=cells[n].getX();
			int y=cells[n].getY();
			int place=cells[n].getplace();
			cells[n].setLocation(cells[m].getX(),cells[m].getY());//���������Ԫ�Ե�
			cells[n].setplace(cells[m].getplace());
			cells[m].setLocation(x,y);
			cells[m].setplace(place);
			if(i!=cells.length-1) { //Ϊ���հ׵�Ԫ����ĵ�Ԫ����¼�
				cells[i].addActionListener(this); 
			}
		}
		
	}
	//�ж�ƴͼ��ɺ���
	public boolean checkFinish() {
		boolean result=true;
		for(int i=0;i<cells.length;i++) {
			int x=cells[i].getX();
			int y=cells[i].getY();
			if(x!=i%rows*cells[i].getWIGHT()||y!=i/rows*cells[i].getWIGHT()) {
				result=false;
				break;
			}
		}
		return result;
	}
	//ƴͼ��ɺ�Ĵ�����ʾ
	public void FinishWindow() {
		for(int i=0;i<cells.length;i++) {
			if(i!=cells.length-1) { //Ϊ��Ԫ�Ƴ��¼�
				cells[i].removeActionListener(this); 
			}
		}
		Dialog dia=new Dialog(new JFrame(),"�����ʾ",true);
		JLabel lab=new JLabel("��ϲ���ƴͼ���Ƿ�����һ�֣�");
		JPanel pane1=new JPanel();
		pane1.add(lab);
		dia.add(pane1,"Center");
		JButton b1=new JButton("��");
		JButton b2=new JButton("��");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				random();
				dia.setVisible(false);
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dia.setVisible(false);
			}
		});
		JPanel pane2=new JPanel();
		pane2.add(b1);
		pane2.add(b2);
		dia.add(pane2,"South");
		dia.setBounds(700,400,300,200);
		dia.setVisible(true);
	}
}






