import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.awt.geom.*;

public class CutImageFrame extends JFrame {
	private JSplitPane pane=new JSplitPane();//����һ��ˮƽ�ָ����
	private JPanel olderPane=new OlderImagePane();
	private JPanel cutPane=new CutImagePane();
	private Image image=null;
	private BufferedImage bufimage=null;
	private Robot robot=null;
	//��¼��갴�º��ͷ�ʱ������
	int cpressX=0;//������������
	int cpressY=0;//������������
	int pressX=0;
	int pressY=0;
	int releaseX=0;
	int releaseY=0;
	
	public static void main(String[] args) {
		CutImageFrame that=new CutImageFrame();
		that.go();
	}
	private void go() {
		setTitle("ͼƬ�ü�");
		pane.setLeftComponent(olderPane);
		pane.setRightComponent(cutPane);
		setContentPane(pane);
		
        image = Toolkit.getDefaultToolkit().getImage("image\\image(2).jpg"); // ��ȡͼ����Դ
        pane.setDividerLocation(400);
        //�������¼�
        olderPane.addMouseListener(new MouseAdapter() {
        	public void mousePressed(MouseEvent e){
        		pressedPerformed(e);
        	} 
        	public void mouseReleased(MouseEvent e){
        		releasedPerformed(e);
        	}	
        });
        olderPane.addMouseMotionListener(new MouseMotionAdapter() {
        	public void mouseDragged(MouseEvent e) {
        		motionPerformed(e);
        	}
        });
        
		setSize(800,400);
		setLocation(400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//��갴��ʱ��Ӧ
	private void pressedPerformed(MouseEvent e) {
		//��������
		pressX=e.getXOnScreen();
		pressY=e.getYOnScreen();
		//�������
		cpressX=e.getX();
		cpressY=e.getY();
	}
	//����ͷ�ʱ��Ӧ
	private void releasedPerformed(MouseEvent e) {
		releaseX=e.getXOnScreen();
		releaseY=e.getYOnScreen();
		Rectangle cutrec=new Rectangle(pressX+1,pressY+1,releaseX-pressX-1,releaseY-pressY-1);
			try {
					robot=new Robot();
					bufimage=robot.createScreenCapture(cutrec);	
				} catch (AWTException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
		olderPane.repaint();
		cutPane.repaint();
	}
	private void motionPerformed(MouseEvent e) {
		releaseX=e.getXOnScreen();
		releaseY=e.getYOnScreen();
		olderPane.repaint();
	}
	private class OlderImagePane extends JPanel{
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d=(Graphics2D)g;
			g2d.drawImage(image,0, 0, this.getWidth(), this.getHeight(),this);
			
			float arr[]= {5.0f};
			BasicStroke stock=new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,1.0f,arr,0);
			Rectangle rec=new Rectangle(cpressX,cpressY,releaseX-pressX,releaseY-pressY);
			g2d.setStroke(stock);
			g2d.setColor(Color.white);
			g2d.draw(rec);
		}
	}
	private class CutImagePane extends JPanel{
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d=(Graphics2D)g;
			g2d.drawImage(bufimage, 5, 5, (releaseX-pressX-1)*2, (releaseY-pressY-1)*2,this);	
		}
	}

}
	
	
	
	
