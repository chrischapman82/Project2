package project1;

public class Cracked extends Sprite {
	
	
	public Cracked(float x, float y) {
		super(Constant.CRACKED, x, y);
		this.addTag(Tag.CRACKED);
		this.addTag(Tag.BLOCKED);
	}
}
