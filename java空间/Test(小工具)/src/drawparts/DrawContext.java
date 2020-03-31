package drawparts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *��ͼ������
 */
public class DrawContext {
	Color color = Color.black;  //ǰ��ɫ
	int brush = 1;    //���ʴ�С
	int eraser = 10;  //��Ƥ����С
	Shape shape = null; //��ǰ��ͼ��״
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	public DrawContext() {
		useNone();
	}
	
	public Color getColor() {return color;}
	public void setColor(Color color) {this.color = color;}
	public void setBrush(int brush) {this.brush = brush;}
	public void setEraser(int eraser) {this.eraser = eraser;}
	
	public void useEraser() {shape = _eraser;}
	public void useCircle() {shape = circle;}
	public void useArc() {shape = arc;}
	public void useRect() {shape = rect;}
	public void useLine() {shape = line;}
	public void useNone() {shape = _none;}
	
	
	//��갴��ʱ����
	public void downDraw(Graphics2D g2d, MouseEvent e) {
		shape.dwonDraw(g2d, e);
	}
	//����϶�ʱ����
	public void moveDraw(Graphics2D g2d, MouseEvent e) {
		shape.moveDraw(g2d, e);
	}
	//����ͷ�ʱ����
	public void upDraw(Graphics2D g2d, MouseEvent e) {
		shape.upDraw(g2d, e);
	}
	
	
	/**
	 * ������ֻ��DrawContext���õĸ��ֻ�ͼ��״�ڲ�������
	 */
	
	//ͨ�ýӿ�
	interface Shape{
		void dwonDraw(Graphics2D g2d, MouseEvent e);
		void moveDraw(Graphics2D g2d, MouseEvent e);
		void upDraw(Graphics2D g2d, MouseEvent e);
	}
	
	//��ѡ����״
	private Shape _none = new Shape(){
		public void dwonDraw(Graphics2D g2d, MouseEvent e) {
			x1 = e.getX();
			y1 = e.getY();
			g2d.setColor(color);
			g2d.fillArc(x1-brush/2, y1-brush/2, brush, brush, 0, 360);
		}
		public void moveDraw(Graphics2D g2d, MouseEvent e) {
			x2 = e.getX();
			y2 = e.getY();
			g2d.setColor(color);
			g2d.setStroke(new BasicStroke(brush));
			g2d.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		}
		public void upDraw(Graphics2D g2d, MouseEvent e) {
		
		}
	};
	
	//ֱ��
	Shape line = new Shape(){
		public void dwonDraw(Graphics2D g2d, MouseEvent e) {
			x1 = e.getX();
			y1 = e.getY();
		}
		public void moveDraw(Graphics2D g2d, MouseEvent e) {
			
		}
		public void upDraw(Graphics2D g2d, MouseEvent e) {
			x2 = e.getX();
			y2 = e.getY();
			g2d.setColor(color);
			g2d.setStroke(new BasicStroke(brush));
			g2d.drawLine(x1, y1, x2, y2);
		}
	};

	//����
	Shape rect = new Shape(){
		public void dwonDraw(Graphics2D g2d, MouseEvent e) {
			x1 = e.getX();
			y1 = e.getY();
		}
		public void moveDraw(Graphics2D g2d, MouseEvent e) {
			
		}
		public void upDraw(Graphics2D g2d, MouseEvent e) {
			x2 = e.getX();
			y2 = e.getY();
			g2d.setColor(color);
			g2d.setStroke(new BasicStroke(brush));
			g2d.drawRect(x1, y1, x2-x1, y2-y1);
		}
	};
	
	//��Բ��
	Shape arc = new Shape(){
		public void dwonDraw(Graphics2D g2d, MouseEvent e) {
			x1 = e.getX();
			y1 = e.getY();
		}
		public void moveDraw(Graphics2D g2d, MouseEvent e) {
			
		}
		public void upDraw(Graphics2D g2d, MouseEvent e) {
			x2 = e.getX();
			y2 = e.getY();
			g2d.setColor(color);
			g2d.setStroke(new BasicStroke(brush));
			g2d.drawArc(x1, y1, x2-x1, y2-y1, 0, 360);
		}
	};
	
	//Բ��
	Shape circle = new Shape(){
		public void dwonDraw(Graphics2D g2d, MouseEvent e) {
			x1 = e.getX();
			y1 = e.getY();
		}
		public void moveDraw(Graphics2D g2d, MouseEvent e) {
			
		}
		public void upDraw(Graphics2D g2d, MouseEvent e) {
			x2 = e.getX();
			y2 = e.getY();
			int size = Math.min(x2-x1, y2-y1); //Բֱ��
			g2d.setColor(color);
			g2d.setStroke(new BasicStroke(brush));
			g2d.drawArc(x1, y1, size, size, 0, 360);
		}
	};
	
	//��Ƥ��
	Shape _eraser = new Shape(){
		public void dwonDraw(Graphics2D g2d, MouseEvent e) {
			x1 = e.getX();
			y1 = e.getY();
			g2d.setColor(Color.WHITE);
			g2d.fillArc(x1-eraser/2, y1-eraser/2, eraser, eraser, 0, 360);
		}
		public void moveDraw(Graphics2D g2d, MouseEvent e) {
			x2 = e.getX();
			y2 = e.getY();
			g2d.setColor(color.white);
			g2d.setStroke(new BasicStroke(eraser));
			g2d.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		}
		public void upDraw(Graphics2D g2d, MouseEvent e) {
		
		}
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}






































