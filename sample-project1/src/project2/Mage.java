package project2;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */

/** Contains positional and resource file info about the Mage class
 *  The mage is an enemy in the game that tries to hunt down the player.
 */
public class Mage extends Movable {

	private int dir1;	// the x direction from the Mage to the player
	private int dir2;	// the y direction from teh Mage to the Player
	
	public Mage(float x, float y) {
		super(Constant.MAGE_PATH, x, y);
		this.addTag(Tag.ENEMY);
		this.addTag(Tag.MAGE);
	}
	
	/* (non-Javadoc)
	 * @see project1.Sprite#update(int)
	 */
	public void update(int delta) {
		
		// Updates the Mage whenever it moves
		if (World.playerMoved) {
			
			Sprite player = World.getSpriteOfType(Tag.PLAYER);
			trackPlayer(player.getX(), player.getY());
		}
		
	}
	
	/** helper function that tracks the player and moves in the direction of the player
	 * using the algorithm given in the project 2 specification
	 * @param player_x	The current x-position of the Player
	 * @param player_y	The current y-position of the Player
	 */
	private void trackPlayer(float player_x, float player_y) {
		
		// Finds the distance from the player to the mage!
		float dist_x = this.getX() - player_x;
		float dist_y = this.getY() - player_y;
		
		// sets directions based on the player's location!
		if (getSign(dist_x) < 0 ) {
			dir1 = DIR_RIGHT;
		} else {
			dir1 = DIR_LEFT;
		}
		
		if (getSign(dist_y) < 0) {
			dir2 = DIR_DOWN;
		} else {
			dir2 = DIR_UP;
		}
		
		System.out.println(this.canMoveDir(dir2));
		if (Math.abs(dist_x) > Math.abs(dist_y) && this.canMoveDir(dir1, App.TILE_SIZE)) {
			this.moveDir(dir1, App.TILE_SIZE);
		} else if (this.canMoveDir(dir2)) {
			this.moveDir(dir2, App.TILE_SIZE);
			
		}
	}
	
	/** helper function that checks the sign of a number
	 * @param num	A float
	 * @return		returns -1 if a negative number
	 * 				returns 1 if a positive number OR 0 
	 */
	private int getSign(float num) {
		if (num < 0) {
			return -1;
		} else {
			return 1;
		}
	}
	
}
