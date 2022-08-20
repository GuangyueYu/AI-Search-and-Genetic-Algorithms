package assignment1;

import java.util.LinkedList;
import java.util.Random;



public class task2{
	public static Grid gridGenerate(int n){
		Grid newGrid = new Grid();
		newGrid.dim = n;
		newGrid.arr = new Cell[n][n];
		int i,k;
		int temp;
		Random rand = new Random();
		for(i = 1;i < n + 1; i++){
			for(k = 1;k < n + 1;k++){
				
				//newGrid.arr[i - 1][k - 1] = newCell;
				 if(i > k){
					 
					temp =  rand.nextInt(n - k + 1);
					Cell newCell = new Cell(i,k,temp);
					newGrid.arr[i - 1][k - 1] = newCell;
				}
				else{
					temp =  rand.nextInt(n - i + 1);
					Cell newCell = new Cell(i,k,temp);
					newGrid.arr[i - 1][k - 1] = newCell;
					
				}
				if(newGrid.arr[i - 1][k - 1].num == 0) {
					newGrid.arr[i - 1][k - 1].num = newGrid.arr[i - 1][k - 1].num + 1;
				}
				if(i == n &&k == n ){
					newGrid.arr[i - 1][k - 1].num = 0;
				}
				System.out.print(newGrid.arr[i - 1][k - 1]);
			}
			System.out.println("");
		}
		return newGrid;
	}
	public static void main(String[] args) {
			Grid grid = new Grid();
			grid = gridGenerate(7);
			task3.bfssolver(grid);
			
			

}
}
