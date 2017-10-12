package project1;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	public static ArrayList<Sprite> sprites;
	private static String curr_lvl;
	private static int level;
	//private Input input;
	// private int nextLevel;
	public static int num_moves = 0;
	public static boolean playerMoved = false;
	public static int playerInput;
	
	static final int MAX_LVL = 5;
	public World(int level) {
		curr_lvl = "res/levels/" + Integer.toString(level) + ".lvl";
		sprites = Loader.loadSprites(curr_lvl);
		//this.level = level;
	}
	
	public static void createSprite(Sprite sprite) {
		
	}
	
	public static void destroySprite(Sprite sprite) {
		
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
		
		// if there's a pushable object, only go there if it can move as well!
		//if (getSpriteOfType("pushable", x, y) != null && ) {
			
		//}
		
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
			//System.out.println(tag);
			//System.out.println(Loader.getTileX(x));
			//System.out.println(Loader.getTileX(sprite.getX()));
			if (sprite.compareTag(tag) 	&& (Loader.getTileX(x) == Loader.getTileX(sprite.getX())) 
										&& (Loader.getTileY(y) == Loader.getTileY(sprite.getY()))) {
				return sprite;
			}
		}
		// returns null if nothing there
		return null;
	}
	
	public void update(Input input, int delta) {
		
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
		
		// restart the level if R is pressed
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
				//System.out.println(input);
				player.update(input, delta);
			}
		}
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
				} else {
					sprite.update(input, delta);
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
		num_moves = 0;
		new World(level);
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
		System.out.println("Loading level: " + level);
		if (level >= MAX_LVL) {
			// do something
			// maybe timer for 5 seconds then leave?
		} else {
			level++;
			num_moves = 0;
			new World(level);
		}
		
	}
	
}
