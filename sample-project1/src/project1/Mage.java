package project1;

public class Mage extends Movable {

	private int dir1;
	private int dir2;
	public Mage(float x, float y) {
		super("res/mage.png", x, y);
		this.addTag("enemy");
		this.addTag("mage");
	}
	
	public void update(int delta) {
		
		//System.out.println(player.getX());
	}
	
}
