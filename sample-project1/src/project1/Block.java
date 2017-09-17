package project1;

//import org.newdawn.slick.Image;

public abstract class Block extends Sprite{

	
	public Block(String image_source, float x, float y) {
		super(image_source, x, y);
	}
	
	
	// check the vis modifier
	public abstract void moveBlock(int dir);
}
