import java.awt.*;
import javax.swing.*;
public class FrameDemo{
	public static void main(String args[]) {
		JFrame frame=new JFrame("Hellow");
		JPanel contentpane=new JPanel();
		JButton button=new JButton("Press me");
	    contentpane.setLayout(new BorderLayout());
		contentpane.add(button,BorderLayout.CENTER);
		frame.setContentPane(contentpane);
		frame.setVisible(true);
		frame.pack();
		frame.setBounds(400,200,400,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		
		
		
	}
	
}