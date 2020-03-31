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
	
	private JButton browse = new JButton("�鿴�ļ�");
	private JButton save = new JButton("����");
	private JButton adapt = new JButton("��Ӧ");
	private JButton showImage = new JButton("�鿴ͼƬ");
	
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
		
		textArea.setFont(new Font("������",Font.PLAIN,16));
		infoArea.setFont(new Font("������",Font.PLAIN,14));
		Box box = Box.createVerticalBox();
		box.add(scrollPane1);
		box.add(scrollPane2);
		
		add(box,"South");
	
		//���browse��ť�������ļ�ѡ������ѡ�񵽵��ı��ļ�����ʾ���ݵ�textArea��
		browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser("E:/�����ļ�/�ʼ�");
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
					textArea.setCaretPosition(0); //����ͼ��������
				}
			}
		});
		
		//���showImag��ť�������ļ�ѡ������ѡ�񵽵�ͼƬ�ļ�����ʾͼƬ���´�����
		showImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser("E:\\�����ļ�\\����ͼƬ");
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
						
						new ImageFrame(files,filenames,indext).setVisible(true); //�������ļ����� �ļ������� ��ǰ�ļ�����
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		//���save�����ļ�ѡ������ѡ��Ҫ������ļ�·��
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser("E:/�����ļ�/�ʼ�");
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
		
		//���adapt��ť�������ڴ�С��Ӧ�ı����ݻ�ԭ
		adapt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(adapt.getText().equals("��Ӧ")) {
					textArea.setColumns(varCols);
					textArea.setRows(varCols/3);
					parent.pack();System.out.println(textCols);
					adapt.setText("��ԭ");
				}else if(adapt.getText().equals("��ԭ")) {
					textArea.setColumns(textCols);
					System.out.println(textCols);
					textArea.setRows(textRows);
					parent.pack();
					adapt.setText("��Ӧ");
				}
				
			}
		});
		
		//Ϊ���Һ��滻ģ����Ӳ����¼�
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
		
		//Ϊ���Һ��滻ģ������滻�¼�
		class ReplaceListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				searchPanel.setText(textArea.getText());
				MarkPosition mp = searchPanel.doReplace();
				if(mp == null) {
					textArea.requestFocus();
					System.out.println("null");
				}else {
					textArea.setText(searchPanel.getText()); //�滻���ǵø����ı�
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



















