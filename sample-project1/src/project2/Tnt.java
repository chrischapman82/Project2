package project1;

public class Tnt extends Pushable {
	
	
	public Tnt(float x, float y) {
		super(Constant.TNT_PATH, x, y);
		this.addTag(Tag.TNT);
	}
	
	public void onMove(int dir, float testX, float testY) {
		
		if (World.getSpriteOfType(Tag.CRACKED, testX, testY) != null) {
			this.moveDir(dir);
			World.destroySprite(this);
		}
	}
	
	// overides so that it can be pushed into a cracked wall
	public void push(int dir) {
		if (dir == DIR_NONE) {
			return;
		}
		
		// If any blockage, don't move there!
		Position candidate_pos = this.getDest(dir, 1);
		System.out.println("candidate pos:" + candidate_pos.getX());
		System.out.println(candidate_pos.getY());
		if (World.getSpriteOfType(Tag.CRACKED, candidate_pos) != null) {
			System.out.println("Moving to cracked wall");
			this.setPos(candidate_pos);
		}
		if (!World.isBlocked(candidate_pos)) {
			//this.setX(candidatePos.getX());
			//this.setY(candidatePos.getY());
			this.setPos(candidate_pos);
		}
	}
}
