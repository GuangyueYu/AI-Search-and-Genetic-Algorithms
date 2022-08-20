package assignment1;

public class task5 {
	public static void moveLeft(Visual visual,Cell cell,int numMove) {
		//System.out.print("left move"+cell.r);
		//System.out.println(cell.c+"  ");
		
			int col = cell.c - numMove;
			int row = cell.r;
		if(visual.cells[row * visual.n + col].visited == false) {//如果第一次经过
			visual.cells[row * visual.n + col].visited = true;
			visual.cells[row * visual.n + col].solution = cell.solution + 1;
			//need to link the new one to the old one
			//System.out.println(visual.cells[row * visual.n + col].solution);
		}
		else {//如果第二次经过
			if(visual.cells[row * visual.n + col].solution > cell.solution + 1) {
				visual.cells[row * visual.n + col].solution = cell.solution + 1;
			}
		}
		
		
	}
	public static void moveRight(Visual visual,Cell cell,int numMove) {
		//System.out.print("right move"+cell.r);
		//System.out.println(cell.c+"  ");
		
			int col = cell.c + numMove;
			int row = cell.r;
		if(visual.cells[row * visual.n + col].visited == false) {
			visual.cells[row * visual.n + col].visited = true;
			visual.cells[row * visual.n + col].solution = cell.solution + 1;
			//need to link the new one to the old one
			//System.out.println(visual.cells[row * visual.n + col].solution);
			//BFS(grid,grid.arr[row][col]);
		}
		else {
			if(visual.cells[row * visual.n + col].solution > cell.solution + 1) {
				visual.cells[row * visual.n + col].solution = cell.solution + 1;
			}
		}
		}
	public static void moveUp(Visual visual,Cell cell,int numMove) {
		//System.out.print("up move"+cell.r);
		//System.out.println(cell.c+"  ");
		
			int col = cell.c;
			int row = cell.r - numMove;
		if(visual.cells[row * visual.n + col].visited == false) {
			visual.cells[row * visual.n + col].visited = true;
			visual.cells[row * visual.n + col].solution = cell.solution + 1;
			//need to link the new one to the old one
			//System.out.println(visual.cells[row * visual.n + col].solution);
			//BFS(grid,grid.arr[row][col]);
		}
		else {
			if(visual.cells[row * visual.n + col].solution > cell.solution + 1) {
				visual.cells[row * visual.n + col].solution = cell.solution + 1;
			}
		}
		
	}
	public static void moveDown(Visual visual,Cell cell,int numMove) {
		//System.out.print("down move"+cell.r);
		//System.out.println(cell.c+"  ");
		
			int col = cell.c;
			int row = cell.r + numMove;
		if(visual.cells[row * visual.n + col].visited == false) {
			visual.cells[row * visual.n + col].visited = true;
			visual.cells[row * visual.n +col].solution = cell.solution + 1;
			//need to link the new one to the old one
			//System.out.println(visual.cells[row * visual.n + col].solution);
			//BFS(grid,grid.arr[row][col]);
		}
		else {
			if(visual.cells[row * visual.n + col].solution > cell.solution + 1) {
				visual.cells[row * visual.n + col].solution = cell.solution + 1;
			}
		}
		
	}
	//public static void BFS(Grid grid) {
	//	moveLeft(Grid grid,grid.)
	//}
	public static void SPF(Visual visual, Cell cell) {
		///////////check the move option for the cell/////////
		int tempRow,tempCol;
		if(cell.c == visual.n - 1&&cell.r == visual.n - 1) {
			
			
		}
		else {
			tempRow = cell.r;
			tempCol = cell.c;
			//System.out.println(tempRow+"  "+tempCol+"   "+grid.dim);
			if(tempRow + cell.num < visual.n&&tempRow >= 0) { //向下
				//System.out.println("I'm here");
				if(visual.cells[(tempRow + cell.num)* visual.n + tempCol].visited == false||cell.solution + 1 <visual.cells[(tempRow + cell.num) * visual.n + tempCol].solution) {
					
					moveDown(visual,cell,cell.num);//如果可以，向下走
					SPF(visual, visual.cells[(tempRow + cell.num) * visual.n + tempCol]);//把这个新cell当root继续
				}
			}
				
			if(tempRow - cell.num < visual.n&&tempRow - cell.num >= 0) { //向上
				if(visual.cells[(tempRow - cell.num) * visual.n + tempCol].visited == false||cell.solution + 1 <visual.cells[(tempRow - cell.num) * visual.n + tempCol].solution) {
					moveUp(visual,cell,cell.num);
					SPF(visual, visual.cells[(tempRow - cell.num) * visual.n + tempCol]);
				}
			}
			if(tempCol+cell.num < visual.n&&tempCol >= 0) {//向右
				if(visual.cells[tempRow * visual.n + tempCol + cell.num].visited == false||cell.solution + 1 <visual.cells[tempRow * visual.n + tempCol + cell.num].solution) {
					moveRight(visual,cell,cell.num);
					SPF(visual, visual.cells[tempRow * visual.n + tempCol + cell.num]);
				}
			}
			if(tempCol - cell.num < visual.n&&tempCol -  cell.num >= 0) {//向左
				if(visual.cells[tempRow * visual.n + tempCol - cell.num].visited == false||cell.solution + 1 <visual.cells[tempRow * visual.n + tempCol - cell.num].solution) {
					moveLeft(visual,cell,cell.num);
					SPF(visual, visual.cells[tempRow * visual.n + tempCol - cell.num]);
				}
			}
		}
		
		
	}
	public static int spfsolver(Visual visual) {
		int i,j;
		int n = visual.n;
		int numOfCell = visual.numberOfCells;
		for(i = 0; i < numOfCell; i++) {
			
				visual.cells[i].solution = -1;//把所有没走过的cell的结果设为-1
			
		}
		visual.cells[0].solution = 0;//把第一个的结果归零
		visual.cells[0].visited = true;
		int k = 0;
		SPF(visual,visual.cells[0]);
		if(visual.cells[numOfCell - 1].solution == -1) {
			for(i = 0;i < numOfCell;i++) {
				if(visual.cells[i].solution == -1) {
					k--;
				}
			}
		}
		else {
		k = visual.cells[numOfCell - 1].solution;
		}
		return k;
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
	public static void reset(Visual visual) {
		int n = visual.numberOfCells;
		int i;
		for(i = 0;i < n;i++) {
			visual.cells[i].solution = -1;
			visual.cells[i].visited = false;
		}
	}
	public static void printH(Visual visual) {
		int n = visual.numberOfCells;
		int i;
		int temp;
		for(i = 0;i < n;i++) {
			temp = visual.cells[i].heuristic;
			System.out.print(temp);
			if((i + 1) % visual.n == 0) System.out.println("");
		}
	}
}
	

