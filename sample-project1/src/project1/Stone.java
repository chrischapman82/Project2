package project1;

public class Stone extends Block {
	
	public final static String STONE_SRC = "res/stone.png";
	
	public Stone(float x, float y) {
		super(STONE_SRC, x, y);
	}

	@Override
	public void moveBlock(int dir) {
		moveDir(dir);
	}
	
}
