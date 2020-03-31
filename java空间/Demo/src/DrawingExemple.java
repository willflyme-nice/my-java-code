import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class DrawingExemple implements ActionListener{
	JFrame frame;
	JButton button1,button2,button3;
	MyPanel panel;
	int tag=0;
	
	public static void main(String args[]) throws Exception{
		DrawingExemple  that=new DrawingExemple ();
		that.go();
	}
	public void go() throws Exception{
		frame=new JFrame("Drawing Exemple");
		Container contentPane=frame.getContentPane();
		button1=new JButton("   Draw Line  ");
		button2=new JButton("Draw Rectangle");
		button3=new JButton("   Draw Oval  ");
		
		Box box=Box.createHorizontalBox();
		box.add(Box.createGlue());
		box.add(button1);
		box.add(Box.createGlue());
		box.add(button2);
		box.add(Box.createGlue());
		box.add(button3);
		box.add(Box.createGlue());
		
		contentPane.add(box,BorderLayout.SOUTH);
		panel=new MyPanel();
		contentPane.add(panel,BorderLayout.CENTER);
		
        button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		
		
		
		frame.setBounds(400,300,400,300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	


	
	
      class MyPanel extends JPanel{
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(tag==1) {
				g.setColor(Color.red);
				g.drawLine(10, 10, 300, 200);
				
			}
			if(tag==2) {
				g.setColor(Color.CYAN);
				g.drawRect(20, 10, 253, 200);
			}
		    if(tag==3) {
				g.setColor(Color.GREEN);
				g.drawOval(20, 10, 253, 200);
				
			}
		}
		
	}
	
      public void actionPerformed(ActionEvent e) {
			if((JButton)e.getSource()==button1) {
				tag=1;
			}
			if((JButton)e.getSource()==button2) {
				tag=2;
			}
			if((JButton)e.getSource()==button3) {
				tag=3;
			}
			panel.repaint();
		}
	//class MyListener implements ActionListener{}
	
}	


	
	
	
	
	
	
	
	
	
	
	
	
	
