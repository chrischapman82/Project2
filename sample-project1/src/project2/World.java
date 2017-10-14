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
	public static int playerDir;
	public static int rogueDir;
	
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
		System.out.println("destroying sprite");
	}
	
	public static boolean isBlocked(float x, float y) {
		
		// checks that the given pos is within the game bounds
		if (!Loader.inBounds((int)Loader.getTileX(x),(int)Loader.getTileY(y))) {
			return true;
		}
		
		if (getSpriteOfType(Tag.BLOCKED, x, y) != null) {
			// also check that it's not blocked by a sprite!
			System.out.println("Is blocked");
			return true;
		}
		
		return false;
	}
	
public static boolean isBlocked(Position pos) {
		
		// checks that the requested position is in bounds
		if (!Loader.inBounds((int)Loader.getTileX(pos.getX()),(int)Loader.getTileY(pos.getY()))) {
			return true;
		}
		
		// checks whether the requested position is taken by an object with the blocked tag
		if (getSpriteOfType(Tag.BLOCKED, pos.getX(), pos.getY()) != null) {
			// also check that it's not blocked by a sprite!
			System.out.println("Is blocked");
			return true;
		}
		
		return false;
	}
	
	public static void updateMovableHistory() {
		for (Sprite sprite: sprites) {
			if (sprite.compareTag("movable")) {
				((Movable)sprite).addToHistory();
			}
		}
	}
	
	public static void undoMovables() {
		for (Sprite sprite : sprites) {
			// undo all movable sprites!
			if (sprite.compareTag(Tag.UNDOABLE)) {
				((Movable)sprite).undo();
				//System.out.println(sprite.getClass());
			}
			
		}
		// reduces by 1 each time!
		// done as per specs, just decreases whenever the player undoes a move!
		// Cannot go negative!
		if (num_moves > 0) {
			num_moves--;
		}
	}
	
	// gets the first available sprite of type tag
	public static Sprite getSpriteOfType(String tag) {
		for (Sprite sprite : sprites) {
			if (sprite.compareTag(tag)) {
				return sprite;
			}
		}
		return null;
	}
	
	public static Sprite getSpriteOfType(String tag, float x, float y) {
		for (Sprite sprite : sprites) {
			//System.out.println("Comparing:");
			//System.out.format("x: %f %f\n", Loader.getTileX(x), Loader.getTileX(sprite.getX()));
			//System.out.format("y: %f %f\n", Loader.getTileY(y), Loader.getTileY(sprite.getY()));
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
	
	// for when getting the sprite doesn't matter
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
	
	public void update(Input input, int delta) {
		
		this.input = input;
		
		// For keyboard commands
		// check if you've won!
		if (hasWon()) {
			System.out.println("You are a winner");
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
			return;
		}
		
		// do player first:
		for (Sprite player : sprites) {
			if (player != null && player.compareTag(Tag.PLAYER)) {
				
				player.update(delta);
			}
		}
		
		//448,352 -> cracked pos
		// next the enemies, as some have special interactions with other blocks
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
					if (hasSpriteAtPos(Tag.PLAYER, sprite.getX(), sprite.getY())) {
						((Pushable)sprite).push(playerDir);
					}
					
					if (hasSpriteAtPos(Tag.ROGUE, sprite.getX(), sprite.getY())) {
						((Pushable)sprite).push(rogueDir);
					}
					
					if (sprite.compareTag(Tag.TNT)) {
						// if the tnt hit's a cracked wall... 
						
						if (getSpriteOfType(Tag.CRACKED, sprite.getX(), sprite.getY()) != null) {
							System.out.println("hey guys");
							//destroySprite(sprite);
						}
					}
					
					if (sprite.compareTag(Tag.ICE))  {
						((Ice)sprite).update(delta);
					}
				} else if (!sprite.compareTag(Tag.ENEMY)){
					sprite.update(delta);
				}
				
			}
			
			for (Sprite test : sprites) {
				if (test.compareTag(Tag.TNT)) {
					if (hasSpriteAtPos(Tag.CRACKED, test.getPos())) {
						Sprite cracked = getSpriteOfType(Tag.CRACKED, test.getX(), test.getY());
						System.out.println(cracked.getX());
					}
				}
			}
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
		if (curr_level >= Constant.MAX_LVL) {
			// do something
			// maybe timer for 5 seconds then leave?
		} else {
			curr_level++;
			new World(curr_level);
		}
		
	}
	
}
