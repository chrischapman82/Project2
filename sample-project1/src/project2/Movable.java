package project2;

import java.util.Stack;


/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */
public abstract class Movable extends Sprite{

	private Stack<Position> history;	// a stack holding the history of all previous positions
	
	public Movable (String sprite_name, float x, float y) {
		super(sprite_name, x, y);
		history = new Stack<>();
		this.addTag(Tag.MOVABLE);
	}
	
	/** returns whether the history stack contains any elements! Used for privacy
	 * @return true if the history stack is not empty
	 */
	public boolean hasHistory() {
		if (history.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/** Adds the current sprite to the history stack.
	 * 	If the stored positions goes over the MAX_STACK_SIZE, removes from history.
	 * 	For flexibility, change Constant.MAX_STACK_SIZE for a different number of max undos.
	 */
	public void addToHistory() {
		if (history.size() > Constant.MAX_STACK_SIZE) {
			removeFromHistory(0);
		}
		Position curr_pos = new Position(this.getX(), this.getY());
		history.add(curr_pos);
	}
	
	/**	For adding a position to the history, rather than interrogating the sprite for it 
	 * @param pos	The position that is to be added to the History stack.
	 */
	public void addToHistory(Position pos) {
		history.add(pos);
	}
	
	/**	Gets the size of the current history stack.
	 * @return	the current size of the history stack.
	 */
	public int getStackSize() {
		return history.size();
	}
	
	/** Removes a position from the history stack at the given index.
	 * @param index	Specifies what index would like to be removed. 
	 */
	public void removeFromHistory(int index) {
		history.remove(index);
	}
	
	/**	Returns and removes the most recent element from the stack.
	 * @return	The most recently recorded positioin
	 */
	public Position pop() {
		Position pos = history.pop();
		return pos;
	}
	
	
	/**	Undoes the most recent move by getting the most recent position, and setting the Sprites
	 * 	position to that.
	 */
	public void undo() {
		
		if (hasHistory()) {
			Position prev_pos = history.pop();
			
			this.setPos(prev_pos);
		}
		// otherwise do nothing!
	}
	
	
	/**	Same as canMoveDir(int dir, float speed), but uses the tile Speed to set the pace.
	 * @param dir	The direction that sprite would like to check
	 * @return
	 */
	public boolean canMoveDir(int dir) {
		return canMoveDir(dir, App.TILE_SIZE);
	}
	
	// 
	// r
	/**	Checks if the given sprite can move a certain direction!
	 * @param dir	The direction that sprite would like to check
	 * @param speed	The distance from the sprite
	 * @return
	 */
	public boolean canMoveDir(int dir, float speed) {
		
		Position candidatePos = this.getDest(dir, speed, 1);
		
		if (World.isBlocked(candidatePos)) {
			
			// Equivelant to: curr_sprite-> canPush && pushableSprites @ candidatePos
			if (!this.compareTag(Tag.CAN_PUSH) || !World.hasSpriteAtPos(Tag.PUSHABLE, candidatePos)) {
				return false;
			}
			
			// if the sprite can push, it's pushing a TNT and there's a cracked wall next to it, go for it fam.
			if (World.hasSpriteAtPos(Tag.TNT, candidatePos) && World.hasSpriteAtPos(Tag.CRACKED, this.getDest(dir, speed, 2))) {
				return true;
			}
			
			// If the sprite is pushing a pushable object, can it move?
			// If there's an object blocking the way of the pushable object in front of this sprite, then don't you dare move!
			if ((World.isBlocked(this.getDest(dir, speed, 2)))) {
				// don't move
				return false;
			}
		}
		return true;
	}
	
	/** Moves sprite in the given direction. Uses standard speed of tile size
	 * @param dir	The direction that it'd like to move to.
	 */
	public void moveDir(int dir) {
		
		float speed = App.TILE_SIZE;
		this.moveDir(dir, speed);
	}
	
	
	/** Moves sprite in the given direction. Uses standard speed of tile size
	 * @param dir	The direction that it'd like to move to.
	 * @param speed	The speed at which the sprite can move
	 */
	public void moveDir(int dir, float speed) {
		
		// if no direction, do nothing!
		if (dir == DIR_NONE) {
			return;
		}
		
		if (canMoveDir(dir, speed)) {
			Position candidatePos = this.getDest(dir, speed, 1);
			this.setPos(candidatePos);
		}
	}
}
