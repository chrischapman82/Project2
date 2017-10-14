package project1;

public class Target extends Sprite {
	public Target(float x, float y) {
		super(Constant.TARGET_PATH, x, y);
		this.addTag(Tag.TARGET);
	}
	
	public boolean activated() {
		Sprite sprite;
		// returns true if there is a pushable block on the target
		if ((sprite = World.getSpriteOfType(Tag.PUSHABLE, this.getX(), this.getY())) != null) {
			return true;
		}
		return false;
	}
}
