import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class CheckIP implements ActionListener{
	JFrame frame;
	JTextField iptext;
	JLabel lab;
	JButton button;
	public static void main(String args[]) {
		CheckIP that=new CheckIP();
		that.go();
	}
	
	void go(){
		frame=new JFrame("Diolog Demo");
		lab=new JLabel("Please Input IP");
		iptext=new JTextField(13);
		button=new JButton("OK");
		button.addActionListener(this);
		
		Container cp=frame.getContentPane();
		JPanel pane=new JPanel(new FlowLayout(FlowLayout.CENTER));
		pane.setBorder(new EmptyBorder(10,10,10,10));
		pane.add(lab);
		pane.add(iptext);
		
		JPanel pane1=new JPanel(new FlowLayout(FlowLayout.CENTER));
		pane.setBorder(new EmptyBorder(10,10,10,10));
		pane1.add(button);
		cp.add(pane);
		cp.add(pane1,"South");
		
		
		
		frame.pack();
		//frame.setSize(600,260);
		frame.setLocation(800,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		String regex="^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		
		String text=iptext.getText();
		if(text.matches(regex)==true){
			JOptionPane.showMessageDialog(null,"It a cracted ip");
		}
		else {
			JOptionPane.showMessageDialog(null,"It a wrong ip");
		}
	    
		
	}
	public class MyClass implements ActionListener{
		
		
	}
}
