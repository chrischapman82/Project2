package project1;

public class Timer {

	private int time;
	private int target;
	
	public Timer(int time, int target) {
		this.time = time;
		this.target = target;
	}
	
	public void update(int delta) {
		time = (int)System.currentTimeMillis();
	}
	
	public boolean expired() {
		
		return false;
	}
	
}
