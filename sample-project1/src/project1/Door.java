package project1;

public class Door extends Sprite {

	private boolean closed;
	public Door(float x, float y) {
		super(Constant.DOOR_PATH, x, y);
		this.addTag(Tag.DOOR);
		closed = true;
	}
	
	public void close_door() {
		closed = true;
	}
	
	public void open_door() {
		closed = false;
	}
	
	
}
