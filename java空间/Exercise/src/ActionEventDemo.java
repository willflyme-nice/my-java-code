import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public static ActionEventDemo implements ActionListener{
	JFrame frame;
	JButton b1,b2;
	JLabel l1,l2;
	
	public static void main(String args[]) {
		ActionEventDemo that=new ActionEventDemo();
		that.go();
	}
	public void go() {
		frame=new JFrame("Action Event Demo");
		Container contentPane=frame.getContentPane();
		b1=new JButton("see the tip");
		b2=new JButton("exist");
		b1.addActionListener(this);
		b2.addActionListener(this);
		contentPane.add(b1,"West");
		contentPane.add(b2,"East");
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
	}
}