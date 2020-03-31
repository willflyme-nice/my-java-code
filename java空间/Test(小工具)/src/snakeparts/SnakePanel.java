package snakeparts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

/**
 * 专门负责重绘的面板类
 */
public class SnakePanel extends JPanel {
	private int size = 30; //自定义一格的大小
	private int width = Block.COLS*size;  //宽
	private int height = Block.ROWS*size; //高
	private Snake snake = new Snake();
	private Thread thread;
	
	public SnakePanel() {
		setPreferredSize(new Dimension(width, height));
	}
	
	//开启重绘线程
	public void go() {
		snake.Init();
		thread = new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(700);
						SnakePanel.this.repaint();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						this.interrupt();
					}
					if(isInterrupted()) break;
				}
			}
		};
		thread.start();  //开启重绘线程
		addKeyListener(snake.getKeyListener());
		requestFocus(); //获得焦点
	}
	
	//停止重绘线程
	public void stop() {
		thread.interrupt();
		System.out.println("已中断线程");
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		List<Block> snakeBody = snake.getSnakeBody();
		Image image = null; //此图片用来渲染蛇身
		try {
			image = ImageIO.read(new File("image/蛇身纹理.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Block block : snakeBody) {
			g.drawImage(image,block.getCol()*size, block.getRow()*size, size, size,null); //绘制蛇身
		}
		RandomFood rFood = snake.getRandomFood();
		g.setColor(Color.red);
		g.drawRect(rFood.getCol()*size, rFood.getRow()*size, size, size); //绘制蛇的食物
	}
	
	/*public static void main(String s[]) {
		JFrame frame = new JFrame();
		SnakePanel panel = new SnakePanel();
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.go();
		panel.addKeyListener(panel.snake.getKeyListener());
		panel.requestFocus();
	}*/
}
