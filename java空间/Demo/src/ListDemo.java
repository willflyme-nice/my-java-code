import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;

public class ListDemo{
	JFrame frame=new JFrame("This is JTextArea Demo");
	JTextArea ta1,ta2;
	JButton copy,clear;
	
	public static void main(String args[]) throws Exception{
		
		ListDemo that=new ListDemo ();that.go();
				
	}
	
	public void go() throws Exception{
		ta1=new JTextArea("01234",3,15);
		ta1.selectAll();
		ta1.setSelectedTextColor(Color.red);
		ta2=new JTextArea(7,20);
		ta2.setEditable(false);
		JScrollPane jp1=new JScrollPane(ta1);
		JScrollPane jp2=new JScrollPane(ta2);
		Icon icon=new ImageIcon("image/04.jpg");
		copy=new JButton("Copy",icon);
		clear=new JButton("Clear");
		
		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ta1.getSelectedText()!=null) {
					ta2.append(ta1.getSelectedText()+"\n");
				}
				else {
					ta2.append(ta1.getText()+"\n");
				}
				ta1.setText("");
			}
		});
		
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ta2.setText("");
			}
			
		});
		
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		Border etched=BorderFactory.createEtchedBorder();
		Border border1=BorderFactory.createTitledBorder(etched,"������");
		Border border2=BorderFactory.createTitledBorder(etched,"������");
		panel1.setBorder(border1);
		panel2.setBorder(border2);
		panel1.add(jp1);
		panel1.add(copy);
		panel2.add(jp2);
		panel2.add(clear);
		Container cp=frame.getContentPane();
		cp.add(panel1);
		cp.add(panel2, "South");
		
		
		frame.setBounds(300,200,300,200);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		}
	
	
	
		
}