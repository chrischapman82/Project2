package project1;

import java.util.Stack;

public abstract class Movable extends Sprite{

	private Stack<Position> stack;
	
	public Movable (String sprite_name, float x, float y) {
		super(sprite_name, x, y);
		stack = new Stack<>();
		this.addTag(Tag.MOVABLE);
	}
	
	public boolean hasHistory() {
		if (stack.isEmpty()) {
			System.out.println("stack is empty");
			return false;
		}
		// otherwise it has history
		return true;
	}
	
	public void addToHistory() {
		if (stack.size() > Constant.MAX_STACK_SIZE) {
			removeFromHistory(0);
		}
		Position curr_pos = new Position(this.getX(), this.getY());
		stack.add(curr_pos);
	}
	
	// for using outside of the funciton
	public int getStackSize() {
		return stack.size();
	}
	
	public void removeFromHistory(int index) {
		stack.remove(index);
	}
	
	public Position pop() {
		Position pos = stack.pop();
		return pos;
	}
	
	public void addToHistory(Position pos) {
		stack.add(pos);
	}
	
	public void undo() {
		
		if (hasHistory()) {
			
			
			Position prev_pos = stack.pop();
			if (this.compareTag("player")) {
				System.out.println("Has History! size  = " + stack.size());
				System.out.format("%f %f", prev_pos.getX(), prev_pos.getY());
			}
			
			// I'd like to make this all position rather than x and y
			this.setPos(prev_pos);
			
			//this.setX(prev_pos.getX());
			//this.setY(prev_pos.getY());
		}
		
		// otherwise do nothing!
		
	}
	
	
	// Moves @ the standard speed!
	public boolean canMoveDir(int dir) {
		return canMoveDir(dir, App.TILE_SIZE);
	}
	
	// checks if the given sprite can move a certain direction!
	// the given dir
	public boolean canMoveDir(int dir, float speed) {
		
		Position candidatePos = this.getDest(dir, speed, 1);
		
		if (World.isBlocked(candidatePos)) {
			// some boolean algebra equive for:
			// if (this.compareTag("can_push") && World.hasSpriteAtPos("pushable", candidatePos)) {
			if (!this.compareTag(Tag.CAN_PUSH) || !World.hasSpriteAtPos(Tag.PUSHABLE, candidatePos)) {
				return false;
			}
			
			// This means that this object can push, and checks
			// If there's an object blocking the way of the pushable object in front of this sprite, then don't you dare move!
			// Check 2 tiles away
			if ((World.isBlocked(this.getDest(dir, speed, 2)))) {
				// don't move
				return false;
			}
		}
		return true;
	}
	
	public void moveDir(int dir) {
		
		// In general, the std speed
		float speed = App.TILE_SIZE;
		this.moveDir(dir, speed);
		// Translate the direction to an x and y displacement
		
	}
	
	
	// for specifying speed!
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
	
	public void onMove(int dir, float testX, float testY) {
		
	}
}
