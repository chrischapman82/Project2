package project1;


public class Stone extends Pushable {
	public Stone(float x, float y) {
		super(Constant.STONE_PATH, x, y);
		this.addTag(Tag.STONE);
		this.addTag(Tag.BLOCKED);
	}
	
}
