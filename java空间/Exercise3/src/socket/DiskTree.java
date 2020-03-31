package socket;
import java.awt.Color;
import java.awt.Component;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

public class DiskTree extends JFrame{
	JTree fileTree;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DiskTree that = new DiskTree();
		that.go();
		that.setVisible(true);
	}
	void go() {
		setSize(500,500);
		setLocation(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("�ҵĵ���");
		File[] disks = File.listRoots();
		for(File disk : disks) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(disk);
			root.add(node);
		}
		fileTree = new JTree(root);
	   
		fileTree.setCellRenderer(new FileTreeRenderer());
		
		//����ѡ��ڵ��¼�
		fileTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path =  e.getPath();
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent(); //��ȡ·��ĩβ�Ľڵ�
				Object userObj = node.getUserObject(); //��ȡ�û�����
				if( !(userObj instanceof File)) {
					return;
				}
				File file = (File)userObj;
				if( !file.isDirectory() ) {
					return;
				}
				File[] files = file.listFiles();
				for(File f : files) {
					DefaultMutableTreeNode no = new DefaultMutableTreeNode(f);
					node.add(no);
				}
			}
		});
		
		add(new JScrollPane(fileTree));

	}
	
	//��д��Ⱦ��
	class FileTreeRenderer implements TreeCellRenderer{

		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
				boolean leaf, int row, boolean hasFocus) {
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
			Object obj = node.getUserObject();
			
			if( ! (obj instanceof File) ) {
				return new JLabel(obj.toString());
			}
			
			File file = (File)obj;
			FileSystemView view = FileSystemView.getFileSystemView();
			Icon icon = view.getSystemIcon(file);
			
			String filePath = file.getPath();
			String fileName = filePath;
			if(!filePath.substring(filePath.lastIndexOf("\\")+1).equals("")) {
				fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
			}
			JLabel label = new JLabel(fileName,icon,JLabel.LEADING);
			label.setBackground(Color.green);
			if(selected) {
				label.setOpaque(true);
			}
			return label;
		}
		
	}

}













