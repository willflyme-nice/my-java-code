package socket;
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
	BufferedReader rec; //������
	PrintWriter send;    //�����

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocketFrame that=new ServerSocketFrame();
		that.go();
		that.setVisible(true);
	}
	void go() {
		setTitle("���������׽���");
		setSize(400,400);
		setLocation(1500,50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//����
		add(new JScrollPane(ta_info),"Center");
		add(tf,"South");
		
		//����������߳�
		Thread thread=new ConnectThread();
		thread.start();
		
		//����¼�
		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(socket==null) {
					JOptionPane.showMessageDialog(null, "���Ƚ������Ӳ��ܶԻ�");
					return;
				}
				send.println(tf.getText());
				ta_info.append("���ͣ�"+tf.getText()+"\n");
				tf.setText("");
			}
		});
	}
	
	class ConnectThread extends Thread{
		public void run() {
			ta_info.append("�������׽����Ѿ������ɹ�\n");
			while(true) {
				try {
					server=new ServerSocket(1978);
					ta_info.append("�ȴ��ͻ���������...\n");
					socket=server.accept();  //�������ӵ����׽��ֵ����׽���,�˴��γ�����
					
					ta_info.append("���ӳɹ�...\n");	
					ta_info.append("��ʼͨ��...\n");	
					ta_info.append("\n");
					
					rec=new BufferedReader(new InputStreamReader(socket.getInputStream()));//��������
					send=new PrintWriter(socket.getOutputStream(),true); //�������
					
					//��ʼ��������
					while(true) {
						ta_info.append("���գ�"+rec.readLine()+"\n");
					}
					
				}
				catch(IOException e) {
					ta_info.append("�ͻ����˳�����...\n\n");	
				}
				finally {
					try {
						if(server!=null) {
							server.close(); //�ر��׽���
						}
						if(socket!=null) {
							socket.close(); //�ر��׽���
						}	
						if(rec!=null) {
							rec.close(); //�ر�������
						}
						if(send!=null) {
							send.close(); //�ر���� ��
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
