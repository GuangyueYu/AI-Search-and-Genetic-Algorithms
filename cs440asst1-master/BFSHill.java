import java.util.*;

public class BFSHill {
	

	private static int getNValue(int[] distances) {
		int NValue = distances[distances.length -1 ]; 
		if (NValue == -1){
		    int badcell = 0;
		    for(int distance: distances){
		    	if(distance == -1){
		        	badcell++;
		            }
		         }
		    NValue = -1*badcell;
		}
		      return NValue;

	}
	
	private static LinkedList<Cell> generateSolutionPath(Visual visual) {
			LinkedList<Cell> pathToSolution = new LinkedList<Cell>();
			Cell start = visual.getStart();
			Cell goal = visual.getGoal();
			Cell cell = goal;
			int NValue = visual.cellDistance(start, goal);
			if (NValue < 0){
				return pathToSolution;
			}
			for (int i = 0; i < NValue; i++){
				pathToSolution.addFirst(cell);
				if (cell.getPrev() == null){
					break;
					}
				cell = cell.getPrev();
			}
			return pathToSolution;
	}
		
	public static Solution BFS(Visual visual) {
		Solution solution;
		LinkedList<Cell> path = new LinkedList<>();
		Cell cell = visual.getStart();
		cell.visit();
		path.add(cell);
		while(path.size() > 0 ) {
			cell = path.remove();
			if(cell.getnum() == 0)
				break;
			
			LinkedList<Cell> neighbors = cell.getneighbor();
			for(int i = 0; i < neighbors.size(); i++) {
				Cell neighbor = neighbors.get(i);
				if(neighbor.isVisited() == false) {
					neighbor.visit();
					neighbor.setPrev(cell);
					path.add(neighbor);
				}
			}
		}
		 visual.setDistances();
		 getNValue(visual.getDistances());	
		 int NValue = visual.cellDistance(visual.getStart(), visual.getGoal());
	     if (NValue < 0){
	        solution = new Solution(NValue);
	        
	     } else {
	        LinkedList<Cell> pathToSolution = generateSolutionPath(visual);
	        solution = new Solution(NValue, pathToSolution);
	     	}
		 
	     return solution;
		}
		 public static void getBFS(Visual visual, int n) {
			 System.out.println();
			 int tempsol;
			 int count = 0;
			 String sb = "";
			 //put answers into a reversed string
			 for(int j = 0; j < visual.getNumberOfCells(); j++) {
				 tempsol = BFSHill.BFS(visual).getN();
//				 if(tempsol < 0) {
//					 System.out.print("X");
//					 visual.setGoal(visual.findCell(visual.getNumberOfCells()-1-j));
//					 sb += "X";
//					 continue;
//
//				 }
//				 System.out.print(tempsol);
				 visual.setGoal(visual.findCell(visual.getNumberOfCells()-1-j));
//				 sb += ""+tempsol+BFS.BFS(visual).toString()+"\n";
				 if(tempsol < 0) {
//				 System.out.print("X");
				 visual.setGoal(visual.findCell(visual.getNumberOfCells()-1-j));
				 sb += "X";
				 continue;

				 }
				 sb += ""+tempsol;
			 }
			 //reverse the string and make new lines
			 sb = new StringBuffer(sb).reverse().toString();
			 sb = sb.substring(0, visual.getNumberOfCells()-1);
			 sb = "0"+ sb;
			 for(int k = 0; k < visual.getNumberOfCells(); k++) {
				 if(sb.charAt(k) == 'X') count--;
			 }
			 
			 for(int i = 0; i < visual.getNumberOfCells(); i++) {
				 System.out.print(sb.charAt(i) + "  ");
				 if((i+1)%n == 0) {
					 System.out.print("\n");
				 }
			 }
			 if(sb.charAt(visual.getNumberOfCells()-1) == 'X')
			 System.out.print("the solution by BFS is: " + count + "\n");
			 
		 }
		 public static void printgrid(Visual visual) {
				int n = visual.numberOfCells;
				int temp;
				int i;
				System.out.println("");
				for(i = 0;i < n;i++) {
						temp = visual.cells[i].solution;
						System.out.print(temp + "  ");
						if((i + 1) % visual.n == 0) System.out.println("");
				}
			}

