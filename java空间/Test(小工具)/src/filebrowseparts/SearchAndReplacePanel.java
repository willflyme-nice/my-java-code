package filebrowseparts;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchAndReplacePanel extends JPanel{
	JTextField tf1 = new JTextField(10); 
	JButton b1 = new JButton("查找");
	JTextField tf2 = new JTextField(10);
	JButton b2 = new JButton("替换");
	
	String text;  //要查找或替换的原文
	int index;    //下一次索引
	int start=-1; //当前查找到的索引
	int len=0;    //当前查找到的长度
	
	public SearchAndReplacePanel() {
		super();
		add(tf1);
		add(b1);
		add(tf2);
		add(b2);
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	//为查找按钮添加事件
	public void addSerchListener(ActionListener listener) {
		b1.addActionListener(listener);
	}
	
	//为替换按钮添加事件
	public void addReplaceListener(ActionListener listener) {
		b2.addActionListener(listener);
	}
	
	//查找工作
	public MarkPosition doSearch() {
		start = text.indexOf(tf1.getText(), index);
		len = tf1.getText().length();
		if(start>=0) {
			index = start + len;
			return new MarkPosition(start, len);
		}else {
			index = 0;   //找不到下一个则返回文本开头
			return null;
		}	
	}
	
	//替换工作
	public MarkPosition doReplace() {
		System.out.println("doReplace()");
		if(start>=0) {
			StringBuffer sbuf = new StringBuffer();
			if(start>0)
				sbuf.append(text.substring(0, start));
			sbuf.append(tf2.getText());
			sbuf.append(text.substring(start+len, text.length()-1));
			String result = sbuf.toString();
			text = result;
			
			len = tf2.getText().length();
			MarkPosition mp = new MarkPosition(start, len);
			index = start + len;
			start = -1;  //避免重复替换
			return  mp;
		} 
		return null;
	}
}

//表示文本标记位置
class MarkPosition{
	private int start;
	private int len;
	
	public MarkPosition(int start, int len) {
		this.start = start;
		this.len = len;
	}
	public int getLen() {
		return len;
	}
	public int getStart() {
		return start;
	}
	
}












