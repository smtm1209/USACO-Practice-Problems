/*
ID: sarkistm1
PROG: cowtour
LANG: JAVA

Algo: Floyd-Warshall
Efficiency: O(2*n^3)
Max-Computations: ~ 1012500

OUTPUT (Original):

Executing...
   Test 1: TEST OK [0.180 secs, 522120 KB]
   Test 2: TEST OK [0.173 secs, 522120 KB]
   Test 3: TEST OK [0.173 secs, 488832 KB]
   Test 4: TEST OK [0.259 secs, 522120 KB]
   Test 5: TEST OK [0.533 secs, 525192 KB]
   Test 6: TEST OK [0.590 secs, 524352 KB]
   Test 7: TEST OK [0.518 secs, 524168 KB]
   Test 8: TEST OK [0.504 secs, 523144 KB]
   Test 9: TEST OK [0.569 secs, 524168 KB]

All tests OK.

Your program ('cowtour') produced all correct answers!  This is your
submission #5 for this problem.  Congratulations!
*/
import java.util.*;
import java.io.*;
import java.text.*;
public class cowtour {
   public static void main(String[] args) throws IOException {
      Scanner fin = new Scanner(new File("cowtour.in"));
      PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
      int n = fin.nextInt();
      Coord[] coords = new Coord[n];
   	//O(n) ~ 150 computations
      for(int i = 0; i < n; i++) {
         coords[i] = new Coord(fin.nextInt(), fin.nextInt());
      }
   	//O(n^2) ~ 22500 computations
      double[][] adj = new double[n][n];
      for(int i = 0; i < n; i++) {
         char[] row = fin.next().toCharArray();
         for(int j = 0; j < n; j++) {
            if(row[j] == '1')
               adj[i][j] = dist(coords[i], coords[j]);
            else if(i == j) 
               adj[i][j] = 0;
            else
               adj[i][j] = Double.MAX_VALUE / 2;
         }
      }
   	//O(n^3) ~ 3375000 computations
      for(int k = 0; k < n; k++) {
         for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
               adj[i][j] = adj[j][i] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
            }	
         }
      }
   	//O(n^2) ~ 22500 computations
      double[] farthest = new double[n];
      for(int i = 0; i < n; i++) {
         double max = 0.0;
         for(int j = 0; j < n; j++) {
            if(adj[i][j] != Double.MAX_VALUE / 2 && adj[i][j] > max) {
               max = adj[i][j];
            }
         }
         farthest[i] = max;
      }

      //New Strat
   	//O(n^3) ~ 3375000 computations
      List<ArrayList<Integer>> fields = new ArrayList<ArrayList<Integer>>(151);
      for(int i = 0; i < n; i++) {
         boolean hasHome = false;
         for(ArrayList<Integer> field : fields) {
            if(connected(adj, field, i)) {
               field.add(i);
               hasHome = true;
               break;
            }
         }
         if(!hasHome) {
            fields.add(new ArrayList<Integer>());
            fields.get(fields.size()-1).add(i);
         }
      }
      //O(n^2) despite nested loops ~ 22500 computations
      int f1con = 0, f2con = 0;
      List<Integer> field1 = null, field2 = null;
      double dist = Double.MAX_VALUE / 2;
      for(int k = 0; k < fields.size(); k++) {
         List<Integer> f1 = fields.get(k);
         for(int l = 0; l < fields.size(); l++) {
            List<Integer> f2 = fields.get(l);
            if(k >= l) continue;
            double dist2 = Double.MAX_VALUE / 2;
            int f1con2 = 0, f2con2 = 0;
            for(int i : f1) {
               for(int j : f2) {
                  if(farthest[i] + dist(coords[i], coords[j]) + farthest[j] < farthest[f1con2] + dist2 + farthest[f2con2]) {
                     f1con2 = i;
                     f2con2 = j;
                     dist2 = dist(coords[i], coords[j]);
                  }
               }
            }
            if(dist2 < dist) {
               f1con = f1con2;
               f2con = f2con2;
               dist = dist2;
               field1 = f1;
               field2 = f2;
            }
         }
      }
      adj[f1con][f2con] = adj[f2con][f1con] = dist;
      
   	//O(n^3) ~ 3375000 computations
      for(int k = 0; k < n; k++) {
         for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
               adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
            }	
         }
      }
   	//O(n^2) ~ 22500 computations
      double max = 0;
      for(int i = 0; i < n; i++) {
         for(int j = i + 1; j < n; j++) {
            if(adj[i][j] != Double.MAX_VALUE / 2 && connected(adj, field1, i) && connected(adj, field2, j))
               max = Math.max(max, adj[i][j]);
         }
      }
      DecimalFormat df = new DecimalFormat("#.000000");
      String output = df.format(max);
      System.out.println(output);
      fout.println(output);
      fout.close();
   
   }
   private static boolean connected(double[][] adj, List<Integer> graph, int point) {
      if(graph.isEmpty()) 
         return true;
      for(int i : graph) 
         if(adj[i][point] < Double.MAX_VALUE / 2)
            return true;
      return false;
   }
   private static double dist(Coord a, Coord b) {
      double x2 = (b.x - a.x) * (b.x - a.x);
      double y2 = (b.y - a.y) * (b.y - a.y);
      return Math.sqrt(x2 + y2);
   }
   public static class Coord {
      int x, y;
      public Coord(int x, int y) {
         this.x = x;
         this.y = y;
      }
      public String toString() {
         return "(" + this.x + ", " + this.y + ")";
      }
   }
}
/*
      //Old Strat
      List<Integer> f1 = new LinkedList<Integer>();
      List<Integer> f2 = new LinkedList<Integer>();
      if(dist(coords[0],coords[1]) > dist(coords[coords.length-1],coords[coords.length-2])) {
         for(int i = n-1; i >= 0; i--) {
            if(connected(adj, f1, i))
               f1.add(i);
            else if(connected(adj, f2, i))
               f2.add(i);
         }
      }
      else {
         for(int i = 0; i < n; i++) {
            if(connected(adj, f1, i))
               f1.add(i);
            else if(connected(adj, f2, i))
               f2.add(i);
         }
      }
      int f1con = 0, f2con = 0;
      double dist = Double.MAX_VALUE;
      for(int i : f1) {
         for(int j : f2) {
            if(farthest[i] + dist(coords[i], coords[j]) + farthest[j] < dist + farthest[f1con] + farthest[f2con]) {
               f1con = i; 
               f2con = j;
               dist = dist(coords[i], coords[j]);
            } 
         }
      }
      */