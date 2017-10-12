package project1;

public class Switch extends Sprite{
	
	Door door;
	public Switch(float x, float y) {
		super("res/switch.png", x, y);
		this.addTag("switch");
	}
	
	public void setDoor(Door door) {
		this.door = door;
	}
	
	public void update(int delta) {
		if (World.getSpriteOfType("pushable", this.getX(), this.getY()) != null) {
		}
	}
}
