/*
ID: sarkistm1
PROG: milk3
LANG: JAVA
*/
import java.io.*;
import java.util.*;
public class milk3
{
   static int maxA, maxB, maxC;
   static int[][][] map = new int[21][21][21];
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("milk3.in"));
      BufferedWriter outfile = new BufferedWriter(new FileWriter("milk3.out"));
      maxA = infile.nextInt();
      maxB = infile.nextInt();
      maxC = infile.nextInt();
      solve(0,0,maxC);
      List<Integer> ans = new ArrayList<Integer>(20);
      Set<Integer> chk = new HashSet<Integer>(20);
      for(int i = 0; i <= 20; i++)
         for(int j = 0; j <= 20; j++)
            if(map[0][j][i]==1&&!chk.contains(i))
            {
               chk.add(i);
               ans.add(i);
            }
      Collections.sort(ans);
      for(int k = 0; k < ans.size() - 1; k++)
      {
         outfile.write(ans.get(k) + " ");
      }
      if(ans.size() > 0)
      {
         outfile.write(ans.get(ans.size()-1)+"\n");
      }
      outfile.close();
   }
   public static void solve(int a, int b, int c)
   {
      if(map[a][b][c] != 0) return;
      add(a,b,c);
      if(a > maxB - b) solve(a-maxB+b,maxB,c); //1->2, b too full :(
      else solve(0,a+b,c); //1->2, b not too full
      if(a > maxC - c) solve(a-maxC+c,b,maxC); //1->3
      else solve(0,b,a+c);
      if(b > maxC - c) solve(a,b-maxC+c,maxC);//2->3
      else solve(a,0,b+c);
      if(b > maxA - a) solve(maxA,b-maxA+a,c);//2->1
      else solve(a+b,0,c);
      if(c > maxA - a) solve(maxA,b,c-maxA+a);//3->1
      else solve(a+c,b,0);
      if(c > maxB - b) solve(a,maxB,c-maxB+b);//3->2
      else solve(a,b+c,0);
   }
   public static void add(int x, int y, int z)
   {
      map[x][y][z] = 1;
   }
}