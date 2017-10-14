package project2;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */

/** Contains information about the explosion class, which occurs when some objects are destroyed. */
public class Explosion extends Sprite {

	/** the time passed since the previous update */
	private int time;		
	
	/** The length of the explosion*/
	public static float EXPLOSION_LENGTH = (float) (0.4 * Constant.DELTA_IN_SECOND);

	/** true if the explosion is currently happening */
	private boolean active;
	
	/** Creates explosion Sprite at position (x,y)
	 * @param x the x-pos
	 * @param y the y-pos
	 */
	public Explosion(float x, float y) {
		super(Constant.EXPLOSION_PATH, x, y);
		this.addTag(Tag.EXPLOSION);
		active = true;
	}
	
	
	/* (non-Javadoc)
	 * @see project2.Sprite#update(int)
	 */
	public void update(int delta) {
		time += delta;
		if (time > EXPLOSION_LENGTH) {
			this.active = false;
		}
	}
	
	/** 
	 * @return true if Explosion is currently active, meaning that the explosion is currently happening
	 */
	public boolean isActive() {
		return active;
	}
}


