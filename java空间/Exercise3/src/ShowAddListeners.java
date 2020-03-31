import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mypack.util.SwingConsole;

/**利用反射来查找Swing组件的addListener方法*/
public class ShowAddListeners extends JFrame{
	private JTextField tf = new JTextField(10);
	private JTextArea ta = new JTextArea(20,15);
	
	private Pattern pat1= Pattern.compile("add\\w+Listener");
	
	private class mListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = tf.getText().trim();
			if(name.equals("")) {
				ta.setText("没有输入类名");
				return;
			}
			try {
				Class cl = Class.forName("javax.swing."+name);
				Method[] methods = cl.getMethods();	
				ta.setText(name+"的addxxxListener方法如下:\n");
				for(Method method : methods) {
					String methodName = method.getName();
					System.out.println(methodName);
					Matcher matcher= pat1.matcher(methodName);
					if(matcher.find()) ta.append(methodName+"\n");
				}
			} catch (ClassNotFoundException e1) {
				ta.setText("没有这个类");
			} 
		}
	}
	
	public ShowAddListeners(){
		JPanel pane = new JPanel();
		pane.add(new JLabel("Swing类名"));
		pane.add(tf);
		JButton but = new JButton("查询"); 
		pane.add(but);
		add(pane);
		add(new JScrollPane(ta), BorderLayout.SOUTH);
		but.addActionListener(new mListener());
		
	}
	
	public static void main(String arg[]) {
		SwingConsole.run(new ShowAddListeners(), 500, 500);
	}
}














 