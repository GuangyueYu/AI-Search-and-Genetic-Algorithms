import java.util.LinkedList;

public class Solution {
	int n; //the solution
	LinkedList<Cell> path; //the path to the solution
	long time; //time for executing
	
	public Solution(int n, LinkedList<Cell> path, long time) {
		this.n = n;
		this.path = path;
		this.time = time;
		
	}
	
	public Solution(int n) {
		this.n = n;
	}
	
	public Solution(int n, LinkedList<Cell> path) {
		this.n = n;
		this.path = path;
		
	}
	public int getN() {
		return this.n;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(n > 0) {

		sb.append("The solution is: " + n + "\n");
		}
		if (path != null) {
			sb.append("The path to the solution is: ");
			Cell cell;
			for (int i = 0; i < n; i++){
				cell = path.get(i);
				sb.append(cell.toString());
			}
		}
//			      double ms = (double) time / Math.pow(10,6);
//			      sb.append("\nFound in: " + (time / Math.pow(10,6)) + " ms\n");
		String result = sb.toString();
		return result;
		
			   }
	
	
	
	

	
	
	
}

