package snakeparts;

/**
 * ������
 */
public class Block {
	public static final int ROWS = 12; //������ 20��
	public static final int COLS = 12; //������ 20��
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
		return row * COLS + col; //�����ϵ��£������ҷ���hashCode
	}
	//��������ӵ�ֵ��ֵ�ڵ�ǰ����
	public void setValue(Block b) {
		this.row = b.getRow();
		this.col = b.getCol();
	}
	//���ӳ�����
	public void up() {
		row = (row + ROWS - 1) % ROWS;
	}
	//���ӳ�����
	public void down() {
		row = (row + 1) % ROWS;
	}
	//���ӳ�����
	public void left() {
		col = (col + COLS - 1) % COLS;
	}
	//���ӳ�����
	public void right() {
		col = (col + 1) % COLS;
	}
}
