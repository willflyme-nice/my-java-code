import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JDialogDemo implements ActionListener,ItemListener{
	JFrame frame=new JFrame("Menu Demo");
	JTextField tf=new JTextField();
	JTextField tf1=new JTextField();
    public static void main(String args[]) throws Exception{
		JDialogDemo  that=new JDialogDemo();
		that.go();
	}
	public void go() throws Exception{
		JMenuBar menubar=new JMenuBar();
		frame.setJMenuBar(menubar);
		JMenu menu,submenu;
		JMenuItem menuItem;
		
		menu=new JMenu("нд╪Ч");
		menu.setMnemonic(KeyEvent.VK_F);
		menubar.add(menu);
		
		menuItem=new JMenuItem("Open...");
		menuItem.setMnemonic(KeyEvent.VK_P);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,ActionEvent.ALT_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem=new JMenuItem("Save",KeyEvent.VK_S);
		
		menuItem.setEnabled(false);
		
		
		menuItem=new JMenuItem("Close");
		menuItem.setMnemonic(KeyEvent.VK_C);
        menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menu.add(new JSeparator());
		
		menuItem=new JMenuItem("Exit");
		menuItem.setMnemonic(KeyEvent.VK_E);
        menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menu=new JMenu("Option");
		menubar.add(menu);
		menu.add("Font...");
		submenu=new JMenu("Color...");
		menu.add(submenu);
		menuItem=new JMenuItem("Foreground");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,ActionEvent.ALT_MASK));
		submenu.add(menuItem);
		
		menuItem=new JMenuItem("Background");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,ActionEvent.ALT_MASK));
		submenu.add(menuItem);
		
		menu.add(new JSeparator());
		
		JCheckBoxMenuItem cm=new JCheckBoxMenuItem("Always On Top");
		cm.addItemListener(this);
		menu.add(cm);
		menu.addSeparator();
		JRadioButtonMenuItem rm=new JRadioButtonMenuItem("Small",true);
		rm.addItemListener(this);
		menu.add(rm);
		ButtonGroup group=new ButtonGroup();
		group.add(rm);
		rm=new JRadioButtonMenuItem("Large");
		group.add(rm);
		rm.addItemListener(this);
		menu.add(rm);
		group.add(rm);
		
		menu=new JMenu("Help");
		menubar.add(menu);
		menuItem=new JMenuItem("about...",new ImageIcon("image/augilogo.gif"));
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		tf.setEditable(false);
		tf1.setEditable(false);
		
		JPanel panel=new JPanel(new GridLayout(2,1));
		panel.add(tf);
		panel.add(tf1);
		Container cp=frame.getContentPane();
		cp.add(panel, BorderLayout.SOUTH);
		
		
		
		
		frame.setSize(300,200);
		frame.setLocation(200, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		tf.setText(e.getActionCommand());
		if(e.getActionCommand()=="Exit")
			System.exit(0);
		
	}
	
	public void itemStateChanged(ItemEvent e) {
		int state=e.getStateChange();
	     JMenuItem amenuItem=(JMenuItem)e.getSource();
	     String command=amenuItem.getText();
	     if(state==ItemEvent.SELECTED)
	    	 tf.setText(command+" selected");
	     else 
	    	 tf1.setText(command+" deselected");
	     
	}

	
}	