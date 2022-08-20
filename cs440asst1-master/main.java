
public class main {
	   static int n = 5;

	   public static void main(String[] args) {
		   Visual visual = new Visual(n);
	       visual.populateVisual(); 
	       visual.findnei();
//	       visual.show();
	       visual.grid(n);//task2
	       task5.reset(visual);
	       Solution bfs = new Solution(n);//task3
	       bfs = BFSHill.BFS(visual);
	       BFSHill.getBFS(visual,n);
	   //    task5.printgrid(visual);
	       System.out.print(bfs.toString());//get the answer for final goal(task3 ends)
	       task5.reset(visual);
	       Task4();
	       
		    int k;
		    long startTime = System.nanoTime(); 
			k = task5.spfsolver(visual);
			long stopTime = System.nanoTime();
			long timeRes = stopTime - startTime;
			System.out.println("\ntime for SPF:"+ (timeRes * Math.pow(10,-6)+"ms"));
			task5.printgrid(visual);
			task5.reset(visual);
			startTime = System.nanoTime(); 
			k = task6.aStarSolver(visual);
			stopTime = System.nanoTime();
			System.out.println("value of k ="+ k);
			timeRes = stopTime - startTime;
			System.out.println("\ntime for Astar:"+ (timeRes*Math.pow(10,-6)+"ms"));
			task5.printgrid(visual);

	       
	       
	   }

//	public static int task4() {
//		   int hilltimes = 80;
//		   Visual visual;
//		   Solution sol;
//		   int maxtimes = 0;
//		   int [] eachtime = null;
//		   for(int i = 0; i < hilltimes; i++) {
//			   visual = new Visual(n);
//			   visual.populateVisual();
//			   visual.findnei();
//			   sol = BFS.BFS(visual);
//			   eachtime[i] = sol.getN();
//			   if (sol.getN() > maxtimes) maxtimes = sol.getN();
//		   }
//		  return maxtimes;
//	   }


private static Visual Task4() {
    int hilltimes = 50;
    Visual Visual = null;
    Visual maxVisual = null;
    Solution solution = null;
    double maxVisualTime_ms = 0;

       double avgTimes_ms = 0;
       int maxK = 0;
       for (int i = 0; i < hilltimes; i++) {
          Visual = new Visual(n);
          Visual.populateVisual();
          Visual.findnei();
          solution = BFSHill.BFS(Visual);
          Hill2 hillResult = BFSHill.HillClimbing(Visual,solution, hilltimes);
          avgTimes_ms += hillResult.getAvgTimeMs();
          if (hillResult.getK() > maxK) {
             maxK = hillResult.getK();
             maxVisual = hillResult.getVisual();
             maxVisualTime_ms = hillResult.getTimeMs();
          }
       }
       double avgTime_ms = avgTimes_ms / hilltimes;
       System.out.printf("\nAverage Hill Climbing runtime is %.6f ms\n",avgTime_ms);
       System.out.printf("The most difficult graph appears in %.6f ms\n",maxVisualTime_ms);
//       System.out.println("The most difficult graph is: ");
  //      	BFSHill.printgrid(maxVisual);
   //    BFSHill.run(maxVisual);
//	   int[] distancePerCell = maxVisual.getDistances();
//	   for(int j = 0; j < distancePerCell.length; j++) {
//	   System.out.print(distancePerCell[j]);
//	   }
       Solution difficultSolution = BFSHill.BFS(maxVisual);
//    System.out.print("The solution of BFS on most difficult Visual");
       System.out.println(difficultSolution);

    return maxVisual;

 }




}
