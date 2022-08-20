import java.util.LinkedList;

public class Cell {
	
	public int r; //row
	public int c; //column;
	public int num; //the number on the cell
	public int solution;
	public int heuristic;
	public int aStarScore;
	public LinkedList<Cell> neighbor; //where can it jump to.
	public Cell prev;
	public boolean visited = false;
	public int[][] pic;
	
	public Cell(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
	}
	
	public int getr() {
		return r;
	}
	public int getc() {
		return c;
	}
	public int getnum() {
		return num;
	}
	public void setnum(int num) {
		this.num = num;
	}
	public LinkedList<Cell> getneighbor(){
		return this.neighbor;
	}
	public void setneighbor(LinkedList<Cell> neighbor) {
		this.neighbor = neighbor;
	}
	public void setPrev(Cell cell) {
		this.prev = cell;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void visit() {
		setVisited(true);
	}

	public Cell getPrev() {
		return prev;
	}
	
	public String toString() {
			String string = getr() + "," + getc() + "\t";
		return string;
	}
}
