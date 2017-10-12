package project1;

public class Tnt extends Pushable {
	
	
	public Tnt(float x, float y) {
		super("res/tnt.png", x, y);
		this.addTag("tnt");
	}
	
	public void onMove(int dir, float testX, float testY) {
		
		if (World.getSpriteOfType("cracked", testX, testY) != null) {
			this.moveDir(dir);
			World.destroySprite(this);
		}
	}
}
