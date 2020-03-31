package XMLdemo;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

public class Person {
	private String first;
	private String last;
	
	public Person(String f, String la) {
		first = f;
		last = la;
	}
	
	public Element getXML() {
		Element person = new Element("person");
		
		Element firstname = new Element("first");
		firstname.appendChild(first);   //����ı��ӽڵ�
		Element lastname = new Element("last");
		lastname.appendChild(last); 	//����ı��ӽڵ�
		
		person.appendChild(firstname);  //���Ԫ���ӽڵ�
		person.appendChild(lastname);	//���Ԫ���ӽڵ�
		
		return person;
	}
	
	public Person(Element person) {
		first = person.getFirstChildElement("first").getValue();
		last = person.getFirstChildElement("last").getValue();
	}
	
	public String toString() {
		return first+" "+last;
	}
	
	//��xml����תΪ�ɶ��ĸ�ʽ
	public static void format(OutputStream os, Document doc) throws Exception{
		Serializer serializer = new Serializer(os, "ISO-8859-1");
		serializer.setIndent(4);
		serializer.setMaxLength(60);
		serializer.write(doc);
		serializer.flush();
	}
	
	public static void main(String[] args) throws Exception{
		List<Person> people = Arrays.asList(new Person("aa","1"),new Person("bb","2"),new Person("cc","3"));
		System.out.println(people);
		Element root = new Element("people");
		for(Person p : people){
			root.appendChild(p.getXML());
		}
		Document doc = new Document(root);
		format(System.out, doc);  //��XML�ĵ������ʽ�����������̨
		format(new BufferedOutputStream(new FileOutputStream("people.xml")), doc); //��XML�ĵ������ʽ�������ָ���ļ�
	}
}
























