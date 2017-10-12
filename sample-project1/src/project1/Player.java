package project1;

import org.newdawn.slick.Input;

public class Player extends Movable {
	
	public Player(float x, float y) {
		super("res/player_left.png", x, y);
		this.addTag("player");
	}

	@Override
	public void update(Input input, int delta) {
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
			//World.playerMoved = true;
			//World.num_moves++;
		}
		// Move to our destination
		moveDir(dir);
	}
}
