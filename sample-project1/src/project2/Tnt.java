package project2;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */
public class Tnt extends Pushable {
	
	private boolean active;
	public Tnt(float x, float y) {
		super(Constant.TNT_PATH, x, y);
		this.addTag(Tag.TNT);
		active = true;
	}
	
	// overides so that it can be pushed into a cracked wall
	public void push(int dir) {
		if (dir == DIR_NONE) {
			return;
		}
		
		// If any blockage, don't move there!
		Position candidate_pos = this.getDest(dir, 1);
		
		
		if (World.hasSpriteAtPos(Tag.CRACKED, candidate_pos)) {
			this.setPos(candidate_pos);
		}
		if (!World.isBlocked(candidate_pos)) {
			this.setPos(candidate_pos);
		}
	}
	
	public void explode() {
		active = false;
	}
	
	
	public boolean isActive() {
		return active;
	}
}
