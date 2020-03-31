import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class PictureMixFrame extends JFrame{
	JSlider slider=new JSlider(); //����һ��0��100�Ļ��飬��ʼֵΪ50
	PictureMixPanel pane=new PictureMixPanel();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("....");
		PictureMixFrame that=new PictureMixFrame();
		that.go();
	}
	void go() {
		setTitle("ͼƬ�ں���Ч");
		setResizable(false);
		add(pane);
		add(slider,"South");
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				pane.repaint();
			}
		});
		
		setSize(400,350);
		setLocation(400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	class  PictureMixPanel extends JPanel{
		Image img1;
		Image img2;
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2d=(Graphics2D)g;
			
			//���Ƶ�һ��ͼƬ
			try {
				img1=ImageIO.read(new File("image//image3.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			AlphaComposite comp=AlphaComposite.SrcOver.derive(0.5f);
			g2d.setComposite(comp);
			g2d.drawImage(img1, 0, 0, this.getWidth(), this.getHeight(), this);
			
			//���Ƶڶ���ͼƬ
			try {
				img2=ImageIO.read(new File("image//image4.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			float value=slider.getValue()/100f; //����˿̻����Ӧ��͸��ֵ
			comp=AlphaComposite.SrcOver.derive(value);
			g2d.setComposite(comp);
			g2d.drawImage(img2, 0, 0, this.getWidth(), this.getHeight(), this);	
			}
		}
		
}






