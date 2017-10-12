package project1;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	private static ArrayList<Sprite> sprites;
	private static int curr_level;
	public static Input input;
	public static int num_moves;
	public static boolean playerMoved;
	public static int playerInput;
	
	static final int MAX_LVL = 5;
	
	public World(int level) {
		// loads in the current level from file
		sprites = Loader.loadSprites("res/levels/" + Integer.toString(curr_level) + ".lvl");
		playerMoved = false;
		num_moves = 0;
	}
	
	
	public void createSprite(Sprite sprite) {
		
	}
	
	public static void destroySprite(Sprite sprite) {
		int index = sprites.indexOf(sprite);
		sprites.set(index, null);
		System.out.println();
	}
	
	public static boolean isBlocked(float x, float y) {
		
		// checks that it's in bounds
		// maybe could put getTileX and Y in Loader inBounds
		if (!Loader.inBounds((int)Loader.getTileX(x),(int)Loader.getTileY(y))) {
			return true;
		}
		
		//System.out.println(x);
		if (getSpriteOfType("blocked", x, y) != null) {
			// also check that it's not blocked by a sprite!
			System.out.println("Is blocked");
			return true;
		}
		
		return false;
	}
	
public static boolean isBlocked(Position pos) {
		
		// checks that it's in bounds
		// maybe could put getTileX and Y in Loader inBounds
		if (!Loader.inBounds((int)Loader.getTileX(pos.getX()),(int)Loader.getTileY(pos.getY()))) {
			return true;
		}
		
		//System.out.println(x);
		if (getSpriteOfType("blocked", pos.getX(), pos.getY()) != null) {
			// also check that it's not blocked by a sprite!
			System.out.println("Is blocked");
			return true;
		}
		
		return false;
	}
	
	public void updateMovableHistory() {
		for (Sprite sprite: sprites) {
			if (sprite.compareTag("movable")) {
				System.out.println("Adding to history!");
				((Movable)sprite).addToHistory();
			}
		}
		// for sprite:sprites: if movable, do the moveable stuff!
	}
	
	public static void undoMovables() {
		
		for (Sprite sprite : sprites) {
			if (sprite.compareTag("movable")) {
				((Movable)sprite).undo();
				System.out.println(sprite.getClass());
			}
			
		}
		// reduces by 1 each time!
		// done as per specs, just decreases whenever the player undoes a move!
		// However, doesn't go negative, as that would kinda be bad
		if (num_moves > 0) {
			num_moves--;
		}
	}
	
	public static Sprite getSpriteOfType(String tag) {
		// for stuff like move rogue when I move!
		return null;
	}
	
	public static Sprite getSpriteOfType(String tag, float x, float y) {
		for (Sprite sprite : sprites) {
			
			if (sprite.compareTag(tag) 	&& (Loader.getTileX(x) == Loader.getTileX(sprite.getX())) 
										&& (Loader.getTileY(y) == Loader.getTileY(sprite.getY()))) {
				return sprite;
			}
		}
		
		// returns null if nothing there
		return null;
	}
	
	
	// for when using a position is better
	public static Sprite getSpriteOfType(String tag, Position pos) {
		for (Sprite sprite : sprites) {
			
			if (sprite.compareTag(tag) 	&& (Loader.getTileX(pos.getX()) == Loader.getTileX(sprite.getX())) 
										&& (Loader.getTileY(pos.getY()) == Loader.getTileY(sprite.getY()))) {
				return sprite;
			}
		}
		// returns null if nothing there
		return null;
	}
	
	
	public void update(Input input, int delta) {
		
		this.input = input;
		
		// For keyboard commands
		// check if you've won!
		if (hasWon()) {
			System.out.println("You are a winner lul");
			loadNextLevel();
		}
		
		// exits the program
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
		
		// Go to next level. For testing!
		if (input.isKeyPressed(Input.KEY_L)) {
			loadNextLevel();
		}
		
		// If R, restart level
		if (input.isKeyPressed(Input.KEY_R)) {
			restartLevel();
		}
		
		// undoing a move!
		if (input.isKeyPressed(Input.KEY_Z)) {
			undoMovables();
		}
		
		
		// do player first:
		for (Sprite player : sprites) {
			if (player != null && player.compareTag("player")) {
				
				// TODO check if it's moved
				player.update(delta);
			}
		}
		
		// for movables:
		// for sprite:sprites where tag == movable
		// onMove
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				
				// for pushable objects!
				if (sprite.compareTag("pushable")) {
					
					// see if the player pushes the object
					if (getSpriteOfType("player", sprite.getX(), sprite.getY()) != null) {
						System.out.println("I've got a player on me!");
						System.out.println(playerInput);
						((Pushable)sprite).push(playerInput);
					}
					
					// see if it's on a switch
					if (getSpriteOfType("switch", sprite.getX(), sprite.getY()) != null) {
						
					}
					
					if (sprite.compareTag("tnt")) {
						// if the tnt hit's a cracked wall... 
						
						if (getSpriteOfType("cracked", sprite.getX(), sprite.getY()) != null) {
							//destroySprite(sprite);
						}
					}
					
					if (sprite.compareTag("ice"))  {
						((Ice)sprite).update(delta);
					}
				} 
				// now for the eneie
				else if (sprite.compareTag("enemy")) {
					
					//sprite.update(delta);
					// check uml. Need to change update in sprite
					
					if (sprite.compareTag("skeleton")) {
						((Skeleton)sprite).update(delta);
					}
					if (sprite.compareTag("rogue") && playerMoved) {
						((Rogue)sprite).update(delta);
						//TODO push blocks
					}
					if (sprite.compareTag("mage") && playerMoved) {
						((Mage)sprite).update(delta);
					}
					// check if the enemy killed you!
					if (getSpriteOfType("player", sprite.getX(), sprite.getY()) != null) {
						restartLevel();
						return;
					}
				} else {
					sprite.update(delta);
				}
				
			}
		}
		
		// update all movables whenever the player moves!
		if (playerMoved) {
			System.out.println("Updating history!");
			updateMovableHistory();
		}
	}
	
	public void render(Graphics g) {
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				if (sprite.compareTag("tnt")) {
					
				}
				sprite.render(g);
			}
		}
		
		// drawing the number of moves
		g.drawString("Moves: " + num_moves, 0, 0);
		if (playerMoved) {
			num_moves++;
			playerMoved = false;
		}
		
		
	}
	
	public void restartLevel() {
		new World(curr_level);
	}
	
	public boolean hasWon() {
		
		for (Sprite sprite: sprites) {
			if (sprite.compareTag("target") && !((Target)sprite).activated()) {
				return false;
			}
		}
		return true;
		
	}
	
	public void loadNextLevel() {
		System.out.println("Loading level: " + curr_level);
		if (curr_level >= MAX_LVL) {
			// do something
			// maybe timer for 5 seconds then leave?
		} else {
			curr_level++;
			new World(curr_level);
		}
		
	}
	
}
