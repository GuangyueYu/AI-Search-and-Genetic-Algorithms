  
public class Hill2 {

   private Visual Visual;
   private int[] kValues;
   private int chosenIndex;
   private double[] times_ms;


   public Hill2(Visual Visual, int[] kValues, int chosenIndex,
                             double[] times_ms) {
      this.Visual = Visual;
      this.kValues = kValues;
      this.chosenIndex = chosenIndex;
      this.times_ms = times_ms;
   }

   public int getKValue(){
      int[] kValues =  getkValues();
      return kValues[getChosenIndex()];
   }

   public double getTimeMs(){
      double[] times_ms = getTimes_ms();
      return times_ms[getChosenIndex()];
   }

   public double getAvgTimeMs(){
      double totalTime_ms = 0;
      for (double time_ms : getTimes_ms()){
         totalTime_ms+= time_ms;
      }
      double avgTime_ms = totalTime_ms / this.times_ms.length;
      return avgTime_ms;
   }

   public int getK(){
      int[] kValues = this.getkValues();
      return kValues[getChosenIndex()];
   }

   public Visual getVisual() {
      return this.Visual;
   }

   public int[] getkValues() {
      return kValues;
   }

   public int getChosenIndex() {
      return chosenIndex;
   }

   public double[] getTimes_ms() {
      return times_ms;
   }
}