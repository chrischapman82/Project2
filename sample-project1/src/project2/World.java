package project2;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

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
 * 
 * 
 * World class
 */

public class World {
	/** arraylist containing all the sprites in the world*/
	private static ArrayList<Sprite> sprites;
	private static int curr_level;
	public static Input input;
	public static int num_moves;
	public static boolean playerMoved;
	public static int playerDir;
	public static int rogueDir;
	
	
	/** This class controls, renders, and updates all the sprites in the game.
	 * 
	 */
	public World(int level) {
		// loads in the current level from file
		sprites = Loader.loadSprites("res/levels/" + Integer.toString(curr_level) + ".lvl");
		num_moves = 0;
		initSwitch();		// if there is a switch, pair the switch and door @ runtime
	}
	
	
	/** If there is a switch, links it to a door so that it can control it
	 * Assumes that if there's a switch, there is also a door!
	 */
	public static void initSwitch() {
		
		if (World.getSpriteOfType(Tag.SWITCH) != null) {
			((Switch)World.getSpriteOfType(Tag.SWITCH)).setDoor((Door)(World.getSpriteOfType(Tag.DOOR)));
		}
		
	}
	
	/** sets a Sprite to null in the array! Never to be seen again */
	public static void destroySprite(Sprite sprite) {
		int index = sprites.indexOf(sprite);
		System.out.println("Destroying sprite");
		sprites.set(index, new Explosion(sprite.getX(), sprite.getY()));
	}
	
	/** creates an explosion object. Used when destroying Sprites */
	public static void createExplosion(int index, float x, float y) {
		
		sprites.set(index, new Explosion(x,y));
	}

