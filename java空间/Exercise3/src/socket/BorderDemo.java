package socket;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.StrokeBorder;

import com.mypack.util.SwingConsole;;;

public class BorderDemo extends JFrame{
	
	public JPanel getPanel(String name, Border border) {
		JPanel p1 =new JPanel();
		p1.setLayout(new BorderLayout());
		p1.add(new JLabel(name, JLabel.CENTER));
		p1.setBorder(border);
		return p1;
	}
	
	public BorderDemo() {
		setLayout(new GridLayout(2,5,10,10));
		add(getPanel("BevelBorder" , new BevelBorder(BevelBorder.LOWERED)));
		add(getPanel("BevelBorder" , new BevelBorder(BevelBorder.RAISED)));
		add(getPanel("CompoundBorder" , new CompoundBorder()));
		add(getPanel("EtchedBorder" , new EtchedBorder()));
		add(getPanel("EtchedBorder" , new EtchedBorder(EtchedBorder.RAISED, Color.GREEN,Color.BLUE)));
		add(getPanel("MatteBorder" , new MatteBorder(2,2,3,3,Color.cyan)));
		add(getPanel("SoftBevelBorder" , new SoftBevelBorder(BevelBorder.RAISED)));
		add(getPanel("StrokeBorder" , new StrokeBorder(new BasicStroke(5))));
		
	}
	
	public static void main(String arg[]) {
		SwingConsole.run(new BorderDemo(), 500, 500, 300, 300);
	}
}
