import java.util.*;
import java.io.*;
import java.text.*;
public class Prob04 {
   public static void main(String[] args) throws Exception {
      Scanner fin = new Scanner(new File("Prob04.in.txt"));
      int T = fin.nextInt();
      for(int count1 = 0; count1 < T; count1++) {
         int N = fin.nextInt();
         for(int count2 = 0; count2 < N; count2++) {
            String next = fin.next();
            double orig = Double.parseDouble(next);
            String oscl = fin.next();
            double ntemp = 0;
            String nscl = "F";
            if(oscl.equals("C")) {
               ntemp = F(orig);
               nscl = "F";
            }
            else if(oscl.equals("F")) {
               ntemp = C(orig);
               nscl = "C";
            }
            DecimalFormat df = new DecimalFormat("#.#");
            
            System.out.println(orig + " " + oscl + " = " + df.format(ntemp) + " " + nscl);
         }
      }
   }
   static double C(double F) {
      return (5.0/9) * (F - 32);
   }
   static double F(double C) {
      return (9.0/5) * C + 32;
   }
}