import java.awt.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;


public class MyDraw{
    double angle;
	static public void main(String[]arg) {
		MyDraw that=new MyDraw();
		that.go();
	}
	
	void go() {
		JFrame frame=new JFrame("ʱ����ʾ");
		My_Panel panel=new My_Panel();
		frame.getContentPane().add(panel);
		
		
		
		frame.setSize(300, 300);
		frame.setLocation(300, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		for(int i=0;i<60;i++) {
			angle=6.28/60*(float)i;
			panel.repaint();
			try {Thread.sleep(1000);
			}catch(InterruptedException e) {}
			
		}
	}
	
	class My_Panel extends JPanel{
		
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			Font font=new Font("΢���ź�",Font.PLAIN,15);
			g.setFont(font);
			g.drawString("����ʱ��",120,250);
			g.setColor(Color.BLUE);
			g.drawOval(40, 20, 200, 200);
			//������
			Graphics2D g2d=(Graphics2D)g;
			BasicStroke stroke=new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL);
			g2d.setStroke(stroke);
			g2d.setPaint(new GradientPaint(140,120,Color.YELLOW,240,120,Color.BLUE));
			Line2D line=new Line2D.Double(140, 120, 240, 120);
			
			AffineTransform trans=new AffineTransform();
			trans.rotate(angle,140,120);
			g2d.setTransform(trans);
			g2d.draw(line);
			
		}
	}
}

