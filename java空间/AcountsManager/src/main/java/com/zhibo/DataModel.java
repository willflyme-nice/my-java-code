package com.zhibo;

import java.util.Map;

/**����ģ�ͽӿ�*/
public interface DataModel {
	
	public String search(String key) ;					//��ѯ
	
	public boolean add(String key, String value) ;		//���
	
	public boolean update(String key, String value) ;	//����
	
	public boolean delete(String key);					//ɾ��
	
	public boolean save();								//���浽�ļ�
	
	public Map<String,String> getItems(ViewSelect sel);	//��ȡ��ͼ
	
	public boolean getModified();						//��ȡ���ı�־
			
}
