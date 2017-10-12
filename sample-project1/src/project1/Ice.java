package project1;

public class Ice extends Pushable {
	
	private int dir;
	private float lastX;
	private float lastY;
	private int time;
	//private Timer timer;
	
	public Ice(float x, float y) {
		super("res/ice.png", x, y);
		lastX = x;
		lastY = y;
		time = 0;
		dir = Sprite.DIR_NONE;
		this.addTag("ice");
	}
	
	public void addToHistory() {
		if (getStackSize() > 20) {
			removeFromHistory(0);
		}
		Position last_pos = new Position(lastX, lastY);
		super.addToHistory(last_pos);
	}
	
	public void push(int dir) {
		if (dir == DIR_NONE) {
			return;
		}
		this.dir = dir;
		System.out.println("ice moving");
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
		// Ice needs to take into account blocks as well!
		float candidate_x = this.getX() + delta_x;
		float candidate_y =  this.getY() + delta_y;
		if (!World.isBlocked(candidate_x, candidate_y) && 
				(World.getSpriteOfType("pushable", candidate_x, candidate_y) == null)) {
			
			this.setX(this.getX() + delta_x);
			this.setY(this.getY() + delta_y);
		} else {
			// if it's blocked, stop where it is
			dir = DIR_NONE;
		}
	}
	
	// TODO use timer here
	public void update(int delta) {
		
		//System.out.println("Updating ice!" + dir);
		//System.out.println(delta);
		time += delta;
		if (time > 100) {
			push(dir);
			time -= 100;
		}
	}
	
	public void onMove(int dir, float textX, float testY) {
		
	}
}
