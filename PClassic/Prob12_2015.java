import java.util.*;
import java.io.*;
public class Prob12_2015 {
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("IslandFerriesIN.txt"));
		while(fin.hasNextInt()) {
			int v = fin.nextInt();
			int e = fin.nextInt();
			int end = fin.nextInt();
			Vertex[] graph = new Vertex[v+1];
			for(int i = 0; i < v+1; i++)
				graph[i] = new Vertex();
			for(int i = 0; i < e; i++) {
				int s = fin.nextInt(), int f = fin.nextInt(), int w = fin.nextInt();
				graph[s].add(f, w);
			}
			int possible_flips = 0;
			Set<Edge> removed = new HashSet<Edge>();
			for(int i = 0; i < v+1; i++) {
				int add = graph[i].adj.size();
				if(add > 0) add--;
				possible_flips += add;
			}
			int[] parent = new int[v+1], distance = new int[v+1];
			boolean[] visited = new boolean[v+1];
			while(possible_flips > 0) { //can run dijkstra
				Queue<Edge> pq = new PriorityQueue<Edge>();
				int visited = 0;
				for(int i = 0; i < v+1; i++) {
					parent[i] = -1;
					distance[i] = Integer.MAX_VALUE;
					visited[i] = false;
				}
				pq.add(new Edge(1, 0));
			}
			while(visited < v) {
				Edge next = pq.remove();

			}
		}
	}
	private static class Vertex {
		List<Edge> adj;
		Queue<Edge> flip;
		int me;
		public Vertex(int num) {
			me = num;
			adj = new LinkedList<Edge>();
			flip = new PriorityQueue<Edge>();
		}
		public void add(int start, int dest, int weight) {
			Edge e = new Edge(start, dest, weight);
			adj.add(e);
			flip.add(e);
		}
	}
	private static class Edge implements Comparable<Edge> {
		int dest, weight;
		public Edge(int d, int w) {
			dest = d;
			weight = w;
		}
		public int compareTo(Edge that) {
			return that.weight - weight;
		}
		public int hashCode() {
			return toString().hashCode();
		}
		public String toString() {
			return dest + " " + weight;
		}
	}	
}