import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class ASCIIViewer extends JFrame {
	JTextField tf1;
	JTextField tf2;
	JLabel lab1;
	JLabel lab2;
	JButton button1;
	JButton button2;
	public static void main(String arg[]) {
		ASCIIViewer that=new ASCIIViewer();
		that.go();
	}
	protected void go() {
		lab1=new JLabel("ת�������    ");
		lab2=new JLabel("ת�������    ");
		tf1=new JTextField(5);
		tf2=new JTextField(5);
		button1=new JButton("ת��");
		button2=new JButton("ת��");
		
		JPanel pane1=new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		pane1.add(new JLabel("�����ַ�:"));
		pane1.add(tf1);
		pane1.add(lab1);
		pane1.add(button1);
		JPanel pane2=new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		pane2.add(new JLabel("��������:"));
		pane2.add(tf2);
		pane2.add(lab2);
		pane2.add(button2);
		JPanel pane=new JPanel(new GridLayout(2,1));
		pane.add(pane1);
		pane.add(pane2);
		getContentPane().add(pane,BorderLayout.CENTER);
		
		pane1.setBorder(new EtchedBorder());
		pane2.setBorder(new EtchedBorder());
					
		setSize(350,150);
		setLocation(800,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				do_button1_actionPerformed(e);
			}
		});
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				do_button2_actionPerformed(e);
			}
		});
	}
	
	protected void do_button1_actionPerformed(ActionEvent e) {
		String str=tf1.getText();
		int code=Character.codePointAt(str,0);
		lab1.setText("ת�����:   "+code);
	}
	protected void do_button2_actionPerformed(ActionEvent e) {
		String str=tf2.getText();
		int code=Integer.parseInt(str);
		char[] cha=Character.toChars(code);
		lab2.setText("ת�������   "+new String(cha));
	}
	

}










