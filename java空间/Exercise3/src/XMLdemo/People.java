package XMLdemo;

import java.util.ArrayList;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Elements;

public class People extends ArrayList<Person>{
	
	public People(String fileName) throws Exception {
		Document doc = new Builder().build(fileName);  //��ָ���ļ�����XML�ĵ�����
		Elements elements = doc.getRootElement().getChildElements();
		System.out.println(elements);
		for(int i=0; i<elements.size(); i++) {
			add(new Person(elements.get(i)));
		}
	}
	
	public static void main(String[] args) throws Exception{
		People p = new People("people.xml");
//		System.out.println(p);
	}
}
