import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


	public class LayoutDemo{
		JFrame frame;
		JPanel p;
		Box b1,b2,b3,b4;
		JScrollPane sp;
		public static void main(String args[]) {
			LayoutDemo that=new LayoutDemo();
			that.go();
		}
		public void go() {
			frame=new JFrame("Glue and Strut example");
			
			sp=new JScrollPane();
			p=new JPanel();
			p.setLayout(new GridLayout(4,1));
			
			b1=Box.createHorizontalBox();
			b1.add(new JLabel("Box1:"));
			b1.add(new JButton("Yes"));
			b1.add(new JButton("No"));
			b1.add(new JButton("Cancel"));
			
			b2=Box.createHorizontalBox();
			b2.add(new JLabel("Box2:"));
			b2.add(new JButton("Yes"));
			b2.add(Box.createGlue());
			b2.add(new JButton("No"));
			b2.add(Box.createGlue());
			b2.add(new JButton("Cancel"));
			 
			b3=Box.createHorizontalBox();
			b3.add(new JLabel("Box3:"));
			b3.add(new JButton("Yes"));
			b3.add(new JButton("No"));
			b3.add(Box.createHorizontalStrut(20));
			b3.add(new JButton("Cancel"));
			
			b4=Box.createHorizontalBox();
			b4.add(new JLabel("Box4:"));
			b4.add(new JButton("Yes"));
			b4.add(new JButton("No"));
			b4.add(Box.createRigidArea(new Dimension(50,200)));
			b4.add(new JButton("Cancel"));
			
			p.add(b1);
			p.add(b2);
			p.add(b3);
			p.add(b4);
			sp.add(p);
			frame.getContentPane().add(sp);
			
			
			 frame.setSize(300, 200);
			 frame.setVisible(true);
			 System.out.println("nihaoo");
			 
			 
			 
			 
			 
			 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 
			
			
		}
		
	
	
	
}

