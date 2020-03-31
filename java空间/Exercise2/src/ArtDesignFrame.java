import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.Random;
public class ArtDesignFrame extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArtDesignFrame that=new ArtDesignFrame();
		that.go();
	}
	private void go() {
		setTitle("艺术图案窗口");
		ArtDesignPane pane=new ArtDesignPane();
		add(pane);
		
		setSize(400,300);
		setLocation(400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*Update ud=new Update();
		ud.start();*/
	}
	private class Update extends Thread{
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private class ArtDesignPane extends JPanel{
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d=(Graphics2D)g;
			Ellipse2D.Float ellipse=new Ellipse2D.Float(-100,10,200,10);
			g2d.translate(180,120);
			Random radam=new Random();
			int R=radam.nextInt(256);
			int B=radam.nextInt(256);
			int G=radam.nextInt(256);
			g2d.setColor(new Color(R,B,G));
			g2d.draw(ellipse);
			Ellipse2D.Float point=new Ellipse2D.Float(100,10,10,10);
			g2d.fill(point);
			int i=0;
			while(i<1) {
				g2d.rotate(0.3);
				R=radam.nextInt(256);
				B=radam.nextInt(256);
				G=radam.nextInt(256);
				g2d.setColor(new Color(R,B,G));
				g2d.draw(ellipse);
				g2d.fill(point);
				i++;
			}
			
		}
	}

}
