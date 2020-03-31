package drawparts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * 绘图区
 */
public class DrawPanel extends JPanel implements MouseMotionListener,MouseListener{

	private DrawContext drawContext;

	private Graphics2D g2d;
	private int brush = 5;   //笔宽
	private Color color = Color.black;  //颜色
	
	private JLabel icon = new JLabel();  //显示画笔大小
	
	public DrawPanel(DrawContext drawContext) {
		this.drawContext = drawContext;
		setBackground(Color.white);
		addMouseMotionListener(this);
		addMouseListener(this);
	
		setLayout(null);
		icon.setBorder(new LineBorder(Color.black));
		add(icon);
	}

	
	@Override
	public void mouseDragged(MouseEvent e) {
		g2d = (Graphics2D)DrawPanel.this.getGraphics();
		drawContext.moveDraw(g2d, e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		g2d = (Graphics2D)DrawPanel.this.getGraphics();
		drawContext.downDraw(g2d,e);
	}
	
	public void mouseMoved(MouseEvent e) {
//		icon.setBounds(e.getX()-brush/2,e.getY()-brush/2,brush,brush);
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {
		g2d = (Graphics2D)DrawPanel.this.getGraphics();
		drawContext.upDraw(g2d,e);
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
		
}


















