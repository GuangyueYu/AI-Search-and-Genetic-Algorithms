import java.util.LinkedList;
import java.util.Random;
class Visual {
	   public int n;
	public int numberOfCells;
	   public Cell[] cells;
	   public Cell start, goal;
	   public int[] distances;

	   Visual(int n) {
	      this.n = n;
	      this.numberOfCells = n * n;
	      this.cells = new Cell[numberOfCells];
	      this.start = cells[0];
	      this.goal = cells[numberOfCells - 1];
	   }

	   Visual(Visual Visual){
	      this.n = Visual.getN();
	      this.numberOfCells = Visual.getNumberOfCells();
	      this.cells = Visual.getcells();
	      this.start = Visual.getStart();
	      this.goal = Visual.getGoal();
	      this.distances = Visual.getDistances();
	   }

	   public void populateVisual() {

	      int rMin, rMax, cMin, cMax;
	      rMin = cMin = 1;
	      rMax = cMax = n;

	      for (int r = rMin; r <= rMax; r++) {
	         for (int c = cMin; c <= cMax; c++) {
	            if (c == cMax && r == rMax) { 
	               Cell cell = new Cell(n - 1, n - 1, 0);
	               this.goal = cell;
	               this.add(cell);
	               continue;
	            }
	            int rJumps = Math.max((rMax - r), (r - rMin));
	            int cJumps = Math.max((cMax - c), (c - cMin));
	            int numberOfJumps = Math.max(rJumps, cJumps);
	            numberOfJumps = genRandNumber(numberOfJumps);
	            Cell cell = new Cell(r - 1, c - 1, numberOfJumps);
	            if (c == cMin && r == rMin){
	               this.start = cell;
	            }
	            this.add(cell);
	         }
	      }
	   }


	   public void findnei() {

	      int rMin, rMax, cMin, cMax;
	      rMin = cMin = 0;
	      rMax = cMax = n - 1;

	      for (int r = rMin; r <= rMax; r++) {
	         for (int c = cMin; c <= cMax; c++) {
	            if (c == cMax && r == rMax) { // On last cell
	               break;
	            }
	            Cell cell = findCell(r, c);
	            findNeighbor(cell);
	         }
	      }
	   }

	   public LinkedList<Cell> findNeighbor(Cell cell) {
	      int r = cell.getr();
	      int c = cell.getc();
	      int jumps = cell.getnum();
	      LinkedList<Cell> neighbors = new LinkedList<Cell>();

	      Cell neighborCell;
	      int newR, newC;
	      if ((r + jumps) <= n - 1) {
	         newR = r + jumps;
	         neighborCell = findCell(newR, c);
	         neighbors.add(neighborCell);
	      }
	      if ((r - jumps) >= 0) {
	         newR = r - jumps;
	         neighborCell = findCell(newR, c);
	         neighbors.add(neighborCell);
	      }
	      if ((c + jumps) <= n - 1) {
	         newC = c + jumps;
	         neighborCell = findCell(r, newC);
	         neighbors.add(neighborCell);
	      }
	      if ((c - jumps) >= 0) {
	         newC = c - jumps;
	         neighborCell = findCell(r, newC);
	         neighbors.add(neighborCell);
	      }
	      cell.setneighbor(neighbors);
	      return neighbors;
	   }

	   public void add(Cell cell) {
	      int index = (cell.getr() * n) + cell.getc();
	      cells[index] = cell;
	   }

	   public Cell findCell(int r, int c) {
	      int index = (n * r) + c;
	      Cell cell = cells[index];
	      return cell;
	   }

	   public Cell findCell(int index){
	      return cells[index];
	   }

	   public int getIndex(Cell cell) {
	      int r, c;
	      r = cell.getr();
	      c = cell.getc();
	      int index = (n * r) + c;
	      return index;
	   }

	   private int genRandNumber(int numberOfJumps) {
	      Random r = new Random();
	      int randNum = r.nextInt((numberOfJumps - 1) + 1) + 1;
	      return randNum;
	   }


	   public void show() {
	      for (int i = 0; i < numberOfCells; i++) {
	         System.out.printf("Cell %d can jump " +
	                         "neighbors are: ", i, cells[i].toString(),
	                 cells[i].getnum());
	         showNeighbors(cells[i]);
	      }
	   }
	   public void grid(int n) {
		   for(int i = 0; i<numberOfCells; i++) {
				   System.out.print(cells[i].getnum()+"  ");  
				   if((i+1) % n == 0)
					   System.out.print('\n');
			   }

	   }
	   public void grid2(Visual visual) {
		   for(int i = 0; i<numberOfCells; i++) {
				   System.out.print(visual.findCell(i).getnum());
				   if((i+1) % n == 0)
					   System.out.print('\n');
			   }

	   }

	   public int getNumberOfCells() {
	      return this.numberOfCells;
	   }

	   public int getN() {
	      return this.n;
	   }

	   public Cell[] getcells(){
	      return this.cells;
	   }

	   public Cell getStart() {
	      return start;
	   }

	   public Cell getGoal() {
	      return goal;
	   }
	   
	   public void setGoal(Cell cell) {
		   this.goal = cell;
	   }

	   public void deleteNeighbors(Cell cell){
	      cell.setneighbor(null);
	   }


	   public void showNeighbors(Cell cell) {
	      if (cell.getneighbor() == null) {
	         System.out.printf(" No neighbors\n", cell.toString());
	      } else {
	         for (int i = 0; i < cell.getneighbor().size(); i++) {
	            System.out.print(cell.getneighbor().get(i).toString());
	         }
	         System.out.println();
	      }
	   }

	   public int cellDistance(Cell start, Cell goal) {
	      Cell cell = goal;
	      int NValue = 0;
	      if (start.equals(goal)){
	         return 0;
	      }
	      while (cell.getPrev() != null){
	         cell = cell.getPrev();
	         NValue++;
	         if (cell.equals(start)) {
	            return NValue;
	         }
	      }
	      return -1;
	   }

	   public int heuristic(Cell cell){
	     if (cell.getr() == n && cell.getc() == n){
	        return 2;
	     } else if (cell.getr() == n || cell.getc() == n){
	        return 1;
	     } else {
	        return 0;
	     }
	   }

	   public void setDistances(){
	      this.distances = new int[numberOfCells];
	      for (int i = 0; i < numberOfCells; i++) {
	         int value = cellDistance(start, cells[i]);
	         this.distances[i] = value;
	      }
	   }

	   public int[] getDistances(){
	      return this.distances;
	   }
	 

	   public void changeOneRandomCell(){
		      int randomIndex = (int )(Math.random() * this.getNumberOfCells() -1); // 0(inclusive) to n^2 - 1exclusive so that 24 is not picked
		      Cell randCell = this.findCell(randomIndex);

		      int R_MIN, R_MAX, C_MIN, C_MAX;
		      R_MIN = C_MIN = 1;
		      R_MAX = C_MAX = this.getN();

		      int r = randCell.getr() + 1;
		      int c = randCell.getc() + 1;

		      int rJumps = Math.max((R_MAX - r), (r - R_MIN));
		      int cJumps = Math.max((C_MAX - c), (c - C_MIN));
		      int numberOfJumps = Math.max(rJumps, cJumps);
		      numberOfJumps = genRandNumber(numberOfJumps);

		      //System.out.printf("RandCell is %s and numberJumps is %d\n", randCell, numberOfJumps);

		      randCell.setnum(numberOfJumps);// updates number of jumps
		      this.deleteNeighbors(randCell); // erases old neighbors
		      this.findNeighbor(randCell);

		      return;

		   }
}