import java.util.*;
import java.io.*;

public class prob1 {
	static String filename = "moocast";
	public static void main(String[] args) throws Exception {
		Scanner fin = new Scanner(new File(filename + ".in"));
		PrintWriter fout = new PrintWriter(new FileWriter(filename + ".out"));
		int N = fin.nextInt();
		Vertex[] graph = new Vertex[N];
		for (int i = 0; i < N; i++) graph[i] = new Vertex(fin.nextInt(), fin.nextInt());
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == j) continue;
				else graph[i].add(i, j, dist(graph, i, j));
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
		Edge max = mst.get(0);
		for(Edge e : mst) {
			if(e.w > max.w) max = e;
		}
		fout.println("" + ((int) Math.round(max.w * max.w)));
		fout.close();
		
	}
	public static double dist(Vertex[] g, int i, int j) {
		//compute pythagorean distance
		return Math.sqrt(Math.pow(g[i].x - g[j].x, 2) + Math.pow(g[i].y - g[j].y, 2));
	}
	private static class Vertex {
		int x, y;
		List<Edge> adj;
		public Vertex(int x, int y) {
			this.x = x; this.y = y;
			adj = new LinkedList<Edge>();
		}
		public void add(int s, int e, double w) {
			adj.add(new Edge(s,e,w));
		}
		public String toString() {
			return adj.toString();
		}
	}
	private static class Edge implements Comparable<Edge> {
		int s, e;
		double w;
		public Edge(int s, int e, double w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		public int compareTo(Edge that) {
			if(this.w > that.w) return 1;
			else if(this.w < that.w) return -1;
			else return 0;
		}
		public String toString() {
			return "<" + s + ", " + e + ">: " + w;
		}
   }

}



