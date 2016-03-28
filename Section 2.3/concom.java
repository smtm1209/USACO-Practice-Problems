/*
ID: sarkistm1
PROG: concom
LANG: JAVA
*/
import java.util.*;
import java.io.*;

public class concom {
   public static void main(String[] args) throws IOException {
      int[][] percent = new int[101][101];
      boolean[][] owned = new boolean[101][101];
      Scanner fin = new Scanner(new File("concom.in"));
      PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
      int n = fin.nextInt();
      for(int i = 0; i < n; i++) {
         percent[fin.nextInt()][fin.nextInt()] = fin.nextInt();
      }
      for(int i = 1; i < percent.length; i++) {
         for(int j = 1; j < percent.length; j++) {
            if(!owned[i][j] && percent[i][j] > 50) {
               owned[i][j] = true;
               for(int j2 = 1; j2 < percent.length; j2++) {
                  percent[i][j2] += percent[j][j2];
                  if(owned[j][j2]) owned[i][j2] = true;
               }
               j = 0;
            }
         }
      }
      for(int i = 1; i < 101; i++) {
         for(int j = 1; j < 101; j++) {
            if(i == j) 
               continue;
            else if(owned[i][j]) {
               System.out.println(i + " " + j);
               fout.println(i + " " + j);
            }
         }
      }
      fout.close();
   	
   }
}