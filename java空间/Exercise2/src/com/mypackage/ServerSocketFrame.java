package com.mypackage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import javax.swing.*;
import java.net.*;

public class ServerSocketFrame extends JFrame{
	JTextArea ta_info=new JTextArea();
	JTextField tf=new JTextField();
	ServerSocket server;
	Socket socket;
	BufferedReader rec; //输入流
	PrintWriter send;    //输出流

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocketFrame that=new ServerSocketFrame();
		that.go();
		that.setVisible(true);
	}
	void go() {
		setTitle("服务器端套接字");
		setSize(400,400);
		setLocation(1500,50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//布局
		add(new JScrollPane(ta_info),"Center");
		add(tf,"South");
		
		//启动服务端线程
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
	
	class ConnectThread extends Thread{
		public void run() {
			ta_info.append("服务器套接字已经创建成功\n");
			while(true) {
				try {
					server=new ServerSocket(1978);
					ta_info.append("等待客户机的连接...\n");
					socket=server.accept();  //侦听连接到此套接字的新套接字,此处形成阻塞
					
					ta_info.append("连接成功...\n");	
					ta_info.append("开始通话...\n");	
					ta_info.append("\n");
					
					rec=new BufferedReader(new InputStreamReader(socket.getInputStream()));//绑定输入流
					send=new PrintWriter(socket.getOutputStream(),true); //绑定输出流
					
					//开始接收数据
					while(true) {
						ta_info.append("接收："+rec.readLine()+"\n");
					}
					
				}
				catch(IOException e) {
					ta_info.append("客户机退出连接...\n\n");	
				}
				finally {
					try {
						if(server!=null) {
							server.close(); //关闭套接字
						}
						if(socket!=null) {
							socket.close(); //关闭套接字
						}	
						if(rec!=null) {
							rec.close(); //关闭输入流
						}
						if(send!=null) {
							send.close(); //关闭输出 流
						}
					}
					catch(IOException e) {
						e.printStackTrace();
					}	
				}
				
			}
				
		}
		
		
		
	}
	
	

}
