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
 * ר�Ÿ����ػ�������
 */
public class SnakePanel extends JPanel {
	private int size = 30; //�Զ���һ��Ĵ�С
	private int width = Block.COLS*size;  //��
	private int height = Block.ROWS*size; //��
	private Snake snake = new Snake();
	private Thread thread;
	
	public SnakePanel() {
		setPreferredSize(new Dimension(width, height));
	}
	
	//�����ػ��߳�
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
		thread.start();  //�����ػ��߳�
		addKeyListener(snake.getKeyListener());
		requestFocus(); //��ý���
	}
	
	//ֹͣ�ػ��߳�
	public void stop() {
		thread.interrupt();
		System.out.println("���ж��߳�");
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		List<Block> snakeBody = snake.getSnakeBody();
		Image image = null; //��ͼƬ������Ⱦ����
		try {
			image = ImageIO.read(new File("image/��������.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Block block : snakeBody) {
			g.drawImage(image,block.getCol()*size, block.getRow()*size, size, size,null); //��������
		}
		RandomFood rFood = snake.getRandomFood();
		g.setColor(Color.red);
		g.drawRect(rFood.getCol()*size, rFood.getRow()*size, size, size); //�����ߵ�ʳ��
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
