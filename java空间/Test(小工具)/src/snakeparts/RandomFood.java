package snakeparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * 贪吃蛇的随机食物
 */
public class RandomFood {
	private Block food;
	private RandomFood(Block block) {
		food = block;
	}
	public static List<Block> all; //用来保存所有的格子
	static {
		all = new ArrayList<Block>();
		for(int r=0; r<Block.ROWS; r++) {
			for(int c=0; c<Block.ROWS; c++) {
				all.add(new Block(r, c));
			}
		}
	}
	//获取下一个随机食物的静态方法
	public static RandomFood Next(Snake snake) {
		Random random = new Random();  
		List<Block> blank = new ArrayList<Block>();
		blank.addAll(all);
		blank.removeAll(snake.getSnakeBody());
		System.out.println("all:"+all.size());
		System.out.println("blank:"+blank.size());
		Block block = blank.get(random.nextInt(blank.size()-1));
		return new RandomFood(block);
	}
	public int getRow(){
		return food.getRow();
	}
	public int getCol() {
		return food.getCol();
	}
	public String toString() {
		return "food is at "+getRow()+" row "+getCol()+" col\r\n";
	}
	
	public static void main(String arg[]) {
		Snake snake = new Snake();
		RandomFood rfood = RandomFood.Next(snake);
		System.out.println(rfood);
	}
}
