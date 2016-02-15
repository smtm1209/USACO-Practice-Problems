/*
ID: sarkistm1
PROG: skidesign
LANG: JAVA
*/
import java.io.*;
import java.util.*;
public class skidesign
{
   static List<Integer> hills;
   static int cost = Integer.MAX_VALUE;
   static int upperbound = 0, lowerbound = 101;
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("skidesign.in"));
      BufferedWriter outfile = new BufferedWriter(new FileWriter("skidesign.out"));
      int N = infile.nextInt();
      hills = new ArrayList<Integer>(N);
      for(int k = 0; k < N; k++)
      {
         int n = infile.nextInt();
         if(n > upperbound)
            upperbound = n;
         if(n < lowerbound)
            lowerbound = n;
         hills.add(n);
      }
      Collections.sort(hills);
      double avg = ((int) calculateAverage(hills)) + 0.5;
      recur('+',avg);
      recur('-',avg - 1);
      outfile.write(cost+"\n");
      outfile.close();
      
   }
   private static void recur(char dir, double center)
   {
      int currCost = 0;
      int uB = (int) (center + 8.5);
      int lB = (int) (center - 8.5);
      List<Integer> nl = new ArrayList<Integer>(hills);
      while(nl.get(nl.size()-1) - nl.get(0) > 17)
      {
         int countHigh = 0, countLow = 0;
         int lH = nl.get(0), uH = nl.get(nl.size()-1);
         while(true)
         {
            if(lH<lB)
            {
               lH++;
               countLow++;
            }
            if(uH-lH<=17)
               break;
            if(uH>uB)
            {
               uH--;
               countHigh++;
            }
            if(uH-lH<=17)
               break;
         }
         currCost += countHigh * countHigh;
         currCost += countLow * countLow;
         nl.set(0, lH);
         nl.set(hills.size() - 1, uH);
         Collections.sort(nl);
      }
      if(currCost<cost)
      {
         cost = currCost;
         if(dir == '+')
         {
            if(center+1 <= upperbound)
            {
               recur(dir, center+1);
            }
         }
         else //dir == '-'
         {
            if(center-1 >= lowerbound)
            {
               recur(dir, center-1);
            }
         }
      }
      
   }
   private static double calculateAverage(List <Integer> marks) {
      Integer sum = 0;
      if(!marks.isEmpty()) {
         for (Integer mark : marks) {
            sum += mark;
         }
         return sum.doubleValue() / marks.size();
      }
      return sum;
   }
   private static int[] convertIntegers(List<Integer> integers)
   {
      int[] ret = new int[integers.size()];
      Iterator<Integer> iterator = integers.iterator();
      for (int i = 0; i < ret.length; i++)
      {
         ret[i] = iterator.next().intValue();
      }
      return ret;
   }
}