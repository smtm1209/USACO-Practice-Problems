/*
ID: sarkistm1
PROG: beads
LANG: JAVA
*/
import java.util.*;
import java.io.*;
public class beads
{
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("beads.in"));
      int n = infile.nextInt();
      List<Character> al = new ArrayList<Character>(n);
      String str = infile.next();
      infile.close();
      for(int k = 0; k < n; k++)
      {
         al.add(str.charAt(k));
      }
      int val = check(al);
      BufferedWriter outfile = new BufferedWriter(new FileWriter("beads.out"));
      outfile.write(val+"\n");
      outfile.close();
   }
   public static int check(List<Character> beads)
   {
      //check if all one color
      if(beads.size() == 0)
      {
         return 0;
      }
      char first = beads.get(0);
      for(int k = 1; k < beads.size(); k++)
      {
         if(beads.get(k) != first)
         {
            break;
         }
         else if(k == beads.size() - 1 && beads.get(k) == first)
         {
            return beads.size();
         }
      }
      int max = 0;
      for(int k = 0; k < beads.size(); k++)
      {
         int val = test(k,beads);
         if(val > max)
         {
            max = val;
         }
      }
      if(max > beads.size())
      {
         max = beads.size();
      }
      return max;
   }
   public static int test(int startPos, List<Character> beads)
   {
      char rightcol, leftcol;
      int rPos = startPos;
      int lPos = startPos - 1;
      if(lPos < 0)
      {
         lPos = beads.size() - 1;
      }
      if(rPos >= beads.size())
      {
         rPos = 0;
      }
      int right = rPos;
      while(beads.get(right) == 'w')
      {
         right++;
         if(right >= beads.size())
         {
            right = 0;
         }
      }
      rightcol = beads.get(right);
      int left = lPos;
      while(beads.get(left) == 'w')
      {
         left--;
         if(left < 0)
         {
            left = beads.size() - 1;
         }
      }
      leftcol = beads.get(left);
      int rcount = 0, lcount = 0;
      while(true)
      {
         if(beads.get(rPos) != 'w' && beads.get(rPos) != rightcol)
         {
            break;
         }
         else
         {
            rPos++;
            rcount++;
            if(rPos >= beads.size())
            {
               rPos = 0;
            }
         }
      }
      while(true)
      {
         if(beads.get(lPos) != 'w' && beads.get(lPos) != leftcol)
         {
            break;
         }
         else
         {
            lPos--;
            lcount++;
            if(lPos < 0)
            {
               lPos = beads.size() - 1;
            }
         }
      }
      return rcount + lcount;
   }
}
