package project1;

public class Mage extends Movable {

	private int dir1;
	private int dir2;
	public Mage(float x, float y) {
		super(Constant.MAGE_PATH, x, y);
		this.addTag(Tag.ENEMY);
		this.addTag(Tag.MAGE);
	}
	
	public void update(int delta) {
		
		//System.out.println(player.getX());
	}
	
}
