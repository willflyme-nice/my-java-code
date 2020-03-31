import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class IOStream implements ActionListener{
	JFrame frame;
	JDialog progressPane;
	JLabel progress;
	JLabel label1;
	JLabel label2;
	JLabel showsrc;
	JLabel showdec;
	JButton open;
	JButton save;
	JButton copy;
	File srcFile;
	File decFile;
	JFileChooser fileChooser=new JFileChooser("E:\\软件学习\\程序代码\\java工作空间\\Exercise");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                   IOStream that=new IOStream();
                   that.go();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
		
	}
	protected void go() {
		frame=new JFrame("文件复制演示");
		//进度对话框设置
		progressPane=new JDialog(frame,"复制进度");
		progress=new JLabel();
		progress.setFont(new Font("黑体",Font.PLAIN,20));
		progressPane.setSize(400, 200);
		progressPane.setLocation(800, 300);
		progressPane.getContentPane().add(progress);
		//
		label1=new JLabel("请选择要复制的文件");
		label2=new JLabel("请选择要保存的位置");
		
		showsrc=new JLabel("未选择任何文件");
		showdec=new JLabel("未选择任何目录");
		EtchedBorder border=new EtchedBorder();
		Border border1= BorderFactory.createTitledBorder(border,"源文件");
		Border border2= BorderFactory.createTitledBorder(border,"目的路径");
		showsrc.setBorder(border1);
		showdec.setBorder(border2);
				
		open=new JButton("打开");
		save=new JButton("保存");
		copy=new JButton("开始复制");
		frame.setLayout(new GridLayout(4,1));
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		panel1.add(label1);
		panel1.add(open);
		panel1.add(label2);
		panel1.add(save);
		panel2.add(copy);
		frame.getContentPane().add(panel1);
		frame.getContentPane().add(showsrc);
		frame.getContentPane().add(showdec);
		frame.getContentPane().add(panel2);
		
		
		open.addActionListener(this);
		save.addActionListener(this);
		copy.addActionListener(this);
		
		frame.setSize(450,300);
		frame.setLocation(800,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton pressed=(JButton)e.getSource();
		if(pressed==open) {
			int selected=fileChooser.showOpenDialog(null);
			if(selected==JFileChooser.APPROVE_OPTION) {
				srcFile=fileChooser.getSelectedFile();
				String path=srcFile.getPath();
				showsrc.setText("已选择文件"+path);
			}
		
		}
		if(pressed==save) {
			int selected=fileChooser.showSaveDialog(null);
			if(selected==JFileChooser.APPROVE_OPTION) {
				File temp=fileChooser.getSelectedFile();
				String path=temp.isDirectory()? temp.getPath():temp.getParent();
				showdec.setText("目的路径"+path);
				decFile=new File(path+"\\"+srcFile.getName());
			}
			
		}
		if(pressed==copy) {
			try {
				if(srcFile.getPath().equals(decFile.getPath()))
				{
					JOptionPane.showMessageDialog(null,"该位置已有同一文件了");
					return;
					
				}
				progressPane.setVisible(true);
				long len=srcFile.length()/1024;
				long haveSend=0;
				progress.setText("已拷贝 "+0+" KB 总共 "+len+" KB");
				FileInputStream fin=new FileInputStream(srcFile);
				FileOutputStream fout=new FileOutputStream(decFile);
				byte buff[]=new byte[1024];
				while(fin.read(buff)!=-1) {
					fout.write(buff);
					haveSend++;
					progress.setText("已拷贝 "+haveSend+" KB 总共 "+len+" KB");
					System.out.println("已拷贝 "+haveSend+" KB 总共 "+len+" KB");
				}
				fin.close();
				fout.close();
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
			
		}
		
	}
	
	

}
