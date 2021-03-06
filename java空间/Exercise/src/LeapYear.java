import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LeapYear{
	JFrame frame=new JFrame("判断某一年是否为闰年");
	JTextArea ta;
	JButton judge;
	JLabel lab1,lab2;
	
    public static void main(String args[]) throws Exception{
		
		LeapYear that=new LeapYear();
		that.go();			
	}
    
    public void go() throws Exception{
    	ta=new JTextArea("",1,10);
    	judge = new JButton("判断");
    	lab1 = new JLabel("在下列框中输入年份");
    	lab2 = new JLabel("结果:  ");
    	
    	judge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str;
				int year;
				str=ta.getText();
				year=Integer.parseInt(str);
				if( year%4==0 && year%100!=0 || year%400==0)
				{
					lab2.setText("结果：闰年");
				}
				else
				{
					lab2.setText("结果：平年");
				}
			}
		});
    	
    	Container cp=frame.getContentPane();
		cp.add(ta);
		cp.add(lab1,"North");
		cp.add(judge,"East");
		cp.add(lab2, "South");
		
		
		frame.setBounds(300,200,300,200);
		//frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
	
}
