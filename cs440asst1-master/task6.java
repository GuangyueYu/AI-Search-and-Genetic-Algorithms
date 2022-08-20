package assignment1;

public class task6 {
	public static void measureH(Visual visual) {
		visual.cells[visual.numberOfCells - 1].heuristic = 0;
		int i = 0;
		for(i = 0;i < visual.numberOfCells;i++) {//calculate the heuristic of each cell
			if((i + 1) % visual.n == 0) {//if the cell is in the same col of goal cell 
				if(visual.cells[i].r + 1 + visual.cells[i].num == visual.n) {
					visual.cells[i].heuristic = 1;
				}
				else {
					visual.cells[i].heuristic = 2;
				}
			}
			else if(i >= visual.n * (visual.n - 1)&&i <= visual.numberOfCells - 1) {//cell is in the same roll of goal cell
				if(visual.cells[i].c + 1 + visual.cells[i].num == visual.n) {
					visual.cells[i].heuristic = 1;
				}
				else {
					visual.cells[i].heuristic = 2;
				}
			}
			else {
				if(visual.cells[i].c + visual.cells[i].num >= visual.n * (visual.n - 1) && visual.cells[i].c + visual.cells[i].num<= visual.numberOfCells - 1) {
					visual.cells[i].heuristic = 2;
				}
				if((visual.cells[i].r + visual.cells[i].num + 1) % visual.n == 0) {
					visual.cells[i].heuristic = 2;
				}
				else {
					visual.cells[i].heuristic = 3;
				}
			}
		}
		
	}
	public static void moveLeft(Visual visual,Cell cell,int numMove) {
		//System.out.print("left move"+cell.r);
		//System.out.println(cell.c+"  ");
		
			int col = cell.c - numMove;
			int row = cell.r;
		if(visual.cells[row * visual.n + col].visited == false) {//如果第一次经过
			visual.cells[row * visual.n + col].visited = true;
			visual.cells[row * visual.n + col].solution = cell.solution + 1;
			visual.cells[row * visual.n + col].aStarScore = visual.cells[row * visual.n + col].solution + visual.cells[row * visual.n + col].heuristic;
			//need to link the new one to the old one
			//System.out.println(visual.cells[row * visual.n + col].solution);
		}
		else {//如果第二次经过
			if(visual.cells[row * visual.n + col].solution > cell.solution + 1) {
				visual.cells[row * visual.n + col].solution = cell.solution + 1;
				visual.cells[row * visual.n + col].aStarScore = visual.cells[row * visual.n + col].solution + visual.cells[row * visual.n + col].heuristic;
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
			visual.cells[row * visual.n + col].aStarScore = visual.cells[row * visual.n + col].solution + visual.cells[row * visual.n + col].heuristic;
			//need to link the new one to the old one
			//System.out.println(visual.cells[row * visual.n + col].solution);
			//BFS(grid,grid.arr[row][col]);
		}
		else {
			if(visual.cells[row * visual.n + col].solution > cell.solution + 1) {
				visual.cells[row * visual.n + col].solution = cell.solution + 1;
				visual.cells[row * visual.n + col].aStarScore = visual.cells[row * visual.n + col].solution + visual.cells[row * visual.n + col].heuristic;
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
			visual.cells[row * visual.n + col].aStarScore = visual.cells[row * visual.n + col].solution + visual.cells[row * visual.n + col].heuristic;
			//need to link the new one to the old one
			//System.out.println(visual.cells[row * visual.n + col].solution);
			//BFS(grid,grid.arr[row][col]);
		}
		else {
			if(visual.cells[row * visual.n + col].solution > cell.solution + 1) {
				visual.cells[row * visual.n + col].solution = cell.solution + 1;
				visual.cells[row * visual.n + col].aStarScore = visual.cells[row * visual.n + col].solution + visual.cells[row * visual.n + col].heuristic;
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
			visual.cells[row * visual.n +col].aStarScore = visual.cells[row * visual.n +col].solution + visual.cells[row * visual.n +col].heuristic;
			//need to link the new one to the old one
			//System.out.println(visual.cells[row * visual.n + col].solution);
			//BFS(grid,grid.arr[row][col]);
		}
		else {
			if(visual.cells[row * visual.n + col].solution > cell.solution + 1) {
				visual.cells[row * visual.n + col].solution = cell.solution + 1;
				visual.cells[row * visual.n +col].aStarScore = visual.cells[row * visual.n +col].solution + visual.cells[row * visual.n +col].heuristic;
			}
		}
		
	}
	//public static void BFS(Grid grid) {
	//	moveLeft(Grid grid,grid.)
	//}
	public static void aStar(Visual visual, Cell cell) {
		///////////check the move option for the cell/////////
		int tempRow,tempCol;
		//System.out.println("I'm here");
		if(cell.c == visual.n - 1&&cell.r == visual.n - 1) {
			
			
		}
		else {
			tempRow = cell.r;
			tempCol = cell.c;
			//System.out.println(tempRow+"  "+tempCol+"   "+grid.dim);
			if(tempRow + cell.num < visual.n&&tempRow >= 0) { //向下
				//System.out.println("I'm here");
				if(visual.cells[(tempRow + cell.num)* visual.n + tempCol].visited == false||cell.solution + 1 + visual.cells[(tempRow + cell.num) * visual.n + tempCol].heuristic < visual.cells[(tempRow + cell.num) * visual.n + tempCol].aStarScore) {
					//System.out.println("I'm here");
					moveDown(visual,cell,cell.num);//如果可以，向下走
					aStar(visual, visual.cells[(tempRow + cell.num) * visual.n + tempCol]);//把这个新cell当root继续
				}
			}
			if(tempCol+cell.num < visual.n&&tempCol >= 0) {//向右
				if(visual.cells[tempRow * visual.n + tempCol + cell.num].visited == false||cell.solution + 1 + visual.cells[tempRow * visual.n + tempCol + cell.num].heuristic < visual.cells[tempRow * visual.n + tempCol + cell.num].aStarScore) {
					moveRight(visual,cell,cell.num);
					aStar(visual, visual.cells[tempRow * visual.n + tempCol + cell.num]);
				}
			}	
			if(tempRow - cell.num < visual.n&&tempRow - cell.num >= 0) { //向上
				if(visual.cells[(tempRow - cell.num) * visual.n + tempCol].visited == false||cell.solution + 1 + visual.cells[(tempRow - cell.num) * visual.n + tempCol].heuristic < visual.cells[(tempRow - cell.num) * visual.n + tempCol].aStarScore) {
					moveUp(visual,cell,cell.num);
					aStar(visual, visual.cells[(tempRow - cell.num) * visual.n + tempCol]);
				}
			}
			if(tempCol - cell.num < visual.n&&tempCol -  cell.num >= 0) {//向左
				if(visual.cells[tempRow * visual.n + tempCol - cell.num].visited == false||cell.solution + 1 + visual.cells[tempRow * visual.n + tempCol - cell.num].heuristic < visual.cells[tempRow * visual.n + tempCol - cell.num].aStarScore) {
					moveLeft(visual,cell,cell.num);
					aStar(visual, visual.cells[tempRow * visual.n + tempCol - cell.num]);
				}
			}
		}
		
		
	}
	public static int aStarSolver(Visual visual) {
		int i;
		task5.reset(visual);
		int numOfCell = visual.numberOfCells;
		for(i = 0; i < numOfCell; i++) {
				visual.cells[i].aStarScore = 0;
				visual.cells[i].solution = -1;//把所有没走过的cell的结果设为-1
		}
		visual.cells[0].solution = 0;//把第一个的结果归零
		visual.cells[0].visited = true;
		int k = 0;
		measureH(visual);
		aStar(visual,visual.cells[0]);
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

}
