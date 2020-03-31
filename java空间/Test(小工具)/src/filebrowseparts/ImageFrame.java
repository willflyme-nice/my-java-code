package filebrowseparts;

import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//һ��ͼƬչʾ������
public class ImageFrame extends JFrame{
	ArrayList<File> files;
	ListIterator<File> it;
	String[] filenames;
	BufferedImage img;
	JButton pre;
	JButton next;
	JComboBox<String> box;
	
	//�������ļ����� �ļ������� ��ǰ�ļ�����
	public ImageFrame(ArrayList<File> files, String[] filenames, int index) throws IOException{
		this.files = files;
		this.it = files.listIterator(index);
		this.filenames = filenames;
		setImage(true); 
		
		setBounds(100,30,800,600);
		setLayout(null);
		
		JPanel glass = (JPanel)getGlassPane();
		glass.setVisible(true);
		glass.setOpaque(false);
		glass.setLayout(null);
		
		box = new JComboBox<String>(filenames);
		box.setBounds(getWidth()-120,30,100,25);
		glass.add(box);
		
		pre = new JButton(new ImageIcon("image/��һ��.png"));
		pre.setBounds(10, getHeight()/2-25, 80, 50);
		pre.setContentAreaFilled(false);
		pre.setBorder(null);
		glass.add(pre);
		
		next = new JButton(new ImageIcon("image/��һ��.png"));
		next.setBounds(getWidth()-90, getHeight()/2-25, 80, 50);
		next.setContentAreaFilled(false);
		next.setBorder(null);
		glass.add(next);   
		
		//����¼�
		pre.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					setImage(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				repaint();
			}
		});
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setImage(true);
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				repaint();
			}
		});
		box.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String filename = (String)e.getItem();
					int i = Arrays.asList(filenames).indexOf(filename);
					it = files.listIterator(i);
					setImage(true);
				}catch(IOException e1) {
					e1.printStackTrace();
				}
				repaint();
			}
		});
	}
	
	private void setImage(boolean isNext) throws IOException{
		System.out.println("���� setImage("+isNext+")");
		if(isNext) {
			if(it.hasNext()) {
				File file = it.next();
				img = ImageIO.read(file);
				setTitle("ͼƬչʾ "+file.getName());
				System.out.println(file.getName());
			}
		}else {
			if(it.hasPrevious()) {
				File file = it.previous();
				img = ImageIO.read(file);
				setTitle("ͼƬչʾ "+file.getName());
			}
		}
	}
//	private void set
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, getWidth()/2-img.getWidth()/2,
				getHeight()/2-img.getHeight()/2, null);
		pre.setLocation(10, getHeight()/2-25);
		next.setLocation(getWidth()-90, getHeight()/2-25);
		box.setLocation(getWidth()-120,30);
	}
	
	public static void main(String []a) {
		JFrame f = new JFrame();
		URI uri ;
		JLabel lab = new JLabel("<html><a>https://baidu.com</a></html>");
		try {
			URL url = new URL("https://baidu.com");
			uri = url.toURI();
			lab.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent en) {
					try {
						Desktop.getDesktop().browse(uri);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		f.add(lab);
		f.setSize(200,100);
		f.setVisible(true);
	}
	
}


