package project1;

public class Rogue extends Movable {
	
	private int dir;
	public Rogue(float x, float y) {
		super("res/rogue.png", x, y);
		this.dir = DIR_LEFT;
		this.addTag("rogue");
		this.addTag("enemy");
		this.addTag("can_push");
	}
	
	public void update(int delta) {
		
		// if the player hasn't done anything. Don't do anything
		if (!World.playerMoved) {
			return;
		}
		
		if (World.getSpriteOfType("pushable", getX(), getY()) == null) {
			// push it honey
		}
		if (World.isBlocked(this.getDest(dir, App.TILE_SIZE, 1))) {
			
			// switch direction between up/down
			if (dir == DIR_LEFT) {
				dir = DIR_RIGHT;
			} else {
				dir = DIR_LEFT;
			}
		}
		this.moveDir(dir);
	}
}
