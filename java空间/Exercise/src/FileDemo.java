import java.awt.*;
import javax.swing.*;
public class FileDemo{
	public static void main(String args[]) {
		JFrame frame=new JFrame("Frame with Panel");
		//Container contentPane=frame.getContentPane();
		frame.setBackground(Color.CYAN);
		JPanel panel=new JPanel();
		panel.setBackground(Color.YELLOW);
		JButton button=new JButton("Press me");
		button.setSize(10,100);
		panel.add(button);
		frame.add(panel,BorderLayout.SOUTH);
		frame.setBounds(300,200,300,200);
		
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
	
}