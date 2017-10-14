package project2;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */
public class Timer {

	private int time;
	private int target;	// target is the amount of time per movement
	
	public static final int DELTA_IN_SECOND = 1000;
	
	public Timer(int time, int target) {
		this.time = time;
		this.target = target;
	}
	
	/**	Tracks the amount of time passed
	 * @param delta	Time since update was last called in ms
	 */
	public void update(int delta) {
		time += delta;
	}
	
	/**	Checks if the current timer has passed its target
	 * @return	True if it has
	 */
	public boolean expired() {
		if (time > target) {
			time -= target;
			return true;
		}
		return false;
		
	}
	
}
