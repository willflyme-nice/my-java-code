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

/**自定义的数据模型*/
public class TableDataModel implements DataModel{
	
	private String filename = "src/main/resources/acounts.txt";          //数据文件名
	private String recycleBin = "src/main/resources/delete_history.txt"; //删除文件名
	
	private LinkedHashMap<String, String> items = new LinkedHashMap<String, String>(); 		 //链表视图（查询视图）
	private TreeMap<String, String> treeItems = new TreeMap<String, String>();  	         //排序视图
	private LinkedHashMap<String, String> deleteItems = new LinkedHashMap<String, String>(); //删除项目视图
	
	private  boolean modified = false; //修改标志
	
	public TableDataModel() {
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			String buf = new String();
			while((buf = in.readLine()) != null) {
				String[] item = buf.split(":");
				String value = item.length==2? item[1] : ""; //若文件中“：”右边无字符串，则设为空字符串
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
	

	//查询
	public String search(String key) {
		return items.get(key);
	}
	
	//添加
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
	
	//更改
	public boolean update(String key, String value) {
		if(items.get(key) == null) {
			return false;
		}
		items.put(key, value);
		treeItems.put(key, value);
		modified = true;
		return true;
	}
	
	//删除
	public boolean delete(String key) {
		if(items.get(key) == null) {
			return false;
		}
		String value = items.remove(key); //更新链表视图
		treeItems.remove(key);            //更新顺序视图
		deleteItems.put(key, value);	  //更新删除项目视图
		modified = true;				  //更新修改标志
		return true;   
	}
	
	//保存到文件
	public boolean save() {
		if(modified == false) return false;   //若无修改，就无需重新写入文件
		try {
			//写入数据文件
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
			
			//写入删除文件
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
			deleteItems.clear();  //清空删除视图
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		modified = false;
		return true;
	}
	
	//获取视图
	public Map<String,String> getItems(ViewSelect sel){
		switch(sel) {
		case link: return items;
		case tree: return treeItems;
		default: return items;
		}
	}
	
	//获取更改标志
	public boolean getModified() {
		return modified;
	}
		
}
