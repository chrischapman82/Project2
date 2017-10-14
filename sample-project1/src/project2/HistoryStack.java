package project1;

// don't really need this!
import java.util.Stack;

// uses the stack from collections like a champion
public class HistoryStack {

	private Stack<Position> stack;
	
	public HistoryStack() {
		stack = new Stack<>();
	}
	
	public boolean hasHistory() {
		if (stack.isEmpty()) {
			System.out.println("stack is empty");
			return false;
		}
		
		// otherwise it has history
		return true;
	}
	
	// for using outside of the funciton
	public int getStackSize() {
		return stack.size();
	}
	
	public void removeFromHistory(int index) {
		stack.remove(index);
	}
	
	public Position pop() {
		Position pos = stack.pop();
		return pos;
	}
	
	public void addToHistory(Position pos) {
		stack.add(pos);
	}
}
