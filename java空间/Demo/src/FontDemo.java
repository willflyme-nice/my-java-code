import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class FontDemo{
	JFrame frame;
	MyPanel panel;
	MyButton button;
	
	public static void main(String args[]) {
		
		FontDemo that=new FontDemo();that.go();
		
			
	}
	
	public void go() {
		
		frame=new JFrame();
		Container contentPane=frame.getContentPane();
		panel=new MyPanel();
		
		
		
		contentPane.add(panel, "Center");
		
		
		frame.setBounds(400,300,400,350);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		

		
	}
	 
	class MyPanel extends JPanel{
		public void paintComponent(Graphics g) {
			Graphics2D g2d=(Graphics2D)g;
			super.paintComponent(g2d);
			BasicStroke stroke=new BasicStroke(20,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_ROUND);//线型效果
			GradientPaint paint1=new GradientPaint(0,0,Color.BLUE,400,0,Color.green);//填充效果1
			GradientPaint paint2=new GradientPaint(0,0,Color.yellow,0,350,Color.red,true);//填充效果2
			
			g2d.setStroke(stroke);
			g2d.setPaint(paint2);      
			Line2D line2=new Line2D.Double(200.0,10.0,200.0,340.0);
			g2d.draw(line2);     
			
			g2d.setPaint(paint1);
			RoundRectangle2D rectRound=new RoundRectangle2D.Double(20,30,300,150,30,20);
		    //g2d.draw(rectRound);
		    
		    AffineTransform trans=new AffineTransform();
		    trans.translate(25, 40);
		    //trans.scale(0.5, 0.5);
		    trans.rotate(45,20+25,30+40);
		    g2d.setTransform(trans);
		    g2d.setColor(Color.orange);
		    g2d.draw(rectRound);
			
		}
	}
	
	class MyButton extends JButton{
		MyButton(String s){
			super(s);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Font f1=new Font(" 浪漫雅圆",Font.PLAIN,50);
			g.setFont(f1);
			g.drawString(" 浪漫雅圆", 10, 50);
			
		}
	}
	
	
}