package snakeparts;

import java.util.List;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * ̰������
 */
public class Snake {
	private List<Block> snakeBody = new ArrayList<Block>(); //��������,��0��Ԫ�ش�����ͷ
	private Direction direction;    //�ߵ�ǰ������ 
	private boolean allowKey = true;  //���������Ӧ
	private RandomFood randomFood; //�ߵ�ʳ��
	
	//��ȡ����λ��
	public List getSnakeBody(){
		return snakeBody;
	}
	
	public RandomFood getRandomFood() {
		return randomFood;
	}
	
	public Snake() {
		Init();
		go();
	}
	
	//��ʼ��3�񳤵���,������ǰ��,���õ�һ��ʳ��
	void Init() {
		snakeBody.clear();
		Collections.addAll(snakeBody, new Block(0,2), new Block(0,1), new Block(0,0));
		right();
		randomFood = RandomFood.Next(this);
	}
	//�߲�����ǰ��,ÿ��һ��ǰ��һ��
	void go() {
		Runnable runnable = new Runnable() {
			public void run ()  {
				int i = 0;
				while(true) {
					try {
						Thread.sleep(700);
						step();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		new Thread(runnable).start();
	}
	
	//��ǰ��һ��
	void step() {
		Block oldTail = snakeBody.get(snakeBody.size()-1); //��¼����β
		Block newTail = new Block(oldTail); //׼���ÿ���Ҫ�õ�������β
		for(int i=snakeBody.size()-1; i>0; i--) {
			snakeBody.get(i).setValue(snakeBody.get(i-1)); //��һ�������ƶ���ǰһ�����ӵ�λ��
		}
		switch (direction) { //������ͷ��λ��
		case UP:
			snakeBody.get(0).up();
			break;
		case DOWN:
			snakeBody.get(0).down();
			break;
		case LEFT:
			snakeBody.get(0).left();
			break;
		case RIGHT:
			snakeBody.get(0).right();
			break;
		}
		//������ͷ��� ������ռ�������
		allowKey = true; 
		//�ж��Ƿ�Ե���ʳ��
		Block head = snakeBody.get(0);
		if( head.getCol()==randomFood.getCol() && head.getRow()==randomFood.getRow() ) {
			//�Ե���,������β�쳤һ��
			snakeBody.add(newTail);
			randomFood = RandomFood.Next(this); //������һ��ʳ��
		}
		//�ж������Ƿ���ײ
		if(snakeBody.size() != new HashSet<Block>(snakeBody).size()) {
			Init();
		}
	}
	//���̼�����
	class KeyListener extends KeyAdapter{
		public void keyTyped(KeyEvent e) {
			if(allowKey) {  //�����������Ӧ
				switch(e.getKeyChar()) {
				case 'w':
					up();break;
				case 's':
					down();break;
				case 'a':
					left();
				case 'd':
					right();
				}
				allowKey = false; //��ֹ�����ظ���Ӧ,ֱ������������
			}	
		}
	}	
	//��ȡһ��������ʵ��
	public KeyListener getKeyListener() {
		return new KeyListener();
	}
	//������ת
	void up() {
		if(direction != Direction.DOWN) { direction = Direction.UP; }	
	}
	//������ת
	void down() { 
		if(direction != Direction.UP) { direction = Direction.DOWN; }	
	}
	//������ת
	void left() { 
		if(direction != Direction.RIGHT) { direction = Direction.LEFT;}
	}
	//������ת
	void right() {
		if(direction != Direction.LEFT) { direction = Direction.RIGHT;}
	}
}

enum Direction {
	UP,
	DOWN,
	LEFT,
	RIGHT	
}





