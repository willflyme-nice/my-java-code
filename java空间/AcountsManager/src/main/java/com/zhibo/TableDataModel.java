package com.zhibo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**�Զ��������ģ��*/
public class TableDataModel implements DataModel{
	
	private String filename = "src/main/resources/acounts.txt";          //�����ļ���
	private String recycleBin = "src/main/resources/delete_history.txt"; //ɾ���ļ���
	
	private LinkedHashMap<String, String> items = new LinkedHashMap<String, String>(); 		 //������ͼ����ѯ��ͼ��
	private TreeMap<String, String> treeItems = new TreeMap<String, String>();  	         //������ͼ
	private LinkedHashMap<String, String> deleteItems = new LinkedHashMap<String, String>(); //ɾ����Ŀ��ͼ
	
	private  boolean modified = false; //�޸ı�־
	
	public TableDataModel() {
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			String buf = new String();
			while((buf = in.readLine()) != null) {
				String[] item = buf.split(":");
				String value = item.length==2? item[1] : ""; //���ļ��С������ұ����ַ���������Ϊ���ַ���
				if (item[0].equals("")) continue;
				items.put(item[0], value);
				treeItems.put(item[0], value);
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
		treeItems.put(key, value);
		modified = true;
		return true;
	}
	
	//����
	public boolean update(String key, String value) {
		if(items.get(key) == null) {
			return false;
		}
		items.put(key, value);
		treeItems.put(key, value);
		modified = true;
		return true;
	}
	
	//ɾ��
	public boolean delete(String key) {
		if(items.get(key) == null) {
			return false;
		}
		String value = items.remove(key); //����������ͼ
		treeItems.remove(key);            //����˳����ͼ
		deleteItems.put(key, value);	  //����ɾ����Ŀ��ͼ
		modified = true;				  //�����޸ı�־
		return true;   
	}
	
	//���浽�ļ�
	public boolean save() {
		if(modified == false) return false;   //�����޸ģ�����������д���ļ�
		try {
			//д�������ļ�
			PrintWriter out = new PrintWriter(filename);
			StringBuilder buf = new StringBuilder();
			for(Entry<String,String> item : items.entrySet()) {
				buf.append(item.getKey());
				buf.append(":");
				buf.append(item.getValue());
				buf.append("\r\n");
			}
			out.print(buf.toString());
			out.close();
			
			//д��ɾ���ļ�
			PrintWriter _out = new PrintWriter(new FileOutputStream(recycleBin, true));
			StringBuilder _buf = new StringBuilder();
			for(Entry<String,String> item : deleteItems.entrySet()) {
				_buf.append(item.getKey());
				_buf.append(":");
				_buf.append(item.getValue());
				_buf.append("\r\n");	
			}
			_out.print(_buf.toString());
			_out.close();
			deleteItems.clear();  //���ɾ����ͼ
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		modified = false;
		return true;
	}
	
	//��ȡ��ͼ
	public Map<String,String> getItems(ViewSelect sel){
		switch(sel) {
		case link: return items;
		case tree: return treeItems;
		default: return items;
		}
	}
	
	//��ȡ���ı�־
	public boolean getModified() {
		return modified;
	}
		
}
