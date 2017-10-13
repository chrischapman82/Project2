package project1;

import org.newdawn.slick.Input;

public class Stone extends Pushable {
	public Stone(float x, float y) {
		super(Constant.STONE_PATH, x, y);
		this.addTag(Tag.STONE);
		this.addTag(Tag.BLOCKED);
	}
	
}
