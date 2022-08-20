import java.util.*;

public class BFS {
	
	public class java {

	}

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
		 solution = new Solution(NValue);
		 
	     return solution;
		}
		 public static void getBFS(Visual visual) {
			 int tempsol;
			 String sb = "";
			 //put answers into a reversed string
			 for(int j = 0; j < visual.getNumberOfCells(); j++) {
				 tempsol = BFS.BFS(visual).getN();
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
			 for(int i = 0; i < visual.getNumberOfCells(); i++) {
				 System.out.print(sb.charAt(i));
				 if((i+1)%5 == 0) {
					 System.out.print("\n");
				 }
			 }
			 
		 }



}
	