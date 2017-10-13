package project1;

public class Ice extends Pushable {
	
	private int dir;		// the direction the ice is currently moving. If DIR_NONE, then it's stopped
	private float lastX;
	private float lastY;
	private int time;
	//private Timer timer;
	
	public Ice(float x, float y) {
		super(Constant.ICE_PATH, x, y);
		lastX = x;
		lastY = y;
		time = 0;
		dir = Sprite.DIR_NONE;
		this.addTag(Tag.ICE);
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
		
		Position candidate_pos = this.getDest(dir, speed, 1);
		// Make sure the position isn't occupied!
		// If it isn't blocked, move there.
		// Ice needs to take into account blocks as well!
		if (!World.isBlocked(candidate_pos)) {
			
			this.setX(candidate_pos.getX());
			this.setY(candidate_pos.getY());
		} else {
			// if it's blocked, stop where it is
			this.dir = DIR_NONE;
		}
	}
	
	// TODO use timer here
	public void update(int delta) {
		
		// only updates the time when the ice is travelling in a direction!
		if (dir != DIR_NONE) {
			
			// when it is moving, 
			time += delta;
			if (time > Constant.DELTA_IN_SECOND) {
				push(dir);
				time -= Constant.DELTA_IN_SECOND;
			}
		}
		
	}
	
	public void onMove(int dir, float textX, float testY) {
		
	}
}
