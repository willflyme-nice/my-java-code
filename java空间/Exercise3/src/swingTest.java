import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mypack.util.SwingConsole;

public class swingTest extends JFrame{
	JTextField tf = new JTextField(15);
	JButton but = new JButton("�л��˵���");
	
	JMenuBar bar = new JMenuBar();
	JMenuBar bar2 = new JMenuBar();

	JMenu fileMenu = new JMenu("file");
	JMenu editMenu = new JMenu("edit");
	JMenu helpMenu = new JMenu("help");
	
	JMenuItem[] file = {
			new JMenuItem("new",KeyEvent.VK_N ),
			new JMenuItem("open",KeyEvent.VK_O),
			new JMenuItem("save",KeyEvent.VK_S)
	};
	JMenuItem[] edit = {
			new JMenuItem("copy"),
			new JMenuItem("paste"),
			new JMenuItem("delet")
	};
	JMenu about = new JMenu("about");
	JMenuItem aboutFile = new JCheckBoxMenuItem("about file");
	JMenuItem aboutApp = new JMenuItem("about app");
	
	public swingTest() {
		for(JMenuItem item : file) {
			fileMenu.add(item);
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("��ݼ�");
				}
			});
		}
		for(JMenuItem item : edit) {
			editMenu.add(item);
		}
		helpMenu.add(about);
		about.add(aboutApp);
		about.add(aboutFile);
		bar.add(fileMenu);
		bar.add(editMenu);
		bar.add(helpMenu);
		setJMenuBar(bar);
		
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JMenuBar currentbar = getJMenuBar();
				setJMenuBar(currentbar==bar?bar2:bar);
				revalidate();
			}
		});
		add(but, "North");
		
		add(tf, "South");
		
		JTabbedPane tabpane = new JTabbedPane();
		tabpane.add("label1",new JPanel());
		tabpane.add("label2",new JPanel());
		tabpane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int index = ((JTabbedPane)e.getSource()).getSelectedIndex();
				tf.setText("�л���"+index+"��ǩ");
				add(new JButton("��ǩ"+index),"East");
			}
		});
		add(tabpane);
	}
	
	public static void main(String args[]) {
		SwingConsole.run(new swingTest(), 500, 500);
	}
}




















