/*
ID: sarkistm1
PROG: dualpal
LANG: JAVA
*/
import java.util.*;
import java.io.*;

public class dualpal
{
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("dualpal.in"));
      final int NUM_OUTCOMES = infile.nextInt();
      final int START_VAL = infile.nextInt();
      int numPrinted = 0;
      BufferedWriter outfile = new BufferedWriter(new FileWriter("dualpal.out"));
      for(int k = START_VAL + 1; k <= Integer.MAX_VALUE; k++)
      {
         if(isDualPalindrome(k))
         {
            outfile.write(k + "\n");
            numPrinted++;
         }
         if(numPrinted == NUM_OUTCOMES)
         {
            break;
         }
         
      }
      
      outfile.close();
   }
   private static boolean isPalindrome(String s)
   {
      for(int k = 0; k < s.length() / 2; k++)
      {
         if(s.charAt(k) != s.charAt(s.length() - k - 1))
         {
            return false;
         }
      }
      return true;
   }
   private static boolean isDualPalindrome(int v)
   {
      int numPals = 0;
      for(int base = 2; base <= 10; base++)
      {
         if(isPalindrome(Integer.toString(v,base)))
            numPals++;
         if(numPals >= 2)
            return true;
      }
      return false;
   }
}