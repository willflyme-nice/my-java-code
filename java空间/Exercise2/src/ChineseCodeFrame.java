import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ChineseCodeFrame extends JFrame{
	JLabel lab1=new JLabel("用户名：");
	JLabel lab2=new JLabel("密码：");
	JLabel lab3=new JLabel("验证码：");
	JTextField tf1=new JTextField();
	JTextField tf2=new JTextField();
	JTextField tf3=new JTextField();
	JButton button1=new JButton("换一张");
	JButton button2=new JButton("登录");
	JButton button3=new JButton("重置");
	ChineseCodePanel codePane=new ChineseCodePanel();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChineseCodeFrame that=new ChineseCodeFrame();
		that.go();  
	}
	void go() {
		setTitle("中文验证码");
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
					ifo="用户名或密码不能为空！";
				}
				else if(!tf3.getText().equals(codePane.getCode())) {
					ifo="验证码错误！";
				}
				else if(tf1.getText().equals("1234")&&tf2.getText().equals("1234")) {
					ifo="登录成功!";					
				}
				else {
					ifo="用户名或密码错误!";
				}
				JOptionPane.showMessageDialog(null, ifo,"提示",JOptionPane.INFORMATION_MESSAGE);
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
	public  class ChineseCodePanel extends JPanel{
		String codebuff="昨夜小楼又东风大漠孤烟值长河落日润物细无声";
		StringBuffer code;
		
		//两个构造方法
		ChineseCodePanel(){
			super();
		}
		ChineseCodePanel(String str){
			super();
			codebuff=str;
		}
		//重写pain方法
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2d=(Graphics2D)g;
			g2d.setColor(Color.WHITE);
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g2d.setFont(new Font("黑体",Font.BOLD,15));
			Random ran=new Random();
			code=new StringBuffer();
		
			for(int i=0;i<4;i++) {
				//设置颜色
				Color color=new Color(ran.nextInt(120),ran.nextInt(120),ran.nextInt(120));
				g2d.setColor(color );
				//设置变形
				AffineTransform trans=new AffineTransform();
				trans.rotate(Math.toRadians(ran.nextInt(45)), i*16+8, 7);//旋转
				float scaleSize=ran.nextFloat()+0.8f;
				if (scaleSize>1.2f) {
					scaleSize=1.2f;
				}
				trans.scale(scaleSize,scaleSize);//缩放
				g2d.setTransform(trans);
				
				//绘制字符
				int index=ran.nextInt(codebuff.length());
				String word=codebuff.substring(index,index+1);
				code.append(word);
				g2d.drawString(word, this.getWidth()*i/5+5, this.getHeight()/2);
			}
		
			
		}
		//其它方法
		public String getCode(){
			return code.toString();
		}
		public void drawAgain() {
			this.repaint();
		}
	}
}





