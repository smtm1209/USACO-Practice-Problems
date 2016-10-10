/*
ID: sarkistm1
PROG: agrinet
LANG: JAVA
ALGO: Prim's
EFFICIENCY: O(VE)
*/
import java.util.*;
import java.io.*;
public class agrinet {
   public static void main(String[] args) throws IOException {
      Scanner fin = new Scanner(new File("agrinet.in"));
      PrintWriter fout = new PrintWriter(new FileWriter("agrinet.out"));
      int N = fin.nextInt();
      Vertex[] graph = new Vertex[N];
      for(int i = 0; i < N; i++) graph[i] = new Vertex();
      for(int i = 0; i < N; i++) {
         for(int j = 0; j < N; j++) {
            if(i == j) {
               fin.nextInt(); //consume input
            }
            else {
               graph[i].add(i, j, fin.nextInt());
            }
         }
      }
      List<Edge> mst = new LinkedList<Edge>();
      Set<Integer> visited = new HashSet<Integer>();
      Queue<Edge> todo = new PriorityQueue<Edge>();
      for(Edge e : graph[0].adj) {
         todo.add(e);
      }
      visited.add(0);
      while(visited.size() != N) {
         Edge e = todo.remove();
         while(visited.contains(e.e)) {
            e = todo.remove();
         }
         visited.add(e.e);
         mst.add(e);
         for(Edge ne : graph[e.e].adj) todo.add(ne);
      }
      int ret = 0;
      for(Edge e : mst) {
         ret += e.w;
      }
      fout.println(ret);
      fout.close();
   }
   private static class Vertex {
      List<Edge> adj;
      public Vertex() {
         adj = new LinkedList<Edge>();
      }
      public void add(int s, int e, int w) {
         adj.add(new Edge(s,e,w));
      }
      public String toString() {
         return adj.toString();
      }
   }
   private static class Edge implements Comparable<Edge> {
      int s, e, w;
      public Edge(int s, int e, int w) {
         this.s = s;
         this.e = e;
         this.w = w;
      }
      public int compareTo(Edge that) {
         return this.w - that.w;
      }
      public String toString() {
         return "<" + s + ", " + e + ">: " + w;
      }
   }
}