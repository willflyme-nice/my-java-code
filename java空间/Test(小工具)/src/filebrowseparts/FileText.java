package filebrowseparts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**���ڶ�ȡ�ļ��ı���������*/
public class FileText {
	BufferedReader reader = null;
	BufferedWriter writer = null;
	int maxCols = 80; //�ı��е������
	
	public FileText(String filename,String mode) throws IOException{
		if(mode=="r") {
			reader = new BufferedReader(new FileReader(filename));
		}else if(mode=="w") {
			writer = new BufferedWriter(new FileWriter(filename));
		}else {
			throw new RuntimeException("FileText�������mode����");
		}
	}
	
	//��ȡ�ļ������ı�
	public String readText() throws IOException{
		if(reader == null) throw new RuntimeException("������Ϊnull");
		StringBuilder builder = new StringBuilder();
		String buff;
		while((buff = reader.readLine()) != null) {
			builder.append(buff);
			builder.append("\n\r");
			maxCols = buff.length()+30>maxCols ? buff.length()+30 : maxCols; //��ȡ�ı��е������
		}
		return builder.toString();
	}
	
	//���ı�д��ָ���ļ�
	public void writeText(String text) throws IOException{
		if(writer == null) throw new RuntimeException("�����Ϊnull");
		writer.write(text);
		writer.newLine();
	}
	
	public void close() throws IOException {
		if(reader != null) {
			reader.close();
		}
		if(writer != null) {
			writer.close();
		}
		System.out.println("�ر�io");
	}
	
	public int getCols() {return maxCols;}
}
