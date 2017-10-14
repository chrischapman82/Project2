package project2;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */
public abstract class Pushable extends Movable {

	public Pushable(String sprite_name, float x, float y) {
		super(sprite_name, x, y);
		this.addTag(Tag.PUSHABLE);
		this.addTag(Tag.BLOCKED);
		this.addTag(Tag.UNDOABLE);
	}
	
	/**	The object is being pushed by a sprite that can_push blocks
	 * @param dir	The direction the Sprite is being pushed in
	 */
	public void push(int dir) {

		// do nothing if no direction given!
		if (dir == DIR_NONE) {
			return;
		}
		
		
		Position candidatePos = this.getDest(dir, 1);
		// If any blockage, don't move there!
		if (!World.isBlocked(candidatePos)) {
			this.setPos(candidatePos);
		}
	}
	
	/**	Determines whether the current sprite is active.
	 * 	Used to check whether a target is being held down.
	 * @return	true if the sprite is on a target
	 */
	public boolean active() {
		if (World.getSpriteOfType(Tag.TARGET, this.getX(), this.getY()) != null) {
			return true;
		}
		return false;
	}
}
