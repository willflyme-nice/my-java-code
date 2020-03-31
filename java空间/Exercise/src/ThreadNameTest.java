import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class ThreadNameTest {
	private JFrame frame = new JFrame("�̼߳��������");
	private JTable table = new JTable();
	private JTextField tf1 = new JTextField(10);
	private JTextField tf2 = new JTextField(10);
	private JButton b1 = new JButton("�½��߳�");
	private JButton b2 = new JButton("�޸�����");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadNameTest that= new ThreadNameTest();
		that.go();
		that.setVisual();
	}
	
	protected void go() {
		b1.setFont(new Font("��Բ",Font.PLAIN,20));
		b2.setFont(new Font("��Բ",Font.PLAIN,20));
		tf1.setFont(new Font("��Բ",Font.PLAIN,20));
		tf2.setFont(new Font("��Բ",Font.PLAIN,20));
		table.setFont(new Font("��Բ",Font.PLAIN,20));
		table.setRowHeight(30);
		table.setSelectionBackground(Color.green);
		
		Container cp=frame.getContentPane();
		cp.add(new JScrollPane(table));
		JPanel panel=new JPanel();
		panel.add(tf1);
		panel.add(b1);
		panel.add(tf2);
		panel.add(b2);
		cp.add(panel,"South");
		
//		frame.addWindowListener(new WindowAdapter() { //��Ӵ��ڼ����¼�
//			public void windowActivated(WindowEvent e) {
//				windowPerform();
//			}
//		});
		
		new UpdateTable("�����߳�").start();
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b1Perform();
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b2Perform();
			}
		});
		
		
		
	}
	
	protected void setVisual() {
		frame.setSize(500,300);
		frame.setLocation(400, 300);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	protected void windowPerform() {
		ThreadGroup group = Thread.currentThread().getThreadGroup();
		Thread[] threads = new Thread[group.activeCount()];
		group.enumerate(threads); //����ǰ�Ļ�̷߳�������
		//�����Ǹ��±��
		DefaultTableModel model =(DefaultTableModel)table.getModel();
		model.setRowCount(0);
		model.setColumnIdentifiers(new Object[]{"�߳�����","�߳�ID"});
		for(Thread thread:threads) {
			model.addRow(new Object[] {thread.getName(),thread.getId()});
		}
		table.setModel(model);	
	}
	
	protected void b1Perform() {
		Thread t1;
		String name=tf1.getText().trim();
		System.out.println(name.isEmpty()?"���ַ�":name);
		if(name.isEmpty()) {
			t1=new Forever();
		}
		else {
			t1=new Forever(name); 
		}
		t1.start();
	}
	
	protected void b2Perform() {
		String name=tf2.getText().trim();
		int select=table.getSelectedRow();
		
		
	}
	
	private class UpdateTable extends Thread{
		UpdateTable(String str){
			super(str);
		}
		public void run() {
			while(true) {
				windowPerform(); //ÿ1����±�
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
	private class Forever extends Thread{
		Forever(){
			super();
		}
		Forever(String str){
			super(str);
		}
		 public void run() {
			 while(true) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			 }
		 }	
	}
	
}



