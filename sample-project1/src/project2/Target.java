package project2;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */

/** Contains information about the Target class */
public class Target extends Sprite {

	
	public Target(float x, float y) {
		super(Constant.TARGET_PATH, x, y);
		this.addTag(Tag.TARGET);
	}
	
	/**	Sees if the target is activated. Used to decided whether the player has won
	 * @return	true if the current target has been activated
	 */
	public boolean activated() {
		if (World.hasSpriteAtPos(Tag.PUSHABLE, this.getPos())) {
			return true;
		}
		return false;
	}
}
