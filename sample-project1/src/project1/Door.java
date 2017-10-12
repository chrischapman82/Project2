package project1;

public class Door extends Sprite {

	private boolean closed;
	public Door(float x, float y) {
		super("res/door.png", x, y);
		this.addTag("door");
		closed = true;
	}
	
	public void close_door() {
		closed = true;
	}
	
	public void open_door() {
		closed = false;
	}
	
	
}
