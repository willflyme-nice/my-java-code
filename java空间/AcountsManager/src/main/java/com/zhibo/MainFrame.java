package com.zhibo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame{
	private JTextField tf = new JTextField(20);
	private JButton search = new JButton(new ImageIcon("src/main/resources/search_mini.png"));
	private JButton showTable = new JButton(new ImageIcon("src/main/resources/add_mini.png"));

	private JTable table = new JTable(); 	  //���
	private List dataList = new ArrayList();  //��������б�(ֻ��key)
	private DataModel processor = new TableDataModel();  //�����ʹ�õ�����ģ��
	private ViewSelect viewSel = ViewSelect.link; //���ǰ��ʹ�õ���ͼ��ѡ����
	
	private JTextField addKey = new JTextField(10);
	private JTextField addValue = new JTextField(20);
	private JButton addItem = new JButton("���");
	private JButton alterItem = new JButton("�޸�");
	private JButton deleteItem = new JButton("ɾ��");
	private JButton saveFile = new JButton("����"); 
	private JButton switchView = new JButton("�л���ͼ");
	private JLabel infoBar = new JLabel("��Ϣ��");  
	private JPanel tablePane = new JPanel(new BorderLayout());
	
	
	
	public MainFrame() {
		search.setContentAreaFilled(false);
		search.setBorder(null);
		showTable.setContentAreaFilled(false);
		showTable.setBorder(null);
		
		Box box = Box.createHorizontalBox();
		box.add(tf);
		box.add(search);
		box.add(Box.createHorizontalStrut(20));
		box.add(showTable);
		box.setBorder(new EmptyBorder(5,5,5,5));
		add(box,"North"); 	         //������������������
		
		table.setFont(new Font("����", Font.PLAIN, 15));
		table.setRowHeight(19);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addColumn("acount");
		model.addColumn("password");
		updateTableData();
		
		tablePane.add(new JScrollPane(table),"North");
		JPanel panel = new JPanel();
		panel.add(addKey);
		panel.add(addValue);
		panel.add(addItem);
		panel.add(alterItem);
		panel.add(deleteItem);
		panel.add(saveFile);
		panel.add(switchView);
		tablePane.add(panel,"Center");
		tablePane.add(infoBar,"South");
		tablePane.setVisible(false);
		add(tablePane,"Center");     //��������ӱ����壨��������ʱ���أ�
		
		//���������¼�������رհ�ťʱ�����Ի���
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(processor.getModified() == false) {
					System.exit(0);
				} 
				int res = JOptionPane.showConfirmDialog(MainFrame.this, "�Ƿ�Ҫ�����޸�?","��ʾ",JOptionPane.YES_NO_CANCEL_OPTION);
				if(res == JOptionPane.YES_OPTION) {
					processor.save();
					System.exit(0);
				}else if(res == JOptionPane.NO_OPTION) {
					System.exit(0);
				}else {
					return;
				}		
			}
		});
		
		//�󶨲�ѯ��ť�¼�
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSearch();
			}
		});
		
		//�󶨻س����¼�
		tf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					doSearch();
				}
			}
			
		});
		
		//�����+����ťչ���б�
		showTable.addActionListener(new ActionListener() {
			private boolean isShow = false;
			public void actionPerformed(ActionEvent e) {
				if(isShow) {
					tablePane.setVisible(false);
					isShow = false;
				}else {
					tablePane.setVisible(true);
					isShow = true;
				}
				MainFrame.this.pack();
			}
		});
		
		//�����Ŀ��ť
		addItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = addKey.getText().trim();
				boolean b = processor.add(key, addValue.getText());
				if(b == true) {
					updateTableData();
					infoBar.setText("��Ϣ����Ӽ�¼�ɹ�!");
				}else {
					infoBar.setText("��Ϣ����Ӽ�¼ʧ�ܣ������ֵ�Ƿ��ظ���Ϊ��");
				}
			}
		});
		
		//�޸���Ŀ��ť
		alterItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i < 0) {
					infoBar.setText("��Ϣ����ѡ��Ҫ�޸ĵ���");
					return;
				}
				new MyDialog(MainFrame.this, "�޸���Ŀ", i).setVisible(true);
			}
		});
		
		//ɾ����Ŀ��ť
		deleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i < 0) {
					infoBar.setText("��Ϣ����ѡ��Ҫɾ������");
					return;
				}
				String key = (String)table.getValueAt(i, 0);
				String value = (String)table.getValueAt(i, 1);
				int res = JOptionPane.showConfirmDialog(MainFrame.this, "��ȷ��Ҫɾ��һ����Ŀ��\n"+key+":"+value);
				if(res == JOptionPane.YES_OPTION) { 
					processor.delete(key);
					updateTableData();
					infoBar.setText("��Ϣ����ɾ��ѡ�еļ�¼:"+key+":"+value);
				}else {
					infoBar.setText("��Ϣ��û��ɾ���κμ�¼");
				}
			}
		});
		
		//���水ť
		saveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b = processor.save();
				if(b == true) {
					processor.save();
					infoBar.setText("��Ϣ���ѱ������ļ���");
				}else {
					infoBar.setText("��Ϣ�����޸ļ�¼��û�б���");
				}
			}
		});
		
		//�л���ͼ��ť
		switchView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewSel = viewSel.next(); //�л���ͼѡ����
				updateTableData(); //���±��
			}
		});
		
	}
	
	//��װ����1����ѯ��¼
	private void doSearch() {
		String key = tf.getText().trim();
		String res;
		if(key.equals("")) {
			res = "������ؼ���";
		} else {
			res = processor.search(key);
		}
		tf.setText(key + " : " + res);
		tf.selectAll();
		tf.requestFocus();
		//ѡ�ж�Ӧ�ı��¼��
		if(res!=null) {
			int index =dataList.indexOf(key);
			table.setRowSelectionInterval(index, index);
		}	
	}
	
	//��װ����2�����±������
	public void updateTableData() {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.setRowCount(0);
		Map<String,String> items = processor.getItems(viewSel); //��ȡָ������ͼ
		dataList.clear();
		for(Entry<String,String>item : items.entrySet()) {
			dataList.add(item.getKey());
			model.addRow(new String[]{item.getKey(),item.getValue()});
		}
		int sum = items.size();
		model.addRow(new String[] {"�ܼ�¼��", Integer.toString(sum)});
	}
	
	//��ں���
	public static void main(String arg[]) {
		MainFrame f = new MainFrame();
		f.setVisible(true);
		f.pack();
		f.setLocation(100, 100);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	//�ڲ��ࣺ�Զ���Ի���
	class MyDialog extends JDialog{
		JLabel lab = new JLabel("");
		JTextField text = new JTextField(15);
		JButton confirm = new JButton("ȷ��");
		JButton cancel = new JButton("ȡ��");
		public MyDialog(JFrame f, String t, int i) {
			super(f, t);
			setModal(true);
			setLayout(new FlowLayout());
			add(lab);
			add(text);
			add(confirm);
			add(cancel);
			setBounds(100,100,280,120);
			final String key = (String)table.getValueAt(i, 0);
			final String value = (String)table.getValueAt(i, 1);
			lab.setText(key);
			text.setText(value);
			confirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!text.getText().equals(value)) {
						processor.update(key, text.getText());
						updateTableData();
						infoBar.setText("��Ϣ���Ѿ��޸�");
						setVisible(false);
					}else {
						infoBar.setText("��Ϣ��û���޸�");
						setVisible(false);
					}
				}
			});
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}
	}
	
}
