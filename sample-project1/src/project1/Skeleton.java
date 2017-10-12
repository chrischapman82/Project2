package project1;

public class Skeleton extends Movable {

	private int dir;
	
	public Skeleton(float x, float y) {
		super("res/skull.png", x, y);
		this.dir = DIR_LEFT;
	}
	
}
