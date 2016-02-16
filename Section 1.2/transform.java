/*
ID: sarkistm1
PROG: transform
LANG: JAVA
*/
import java.util.*;
import java.io.*;
public class transform
{
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("transform.in"));
      int N = infile.nextInt();infile.nextLine();
      char[][] tiles = new char[N][N];
      char[][] target = new char[N][N];
      for(int i = 0; i < N; i++)
      {
         String line = infile.nextLine();
         char[] arr = line.toCharArray();
         for(int j = 0; j < N; j++)
         {
            tiles[i][j] = arr[j];
         }
      }
      for(int i = 0; i < N; i++)
      {
         String line = infile.nextLine();
         char[] arr = line.toCharArray();
         for(int j = 0; j < N; j++)
         {
            target[i][j] = arr[j];
         }
      }
      int val = figureOut(tiles, target);
      BufferedWriter outfile = new BufferedWriter(new FileWriter("transform.out"));
      outfile.write(val+"\n");
      outfile.close();
   }
   static void transpose(char[][] m)
   {
      for (int i = 0; i < m.length; i++) {
         for (int j = i; j < m[0].length; j++) {
            char x = m[i][j];
            m[i][j] = m[j][i];
            m[j][i] = x;
         }
      }
   }
   static char[][] copy(char[][] m)
   {
      char[][] output = new char[m.length][];
      for(int i = 0; i < m.length; i++)
      {
         output[i] = m[i].clone();
      }
      return output;
   }
   static char[][] r90(char[][] m)
   {
      char[][] mat = copy(m);
      transpose(mat);
      for (int  j = 0; j < mat[0].length/2; j++) {
         for (int i = 0; i < mat.length; i++) {
            char x = mat[i][j];
            mat[i][j] = mat[i][mat[0].length -1 -j]; 
            mat[i][mat[0].length -1 -j] = x;
         }
      }
      return mat;
   }
   static char[][] r180(char[][] m)
   {
      char[][] a = r90(m);
      char[][] b = r90(a);
      return b;
   }
   static char[][] r270(char[][] m)
   {
      char[][] a = r90(m);
      char[][] b = r90(a);
      char[][] c = r90(b);
      return c;
   }
   static char[][] mirror(char[][] in) {
      char[][] out = new char[in.length][in[0].length];
      for (int i = 0; i < in.length; i++) {
         for (int j = 0; j < in[i].length; j++) {
            out[i][in[i].length - j - 1] = in[i][j];
         }
      }
      return out;
   }
   static int figureOut(char[][] start, char[][] goal)
   {
      if(Arrays.deepEquals(goal,r90(start)))
         return 1;
      else if(Arrays.deepEquals(goal,r180(start)))
         return 2;
      else if(Arrays.deepEquals(goal,r270(start)))
         return 3;
      char[][] flip = mirror(start);
      if(Arrays.deepEquals(goal,flip))
         return 4;
      else if(Arrays.deepEquals(goal,r90(flip)))
         return 5;
      else if(Arrays.deepEquals(goal,r180(flip)))
         return 5;
      else if(Arrays.deepEquals(goal,r270(flip)))
         return 5;
      else if(Arrays.deepEquals(goal, start))
         return 6;
      else
         return 7;
   }
}