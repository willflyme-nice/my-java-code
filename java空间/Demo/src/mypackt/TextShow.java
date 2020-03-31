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
		
		frame=new JFrame("我的文本阅读器");
		Container contentPane=frame.getContentPane();
		area=new MyJTextArea();
		scro=new JScrollPane(area);
		contentPane.add(scro);
		
		FileInputStream fis=new FileInputStream("E:/软件学习/程序代码/51程序模版.txt");
		 BufferedInputStream buf=new BufferedInputStream(fis);
		 FileOutputStream fos=new FileOutputStream("E:/软件学习/程序代码/51程序模版副本.txt");
		 BufferedOutputStream buof=new BufferedOutputStream(fos);
		int n;
		byte ch[] =new byte[fis.available()];
		area.setFont(new Font("浪漫雅圆",Font.PLAIN,15));
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
			Font f=new Font("浪漫雅圆",Font.PLAIN,50);
			g.setFont(f);
		
			
		}
		
		
	}
	
	
	
	
}