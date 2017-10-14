package project2;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */
public class Wall extends Sprite {
	public Wall(float x, float y) {
		super("res/wall.png", x, y);
		this.addTag("blocked");
	}
}
