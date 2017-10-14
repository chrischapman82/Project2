package project2;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */

/** Contains info about the stone class, including tags, images and position */
public class Stone extends Pushable {
	/**
	 * @param x
	 * @param y
	 */
	public Stone(float x, float y) {
		super(Constant.STONE_PATH, x, y);
		this.addTag(Tag.STONE);
		this.addTag(Tag.BLOCKED);
	}
	
}
