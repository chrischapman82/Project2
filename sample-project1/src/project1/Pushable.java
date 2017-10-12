package project1;

public abstract class Pushable extends Movable {

	public Pushable(String sprite_name, float x, float y) {
		super(sprite_name, x, y);
		this.addTag("pushable");
	}
	
	public void push(int dir) {
		if (dir == DIR_NONE) {
			return;
		}
		System.out.println("moving");
		System.out.println(this.toString());
		
		float speed = App.TILE_SIZE;
		// Translate the direction to an x and y displacement
		// maybe should do get_displacement for less code duplication, returning position delta_x, delta_y!
		float delta_x = 0,
				delta_y = 0;
		switch (dir) {
			case DIR_LEFT:
				delta_x = -speed;
				break;
			case DIR_RIGHT:
				delta_x = speed;
				break;
			case DIR_UP:
				delta_y = -speed;
				break;
			case DIR_DOWN:
				delta_y = speed;
				break;
		}
		
		// Make sure the position isn't occupied!
		// If it isn't blocked, move there.
		if (!World.isBlocked(this.getX() + delta_x, this.getY() + delta_y)) {
			
			this.setX(this.getX() + delta_x);
			this.setY(this.getY() + delta_y);
		}
	}
	
	public boolean active() {
		if (World.getSpriteOfType("target", this.getX(), this.getY()) != null) {
			return true;
		}
		return false;
	}
}
