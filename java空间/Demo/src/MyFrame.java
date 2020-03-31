import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MyFrame extends JFrame implements ActionListener{
	JLabel lb1,lb2;
	JTextArea ta;
	JComboBox cb;
	JButton close;
	
	MyFrame(String s){
		super(s);
		lb1=new JLabel("Information");
		lb2=new JLabel("Select Name");
		ta=new JTextArea("Select name from combobox",10,17);
		ta.setLineWrap(true);
		JScrollPane jsp=new JScrollPane(ta);
		close=new JButton("Close");
		String name[]= {"Tom      ","Jack","Mary","Linda"};
		cb=new JComboBox(name);
		cb.addActionListener(this);
		close.addActionListener(this);
		
		JPanel panel1=new JPanel(new FlowLayout(FlowLayout.LEFT,20,20));
		panel1.add(lb1);
		panel1.add(jsp);
		JPanel panel2=new JPanel(new FlowLayout(FlowLayout.LEFT,60,20));
		panel2.add(lb2);
		panel2.add(cb);
		panel2.add(Box.createRigidArea(new Dimension(200,80)));
		Box box=Box.createVerticalBox();
		box.add(Box.createGlue());
		box.add(close);
		panel2.add(box);
		
		Container cp=this.getContentPane();
		cp.setLayout(new GridLayout(1,2));
		cp.add(panel1);
		cp.add(panel2);
		
		this.setSize(450,300);
		this.setVisible(true);
		this.setLocation(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[]arg) {
		new MyFrame("My Frame");
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==cb) {
		     String imformation[]= {
				 "Tom is a good boy.He like playing basketeball.He study very hard.",
				 "Jack is also a good boy as Tom is.He and Tom are very good friends.",
				 "Mary is a smart girl,she like English.Her study is very goog.Many students ofen asking help"
				  + "in study to her",
				 "Linda is beautiful girl ,she have many like such as eating working"		
		     };
		
		     int i=cb.getSelectedIndex();
		     ta.setText(imformation[i]);
		}
		if(e.getSource()==close) {
			JDialog dialog=new JDialog(this,"Are you sure close",true);
			dialog.setSize(200, 200);
			
			dialog.setVisible(true);
		}
	}
	
	MyDraw myDraw = new MyDraw();
	
}




