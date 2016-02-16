/*
ID: sarkistm1
PROG: crypt1
LANG: JAVA
*/
import java.util.*;
import java.io.*;
public class crypt1
{
   static BufferedWriter outfile;
   static ArrayList<Integer> digits;
   static Set<Integer> digitCheck;
   static int posVals = 0;
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("crypt1.in"));
      outfile = new BufferedWriter(new FileWriter("crypt1.out"));
      final int DIGITS = infile.nextInt();
      digits = new ArrayList<Integer>(DIGITS);
      for(int k = 0; k < DIGITS; k++)
      {
         digits.add(infile.nextInt());
      }
      digitCheck = new HashSet<Integer>(digits);
      recur("","");
      outfile.write(posVals+"\n");
      outfile.close();
   }
   public static void recur(String abc, String de)
   {
      if(abc.length() < 3)
      {
         for(int k = 0; k < digits.size(); k++)
         {
            recur(abc + digits.get(k), de);
         }
      }
      else if(de.length() < 2)
      {
         for(int k = 0; k < digits.size(); k++)
         {
            recur(abc, de + digits.get(k));
         }
      }
      else
      {
         int iabc = Integer.parseInt(abc);
         int d = Integer.parseInt(de.charAt(0)+"");
         int e = Integer.parseInt(de.charAt(1)+"");
         int pp1 = iabc * e;
         int pp2 = iabc * d;
         String spp1 = ""+pp1;
         if(spp1.length() > 3)
            return;
         String spp2 = ""+pp2;
         if(spp2.length() > 3)
            return;
         for(char c : spp1.toCharArray())
         {
            if(!digitCheck.contains(Integer.parseInt(c+"")))
               return;
         }
         for(char c : spp2.toCharArray())
         {
            if(!digitCheck.contains(Integer.parseInt(c+"")))
               return;
         }
         int finalV = pp1 + 10 * pp2;
         String finalVS = ""+finalV;
         for(char c : finalVS.toCharArray())
         {
            if(!digitCheck.contains(Integer.parseInt(c+"")))
               return;
         }
         posVals++;
      }
   }
}