	/**
	 * @param x, the x locatiion to check
	 * @param y, the y location to check
	 * @return true if the given position (x,y) is either out of bounds, or blocked by an object with the tag Tag.BLOCKED 
	 */
	public static boolean isBlocked(float x, float y) {
		// checks that the given pos is within the game bounds
		if (!Loader.inBounds((int)Loader.getTileX(x),(int)Loader.getTileY(y))) {
			return true;
		}
		
		if (hasSpriteAtPos(Tag.BLOCKED, x, y)) {
			return true;
		}
		return false;
	}
	

/**
 * @param pos the position to check
 * @return checks if pos is blocked by an object w/ Tag.BLOCKED or out of bounds.
 * This is useful when pos is more readily available than x and y coordinates 
 */
public static boolean isBlocked(Position pos) {
		
		// checks that the requested position is in bounds
		if (!Loader.inBounds((int)Loader.getTileX(pos.getX()),(int)Loader.getTileY(pos.getY()))) {
			return true;
		}
		
		// checks whether the requested position is taken by an object with the blocked tag
		if (hasSpriteAtPos(Tag.BLOCKED, pos)) {
			return true;
		}
		
		return false;
	}
	
/**
 * Updates all movable objects history. Allows for undoing
 */
	public static void updateMovableHistory() {
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				if (sprite.compareTag(Tag.MOVABLE)) {
					((Movable)sprite).addToHistory();
				}
			}
		}
	}
	/** Undoes all movable sprites */
	public static void undoMovables() {
		for (Sprite sprite : sprites) {
			
			// undo all movable sprites!
			if (sprite != null) {
				if (sprite.compareTag(Tag.UNDOABLE)) {
					((Movable)sprite).undo();
				}
			}
			
		}
		// reduces by 1 each time!
		// done as per specs, just decreases whenever the player undoes a move!
		// Cannot go negative!
		if (num_moves > 0) {
			num_moves--;
		}
	}
	
	/** gets the first available sprite of type tag */
	public static Sprite getSpriteOfType(String tag) {
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				if (sprite.compareTag(tag)) {
					return sprite;
				}
			}
		}
		return null;
	}
	
	/** Gets a sprite with the given tag at position (x,y) 
	 * 
	 * @param tag
	 * @param x	x-pos of the sprite to be checked
	 * @param y	y-pos of the sprite to be checked
	 * @return	null if no sprite of the type at the given position.
	 * Else, returns the sprite at that place
	 */
	public static Sprite getSpriteOfType(String tag, float x, float y) {
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				if (sprite.compareTag(tag) 	&& (Loader.getTileX(x) == Loader.getTileX(sprite.getX())) 
											&& (Loader.getTileY(y) == Loader.getTileY(sprite.getY()))) {
					return sprite;
				}
			}
		}
		// returns null if nothing there
		return null;
	}
	
	
	/** for when using a position is better	*/
	public static Sprite getSpriteOfType(String tag, Position pos) {
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				if (sprite.compareTag(tag) 	&& (Loader.getTileX(pos.getX()) == Loader.getTileX(sprite.getX())) 
											&& (Loader.getTileY(pos.getY()) == Loader.getTileY(sprite.getY()))) {
					return sprite;
				}
			}
		}
		// returns null if nothing there
		return null;
	}
	
	/** for when getting the sprite doesn't matter, just used as a check */
	public static boolean hasSpriteAtPos(String tag, float x, float y) {
		if (getSpriteOfType(tag, x, y) != null) {
			return true;
		}
		return false;
	}
	
	public static boolean hasSpriteAtPos(String tag, Position pos) {
		if (getSpriteOfType(tag, pos.getX(), pos.getY()) != null) {
			return true;
		}
		return false;
	}
	
	/** updates the sprites once per frame*/
	public void update(Input input, int delta) {
		
		// setting whether the player has moved to false once per round
		playerMoved = false;
		this.input = input;
		
		// For keyboard commands
		// check if you've won!
		if (hasWon()) {
			loadNextLevel();
		}
		
		// exits the program
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
		
		// Go to next level. For testing!
		
		/*if (input.isKeyPressed(Input.KEY_L)) {
			loadNextLevel();
		}*/
		
		// If R, restart level
		if (input.isKeyPressed(Input.KEY_R)) {
			restartLevel();
		}
		
		// undoing a move!
		if (input.isKeyPressed(Input.KEY_Z)) {
			undoMovables();
			return;
		}
		
		// do player first:
		for (Sprite player : sprites) {
			if (player != null && player.compareTag(Tag.PLAYER)) {
				
				player.update(delta);
			}
		}
		
		for (Sprite enemy : sprites) {
			if (enemy != null && enemy.compareTag(Tag.ENEMY)) {
				enemy.update(delta);
				
				// checks if the player has hit an enemy
				if (hasSpriteAtPos(Tag.PLAYER, enemy.getPos())) {
					restartLevel();
				}
			}
		}
		
		// Now for the rest!
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				
				// for pushable objects!
				if (sprite.compareTag(Tag.PUSHABLE)) {
					
					// If the player or rogue is on top of the pushable block, push it!
					if (hasSpriteAtPos(Tag.PLAYER, sprite.getPos())) {
						
						if (sprite.compareTag(Tag.TNT)) {
							((Tnt)sprite).push(playerDir);
						} else {
							((Pushable)sprite).push(playerDir);
						}
					}
					
					if (hasSpriteAtPos(Tag.ROGUE, sprite.getX(), sprite.getY())) {
						((Pushable)sprite).push(rogueDir);
					}
					
					if (sprite.compareTag(Tag.TNT)) {
						
						// if shares the same spot as a cracked wall
						// EXPLODES
						if ((getSpriteOfType(Tag.CRACKED, sprite.getX(), sprite.getY())) != null) {
							destroySprite(sprite);
							destroySprite(getSpriteOfType(Tag.CRACKED, sprite.getX(), sprite.getY()));
						}
					}
					
					if (sprite.compareTag(Tag.ICE))  {
						((Ice)sprite).update(delta);
					}
				} else if (!sprite.compareTag(Tag.ENEMY) && (!sprite.compareTag(Tag.PLAYER))){
					sprite.update(delta);
					
				}
				
			}
		}
	}
	
	
	public void render(Graphics g) {
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				
				// don't render if explosion is not active
				if (sprite.compareTag(Tag.EXPLOSION) && !((Explosion)sprite).isActive()) {
				} else {
					sprite.render(g);
				}
				
			}
		}
		
		// drawing the number of moves in the top left
		if (World.curr_level != Constant.MAX_LVL) {
			g.drawString("Moves: " + num_moves, 0, 0);
			if (playerMoved) {
				num_moves++;
			}
		}
		
	}
	
	public void restartLevel() {
		new World(curr_level);
	}
	
	public boolean hasWon() {
		for (Sprite sprite: sprites) {
			if (sprite != null) {
				if (sprite.compareTag("target") && !((Target)sprite).activated()) {
					return false;
				}
			}
		}
		return true;
		
	}
	
	/** Loads the next level*/
	public void loadNextLevel() {
		
		if (curr_level >= Constant.MAX_LVL) {
		} else {
			curr_level++;
			new World(curr_level);
		}
		
	}
	
}
