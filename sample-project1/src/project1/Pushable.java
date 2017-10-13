package project1;

public abstract class Pushable extends Movable {

	public Pushable(String sprite_name, float x, float y) {
		super(sprite_name, x, y);
		this.addTag(Tag.PUSHABLE);
		this.addTag(Tag.BLOCKED);
		this.addTag(Tag.UNDOABLE);
	}
	
	public void push(int dir) {
		// do nothing if no direction given!
		if (dir == DIR_NONE) {
			return;
		}
		// If any blockage, don't move there!
		Position candidatePos = this.getDest(dir, 1);
		if (!World.isBlocked(candidatePos)) {
			this.setPos(candidatePos);
		}
	}
	
	public boolean active() {
		if (World.getSpriteOfType(Tag.TARGET, this.getX(), this.getY()) != null) {
			return true;
		}
		return false;
	}
}
