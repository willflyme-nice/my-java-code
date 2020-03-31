import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GradientFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame=new JFrame("Ω•±‰ÃÓ≥‰");
		GradientPanel pane=new GradientPanel();
		frame.getContentPane().add(pane);
		
		
//		pack();
		frame.setSize(340,210);
		frame.setLocation(400, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static class GradientPanel extends JPanel{
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d=(Graphics2D)g;
			GradientPaint paint=new GradientPaint(0,0,Color.white,70,0,Color.red,true);
			g2d.setPaint(paint);
			Rectangle2D.Float rec=new Rectangle2D.Float(10,10,300,150);
			g2d.fill(rec);
		}
	}
}
