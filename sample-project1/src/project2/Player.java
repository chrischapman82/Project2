package project2;

import org.newdawn.slick.Input;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 * 
 * Used code from project1-sample
 * @author Eleanor McMurtry
 * 
 * Based off of Sample 2A UML
 * @author Eleanor McMurtry 
 */

public class Player extends Movable {
	
	private int dir;
	
	public Player(float x, float y) {
		super("res/player_left.png", x, y);
		this.addTag(Tag.PLAYER);
		this.addTag(Tag.CAN_PUSH);
		this.addTag(Tag.UNDOABLE);
		dir = DIR_NONE;
	}


	/** gets the direction that the sprite's going
	 * @return
	 */
	public int getDir() {
		return dir;
	}


	/**	sets the direction that the sprite's going 
	 * @param dir	
	 */
	public void setDir(int dir) {
		this.dir = dir;
	}


	/** Uses keyboard input from World to move the player
	 */
	@Override
	public void update(int delta) {
		Input input = World.input;
		int dir = DIR_NONE;

		if (input.isKeyPressed(Input.KEY_LEFT)) {
			dir = DIR_LEFT;
		}
		else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			dir = DIR_RIGHT;
		}
		else if (input.isKeyPressed(Input.KEY_UP)) {
			dir = DIR_UP;
		}
		else if (input.isKeyPressed(Input.KEY_DOWN)) {
			dir = DIR_DOWN;
		}
		World.playerDir = dir;

		if (dir != DIR_NONE) {
			World.playerMoved = true;
			World.updateMovableHistory();
			moveDir(dir);
		}
	}
	
}
