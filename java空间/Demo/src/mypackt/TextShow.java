package mypackt;
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class TextShow {
	JFrame frame;
	MyJTextArea area;
	JScrollPane scro;
	
	
	public static void main(String args[]) throws Exception{
		
		TextShow that=new TextShow();that.go();
		
			
	}
	
	public void go() throws Exception{
		
		frame=new JFrame("�ҵ��ı��Ķ���");
		Container contentPane=frame.getContentPane();
		area=new MyJTextArea();
		scro=new JScrollPane(area);
		contentPane.add(scro);
		
		FileInputStream fis=new FileInputStream("E:/���ѧϰ/�������/51����ģ��.txt");
		 BufferedInputStream buf=new BufferedInputStream(fis);
		 FileOutputStream fos=new FileOutputStream("E:/���ѧϰ/�������/51����ģ�渱��.txt");
		 BufferedOutputStream buof=new BufferedOutputStream(fos);
		int n;
		byte ch[] =new byte[fis.available()];
		area.setFont(new Font("������Բ",Font.PLAIN,15));
		int i=0;
		while((n=buf.read(ch))!=-1) {
			buof.write(ch);
			buof.flush();
			
		}
		buf.close();
		buof.close();
		frame.setBounds(200,100,800,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		
		
		buf.close();
		
	}
	
	class MyJTextArea extends JTextArea{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Font f=new Font("������Բ",Font.PLAIN,50);
			g.setFont(f);
		
			
		}
		
		
	}
	
	
	
	
}