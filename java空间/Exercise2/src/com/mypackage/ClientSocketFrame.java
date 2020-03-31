package com.mypackage;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.*;

/**
 * @author zhibo
 * <p>这是客户端用来通信的类，要结合服务器端进行通信</p>
 * @see ServerSocketFrame
 */
public class ClientSocketFrame extends JFrame{
	JTextArea ta_info=new JTextArea();
	JTextField tf=new JTextField(10);
	Socket socket;
	PrintStream send; //输出流
	BufferedReader rec; //输入流
	JScrollPane pane=new JScrollPane(ta_info);
	
	/**
	 * 静态方法：用来测试
	 * @param 用来存储命令行参数
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientSocketFrame that=new ClientSocketFrame ();
		that.go();
		that.setVisible(true);
	}
	
	/**
	 * 主程序
	 */
	public void go() {
		setTitle("客户端套接字");
		setSize(400,400);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//布局
		add(pane,"Center");
		add(tf,"South");
		
		//启动客户端线程
		Thread thread=new ConnectThread();
		thread.start();
		
		//添加事件
		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(socket==null) {
					JOptionPane.showMessageDialog(null, "请先进行连接才能对话");
					return;
				}
				send.println(tf.getText());
				ta_info.append("发送："+tf.getText()+"\n");
				tf.setText("");
			}
		});
	}
	
	/**
	 * 内部类：通信线程
	 */
	public class ConnectThread extends Thread{
		public void run() {
			while(true) {
				try {
					ta_info.append("正在连接...\n");
					socket=new Socket("192.168.3.53",1978); //创建连接到网络的套接字
					ta_info.append("连接成功...\n");
					ta_info.append("\n");
					
					send=new PrintStream(socket.getOutputStream(),true); //绑定输出流
					rec=new BufferedReader(new InputStreamReader(socket.getInputStream()));//绑定输入流
					
					//开始接收数据
					while(true) {
						ta_info.append("接收："+rec.readLine()+"\n"); 
					}
					
				}
				catch(IOException e) {
					ta_info.append("连接失败\n");
				}	
				finally {
					try {
						if(socket!=null) {
							socket.close(); //关闭套接字
						}
						if(rec!=null) {
							rec.close(); //关闭输入流
						}
						if(send!=null) {
							send.close(); //关输出流
						}	
					}
					catch(IOException e) {
						e.printStackTrace();
					}	
				}
				//等待1秒后再连接
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		}
		
	}

}

class OuterClass{
	int code = 0;
	public int getCode(){
		return code;
	}
}
