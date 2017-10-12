package project1;

public class Target extends Sprite {
	public Target(float x, float y) {
		super("res/Target.png", x, y);
		this.addTag("target");
	}
	
	public boolean activated() {
		Sprite sprite;
		// returns true if there is a pushable block on the target
		if ((sprite = World.getSpriteOfType("pushable", this.getX(), this.getY())) != null) {
			return true;
		}
		return false;
	}
}
