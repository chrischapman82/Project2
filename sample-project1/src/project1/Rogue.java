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
		if (World.isBlocked(this.getDest(dir, App.TILE_SIZE))) {
			
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
