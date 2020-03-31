import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class FlowerFrame extends JFrame{

	public static void main(String[] args) {
		FlowerFrame that=new FlowerFrame();
		that.go();
	}
	private void go() {
		setTitle("绘制花瓣");
		FlowerPane pane=new FlowerPane();
		this.setContentPane(pane);
		
		setSize(400,440);
		setLocation(400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private class FlowerPane extends JPanel{
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d=(Graphics2D)g;
			g2d.translate(200, 200);
			//画绿色花瓣
			Ellipse2D.Float greenPetal=new Ellipse2D.Float(15,15,150,40);
			g2d.setColor(Color.GREEN);
			g2d.fill(greenPetal);
			for(int i=0;i<8;i++) {
				g2d.rotate(Math.PI*2/9);
				g2d.fill(greenPetal);
			}
			//画红色花瓣
			Ellipse2D.Float redPetal=new Ellipse2D.Float(13,13,125,25);
			g2d.setColor(Color.red);
			g2d.fill(redPetal);
			for(int i=0;i<15;i++) {
				g2d.rotate(Math.PI*2/16);
				g2d.fill(redPetal);
			}
			//画黄色花瓣
			Ellipse2D.Float yellowPetal=new Ellipse2D.Float(10,10,100,30);
			g2d.setColor(Color.yellow);
			g2d.fill(yellowPetal);
			for(int i=0;i<8;i++) {
				g2d.rotate(Math.PI*2/9);
				g2d.fill(yellowPetal);
			}
			//画花心
			Ellipse2D.Float redcenter=new Ellipse2D.Float(-20,-20,40,40);
			g2d.setColor(Color.red);
			g2d.fill(redcenter);
			
		}
	}

}
