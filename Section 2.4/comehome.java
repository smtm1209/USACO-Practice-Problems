/*
ID: sarkistm1
PROG: comehome
LANG: JAVA
*/
import java.util.*;
import java.io.*;
public class comehome {
   public static void main(String[] args) throws IOException {
   
   	/* boiler-plate init */
      final int max = 52;
      Scanner fin = new Scanner(new File("comehome.in"));
      PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
      GraphNode[] graph = new GraphNode[max]; //0-25 lower case (unoccupied), 26-50 upper case (occupied), 51 'Z' (barn)
      for(int i = 0; i < max; i++) graph[i] = new GraphNode(i);
   
   	/* read and process input */
      int n = fin.nextInt();
      for(int i = 0; i < n; i++) {
         int first = index(fin.next().charAt(0));
         int second = index(fin.next().charAt(0));
         int dist = fin.nextInt();
         graph[first].add(second, dist);
         graph[second].add(first, dist);
      }
   
   	/* Dijkstra's Algo */
   
   	/* init */
      final int nil = -1;
      final int exit = 51;
      boolean[] visited = new boolean[max];
      int[] distance = new int[max];
      int[] parent = new int[max];
      for(int i = 0; i < max; i++) {
         visited[i] = false;
         distance[i] = Integer.MAX_VALUE;
         parent[i] = nil;
      }
      visited[exit] = true;
      distance[exit] = 0;
      int numvisited = 1;
      for(Tuple t : graph[exit].adj) {
         distance[t.num] = t.weight;
      }
   
   	/* solve */
      while(numvisited < graph.length) {
      
      	/* find next node "I" */
         int I = nil;
         int Iweight = Integer.MAX_VALUE;
         for(int i = 0; i < max; i++) {
            if(distance[i] < Iweight && !visited[i] && graph[i] != null) {
               I = i;
               Iweight = distance[i];
            }
         }
         if(I == nil) 
            break; //all visited
         visited[I] = true;
         numvisited++;
      
      	/* form shortest paths to Node "J" */
         for(Tuple J : graph[I].adj) {
            distance[J.num] = Math.min(distance[J.num], distance[I] + J.weight);
            parent[J.num] = I;
         }
      }
      int ret = Integer.MAX_VALUE;
      char ret2 = ' ';
      for(int i = 26; i < 51; i++) {
         if(graph[i] != null && distance[i] < ret) {
            ret = distance[i];
            ret2 = (char) ('A' + (i - 26));
         }
      }
      System.out.println("" + ret2 + " " + ret);
      fout.println("" + ret2 + " " + ret);
      fout.close();
   
   }
   private static int index(char c) {
      if(c >= 'a' && c <= 'z') 
         return c - 'a';
      else
         return c - 'A' + 26;
   }
   private static class GraphNode {
      List<Tuple> adj;
      int num;
      public GraphNode(int num) {
         this.num = num;
         this.adj = new ArrayList<Tuple>(1000);
      }
      public void add(int num, int weight) {
         if(num == this.num) 
            return; 
         Tuple t = new Tuple(num, weight);
         int index = adj.indexOf(t);
         if(index >= 0 && adj.get(index).weight > weight)
            adj.set(index, t);
         else if(index < 0)
            adj.add(t);
      }
      public String toString() {
         return adj.toString();
      }
   }
   private static class Tuple implements Comparable<Tuple> {
      int num, weight;
      public Tuple(int num, int weight) {
         this.num = num;
         this.weight = weight;
      }
      public int compareTo(Tuple that) {
         return this.weight - that.weight;
      }
      public boolean equals(Object o) {
         Tuple that = (Tuple) o;
         return this.num == that.num;
      }
      public String toString() {
         return "<" + num + "," + weight + ">";
      }
   }
}