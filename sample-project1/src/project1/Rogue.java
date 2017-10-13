package project1;

public class Rogue extends Movable {
	
	private int dir;
	public Rogue(float x, float y) {
		super(Constant.ROGUE_PATH, x, y);
		this.dir = DIR_LEFT;
		this.addTag(Tag.ROGUE);
		this.addTag(Tag.ENEMY);
		this.addTag(Tag.CAN_PUSH);
	}
	
	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public void update(int delta) {
		
		// if the player hasn't done anything. Don't do anything
		if (!World.playerMoved) {
			return;
		}
		
		Position dest = this.getDest(dir, App.TILE_SIZE, 1);
		// so that it won't push 
		// checks whether the destination is blocked AND that there isn't a pushable block
		if (World.isBlocked(dest) 
				&& !(World.hasSpriteAtPos(Tag.PUSHABLE, dest) && !World.isBlocked(this.getDest(delta, App.TILE_SIZE, 2)))) {
			
			// switch direction between up/down
			if (this.dir == DIR_LEFT) {
				this.dir = DIR_RIGHT;
			} else {
				this.dir = DIR_LEFT;
			}
		}
		this.moveDir(this.dir);
	}
}
