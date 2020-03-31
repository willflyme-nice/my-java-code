import java.util.Calendar;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class CalendarFrame extends JFrame{
	JButton b1=new JButton("上个月");
	JButton b2=new JButton("下个月");
	JLabel lab=new JLabel("2017年05月");
	Calendar calendar=new GregorianCalendar();
	JTable table=new JTable(6,7);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalendarFrame that=new CalendarFrame();
		that.go();
	}
	
	private void go() {
		JPanel pane1=new JPanel();
		pane1.add(b1);
		pane1.add(lab);
		pane1.add(b2);
		lab.setFont(new Font("微软雅黑",Font.PLAIN,15));
		b1.setFont(new Font("微软雅黑",Font.PLAIN,15));
		b2.setFont(new Font("微软雅黑",Font.PLAIN,15));
		JPanel pane2=new JPanel();
		pane2.setBorder(new EmptyBorder(5,5,5,5));
		JScrollPane jsp=new JScrollPane(table);
		jsp.setViewportView(table);
		pane2.add(jsp);
		getContentPane().add(pane1,"North");
		getContentPane().add(pane2,"Center");
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(20);
		table.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        table.getTableHeader().setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		updateLabel(0);
		updateTable();
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLabel(-1);
				updateTable();
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLabel(1);
				updateTable();
			}
		});
		
		setSize(489,300);
		setLocation(400, 300);
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    System.out.println(this.getSize());
	}
	
	//下面的函数用来更新标签月份
	private void updateLabel(int i) {
		calendar.add(Calendar.MONTH, i);
		SimpleDateFormat formatter=new SimpleDateFormat("y年MM月");
		String date =formatter.format(calendar.getTime());
		lab.setText(date);
	}
	
	//下面函数用来更新表格数据
	private void updateTable() {
		String[] weekday= {"日","一","二","三","四","五","六"};
		
		int monthDateNum=calendar.getMaximum(Calendar.DAY_OF_MONTH);//获取当月的最大日期
		
		int today=calendar.get(Calendar.DAY_OF_MONTH);//记录下当前日期
		calendar.set(Calendar.DAY_OF_MONTH , 1); //设置为当月第一天
		int firstWeekday=calendar.get(Calendar.DAY_OF_WEEK);//获取当月第一天是星期几
		int blankWeekday=firstWeekday-1; //获取当月第一个星期空缺多少天
		//建个二位数组保存单月数据
		Object[][] monthDays=new Object[6][7]; 
		for(int i=1;i<=monthDateNum;i++) {
			monthDays[(i+blankWeekday-1)/7][(i+blankWeekday-1)%7]=i;
		}
		
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		model.setRowCount(0);
		model.setDataVector(monthDays,weekday);
		table.setModel(model);
		
		table.setRowSelectionInterval(0,(today+blankWeekday-1)/7);
		table.setColumnSelectionInterval(0,(today+blankWeekday-1)/8);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}




