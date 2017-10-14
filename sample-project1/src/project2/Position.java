package project2;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */

/** An (x,y) position on the grid. Allows for greater flexibility */
public class Position {

	private float x;
	private float y;
	
	Position(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**	gets the x coordinate of the Position
	 * @return x pos
	 */
	public float getX() {
		return x;
	}

	/**	Sets the x coordinate of the Position
	 * @param 	an x coord
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**	gets the Y coordinate of the Position
	 * @return	y pos
	 */
	public float getY() {
		return y;
	}

	/**	Sets the Y coordinate of the position
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
	}
	
}
