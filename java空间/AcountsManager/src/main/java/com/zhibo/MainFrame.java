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

	private JTable table = new JTable(); 	  //表格
	private List dataList = new ArrayList();  //表格数据列表(只存key)
	private DataModel processor = new TableDataModel();  //表格所使用的数据模型
	private ViewSelect viewSel = ViewSelect.link; //表格当前所使用的视图的选择器
	
	private JTextField addKey = new JTextField(10);
	private JTextField addValue = new JTextField(20);
	private JButton addItem = new JButton("添加");
	private JButton alterItem = new JButton("修改");
	private JButton deleteItem = new JButton("删除");
	private JButton saveFile = new JButton("保存"); 
	private JButton switchView = new JButton("切换视图");
	private JLabel infoBar = new JLabel("信息：");  
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
		add(box,"North"); 	         //主窗体添加搜索栏面板
		
		table.setFont(new Font("宋体", Font.PLAIN, 15));
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
		add(tablePane,"Center");     //主窗体添加表格面板（表格面板暂时隐藏）
		
		//绑定主窗体事件，点击关闭按钮时弹出对话框
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(processor.getModified() == false) {
					System.exit(0);
				} 
				int res = JOptionPane.showConfirmDialog(MainFrame.this, "是否要保存修改?","提示",JOptionPane.YES_NO_CANCEL_OPTION);
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
		
		//绑定查询按钮事件
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSearch();
			}
		});
		
		//绑定回车键事件
		tf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					doSearch();
				}
			}
			
		});
		
		//点击“+”按钮展开列表
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
		
		//添加项目按钮
		addItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = addKey.getText().trim();
				boolean b = processor.add(key, addValue.getText());
				if(b == true) {
					updateTableData();
					infoBar.setText("信息：添加记录成功!");
				}else {
					infoBar.setText("信息：添加记录失败，请检查键值是否重复或为空");
				}
			}
		});
		
		//修改项目按钮
		alterItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i < 0) {
					infoBar.setText("信息：请选择要修改的行");
					return;
				}
				new MyDialog(MainFrame.this, "修改项目", i).setVisible(true);
			}
		});
		
		//删除项目按钮
		deleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i < 0) {
					infoBar.setText("信息：请选择要删除的行");
					return;
				}
				String key = (String)table.getValueAt(i, 0);
				String value = (String)table.getValueAt(i, 1);
				int res = JOptionPane.showConfirmDialog(MainFrame.this, "您确定要删除一下项目吗：\n"+key+":"+value);
				if(res == JOptionPane.YES_OPTION) { 
					processor.delete(key);
					updateTableData();
					infoBar.setText("信息：已删除选中的记录:"+key+":"+value);
				}else {
					infoBar.setText("信息：没有删除任何记录");
				}
			}
		});
		
		//保存按钮
		saveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b = processor.save();
				if(b == true) {
					processor.save();
					infoBar.setText("信息：已保存至文件！");
				}else {
					infoBar.setText("信息：无修改记录，没有保存");
				}
			}
		});
		
		//切换视图按钮
		switchView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewSel = viewSel.next(); //切换视图选择器
				updateTableData(); //更新表格
			}
		});
		
	}
	
	//封装函数1：查询记录
	private void doSearch() {
		String key = tf.getText().trim();
		String res;
		if(key.equals("")) {
			res = "请输入关键字";
		} else {
			res = processor.search(key);
		}
		tf.setText(key + " : " + res);
		tf.selectAll();
		tf.requestFocus();
		//选中对应的表记录行
		if(res!=null) {
			int index =dataList.indexOf(key);
			table.setRowSelectionInterval(index, index);
		}	
	}
	
	//封装函数2：更新表格数据
	public void updateTableData() {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.setRowCount(0);
		Map<String,String> items = processor.getItems(viewSel); //获取指定的视图
		dataList.clear();
		for(Entry<String,String>item : items.entrySet()) {
			dataList.add(item.getKey());
			model.addRow(new String[]{item.getKey(),item.getValue()});
		}
		int sum = items.size();
		model.addRow(new String[] {"总记录数", Integer.toString(sum)});
	}
	
	//入口函数
	public static void main(String arg[]) {
		MainFrame f = new MainFrame();
		f.setVisible(true);
		f.pack();
		f.setLocation(100, 100);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	//内部类：自定义对话框
	class MyDialog extends JDialog{
		JLabel lab = new JLabel("");
		JTextField text = new JTextField(15);
		JButton confirm = new JButton("确定");
		JButton cancel = new JButton("取消");
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
						infoBar.setText("信息：已经修改");
						setVisible(false);
					}else {
						infoBar.setText("信息：没有修改");
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
