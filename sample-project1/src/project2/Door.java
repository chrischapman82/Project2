package project2;

import org.newdawn.slick.Graphics;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */

/** Contains information about the Door Sprite */

public class Door extends Sprite {

	private boolean closed;		/** whether the door is currently closed or open */
	
	public Door(float x, float y) {
		super(Constant.DOOR_PATH, x, y);
		this.addTag(Tag.DOOR);
		this.addTag(Tag.BLOCKED);
		closed = true;				// on init, the door should be closed
	}
	
	/** closes the door, meaning that the door is now blocked!
	 */
	public void close_door() {
		closed = true;
		if (!this.compareTag(Tag.BLOCKED)) {
			this.addTag(Tag.BLOCKED);
		}
	}
	
	/** Opens the door, meaning that the door is no longer blocked!
	 */
	public void open_door() {
		closed = false;
		if (this.compareTag(Tag.BLOCKED)) {
			this.removeTag(Tag.BLOCKED);
		}
	}
	/** Prints the current state of the world to the screen.
	 * 	Only shows the door whenever it is closed!
	 */
	public void render(Graphics g) {
		if (closed) {
			super.render(g);
		}
	}
}
