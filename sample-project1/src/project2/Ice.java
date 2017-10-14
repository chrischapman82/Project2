package project2;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */
public class Ice extends Pushable {
	
	/** the direction the ice is currently moving. If DIR_NONE, then the ice has stopped stopped */
	private int dir;
	
	/** The last X-position of the ice before it was pushed. */
	private float lastX;	
	/** The last X-position of the ice before it was pushed. */
	private float lastY;
	/** Time elapsed since the last time the ice moved.*/
	private int time;
	
	/** Ice moves a tile every ICE_MOVE_TIME ms*/
	public static final int ICE_MOVE_TIME = Constant.DELTA_IN_SECOND / 4;
	
	/** Contains positional and image data about the Sprite Ice.
	 * @param x x-pos
	 * @param y y-pos
	 */
	public Ice(float x, float y) {
		super(Constant.ICE_PATH, x, y);
		lastX = x;
		lastY = y;
		time = 0;
		dir = Sprite.DIR_NONE;		// Starts off not moving!
		this.addTag(Tag.ICE);
	}
	
	/* (non-Javadoc)
	 * @see project2.Movable#addToHistory()
	 */
	public void addToHistory() {
		if (getStackSize() > 20) {
			removeFromHistory(0);
		}
		Position last_pos = new Position(lastX, lastY);
		super.addToHistory(last_pos);
	}
	
	/* (non-Javadoc)
	 * @see project2.Pushable#push(int)
	 */
	public void push(int dir) {
		if (dir == DIR_NONE) {
			return;
		}
		this.dir = dir;
		lastX = this.getX();
		lastY = this.getY();
		
		float speed = App.TILE_SIZE;
		
		// Translates the direction to an x and y displacement
		Position candidate_pos = this.getDest(dir, speed, 1);
		
		// Make sure the position isn't occupied!
		// If it's not blocked, move in that direction.
		if (!World.isBlocked(candidate_pos)) {
			this.moveDir(dir);
		} else {
			// if it's blocked, stop where it is
			this.dir = DIR_NONE;
		}
	}
	
	/** If the ice has been pushed, move in that direction once every ICE_MOVE_TIME ms
	 * until it hits a blocked object and comes to a stop, resetting time and direction.
	 */
	public void update(int delta) {
		
		// only updates the time when the ice is travelling in a direction!
		if (dir != DIR_NONE) {
			
			// when it is moving, 
			time += delta;
			if (time > ICE_MOVE_TIME) {
				Position dest;
				if (!World.isBlocked(dest = this.getDest(dir, 1))) {
					this.setPos(dest);
					time -= ICE_MOVE_TIME;
				} else {
					// if it hits a blocked object, come to a stop
					// reset the time and direction!
					dir = DIR_NONE;
					time = 0;
				}

			}
		}	
	}
}
