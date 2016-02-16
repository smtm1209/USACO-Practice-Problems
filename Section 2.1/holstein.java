/*
ID: sarkistm1
PROG: holstein
LANG: JAVA
*/
import java.io.*;
import java.util.*;

public class holstein {
   private static int[] vitamins;
   private static List<Feed> feeds;
   private static BufferedWriter outfile;
   public static void main(String[] args) throws Exception {
      Scanner infile = new Scanner(new File("holstein.in"));
      outfile = new BufferedWriter(new FileWriter("holstein.out"));
      int V = infile.nextInt();
      vitamins = new int[V];
      for(int k = 0; k < V; k++)
         vitamins[k] = infile.nextInt();
      int G = infile.nextInt();
      feeds = new ArrayList<Feed>(G);
      for(int f = 0; f < G; f++){
         int[] temp = new int[V];
         for(int v = 0; v < V; v++)
            temp[v] = infile.nextInt();
         feeds.add(new Feed(f+1, temp));
      }
      for(int len = 1; len <= G; len++) {
         gatherAndTest(len);
      }
   }
   private static void gatherAndTest(int len) throws Exception {
      for(int start = 0; start <= feeds.size() - len; start++) {
         List<Feed> temp = new ArrayList<Feed>(len);
         temp.add(feeds.get(start));
         recur(start, 1, len, temp);
      }
   }
   private static void recur(int start, int len, int targetLen, List<Feed> currFeeds) throws Exception {
      if(len == targetLen) {
         if(works(currFeeds)) {
            outfile.write(len + " ");
            for(int i = 0; i < currFeeds.size() - 1; i++) {
               outfile.write(currFeeds.get(i).number + " ");
            }
            outfile.write(currFeeds.get(currFeeds.size() - 1).number + "\n");
            outfile.close();
            System.exit(0);
         }
      }
      else {
         for(int nS = start + 1; nS <= feeds.size() - targetLen + len; nS++) {
            List<Feed> temp = new ArrayList<Feed>(currFeeds);
            temp.add(feeds.get(nS));
            recur(nS, len+1, targetLen, temp);
         }
      }
   }
   private static boolean works(List<Feed> combo) {
      int[] sums = Arrays.copyOf(vitamins, vitamins.length);
      for(int v = 0; v < sums.length; v++) {
         for(Feed f : combo) {
            sums[v] -= f.vCont[v];
         }
      }
      for(int i : sums) {
         if(i > 0)
            return false;
      }
      return true;
   }
   private static class Feed {
      int[] vCont;
      int number;
      public Feed(int num, int[] c)
      {
         number = num;
         vCont = c;
      }
      public String toString()
      {
         return number + ": " + Arrays.toString(vCont);
      }
   }
}
