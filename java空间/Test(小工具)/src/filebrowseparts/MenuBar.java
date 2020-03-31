package filebrowseparts;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar{
	private JMenu menu1 = new JMenu("file");
	private JMenu menu2 = new JMenu("tool");
	private JMenuItem item1 = new JMenuItem("open");
	private JMenuItem item2 = new JMenuItem("save");
	
	public MenuBar() {
		super();
		menu1.add(item1);
		menu1.add(item2);
		add(menu1);
		add(menu2);
	}
}
