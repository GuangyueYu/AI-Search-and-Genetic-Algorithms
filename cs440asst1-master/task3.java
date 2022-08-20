package assignment1;

public class task3 {
	public static void moveLeft(Grid grid,Cell cell,int numMove) {
		int col = cell.c - numMove;
		int row = cell.r;
		grid.arr[row][col].visited = true;
		grid.arr[row][col].solution = cell.solution + 1;
		//need to link the new one to the old one
		BFS(grid,grid.arr[row][col]);
		
		
	}
	public static void moveRight(Grid grid,Cell cell,int numMove) {
		int col = cell.c + numMove;
		int row = cell.r;
		grid.arr[row][col].visited = true;
		grid.arr[row][col].solution = cell.solution + 1;
		//need to link the new one to the old one
		BFS(grid,grid.arr[row][col]);
		}
	public static void moveUp(Grid grid,Cell cell,int numMove) {
		int col = cell.c;
		int row = cell.r + numMove;
		grid.arr[row][col].visited = true;
		grid.arr[row][col].solution = cell.solution + 1;
		//need to link the new one to the old one
		BFS(grid,grid.arr[row][col]);
	}
	public static void moveDown(Grid grid,Cell cell,int numMove) {
		int col = cell.c;
		int row = cell.r - numMove;
		grid.arr[row][col].visited = true;
		grid.arr[row][col].solution = cell.solution + 1;
		//need to link the new one to the old one
		BFS(grid,grid.arr[row][col]);
	}
	//public static void BFS(Grid grid) {
	//	moveLeft(Grid grid,grid.)
	//}
	public static void BFS(Grid grid, Cell cell) {
		///////////check the move option for the cell/////////
		int tempRow = cell.r + cell.num;//向下
		if(tempRow <= grid.dim) moveDown(grid,cell,cell.num);//如果可以，向下走
		//BFS(Grid grid, Cell)//把这个新cell当root继续
		int tempCol = cell.r + cell.num;//向右
		if(tempCol <= grid.dim) moveRight(grid,cell,cell.num);
		tempRow = cell.r-cell.num;//向上
		if(tempCol <= grid.dim) moveUp(grid,cell,cell.num);
		tempCol = cell.r - cell.num;//向左
		if(tempCol <= grid.dim) moveLeft(grid,cell,cell.num);
		
	}
	public static void bfssolver(Grid grid) {
		int i,j;
		int n = grid.dim;
		for(i = 0; i < n; i++) {
			for(j = 0; j < n;j++) {
				grid.arr[i][j].solution = -1;//把所有没走过的cell的结果设为-1
			}
		}
		grid.arr[0][0].solution = 0;//把第一个的结果归零
		BFS(grid,grid.arr[0][0]);
		
	}
	public static void printgrid(Grid grid) {
		int n = grid.dim;
		int temp;
		int i,j;
		for(i = 0;i < n;i++) {
			for(j = 0;j < n;j++) {
				temp = grid.arr[i][j].solution;
				System.out.print(temp);
			}
			System.out.println("");
		}
	}
}
	

