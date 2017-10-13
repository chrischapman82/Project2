package project1;

/**
 * @author chris
 *
 */
public class Mage extends Movable {

	private int dir1;
	private int dir2;
	public Mage(float x, float y) {
		super(Constant.MAGE_PATH, x, y);
		this.addTag(Tag.ENEMY);
		this.addTag(Tag.MAGE);
	}
	
	/* (non-Javadoc)
	 * @see project1.Sprite#update(int)
	 */
	public void update(int delta) {
		
		if (World.playerMoved) {
			
			// gets the player
			Sprite player = World.getSpriteOfType(Tag.PLAYER);
			trackPlayer(player.getX(), player.getY());
		}
		
	}
	
	public void trackPlayer(float player_x, float player_y) {
		
		// 1 find the distance from the player to the mage!
		float dist_x = this.getX() - player_x;
		float dist_y = this.getY() - player_y;
		
		System.out.println(dist_x);
		System.out.println(getSign(dist_x));
		// 3
		// set directions based on the player!
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
		// will only move if possible due to how moveDir works!
	}
	
	// helper function
	// checks the sign of a number num
	public int getSign(float num) {
		if (num < 0) {
			return -1;
		} else {
			return 1;
		}
	}
	
}
