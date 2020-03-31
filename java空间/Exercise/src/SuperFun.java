import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class SuperFun extends JFrame{
	JLabel lab=new JLabel("请输入开奖个数");
	JLabel mylab=new JLabel("我的号码");
	JTextField tf=new JTextField(5);
	JTextField mytf=new JTextField(8);
	JTextArea ta=new JTextArea(15,10);
	JButton button=new JButton("开奖");
	
	public static void main(String[] args) {
		
		SuperFun that=new SuperFun();
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
		String lukeyNum; //记录开奖号码
		Random random=new Random();
		
		for(int i=0;i<number;i++) {
			List<Integer> list1=new ArrayList<Integer>();
			for(int j=1;j<=35;j++) {
				list1.add(j);
			}
			for(int index=0;index<5;index++) {
				int num1=list1.get(random.nextInt(list1.size()));
				lukeyNum=""+num1;
				if(lukeyNum.length()<2) {lukeyNum="0"+lukeyNum;}
				strbu.append(lukeyNum+" ");
				list1.remove(new Integer(num1));
			}
			strbu.append("\t\t");
			
			List<Integer> list2=new ArrayList<Integer>();
			for(int j=1;j<=12;j++) {
				list2.add(j);
			}
			for(int index=0;index<2;index++) {
				int num2=list2.get(random.nextInt(list2.size()));
				lukeyNum=""+num2;
				if(lukeyNum.length()<2) {lukeyNum="0"+lukeyNum;}
				strbu.append(lukeyNum+" ");
				list2.remove(new Integer(num2));
			}
			strbu.append("\n");

		}
		ta.setText(strbu.toString());
	}
}
