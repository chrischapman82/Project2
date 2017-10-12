package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class Sprite {
	// Used to decide what direction an object is moving
	// Look up enums to find a more elegant solution!
	public static final int DIR_NONE = 0;
	public static final int DIR_LEFT = 1;
	public static final int DIR_RIGHT = 2;
	public static final int DIR_UP = 3;
	public static final int DIR_DOWN = 4;
	
	private Image image = null;
	private float x;
	private float y;
	private ArrayList<String> tags = new ArrayList<>();	// maybe only init if called
	
	public Sprite(String image_src, float x, float y) {
		try {
			image = new Image(image_src);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;
		// probably add name to the tags?
		snapToGrid();
	}
	
	// dunno if I want these!
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void addTag(String tag) {
		// adds the string to the tag list
		this.tags.add(tag);
	}
	
	public void removeTag(String tag) {
		// remove auto checks if it's possible to get rid of the tag
		this.tags.remove(tag);
	}
	
	// if the string is in current tags
	public boolean compareTag(String compare) {
		if (this.tags.contains(compare)) {
			return true;
		}
		return false;
	}
	
	public void update(Input input, int delta) {
		
	}
	
	// in here for privacy reasons!
	// Try to get this working later. Used for all movement like pushing and moveDir!
	public Position getDest(int dir, float speed) {
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
		Position pos = new Position(x + delta_x,y + delta_y);
		return pos;
	}
	
	public void render(Graphics g) {
		image.drawCentered(x, y);
	}
	
	// Forces this sprite to align to the grid
	public void snapToGrid() {
		x /= App.TILE_SIZE;
		y /= App.TILE_SIZE;
		x = Math.round(x);
		y = Math.round(y);
		x *= App.TILE_SIZE;
		y *= App.TILE_SIZE;
	}
	//public void moveToDest(int dir) {
	//}
}
