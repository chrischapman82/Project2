package project2;

/**
 * @author chris
 *
 */
public abstract class Constant {
	/** SWEN20003 Object Oriented Software Development 
	 * Project 2
	 * @author Christopher Chapman
	 * Student no: 767336
	 * Email: chapmanc1@student.unimelb.edu.au
	 * 
	 */
	
	/** Class containing constant variables used throughout the package 
	 * 
	 */
	
	// paths for the given sprites
	public static final String TNT_PATH = "res/tnt.png";
	public static final String ICE_PATH = "res/ice.png";
	public static final String STONE_PATH = "res/stone.png";
	public static final String FLOOR_PATH = "res/floor.png";
	public static final String TARGET_PATH = "res/Target.png";
	public static final String ROGUE_PATH = "res/rogue.png";
	public static final String MAGE_PATH = "res/mage.png";
	public static final String SWITCH_PATH = "res/switch.png";
	public static final String DOOR_PATH = "res/door.png";
	public static final String CRACKED_PATH = "res/cracked_wall.png";
	public static final String EXPLOSION_PATH = "res/explosion.png";
	
	
	/** The max_stack size. Also the max number of undos allotted */
	public static final int MAX_STACK_SIZE = 50;	// also the max number of undos
	
	/** Number of levels in the game */
	static final int MAX_LVL = 5;
	
	/** Number of deltas in a second, where each delta is 1 ms*/
	static final int DELTA_IN_SECOND = 1000;
	
}
