import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class ActionEventDemo implements MouseMotionListener,MouseListener{
	JFrame frame;
	JTextField tf;
	JTextField tf1;
	public static void main(String args[]) {
		ActionEventDemo that=new ActionEventDemo();
		that.go();
	}
	public void go() {
		frame=new JFrame("Action Event Demo");
		Container contentPane=frame.getContentPane();
		contentPane.add(new Label("Click and drad the mouse"),BorderLayout.WEST);
		tf=new JTextField(10);
		tf1=new JTextField(30);
		contentPane.add(tf,BorderLayout.SOUTH);
		contentPane.add(tf1,BorderLayout.NORTH);
		frame.addMouseMotionListener(this);
		frame.addMouseListener(this);
		
		frame.setBounds(400,300,500,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	//实现MouseMotionListener接口中的方法
	
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		String s="Mouse dragging:   X="+e.getX()+"Y="+e.getY();
		tf.setText(s);
	}
	
	//实现MouseListener接口中的方法
	
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void  mouseEntered(MouseEvent e) {
    	String s="The mouse entered";
    	tf1.setText(s);
    }
	public void  mouseExited(MouseEvent e) {
		String s="The mouse exited";
    	tf1.setText(s);
		
	}	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	}
