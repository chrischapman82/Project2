package project2;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */

/** Contains information about the cracked wall sprite.*/
public class Cracked extends Sprite {
	
	
	public Cracked(float x, float y) {
		super(Constant.CRACKED_PATH, x, y);
		this.addTag(Tag.CRACKED);
		this.addTag(Tag.BLOCKED);
	}
}
