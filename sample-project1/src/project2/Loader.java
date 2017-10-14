package project2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */
public class Loader {
	/**
	 * 
	 */
	private static String[][] types;
	
	// the world dimensions
	private static int world_width;
	private static int world_height;
	
	// Offset so that the game is placed towards the center of the screen 
	private static int offset_x;
	private static int offset_y;
	
	/**
	 * Create the appropriate sprite given a name and location.
	 * @param name	the name of the sprite
	 * @param x		the x position
	 * @param y		the y position
	 * @return		the sprite object
	 */
	/**
	 * @param name
	 * @param x
	 * @param y
	 * @return
	 */
	private static Sprite createSprite(String name, float x, float y) {
		switch (name) {
			case "wall":
				return new Wall(x,y);
			case "floor":
				return new Floor(x,y);
			case "stone":
				return new Stone(x,y);
			case "target":
				return new Target(x,y);
			case "player":
				return new Player(x,y);
			case "cracked":
				return new Cracked(x,y);
			case "door":
				return new Door(x,y);
			case "ice":
				return new Ice(x,y);
			case "skeleton":
				return new Skeleton(x,y);
			case "mage":
				return new Mage(x,y);
			case "rogue":
				return new Rogue(x,y);
			case "switch":
				return new Switch(x,y);
			case "tnt":
				return new Tnt(x,y);
			case "explosion":
				return new Explosion(x,y);
			default:
				return null;
		}
	}
	
	/** Converts a world X value to a tile x value
	 * @param x value
	 * @return The tile representation of x
	 */
	public static float getTileX(float x) {
		x -= offset_x;
		x /= App.TILE_SIZE;
		x = Math.round(x);
		return x;
	}
	
	/** Converts a world Y value to a tile y value
	 * @param y world value
	 * @return The tile rep of y
	 */
	public static float getTileY(float y) {
		y -= offset_y;
		y /= App.TILE_SIZE;
		y = Math.round(y);
		return y;
	}
	
	/** Whether the given position is in bounds of the game
	 * @param tx - xpos
	 * @param ty - ypos
	 * @return true if the given position is in the game
	 */
	public static boolean inBounds(int tx, int ty) {
		if (tx >= 0 && tx < world_width && ty >= 0 && ty < world_height) {
			return true;
		}
		return false;
	}
	
	/** Loads the sprites from a given file.
	 * @param filename
	 * @return An array of Sprites as specified by the file
	 */
	public static ArrayList<Sprite> loadSprites(String filename) {
		ArrayList<Sprite> list = new ArrayList<>();
		
		// Open the file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			
			// Find the world size
			line = reader.readLine();
			String[] parts = line.split(",");
			world_width = Integer.parseInt(parts[0]);
			world_height = Integer.parseInt(parts[1]);
			
			// Create the array of object types
			types = new String[world_width][world_height];
			
			// Calculate the top left of the tiles so that the level is
			// centered
			offset_x = (App.SCREEN_WIDTH - world_width * App.TILE_SIZE) / 2;
			offset_y = (App.SCREEN_HEIGHT - world_height * App.TILE_SIZE) / 2;

			// Loop over every line of the file
			while ((line = reader.readLine()) != null) {
				String name;
				float x, y;
				
				// Split the line into parts
				parts = line.split(",");
				name = parts[0];
				x = Integer.parseInt(parts[1]);
				y = Integer.parseInt(parts[2]);
				types[(int)x][(int)y] = name;
				
				// Adjust for the grid
				x = offset_x + x * App.TILE_SIZE;
				y = offset_y + y * App.TILE_SIZE;
				
				// Create the sprite
				list.add(createSprite(name, x, y));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
