/*
ID: sarkistm1
PROG: pprime
LANG: JAVA
*/
import java.util.*;
import java.io.*;
public class pprime
{
   private static List<Integer> palis = new LinkedList<Integer>();
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("pprime.in"));
      BufferedWriter outfile = new BufferedWriter(new FileWriter("pprime.out"));
      int a = infile.nextInt(), b = infile.nextInt();
      genPalindrome(b);
      for(int i : palis)
      {
         if(isPrime(i) && i >= a)
            outfile.write(i+"\n");
      }
      outfile.close();
   }
   public static boolean isPalindrome(int i)
   {
      return palis.contains(i);
   }
   public static boolean isPrime(int i)
   {
      for(int k = 2; k * k <= i; k++)
      {
         if(i % k == 0)
            return false;
      }
      return true;
   }
   public static void genPalindrome(int limit)
   {
      for(int i = 5; i <= limit && i <= 9; i++)
      {
         palis.add(i);
      }
      boolean cont = true;
      for (int i = 1; cont; i++) {
         char[] iii = (""+i).toCharArray();
         if(iii[0] !='1' && iii[0] != '3' && iii[0] != '7' && iii[0] != '9')
            continue;
         StringBuilder rev = new StringBuilder("" + i).reverse();
         cont = false;
         for (String d : ",0,1,2,3,4,5,6,7,8,9".split(",")) {
            String s = "" + i + d + rev;
            if(Long.parseLong(s) > Integer.MAX_VALUE)
               break;
            int n = Integer.parseInt(s);
            if (n <= limit) {
               cont = true;
               palis.add(n);
            }
         }
      }
      
   }
}