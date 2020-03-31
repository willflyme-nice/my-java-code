package MyTools;

import javax.swing.JFrame;

import filebrowseparts.FileBrowsePanel;

public class FileBrowseFrame extends JFrame{
	private FileBrowsePanel fileBrowsePanel = new FileBrowsePanel(this);
	
	public FileBrowseFrame() {
		add(fileBrowsePanel);
		pack();
		setLocation(400,80);
		setTitle("ÎÄ¼þä¯ÀÀÆ÷");
	}
}
