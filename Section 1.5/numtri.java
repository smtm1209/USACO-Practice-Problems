/*
ID: sarkistm1
PROG: numtri
LANG: JAVA
*/
import java.io.*;
import java.util.*;
public class numtri
{
   static int depth;
   static int[][] triangle;// sums;
   static Scanner infile;
   static PrintWriter outfile;
   public static void main(String[] args) throws Exception
   {
      infile = new Scanner(new File("numtri.in"));
      outfile = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
      depth = infile.nextInt();
      triangle = new int[depth][];
      //sums = new int[depth][];
      process();
      int sum = search();
      //int sum = search2(0,0);
      outfile.println(sum+"");
      outfile.close();
   }
   public static void process()
   {
      for(int d = 0; d < depth; d++)
      {
         //sums[d] = new int[d+1];
         triangle[d] = new int[d+1];
         for(int i = 0; i < d+1; i++)
         {
            triangle[d][i] = infile.nextInt();
            //sums[d][i] = -1;
         }
      }
   }
   public static int search()
   {
      for(int i = depth-2; i >= 0; i--)
      {
         for(int j = 0; j < triangle[i].length; j++)
         {
            triangle[i][j] = triangle[i][j] + Math.max(triangle[i+1][j],triangle[i+1][j+1]);
         }
      }
      return triangle[0][0];
   }
   /*
   public static int search2(int i, int j)
   {
      if(i>=depth || j>=triangle[i].length)
      {
         return 0;
      }
      else if(sums[i][j] != -1)
      {
         return sums[i][j];
      }
      else if(i <= depth)
      {
         sums[i][j] = triangle[i][j] + Math.max(search2(i+1,j),search2(i+1,j+1));
         return sums[i][j];
      }
      else
      {
         return 0;
      }
   }
   */
}