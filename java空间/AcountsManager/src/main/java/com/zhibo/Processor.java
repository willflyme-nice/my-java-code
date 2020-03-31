package com.zhibo;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Processor implements DataModel{
	private TreeMap<String,String> items = new TreeMap<String,String>();  //����Ȼ����
	private String filename;
	private final String recycleBin = "src/main/resources/delete_history.txt"; //����վ
	private boolean modified = false; //�޸ı�־
	
	public Processor(String filename) {
		try {
			
			this.filename = filename;
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			String buf = new String();
			while((buf = in.readLine()) != null) {
				String[] item = buf.split(":");
				String value = item.length==2? item[1] : ""; //���ļ��С������ұ����ַ���������Ϊ���ַ���
				if (item[0].equals("")) continue;
//				System.out.print(item[0]);
				items.put(item[0], value);
			}
			in.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//��ѯ
	public String search(String key) {
		return items.get(key);
	}
	
	//���
	public boolean add(String key, String value) {
		if(key.trim().equals("")) 
			return false;
		if(items.get(key) != null) {
			return false;
		}
		items.put(key, value);
		modified = true;
		return true;
	}
	
	//����
	public boolean update(String key, String value) {
		if(items.get(key) == null) {
			return false;
		}
		items.put(key, value);
		modified = true;
		return true;
	}
	
	//ɾ��
	public boolean delete(String key) {
		if(items.get(key) == null) {
			return false;
		}
		String value = items.remove(key);
		modified = true;
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(recycleBin, true));
			out.println(key+":"+value);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return true;   
	}
	
	//���浽�ļ�
	public boolean save() {
		if(modified == false) return false;   //�����޸ģ�����������д���ļ�
		try {
			
			PrintWriter out = new PrintWriter(filename);
			StringBuilder buf = new StringBuilder();
			for(Entry<String,String> item : items.entrySet()) {
				buf.delete(0, buf.length());
				buf.append(item.getKey());
				buf.append(":");
				buf.append(item.getValue());
				out.println(buf.toString());
			}
			out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		modified = false;
		return true;
	}
	
	//��ȡ��ͼ
	public TreeMap<String,String> getItems(ViewSelect sel){
		return items;
	}
	
	//��ȡ���ı�־
	public boolean getModified() {
		return modified;
	}
	
}
















