/*
ID: sarkistm1
PROG: barn1
LANG: JAVA
*/
import java.io.*;
import java.util.*;
public class barn1
{
   static int[] stalls;
   final static int covered = 2, occupied = 1, mustCovered = 3, uncovered = 0;
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("barn1.in"));
      BufferedWriter outfile = new BufferedWriter(new FileWriter("barn1.out"));
      int boards = infile.nextInt();
      int num_stalls = infile.nextInt();
      stalls = new int[num_stalls+1];
      int num_filled_stalls = infile.nextInt();
      int max = 0, min = num_stalls+1;
      for(int k = 0; k < num_filled_stalls; k++)
      {
         int n = infile.nextInt();
         if(n > max)
            max = n;
         if(n < min)
            min = n;
         stalls[n] = occupied;
      }
      for(int i = min; i <= max; i++)
      {
         if(stalls[i] == occupied)
         {
            stalls[i] = mustCovered;
         }
         else
         {
            stalls[i] = covered;
         }
      }
      boards--;
      while(boards > 0)
      {
         int s = max, e = min, tc = 0;
         for(int k = min; k <= max; k++)
         {
            int cs = k, ce = k-1, c = 0;
            while(stalls[k] == covered)
            {
               c++; 
               ce++;
               k++;
            }
            if(c>tc)
            {
               s = cs;
               e = ce;
               tc = c;
            }
         }
         if(tc == 0)
         {
            outfile.write(num_filled_stalls+"\n");
            outfile.close();
            System.exit(0);
         }
         for(int i = s; i <= e; i++)
         {
            stalls[i] = uncovered;
         }
         boards--;
      }
      int totc = 0;
      for(int k = 0; k < stalls.length; k++)
      {
         if(stalls[k] >= 2)
         {
            totc++;
         }
      }
      outfile.write(totc + "\n");
      outfile.close();
   }
}