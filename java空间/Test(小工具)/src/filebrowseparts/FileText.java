package filebrowseparts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**用于读取文件文本的流对象*/
public class FileText {
	BufferedReader reader = null;
	BufferedWriter writer = null;
	int maxCols = 80; //文本行的最大宽度
	
	public FileText(String filename,String mode) throws IOException{
		if(mode=="r") {
			reader = new BufferedReader(new FileReader(filename));
		}else if(mode=="w") {
			writer = new BufferedWriter(new FileWriter(filename));
		}else {
			throw new RuntimeException("FileText构造参数mode出错");
		}
	}
	
	//读取文件所有文本
	public String readText() throws IOException{
		if(reader == null) throw new RuntimeException("输入流为null");
		StringBuilder builder = new StringBuilder();
		String buff;
		while((buff = reader.readLine()) != null) {
			builder.append(buff);
			builder.append("\n\r");
			maxCols = buff.length()+30>maxCols ? buff.length()+30 : maxCols; //获取文本行的最大宽度
		}
		return builder.toString();
	}
	
	//将文本写进指定文件
	public void writeText(String text) throws IOException{
		if(writer == null) throw new RuntimeException("输出流为null");
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
		System.out.println("关闭io");
	}
	
	public int getCols() {return maxCols;}
}