		//hill climbing part 
		 public static Visual hill(Visual visual){
		      int n = visual.getN();
		      Visual newVisual = new Visual(visual);

		      int randomIndex = (int )(Math.random() * visual.getNumberOfCells() -1);
		      Cell randCell = newVisual.findCell(randomIndex);

		      int R_MIN, R_MAX, C_MIN, C_MAX;
		      R_MIN = C_MIN = 1;
		      R_MAX = C_MAX = visual.getN();

		      int r = randCell.getr() + 1;
		      int c = randCell.getc() + 1;

		      int rJumps = Math.max((R_MAX - r), (r - R_MIN));
		      int cJumps = Math.max((C_MAX - c), (c - C_MIN));
		      int numberOfJumps = Math.max(rJumps, cJumps);
		      Random ran = new Random();
		      int randjumps = randCell.getnum();

		      while(randjumps == randCell.getnum()){
		         randjumps = ran.nextInt((numberOfJumps - 1) + 1) + 1; 
		      }

		      newVisual.findCell(randomIndex).setnum(randjumps);
		      newVisual.deleteNeighbors(randCell); // erases old neighbors
		      newVisual.findNeighbor(randCell);
		      //newVisual.showNeighbors(randCell);

		      for(int i = 0 ; i < n*n ; i++){
		         newVisual.findCell(i).setPrev(null);
		         newVisual.findCell(i).setVisited(false);
		      }

		      return newVisual;

		   }
		public static Hill2 HillClimbing(Visual Visual,
		        Solution solution,
		        int iterations) {
		Visual curVisual = new Visual(Visual);
		int[] kValues = new int[iterations];
		double[] times_ms = new double[iterations];
		int currentK = solution.getN();

		long bestTime_ns, totalTime_ns;
		bestTime_ns = totalTime_ns = System.nanoTime();
		int chosenIndex = 0;
		for (int i = 0; i < iterations; i++){
		long startTime_ns = System.nanoTime();
		Visual newVisual = new Visual(curVisual);
		newVisual.changeOneRandomCell();
		Solution newSolution = BFS(newVisual);
		int newK = newSolution.getN();
		if (newK >= currentK){
		currentK = newK;
		curVisual = new Visual(newVisual);
		chosenIndex = i;
		}
		kValues[i] = newK;
		long iterationTime_ns = (System.nanoTime() - startTime_ns);
		times_ms[i] = (iterationTime_ns / Math.pow(10,6));
		}

		Hill2 result = new Hill2(curVisual, kValues,
		chosenIndex, times_ms);

		return result;

		}

		public static void run (Visual graph) {

		    int n = graph.getN();
		   // int numberOfButtons = graph.getNumberOfCells();
		    //ArrayList<JButton> buttons = new ArrayList<>(numberOfButtons);

		    int R_MIN, R_MAX, C_MIN, C_MAX;
		    R_MIN = C_MIN = 0;
		    R_MAX = C_MAX = n-1;

		    for (int r = R_MIN; r <= R_MAX; r++){
		        for (int c = C_MIN; c <= C_MAX; c++) {

		            Cell cell = graph.findCell(r, c);
		            int numberOfJumps = cell.getnum();

		            System.out.print(numberOfJumps);
		        }
		    }

		}
		public static void run2(int n, int[] values,
                String name){
			
			int R_MIN, R_MAX, C_MIN, C_MAX;
			R_MIN = C_MIN = 0;
			R_MAX = C_MAX = n-1;
			
			//int index = (n * r) + c;
			for (int r = R_MIN; r <= R_MAX; r++){
				for (int c = C_MIN; c <= C_MAX; c++) {
					int index = (n * r) + c;
					if(values[index] == -1){
						System.out.print("X");			
						continue;
			}else{
				System.out.print(values[index]+"");
			}
			}
			}

			}



}
	