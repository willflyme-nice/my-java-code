import java.awt.*;
import javax.swing.*;

public class WatermarkFrame extends JFrame{
	Image img=null;
	WatermarkPanel pane=new WatermarkPanel();
	public static void main(String[] args) {
		WatermarkFrame that=new WatermarkFrame();
		that.go();
	}
	void go() {
		setTitle("水印图片");
		add(pane);
		img= Toolkit.getDefaultToolkit().getImage("image\\image(2).jpg"); 
		
		setSize(400,400);
		setLocation(400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	class WatermarkPanel extends JPanel{
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			Graphics2D g2d=(Graphics2D)g;
			AlphaComposite alpha=AlphaComposite.SrcOver.derive(0.3f);
			g2d.setComposite(alpha);
			Font font=new Font("楷体",Font.BOLD,60);
			g2d.setFont(font);
			g2d.setColor(Color.white);
			g2d.rotate(Math.toRadians(-20));
			g2d.drawString("山清水秀",-50,120 );
		}
	}
}
