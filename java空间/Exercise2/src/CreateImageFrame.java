import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class CreateImageFrame extends JFrame{
	
	public static void main(String[] args) {
		CreateImageFrame that=new CreateImageFrame();
		that.go();
	}
	private void go() {
		setTitle("使用像素生成图片");
		CreateImagePanel pane=new CreateImagePanel();
		add(pane);
		
		setSize(400,400);
		setLocation(400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private class CreateImagePanel extends JPanel{
		protected void paintComponent(Graphics g) {
			super.paintComponents(g);
			//图像的尺寸，像素组
			int w=300;
			int h=300;
			int pix[]=new int[w*h];
			int red;
			int blue;
			int index=0;
			for(int y=0;y<h;y++) {
				red=y*255/(h-1); //在纵轴位置上从黑色过度到红色
				System.out.print(red+",");
				for(int x=0;x<w;x++) {
					blue=x*255/(w-1); //在横轴位置上从黑色过度到蓝色
					pix[index++]=255<<24|red<<16|blue;
				}
			}
			//下面生成图片
			ImageProducer producer=new MemoryImageSource(w,h,pix,0,w);
			Image img=createImage(producer); //这是Component中生成图片的方法
			g.drawImage(img,0,0,getWidth(),getHeight(),this);
		}
	}

}
