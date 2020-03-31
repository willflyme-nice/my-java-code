import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DateValidator extends JFrame{
	JLabel lab1=new JLabel(" �� �� �� ��   ");
	JTextField tf1=new JTextField(15);
	JLabel lab2=new JLabel("�������ڸ�ʽ");
	JTextField tf2=new JTextField(15);
	JButton button=new JButton("У��");
	
	public static void main(String[] args) {
		DateValidator that=new DateValidator();
		that.go();
	}

	private void go() {
		JPanel pane1=new JPanel();
		pane1.setBorder(new EmptyBorder(10,10,5,5));
		pane1.add(lab1);
		pane1.add(tf1);
		JPanel pane2=new JPanel();
		pane2.setBorder(new EmptyBorder(10,10,5,5));
		pane2.add(lab2);
		pane2.add(tf2);
		JPanel pane3=new JPanel();
		pane3.setBorder(new EmptyBorder(10,10,5,5));
		pane3.add(button);
		getContentPane().add(pane1,BorderLayout.NORTH);
		getContentPane().add(pane2,"Center");
		getContentPane().add(pane3,"South");
		
		lab1.setFont(new Font("΢���ź�",Font.PLAIN,18));
		tf1.setFont(new Font("΢���ź�",Font.PLAIN,18));
		lab2.setFont(new Font("΢���ź�",Font.PLAIN,18));
		tf2.setFont(new Font("΢���ź�",Font.PLAIN,18));
		button.setFont(new Font("΢���ź�",Font.PLAIN,18));
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPerformed();
			}
		});
		
		pack();
//		setSize(500,300);
		setLocation(400, 300);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void buttonPerformed() {
		String dateText=tf1.getText();
		String formText=tf2.getText();
		if(dateText.length()==0||formText.length()==0) {
			JOptionPane.showMessageDialog(this, "���ڻ��ʽ����Ϊ��","��ʾ����",JOptionPane.WARNING_MESSAGE);
			return;	
		}
		SimpleDateFormat format=new SimpleDateFormat(formText);
		
		try {
			format.parse(dateText);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "���ڸ�ʽ��ƥ��","��ʾ����",JOptionPane.WARNING_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(this, "���ڸ�ʽ�໥ƥ��","��ʾ����",JOptionPane.WARNING_MESSAGE);
		
	}
	
}
