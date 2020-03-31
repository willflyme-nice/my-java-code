package com.zhibo;

import java.util.Map;

/**数据模型接口*/
public interface DataModel {
	
	public String search(String key) ;					//查询
	
	public boolean add(String key, String value) ;		//添加
	
	public boolean update(String key, String value) ;	//更改
	
	public boolean delete(String key);					//删除
	
	public boolean save();								//保存到文件
	
	public Map<String,String> getItems(ViewSelect sel);	//获取视图
	
	public boolean getModified();						//获取更改标志
			
}
