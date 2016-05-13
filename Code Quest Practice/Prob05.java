import java.util.*;
import java.io.*;
public class Prob05 {
   public static void main(String[] args) throws Exception {
      Scanner fin = new Scanner(new File("Prob05.in.txt"));
      int T = fin.nextInt();
      for(int count = 0; count < T; count++) {
         String reg = fin.next();
         int N = fin.nextInt();
         List<Tuple> dates = new ArrayList<Tuple>(N);
         for(int c = 0; c < N; c++) {
            dates.add(new Tuple(((int) (fin.nextDouble()+500)) / 1000 * 1000, fin.nextInt()));
         }
         Collections.sort(dates);
         System.out.println(reg + ":");
         for(Tuple t : dates) {
            System.out.print(t.year + " ");
            for(int i = 0; i < (int) t.gdp / 1000; i++) {
               System.out.print("*");
            }
            System.out.println();
         }
      }
   }
   static class Tuple implements Comparable<Tuple> {
      int year;
      double gdp;
      public Tuple(double gdp, int year) {
         this.year = year;
         this.gdp = gdp;
      }
      public int compareTo(Tuple that) {
         return this.year - that.year;
      }
   }
}