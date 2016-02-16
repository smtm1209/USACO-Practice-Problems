/*
ID: sarkistm1
PROG: combo
LANG: JAVA
*/
import java.util.*;
import java.io.*;
public class combo
{
   static Combination JohnCombo;
   static Combination MasterCombo;
   static int numVals;
   static int numCombos;
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("combo.in"));
      BufferedWriter outfile = new BufferedWriter(new FileWriter("combo.out"));
      numVals = infile.nextInt();
      JohnCombo = new Combination(infile.nextInt(),infile.nextInt(),infile.nextInt(),numVals);
      MasterCombo = new Combination(infile.nextInt(),infile.nextInt(),infile.nextInt(),numVals);
      for(int a = 1; a <= numVals; a++)
      {
         for(int b = 1; b <= numVals; b++)
         {
            for(int c = 1; c <= numVals; c++)
            {
               Combination C = new Combination(a,b,c,0);
               if(JohnCombo.withinBounds(C) || MasterCombo.withinBounds(C))
                  numCombos++;
            }
         }
      }
      outfile.write(numCombos+"\n");
      outfile.close();
      
   }
}
class Combination
{
   int first, second, third, num_vals;
   public Combination(int a, int b, int c, int n)
   {
      first = a; second = b; third = c; num_vals = n;
   }
   public boolean withinBounds(Combination C)
   {
      boolean a = false, b = false, c = false;
      if(first==C.first)
         a = true;
      else
      {
         int a1 = scrub(C.first+1);
         if(first == a1)
            a = true;
         a1 = scrub(a1+1);
         if(first == a1)
            a = true;
         a1 = scrub(C.first-1);
         if(first == a1)
            a = true;
         a1 = scrub(C.first-2);
         if(first == a1)
            a = true;
         if(!a)
            return false;
      }
      if(second==C.second)
         b = true;
      else
      {
         int a1 = scrub(C.second+1);
         if(second == a1)
            b = true;
         a1 = scrub(a1+1);
         if(second == a1)
            b = true;
         a1 = scrub(C.second-1);
         if(second == a1)
            b = true;
         a1 = scrub(C.second-2);
         if(second == a1)
            b = true;
         if(!b)
            return false;
      }
      if(third==C.third)
         c = true;
      else
      {
         int a1 = scrub(C.third+1);
         if(third == a1)
            c = true;
         a1 = scrub(a1+1);
         if(third == a1)
            c = true;
         a1 = scrub(C.third-1);
         if(third == a1)
            c = true;
         a1 = scrub(C.third-2);
         if(third == a1)
            c = true;
         if(!c)
            return false;
      }
      return a && b && c;
      
   }
   private int scrub(int k)
   {
      if(k > num_vals)
      {
         return k - num_vals;
      }
      else if(k <= 0)
      {
         return num_vals + k;
      }
      else
      {
         return k;
      }
   }
   public String toString()
   {
      return first + ", " + second + ", " + third;
   }
}