import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PuzzleGameFrame extends JFrame{
	GamePane pane=new GamePane();
	JPanel upPane=new JPanel();
	JButton reStart=new JButton("开始");
	JButton change=new JButton("换图");
	JButton mode=new JButton("简单"); //在3x3和4x4间切换
	JButton plug=new JButton("外挂");
	JTextField tf=new JTextField("替换单元格",6);
	int flag=0;  //0为简单模式，1为高级模式
	int index=1; //图片源标记序号
	final int sum1=3; //简单模式图片源总数
	final int sum2=2; //高级模式图片源总数
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PuzzleGameFrame that=new PuzzleGameFrame();
		that.go();
		that.setVisible(true);
	}
	private void go() {
		setTitle("拼图游戏");
//		pack();
		setSize(366,428);
		setLocation(400,180);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		//布局
		getContentPane().add(pane);
		upPane.add(reStart);
		upPane.add(change);
		upPane.add(mode);
		upPane.add(plug);
		tf.selectAll();
		upPane.add(tf);
		getContentPane().add(upPane,"North");
		
		//添加事件
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
					pane.setCellIcon("images_"+index); //切换图片源	
				}
				if(flag==1) {
					if(index>sum2) {
						index=1;
					}
					pane.setCellIcon("images16_"+index); //切换图片源	
				}
				
			}
		});
		mode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag==0) { //从简单到高级
					flag=1; 
					mode.setText("高级");
					setSize(715,780);
					pane.changeMode(1);	
					index=1;
				}
				else if(flag==1){ //从高级到简单
					flag=0;
					mode.setText("简单");
					setSize(366,428);
					pane.changeMode(0);
					index=1;
				}
			}
		});
		plug.addActionListener(new ActionListener() { //使用外挂
			public void actionPerformed(ActionEvent e) {
				String text=tf.getText();
				try {
						pane.usePlug(Integer.parseInt(text)-1);
				}catch(NumberFormatException ne) {
					JOptionPane.showMessageDialog(pane,"解析错误，请输入整数");
				}
				tf.requestFocus();
				tf.selectAll();
//				tf.setText("");
			} 
		});
		
		
	}
}
/**
 * 创建枚举类型
 */
enum Direction{
	Up,
	Down,
	Left,
	Right
}
/**
 * 创建拼图单元类
 */
class Cell extends JButton{
	private int WIGHT; //单元宽度
	private int place; //位置
	private int ROWS; //拼图的行数
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
 * 创建拼图面板类
 */
class GamePane extends JPanel implements ActionListener{
	private Cell cells[];
	private int rows;//行数
	private Cell blank;
	
	GamePane() {
		super();
		cells=new Cell[9];//初始化为3x3模式
		rows=3;
		setLayout(null);
		for(int i=0;i<cells.length;i++) {
			ImageIcon img=new ImageIcon("image\\puzzle\\images_1\\"+(i+1)+".jpg");
			cells[i]=new Cell(img,i,117,rows);
			add(cells[i]);
		}
		blank=cells[cells.length-1];
	}
	//设置单元图片函数
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
	//切换模式函数
	public void changeMode(int flag) { //=0为简单，=1为高级
		if (flag==0) {
			cells=new Cell[9];//化为3x3模式
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
			cells=new Cell[16];//化为4x4模式
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
	//使用外挂函数
	public void usePlug(int i) {
		if(i>=0&&i<cells.length) {
			Cell source=getCellAt(i);//获取单元
			Point p=source.getLocation();
			source.setLocation(blank.getLocation());
			source.setplace(blank.getplace());
			blank.setLocation(p);
			blank.setplace(i);
		}
		else {
			JOptionPane.showMessageDialog(this, "请输入单元格1~"+cells.length);
		}
		
	}
	//按位置返回单元格
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
	//事件响应函数
	public void actionPerformed(ActionEvent e) {
//		System.out.println("正在响应");
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
	//随机排列函数
	public void random() {
		Random randon=new Random();
		for(int i=0;i<cells.length;i++) {
			int n=randon.nextInt(cells.length);
			int m=randon.nextInt(cells.length);
			int x=cells[n].getX();
			int y=cells[n].getY();
			int place=cells[n].getplace();
			cells[n].setLocation(cells[m].getX(),cells[m].getY());//随机两个单元对调
			cells[n].setplace(cells[m].getplace());
			cells[m].setLocation(x,y);
			cells[m].setplace(place);
			if(i!=cells.length-1) { //为除空白单元以外的单元添加事件
				cells[i].addActionListener(this); 
			}
		}
		
	}
	//判断拼图完成函数
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
	//拼图完成后的窗口显示
	public void FinishWindow() {
		for(int i=0;i<cells.length;i++) {
			if(i!=cells.length-1) { //为单元移除事件
				cells[i].removeActionListener(this); 
			}
		}
		Dialog dia=new Dialog(new JFrame(),"完成提示",true);
		JLabel lab=new JLabel("恭喜完成拼图！是否再来一局？");
		JPanel pane1=new JPanel();
		pane1.add(lab);
		dia.add(pane1,"Center");
		JButton b1=new JButton("是");
		JButton b2=new JButton("否");
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






