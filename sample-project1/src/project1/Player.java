package project1;

import org.newdawn.slick.Input;

public class Player extends Movable {
	
	private int moveCount;
	private int dir;
	
	public Player(float x, float y) {
		super("res/player_left.png", x, y);
		this.addTag("player");
		this.addTag("can_push");
		moveCount = 0;
		dir = DIR_NONE;
	}

	@Override
	public void update(int delta) {
		Input input = World.input;
		int dir = DIR_NONE;

		if (input.isKeyPressed(Input.KEY_LEFT)) {
			dir = DIR_LEFT;
		}
		else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			dir = DIR_RIGHT;
		}
		else if (input.isKeyPressed(Input.KEY_UP)) {
			dir = DIR_UP;
		}
		else if (input.isKeyPressed(Input.KEY_DOWN)) {
			dir = DIR_DOWN;
		}
		World.playerInput = dir;

		// bad privacy
		if (dir != DIR_NONE) {
			World.playerMoved = true;
			moveDir(dir);
		}
		// otherwise do nothing
		// Move to our destination
		//moveDir(dir);
	}
	
}
