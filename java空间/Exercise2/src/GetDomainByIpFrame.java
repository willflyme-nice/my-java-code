import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.net.InetAddress;

public class GetDomainByIpFrame extends JFrame implements ActionListener{
	private JLabel lb=new JLabel("获取任意IP地址的域名和主机名");
	private JLabel lb1=new JLabel("IP地址:");
	private JLabel lb2=new JLabel("域名:");
    private JLabel lb3=new JLabel("主机名:");
    private JTextField tf1=new JTextField();
    private JTextField tf2=new JTextField();
    private JTextField tf3=new JTextField();
    private JButton button1=new JButton("获取域名与主机名");
    private JButton button2=new JButton("退出系统");
    private InetAddress inetAddress; //主机地址
    
	public static void main(String[] args) {
		GetDomainByIpFrame that=new GetDomainByIpFrame();
		that.go();
		that.setVisible(true);
	}
	private void go() {
		setTitle("域名和主机名");
		setSize(400,300);
		setResizable(false);
		setLocation(450, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//布局
		Container pane= this.getContentPane();
		pane.setLayout(null);
		lb.setFont(new Font("宋体",Font.PLAIN,23));
		lb.setForeground(Color.blue);
		lb.setBounds(50,20,500,40);
		pane.add(lb);
		lb1.setBounds(30,60,100,30);
		pane.add(lb1);
		lb2.setBounds(30,100,100,30);
		pane.add(lb2);
		lb3.setBounds(30,140,100,30);
		pane.add(lb3);
		tf1.setBounds(100,60,200,30);
		pane.add(tf1);
		tf2.setBounds(100,100,200,30);
		pane.add(tf2);
		tf3.setBounds(100,140,200,30);
		pane.add(tf3);
		button1.setBounds(50,210,150,30);
		pane.add(button1);
		button2.setBounds(220,210,100,30);
		pane.add(button2);
		
		//添加事件
		button1.addActionListener(this);
		button2.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button1) {
			String ip=tf1.getText();
			String[] ipStrs=ip.split("[.]");
			byte[] ipByte=new byte[4];
			for(int i=0;i<4;i++) {
				int m=Integer.parseInt(ipStrs[i]);
				ipByte[i]=(byte)(m & 0xff);
			}
			
			try {
				inetAddress=InetAddress.getByAddress(ipByte); //获取字节数组指定的IP地址的主机地址
				String canonical=inetAddress.getCanonicalHostName(); //获取域名
				String hostName=inetAddress.getHostName(); //获取主机名
				tf2.setText(canonical);
				tf3.setText(hostName);
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
		}
		if(e.getSource()==button2) {
			System.exit(0);
		}
	}
		
}




