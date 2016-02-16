/*
ID: sarkistm1
PROG: wormhole
LANG: JAVA
*/
import java.io.*;
import java.util.*;
public class wormhole
{  
   static int N;
   static int[] X = new int[13], Y = new int[13], partner = new int[13], next_on_right = new int[13];
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("wormhole.in"));
      BufferedWriter outfile = new BufferedWriter(new FileWriter("wormhole.out"));
      N = infile.nextInt();
      for(int i = 1; i <= N; i++)
      {
         X[i] = infile.nextInt();
         Y[i] = infile.nextInt();
      }
      for(int i = 1; i <=N; i++)
      {
         for(int j = 1; j <= N; j++)
         {
            if(X[j] > X[i] && Y[i] == Y[j])
            {
               if(next_on_right[i] == 0 || X[j]-X[i] < X[next_on_right[i]]-X[i])
               {
                  next_on_right[i] = j;
               }
            }
         }
      }  
      infile.close();
      outfile.write(recur()+"\n");
      outfile.close();
   }  
   private static int recur()
   {
      int first, total = 0;
      for(first = 1; first <= N; ++first)
         if(partner[first] == 0)
            break;
      if(first > N)
         if(cycle_exists())
            return 1;
         else
            return 0;
      for(int second = first+1; second <= N; second++)
      {
         if(partner[second] == 0)
         {
            partner[first] = second;
            partner[second] = first;
            total += recur();
            partner[first] = 0; partner[second] = 0;
         }
      }
      return total;
   }
   public static boolean cycle_exists()
   {
      for(int start = 1; start <= N; start++)
      {
         int pos = start;
         for(int c = 0; c < N; c++)
         {
            pos = next_on_right[partner[pos]];
         }
         if (pos != 0)
         {
            return true;
         }
      }
      return false;
   }
}