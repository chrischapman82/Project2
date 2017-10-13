package project1;

public class Switch extends Sprite{
	
	Door door;
	public Switch(float x, float y) {
		super(Constant.SWITCH_PATH, x, y);
		this.addTag(Tag.SWITCH);
	}
	
	public void setDoor(Door door) {
		this.door = door;
	}
	
	public void update(int delta) {
		if (World.getSpriteOfType(Tag.PUSHABLE, this.getX(), this.getY()) != null) {
		}
	}
}
