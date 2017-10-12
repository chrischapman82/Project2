package project1;

public class Timer {

	private int time;
	private int target;	// target is the amount of time per movement
	
	public static final int DELTA_IN_SECOND = 1000;
	
	public Timer(int time, int target) {
		this.time = time;
		this.target = target;
	}
	
	public void update(int delta) {
		time += delta;
	}
	
	public boolean expired() {
		if (time > target) {
			time -= target;
			return true;
		}
		return false;
		
	}
	
}
