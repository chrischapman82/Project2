package project2;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */
public class Rogue extends Movable {
	
	private int dir;
	public Rogue(float x, float y) {
		super(Constant.ROGUE_PATH, x, y);
		this.dir = DIR_LEFT;
		this.addTag(Tag.ROGUE);
		this.addTag(Tag.ENEMY);
		this.addTag(Tag.CAN_PUSH);
	}
	
	/**	Gets the current direction of the rogue
	 * @return
	 */
	public int getDir() {
		return dir;
	}

	// setter not used, so not included
	
	/* (non-Javadoc)
	 * @see project2.Sprite#update(int)
	 */
	public void update(int delta) {
		
		// if the player has not moved, don't do nothing.
		if (!World.playerMoved) {
			return;
		}
		
		Position dest = this.getDest(dir, App.TILE_SIZE, 1);
		
		// change direction if the destination is blocked AND that there isn't a pushable block that can move
		if (World.isBlocked(dest) 
				&& !(World.hasSpriteAtPos(Tag.PUSHABLE, dest) && !World.isBlocked(this.getDest(delta, App.TILE_SIZE, 2)))) {
			
			// switch direction between up/down
			if (this.dir == DIR_LEFT) {
				this.dir = DIR_RIGHT;
			} else {
				this.dir = DIR_LEFT;
			}
		}
		World.rogueDir = dir;
		this.moveDir(this.dir);
	}
}
