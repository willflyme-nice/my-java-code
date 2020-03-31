import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.net.InetAddress;

public class GetHostForme extends JFrame implements ActionListener{
	private JLabel lb1=new JLabel("��ȡ����������������������");
	private JLabel lb2=new JLabel("����:");
    private JLabel lb3=new JLabel("������:");
    private JTextField tf1=new JTextField();
    private JTextField tf2=new JTextField();
    private JButton button1=new JButton("��ȡ������������");
    private JButton button2=new JButton("�˳�ϵͳ");
    private InetAddress inetAddress; //������ַ
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetHostForme that=new GetHostForme();
		that.go();
		that.setVisible(true);
	}
	private void go() {
		setTitle("��ȡ����������������");
		setSize(400,300);
		setResizable(false);
		setLocation(450, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//����
		Container pane= this.getContentPane();
		pane.setLayout(null);
		lb1.setFont(new Font("����",Font.PLAIN,23));
		lb1.setForeground(Color.blue);
		lb1.setBounds(50,20,500,40);
		pane.add(lb1);
		lb2.setBounds(30,80,100,30);
		pane.add(lb2);
		lb3.setBounds(30,140,100,30);
		pane.add(lb3);
		tf1.setBounds(100,80,200,30);
		pane.add(tf1);
		tf2.setBounds(100,140,200,30);
		pane.add(tf2);
		button1.setBounds(50,210,150,30);
		pane.add(button1);
		button2.setBounds(220,210,100,30);
		pane.add(button2);
		
		//����¼�
		button1.addActionListener(this);
		button2.addActionListener(this);
		
		
		
	}
	public void actionPerformed(ActionEvent e) {
		
		try {
			inetAddress=InetAddress.getLocalHost(); //��ȡ����������ַ
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(e.getSource()==button1) {
			String canonical=inetAddress.getCanonicalHostName(); //��ȡ����
			String hostName=inetAddress.getHostName(); //��ȡ������
			tf1.setText(canonical);
			tf2.setText(hostName);
		}
		if(e.getSource()==button2) {
			System.exit(0);
		}
	}
}




