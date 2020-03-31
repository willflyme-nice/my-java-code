package filebrowseparts;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchAndReplacePanel extends JPanel{
	JTextField tf1 = new JTextField(10); 
	JButton b1 = new JButton("����");
	JTextField tf2 = new JTextField(10);
	JButton b2 = new JButton("�滻");
	
	String text;  //Ҫ���һ��滻��ԭ��
	int index;    //��һ������
	int start=-1; //��ǰ���ҵ�������
	int len=0;    //��ǰ���ҵ��ĳ���
	
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
	
	//Ϊ���Ұ�ť����¼�
	public void addSerchListener(ActionListener listener) {
		b1.addActionListener(listener);
	}
	
	//Ϊ�滻��ť����¼�
	public void addReplaceListener(ActionListener listener) {
		b2.addActionListener(listener);
	}
	
	//���ҹ���
	public MarkPosition doSearch() {
		start = text.indexOf(tf1.getText(), index);
		len = tf1.getText().length();
		if(start>=0) {
			index = start + len;
			return new MarkPosition(start, len);
		}else {
			index = 0;   //�Ҳ�����һ���򷵻��ı���ͷ
			return null;
		}	
	}
	
	//�滻����
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
			start = -1;  //�����ظ��滻
			return  mp;
		} 
		return null;
	}
}

//��ʾ�ı����λ��
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












