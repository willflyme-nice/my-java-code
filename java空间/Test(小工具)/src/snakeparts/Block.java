package snakeparts;

/**
 * 格子类
 */
public class Block {
	public static final int ROWS = 12; //总行数 20行
	public static final int COLS = 12; //总列数 20列
	private int row;
	private int col;
	public Block(int row, int col){
		this.row = row % ROWS;
		this.col = col % COLS;
	}
	public Block(Block b) {
		this.row = b.row;
		this.col = b.col;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public String toString() {
		return "("+row+","+col+")";
	}
	public boolean equals(Object obj) {
		if(obj instanceof Block) {
			Block block = (Block)obj;
			if(block.getCol()==this.col && block.getRow()==this.row) {return true;}
		}
		return false;
	}
	public int hashCode() {
		return row * COLS + col; //按从上到下，从左到右分配hashCode
	}
	//将另外格子的值赋值于当前格子
	public void setValue(Block b) {
		this.row = b.getRow();
		this.col = b.getCol();
	}
	//格子朝上移
	public void up() {
		row = (row + ROWS - 1) % ROWS;
	}
	//格子朝下移
	public void down() {
		row = (row + 1) % ROWS;
	}
	//格子朝左移
	public void left() {
		col = (col + COLS - 1) % COLS;
	}
	//格子朝右移
	public void right() {
		col = (col + 1) % COLS;
	}
}
