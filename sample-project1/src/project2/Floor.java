package project2;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */

public class Floor extends Sprite {
	/** The Floor class. Contains positional and image infomration about the Floor
	 * @param x x-pos
	 * @param y y-pos
	 */  
	public Floor(float x, float y) {
		super(Constant.FLOOR_PATH, x, y);
	}
}
