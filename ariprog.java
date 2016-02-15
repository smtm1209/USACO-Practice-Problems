/*
ID: sarkistm1
PROG: ariprog
LANG: JAVA
*/
import java.io.*;
import java.util.*;
public class ariprog
{
   static int N,M,MAX;
   static Set<Integer> pqs;
   static BufferedWriter outfile;
   static List<Answer> answers = new LinkedList<Answer>();
   static List<Integer> pqs2;
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("ariprog.in"));
      outfile = new BufferedWriter(new FileWriter("ariprog.out"));
      N = infile.nextInt();
      M = infile.nextInt();
      pqs = new HashSet<Integer>(M*M);
      pqs2 = new ArrayList<Integer>(M*M);
      int max = 0;
      for(int p = 0; p <= M; p++)
      {
         for(int q = 0; q <= M; q++)
         {
            int x = p*p+q*q;
            if(x > max)
               max = x;
            if(!pqs.contains(x))
               pqs2.add(x);
            pqs.add(x);
         }
      }
      Collections.sort(pqs2);
      MAX = max;
      //System.out.println(pqs);
      solve();
      if(answers.size() == 0)
      {
         outfile.write("NONE\n");
      }
      else
      {
         Collections.sort(answers);
         for(Answer a : answers)
            outfile.write(a+"\n");
      }
      outfile.close();
      
   }
   private static void solve()
   {
      for(int a : pqs2)
      {
         int max_b = (MAX - a) / (N - 1);
         for(int b = 1; b <= max_b; b++)
         {
            boolean cont = false;
            for(int n = 1; n <= N - 1; n++)
            {
               int i = a + b * n;
               if(!pqs.contains(a + b * n))
               {
                  cont = true;
                  break;
               }
            }
            if(cont)
               continue;
            answers.add(new Answer(a,b));
         }
      }
   }
}
class Answer implements Comparable<Answer>
{
   int a, b;
   public Answer(int aa, int bb)
   {
      a = aa;
      b = bb;
   }
   public int compareTo(Answer A)
   {
      return b - A.b;
   }
   public String toString()
   {
      return a + " " + b;
   }
}