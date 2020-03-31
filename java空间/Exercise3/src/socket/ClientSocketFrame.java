package socket;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.*;

/*
 * @author zhibo
 * <p>���ǿͻ�������ͨ�ŵ��࣬Ҫ��Ϸ������˽���ͨ��</p>
 * @see ServerSocketFrame.java
 */
public class ClientSocketFrame extends JFrame{
	JTextArea ta_info=new JTextArea();
	JTextField tf=new JTextField(10);
	Socket socket;
	PrintStream send; //�����
	BufferedReader rec; //������
	JScrollPane pane=new JScrollPane(ta_info);
	
	/*
	 * ��̬��������������
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientSocketFrame that=new ClientSocketFrame ();
		that.go();
		that.setVisible(true);
	}
	
	/*
	 * �������
	 */
	void go() {
		setTitle("�ͻ����׽���");
		setSize(400,400);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		//����
		add(pane,"Center");
		add(tf,"South");
		
		//�����ͻ����߳�
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
	
	/*
	 * �ڲ��ࣺͨ���߳�
	 */
	class ConnectThread extends Thread{
		public void run() {
			while(true) {
				try {
					ta_info.append("��������...\n");
					socket=new Socket("192.168.3.53",1978); //�������ӵ�������׽���
					ta_info.append("���ӳɹ�...\n");
					ta_info.append("\n");
					
					send=new PrintStream(socket.getOutputStream(),true); //�������
					rec=new BufferedReader(new InputStreamReader(socket.getInputStream()));//��������
					
					//��ʼ��������
					while(true) {
						ta_info.append("���գ�"+rec.readLine()+"\n"); 
					}
					
				}
				catch(IOException e) {
					ta_info.append("����ʧ��\n");
				}	
				finally {
					try {
						if(socket!=null) {
							socket.close(); //�ر��׽���
						}
						if(rec!=null) {
							rec.close(); //�ر�������
						}
						if(send!=null) {
							send.close(); //�������
						}	
					}
					catch(IOException e) {
						e.printStackTrace();
					}	
				}
				//�ȴ�1���������
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
