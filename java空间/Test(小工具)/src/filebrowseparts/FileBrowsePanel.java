package filebrowseparts;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileBrowsePanel extends JPanel{
	private final int textRows = 20;
	private final int textCols = 80;
	private int varCols = textCols;
	private JFrame parent;
	
	MenuBar menubar = new MenuBar();
	
	private JButton browse = new JButton("查看文件");
	private JButton save = new JButton("保存");
	private JButton adapt = new JButton("适应");
	private JButton showImage = new JButton("查看图片");
	
	SearchAndReplacePanel searchPanel = new SearchAndReplacePanel();
	
	private JTextArea textArea = new JTextArea(textRows,textCols);
	private JTextArea infoArea = new JTextArea(textRows/5,textCols);
	
	public FileBrowsePanel(JFrame parent) {
		this.parent = parent;
		setBorder(new EmptyBorder(5,5,5,5));
		this.setLayout(new BorderLayout());
		
		add(menubar,"North");
		
		JPanel panel = new JPanel();
		panel.add(browse);
		panel.add(save);
		panel.add(adapt);
		panel.add(showImage);
		panel.add(searchPanel);
		add(panel,"Center");
		
		JScrollPane scrollPane1 = new JScrollPane(textArea);
		scrollPane1.setBorder(new TitledBorder("Text"));
		JScrollPane scrollPane2 = new JScrollPane(infoArea);
		scrollPane2.setBorder(new TitledBorder("Information"));
		
		textArea.setFont(new Font("新宋体",Font.PLAIN,16));
		infoArea.setFont(new Font("新宋体",Font.PLAIN,14));
		Box box = Box.createVerticalBox();
		box.add(scrollPane1);
		box.add(scrollPane2);
		
		add(box,"South");
	
		//点击browse按钮将弹出文件选择器，选择到的文本文件将显示内容到textArea上
		browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser("E:/下载文件/笔记");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("java & txt", "txt", "java");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println(chooser.getSelectedFile());
					String fileName = chooser.getSelectedFile().getAbsolutePath();
					try {
						FileText ft = new FileText(fileName,"r");
						textArea.setText(ft.readText());
						varCols = ft.getCols();
						ft.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					textArea.setCaretPosition(0); //将视图拉到顶部
				}
			}
		});
		
		//点击showImag按钮将弹出文件选择器，选择到的图片文件将显示图片到新窗口上
		showImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser("E:\\下载文件\\下载图片");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("image type", "jpg","png");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						File file = chooser.getSelectedFile();
						File catalog = file.getParentFile();
						String[] filenames = catalog.list();
						ArrayList<File> files = new ArrayList<File>(Arrays.asList(catalog.listFiles()));
						int indext = files.indexOf(file);
						
						new ImageFrame(files,filenames,indext).setVisible(true); //参数：文件集合 文件名集合 当前文件索引
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		//点击save弹出文件选择器，选择要保存的文件路径
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser("E:/下载文件/笔记");
				int returnVal = chooser.showSaveDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					String fileName = chooser.getSelectedFile().getAbsolutePath();
					try {
						FileText ft = new FileText(fileName,"w");
						if(textArea.getText().trim() != "")
							ft.writeText(textArea.getText());
						ft.close();
					}catch(IOException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		
		//点击adapt按钮调整窗口大小适应文本内容或还原
		adapt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(adapt.getText().equals("适应")) {
					textArea.setColumns(varCols);
					textArea.setRows(varCols/3);
					parent.pack();System.out.println(textCols);
					adapt.setText("还原");
				}else if(adapt.getText().equals("还原")) {
					textArea.setColumns(textCols);
					System.out.println(textCols);
					textArea.setRows(textRows);
					parent.pack();
					adapt.setText("适应");
				}
				
			}
		});
		
		//为查找和替换模块添加查找事件
		class SearchListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				searchPanel.setText(textArea.getText());
				MarkPosition mp = searchPanel.doSearch();
				if(mp == null) {
					System.out.println("null");
				}else {
					textArea.requestFocus();
					textArea.setSelectionStart(mp.getStart());
					textArea.setSelectionEnd(mp.getStart()+mp.getLen());
					System.out.println("mp:"+mp.getStart()+","+mp.getLen());
				}
			}
		}
		searchPanel.addSerchListener(new SearchListener());
		
		//为查找和替换模块添加替换事件
		class ReplaceListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				searchPanel.setText(textArea.getText());
				MarkPosition mp = searchPanel.doReplace();
				if(mp == null) {
					textArea.requestFocus();
					System.out.println("null");
				}else {
					textArea.setText(searchPanel.getText()); //替换完后记得更新文本
					textArea.requestFocus();
					textArea.setSelectionStart(mp.getStart());
					textArea.setSelectionEnd(mp.getStart()+mp.getLen());
					System.out.println("mp:"+mp.getStart()+","+mp.getLen());
				}
			}
		}
		searchPanel.addReplaceListener(new ReplaceListener());
		
		
	}
	
}



















