package project2;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 * 
 * Contains code from project1-sample
 * @author Eleanor McMurtry 
 * 
 * Based off of Sample 2A UML
 * @author Eleanor McMurtry 
 */
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
	
	/**	gets the current position of the sprite
	 * @return
	 */
	public Position getPos() {
		Position pos = new Position(x,y);
		return pos;
	}
	
	/**	Sets the sprites current position
	 * @param pos
	 */
	public void setPos(Position pos) {
		x = pos.getX();
		y = pos.getY();
		snapToGrid();
	}
	
	/**	Gets the X value
	 * @return
	 */
	public float getX() {
		return x;
	}

	/**	Sets the X value and aligns it to grid as floats can be slightly change over time.
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
		snapToGrid();
	}

	/**	Gets the Y value
	 * @return
	 */
	public float getY() {
		return y;
	}

	/**	Sets the Y value and aligns it to grid as floats can be slightly change over time.
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
		snapToGrid();
	}

	/**	Adds a tag describing an attribute from the current sprite to the tag list
	 * @param tag	added to tag list
	 */
	public void addTag(String tag) {
		// adds the string to the tag list
		this.tags.add(tag);
	}
	
	/**	Removes a tag from the sprite list, using ArrayList's remove to check if 
	 * it's possible ot get rid of the tag.
	 * @param tag
	 */
	public void removeTag(String tag) {
		// remove auto checks if it's possible to get rid of the tag
		this.tags.remove(tag);
	}
	
	/**	Checks if a tag is in this sprites tag list
	 * @param compare
	 * @return
	 */
	public boolean compareTag(String compare) {
		if (this.tags.contains(compare)) {
			return true;
		}
		return false;
	}
	
	/**	udpates the current sprite once per frame. Generally overwritten.
	 * @param delta
	 */
	public void update(int delta) {
		
	}
	
	/**	Gets the position num_steps away in the given direction
	 * @param dir		The direction that the destination is.
	 * @param step_size	The size of each tile.
	 * @param num_steps	The number of steps in the given direction
	 * @return	the position of the destination num_steps away in the direction dir.
	 */
	public Position getDest(int dir, float step_size, int num_steps) {
		float delta_x = 0,
				delta_y = 0;
		switch (dir) {
			case DIR_LEFT:
				delta_x = -step_size;
				break;
			case DIR_RIGHT:
				delta_x = step_size;
				break;
			case DIR_UP:
				delta_y = -step_size;
				break;
			case DIR_DOWN:
				delta_y = step_size;
				break;
		}
		
		Position pos = new Position(this.x + num_steps*delta_x, this.y + num_steps*delta_y);
		return pos;
	}
	
	/**	Same as other, but using the standard speed based off of tile size given in app
	 */
	public Position getDest(int dir, int num_tiles) {
		float tile_size = App.TILE_SIZE;
		return getDest(dir, tile_size, num_tiles);
	}
	
	/** Prints the current state of the world to the screen.
	 * Only shows the door whenever it is closed!
	 * @param g
	 */
	public void render(Graphics g) {
		image.drawCentered(x, y);
	}
	
	/**Forces this sprite to align to the grid
	 */
	public void snapToGrid() {
		x /= App.TILE_SIZE;
		y /= App.TILE_SIZE;
		x = Math.round(x);
		y = Math.round(y);
		x *= App.TILE_SIZE;
		y *= App.TILE_SIZE;
	}	
}
