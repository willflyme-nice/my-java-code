package MyTools;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import snakeparts.SnakePanel;

public class SnakeFrame extends JFrame{

	SnakePanel snakePanel = new SnakePanel();
	
	public SnakeFrame() {
		add(snakePanel);
		pack();
		setResizable(false);
		setLocation(400,80);
		setTitle("贪吃蛇");
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				snakePanel.go(); //启动线程
			}

			@Override
			public void windowClosing(WindowEvent e) {
				snakePanel.stop(); //停止线程
			}
		});
	}
	
}
