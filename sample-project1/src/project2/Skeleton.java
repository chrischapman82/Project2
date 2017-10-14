package project2;

public class Skeleton extends Movable {

	private int dir;
	private Timer timer;		// Timer object, containing time since last move
	
	public Skeleton(float x, float y) {
		super("res/skull.png", x, y);
		this.dir = DIR_UP;
		this.addTag(Tag.SKELETON);
		this.addTag(Tag.ENEMY);
		timer = new Timer(0,Constant.DELTA_IN_SECOND);
	}
	
	/** Moves the skeleton every second using delta and a timer to track time between moves.
	 * 	@param	delta is used to track the time between moves
	 */
	public void update(int delta) {
		timer.update(delta);
		if (timer.expired()) {
			
			// if blocked, switch direction between up/down
			if (World.isBlocked(this.getDest(dir, App.TILE_SIZE, 1))) {
				
				if (dir == DIR_UP) {
					dir = DIR_DOWN;
				} else {
					dir = DIR_UP;
				}
			}
			this.moveDir(dir);
		}
	}
}
