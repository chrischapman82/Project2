package project1;

import java.util.Stack;

public abstract class Movable extends Sprite{

	// Make my own stack class at some point!
	// Use already made class History Stack
	private Stack<Position> stack;
	
	public Movable (String sprite_name, float x, float y) {
		super(sprite_name, x, y);
		stack = new Stack<>();
		this.addTag("movable");
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
		if (getStackSize() > 20) {
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
		
		// only undo if it has history
		if (this.hasHistory()) {
			System.out.println("Has History!");
			Position prev_pos = stack.pop();
			// I'd like to make this all position rather than x and y
			this.setX(prev_pos.getX());
			this.setY(prev_pos.getY());
		}
		
		// otherwise do nothing!
		
	}
	
	public void moveDir(int dir) {
		
		// the normal speed!
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
		
		System.out.println("moving");
		System.out.println(this.toString());
		
		Position candidatePos = this.getDest(dir, speed, 1);
		
		if (World.isBlocked(candidatePos)) {
			
			// if this sprite can push pushable objects and there is a pushable object in its way,
			// check that the pushable object can move
			if (this.compareTag("can_push") && World.getSpriteOfType("pushable", candidatePos) != null) {
				
				// if there's another pushable object in front of the pushable object, then don't you dare move!
				// Check 2 tiles away
				if ((World.isBlocked(this.getDest(dir, speed, 2)))) {
					// don't move
					return;
				}
			} else {
				return;
			}
		}
			
			// moves this block to it's new home
		this.setX(candidatePos.getX());
		this.setY(candidatePos.getY());
			
			/*if ((sprite = World.getSpriteOfType("pushable", sprite.getX() + delta_x, sprite.getY() + delta_y)) != null) {
				
				System.out.println("found pushable");
				System.out.println(sprite.toString());
				sprite.moveDir(dir);
			} */
			// adds curr pos to history
			
	}
	
	public void onMove(int dir, float testX, float testY) {
		
	}
}
