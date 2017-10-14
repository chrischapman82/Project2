package project2;

/** SWEN20003 Object Oriented Software Development 
 * Project 2
 * @author Christopher Chapman
 * Student no: 767336
 * Email: chapmanc1@student.unimelb.edu.au
 */
public class Switch extends Sprite{
	
	/** Controls a door on the map, opening when the switch wants it to*/
	Door door;
	
	
	public Switch(float x, float y) {
		super(Constant.SWITCH_PATH, x, y);
		this.addTag(Tag.SWITCH);
	}
	
	/**	Allocates the switch a door 
	 * @param door
	 */
	public void setDoor(Door door) {
		this.door = door;
	}
	
	/** Opens and closes the door depending on whether there is an object on it
	 */
	public void update(int delta) {
		if (World.getSpriteOfType(Tag.PUSHABLE, this.getX(), this.getY()) != null) {
			door.open_door();
		} else {
			door.close_door();
		}
	}
}
