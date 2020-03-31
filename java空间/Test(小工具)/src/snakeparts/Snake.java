package snakeparts;

import java.util.List;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * 贪吃蛇类
 */
public class Snake {
	private List<Block> snakeBody = new ArrayList<Block>(); //代表蛇身,第0个元素代表蛇头
	private Direction direction;    //蛇的前进方向 
	private boolean allowKey = true;  //允许键盘响应
	private RandomFood randomFood; //蛇的食物
	
	//获取蛇身位置
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
	
	//初始化3格长的蛇,并向右前进,放置第一个食物
	void Init() {
		snakeBody.clear();
		Collections.addAll(snakeBody, new Block(0,2), new Block(0,1), new Block(0,0));
		right();
		randomFood = RandomFood.Next(this);
	}
	//蛇不断向前进,每隔一秒前进一格
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
	
	//蛇前进一格
	void step() {
		Block oldTail = snakeBody.get(snakeBody.size()-1); //记录下蛇尾
		Block newTail = new Block(oldTail); //准备好可能要用到的新蛇尾
		for(int i=snakeBody.size()-1; i>0; i--) {
			snakeBody.get(i).setValue(snakeBody.get(i-1)); //后一个格子移动到前一个格子的位置
		}
		switch (direction) { //调整蛇头的位置
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
		//调整蛇头完毕 允许接收键盘输入
		allowKey = true; 
		//判断是否吃到了食物
		Block head = snakeBody.get(0);
		if( head.getCol()==randomFood.getCol() && head.getRow()==randomFood.getRow() ) {
			//吃到了,并在蛇尾伸长一格
			snakeBody.add(newTail);
			randomFood = RandomFood.Next(this); //放置下一个食物
		}
		//判断蛇身是否相撞
		if(snakeBody.size() != new HashSet<Block>(snakeBody).size()) {
			Init();
		}
	}
	//键盘监听器
	class KeyListener extends KeyAdapter{
		public void keyTyped(KeyEvent e) {
			if(allowKey) {  //若允许键盘响应
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
				allowKey = false; //禁止键盘重复响应,直到蛇身调整完毕
			}	
		}
	}	
	//获取一个监听器实例
	public KeyListener getKeyListener() {
		return new KeyListener();
	}
	//蛇向上转
	void up() {
		if(direction != Direction.DOWN) { direction = Direction.UP; }	
	}
	//蛇向下转
	void down() { 
		if(direction != Direction.UP) { direction = Direction.DOWN; }	
	}
	//蛇向左转
	void left() { 
		if(direction != Direction.RIGHT) { direction = Direction.LEFT;}
	}
	//蛇向右转
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





