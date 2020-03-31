import javax.swing.*;

import com.mingrisoft.runtime.MemoryStatus.Memory;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MemoryStatus extends JFrame{
	JLabel lab1=new JLabel("�����ڴ棺");
	JLabel lab2=new JLabel("�ܹ��ڴ�:");
	JProgressBar jpb=new JProgressBar(SwingConstants.VERTICAL,0,100);//������ֱ����Сֵ0�����ֵ100�Ľ�����
	
	public static void main(String[] args) {
		MemoryStatus that=new MemoryStatus();
		that.go();
	}
	private void go() {
		JPanel pane=new JPanel(new GridLayout(2,1));
		pane.add(lab1);
		pane.add(lab2);
		getContentPane().add(pane,"South");
		getContentPane().add(jpb,"Center");
		jpb.setStringPainted(true);
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
		
//		pack();
		setSize(300,350);
		setLocation(400, 300);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	   
	}
	
	protected void do_this_windowActivated(WindowEvent e) {
	        new Update().start();
	    }
	
	private class Update extends Thread{
		public void run() {
			while(true) {
				System.gc();// ǿ����������������������ͷ��ڴ�
				int free=(int)Runtime.getRuntime().freeMemory()/1024; //��ȡ�����ڴ�(KB)
			    int total=(int)Runtime.getRuntime().totalMemory()/1024; //��ȡ�ܹ��ڴ�(KB)
			    int status=free*100/total; //ʹ����
			    lab1.setText("�����ڴ棺"+free+"KB");
			    lab2.setText("�ܹ��ڴ棺"+total+"KB");
			    jpb.setValue(status);
			    jpb.setString("�����ڴ棺"+status+"%");
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
