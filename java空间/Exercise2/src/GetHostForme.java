import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.net.InetAddress;

public class GetHostForme extends JFrame implements ActionListener{
	private JLabel lb1=new JLabel("获取本地主机的域名和主机名");
	private JLabel lb2=new JLabel("域名:");
    private JLabel lb3=new JLabel("主机名:");
    private JTextField tf1=new JTextField();
    private JTextField tf2=new JTextField();
    private JButton button1=new JButton("获取域名与主机名");
    private JButton button2=new JButton("退出系统");
    private InetAddress inetAddress; //主机地址
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetHostForme that=new GetHostForme();
		that.go();
		that.setVisible(true);
	}
	private void go() {
		setTitle("获取本机域名和主机名");
		setSize(400,300);
		setResizable(false);
		setLocation(450, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//布局
		Container pane= this.getContentPane();
		pane.setLayout(null);
		lb1.setFont(new Font("宋体",Font.PLAIN,23));
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
		
		//添加事件
		button1.addActionListener(this);
		button2.addActionListener(this);
		
		
		
	}
	public void actionPerformed(ActionEvent e) {
		
		try {
			inetAddress=InetAddress.getLocalHost(); //获取本地主机地址
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(e.getSource()==button1) {
			String canonical=inetAddress.getCanonicalHostName(); //获取域名
			String hostName=inetAddress.getHostName(); //获取主机名
			tf1.setText(canonical);
			tf2.setText(hostName);
		}
		if(e.getSource()==button2) {
			System.exit(0);
		}
	}
}




