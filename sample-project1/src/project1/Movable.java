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
		
		// USE getDest later. Better for privacy.
		// Position pos = this.getDest(dir, speed);
		// else go about your day
		float delta_x = 0,
				delta_y = 0;
		switch (dir) {
			case DIR_LEFT:
				delta_x = -speed;
				break;
			case DIR_RIGHT:
				delta_x = speed;
				break;
			case DIR_UP:
				delta_y = -speed;
				break;
			case DIR_DOWN:
				delta_y = speed;
				break;
		}
		
		float candidate_x = this.getX() + delta_x;
		float candidate_y = this.getY() + delta_y;
		// Make sure the position isn't occupied!
		if (!World.isBlocked(candidate_x, candidate_y)) {
			
			// if there's a pushable object, check that the pushable object can move
			if (World.getSpriteOfType("pushable", candidate_x, candidate_y) != null) {
				
				// if there's another pushable object in front of the pushable object, then don't you dare move!
				// i believe should still increase the mvoe counter!
				if ((World.isBlocked(candidate_x + delta_x, candidate_y + delta_y)) || 
						(World.getSpriteOfType("pushable", candidate_x + delta_x, candidate_y + delta_y) != null)) {
					// don't move
					return;
				}
			}
			
			// moves this block to it's new home
			this.setX(this.getX() + delta_x);
			this.setY(this.getY() + delta_y);
			
			/*if ((sprite = World.getSpriteOfType("pushable", sprite.getX() + delta_x, sprite.getY() + delta_y)) != null) {
				
				System.out.println("found pushable");
				System.out.println(sprite.toString());
				sprite.moveDir(dir);
			} */
			// adds curr pos to history
			
		}
	}
	
	public void onMove(int dir, float testX, float testY) {
		
	}
}
