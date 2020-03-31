import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SevenStar extends JFrame{
	JLabel lab=new JLabel("请输入开奖个数");
	JLabel mylab=new JLabel("我的号码");
	JTextField tf=new JTextField(5);
	JTextField mytf=new JTextField(8);
	JTextArea ta=new JTextArea(15,10);
	JButton button=new JButton("开奖");
	
	public static void main(String[] args) {
		 try {
	            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	        } catch (Throwable e) {
	            e.printStackTrace();
	        }
		SevenStar that=new SevenStar();
		that.go();
	}
	
	private void go() {
		JPanel pane1=new JPanel();
		pane1.add(lab);
		pane1.add(tf);
		pane1.add(mylab);
		pane1.add(mytf);
		getContentPane().add(pane1,"North");
		getContentPane().add(new JScrollPane(ta),"Center");
		JPanel pane3=new JPanel();
		pane3.add(button);
		getContentPane().add(pane3,"South");
		lab.setFont(new Font("微软雅黑",Font.PLAIN,18));
		tf.setFont(new Font("微软雅黑",Font.PLAIN,18));
		ta.setFont(new Font("微软雅黑",Font.PLAIN,18));
		button.setFont(new Font("微软雅黑",Font.PLAIN,18));
		mylab.setFont(new Font("微软雅黑",Font.PLAIN,18));
		mytf.setFont(new Font("微软雅黑",Font.PLAIN,18));
		
//		pack();
		setSize(500,400);
		setLocation(400, 300);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPerform();
			}
		});
	}

	private void buttonPerform() {
		String tfText=tf.getText();
		if(tfText.length()==0)
		{
			JOptionPane.showMessageDialog(this,	"请输入合适的开奖数目");
			return;
		}
		int number=0;
		try {
			number=Integer.parseInt(tfText);
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this,	"不要输入其它字符");
			return;
		}
		
		if(number<1||number>10) {
			JOptionPane.showMessageDialog(this,	"请输入合适的开奖数目");
			return;
		}
		StringBuilder strbu=new StringBuilder();
		int lukeyNum; //记录开奖号码
		boolean state=false;
		Random random=new Random();
		for(int i=0;i<number;i++) {
			lukeyNum=random.nextInt(100);
			String str=Integer.toString(lukeyNum);
			while(str.length()<2) {
				str="0"+str;
			}
			if(mytf.getText().equals(str)) {
				state=true;
			}
			strbu.append(str+"\n");
		}
		if(state==false) {
			strbu.append("很遗憾，您没有中奖。。。");
		}
		ta.setText(strbu.toString());
		if(state==true) {
			JOptionPane.showMessageDialog(this,	"恭喜您中奖啦！！！\n中奖金额5000000","恭喜",JOptionPane.WARNING_MESSAGE);
		}
	}
}




