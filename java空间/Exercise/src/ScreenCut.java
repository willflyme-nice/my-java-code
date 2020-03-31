import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.awt.image.*;

public class ScreenCut implements ActionListener{
	static JLabel imageLabel;
	public static void main (String arg[]) {
		JFrame frame=new JFrame("��ͼ���");
		Container cp=frame.getContentPane();
		JButton button=new JButton("��ͼ");
		button.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		ImageIcon image=null;
	
		image=new ImageIcon("E:\\���ѧϰ\\�������\\java�����ռ�\\Exercise\\ͼ��1.jpg");
		
		JLabel label=new JLabel("There is a picture",image,SwingConstants.CENTER);
	    label.setFont(new Font("����",Font.PLAIN,30));
	    label.setForeground(Color.RED);
	    label.setBackground(Color.cyan);
	   
		
	    
		imageLabel=new JLabel();
		imageLabel.setSize(400,400);
		JPanel panel=new JPanel();
		panel.setBorder(new EtchedBorder());
		panel.add(imageLabel);
		JPanel buttonPanel=new JPanel();
		buttonPanel.add(button);
		
		 cp.add(label,"North");	
		cp.add(panel, "Center");
		cp.add(buttonPanel,"South");
		
		
		button.addActionListener(new ScreenCut());
		
//		frame.pack();
		frame.setSize(400,500);
		frame.setLocation(800,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void actionPerformed(ActionEvent e) {
		try {
			Robot robot=new Robot();
			Toolkit toolkit=Toolkit.getDefaultToolkit();
			Rectangle area=new Rectangle(toolkit.getScreenSize());
			BufferedImage bufImage=robot.createScreenCapture(area);
			ImageProducer imagePro=bufImage.getSource();
			Image image=toolkit.createImage(imagePro);
			imageLabel.setIcon(new ImageIcon(image));
		}catch(AWTException e1){
			e1.printStackTrace();
		
		}
	}
	
	
}
