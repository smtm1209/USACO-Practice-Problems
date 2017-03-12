import java.util.*;
import java.io.*;

public class visitfj {
	static int[] dr = {0, 1, 2, 3, 2, 1, 0, -1, -2, -3, -2, -1, -1, 1, 0, 0}; 
	static int[] dc = {3, 2, 1, 0, -1, -2, -3, -2, -1, 0, 1, 2, 0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("visitfj.in"));
		PrintWriter fout = new PrintWriter(new FileWriter("visitfj.out"));
		int N = fin.nextInt(), T = fin.nextInt();
		int[][] weights = new int[N][N];
		int[][] D = new int[N][N];
//		List<Vertex> graph = new ArrayList<Vertex>(N * N);		
//		for(int i = 0; i < N; i++) for(int j = 0; j < N; j++) graph.add(new Vertex(i, j));
//		int MAX = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				weights[i][j] = fin.nextInt();
//				MAX  =  Math.max(MAX, weights[i][j] + T);
				D[i][j] = Integer.MAX_VALUE;	
			}
		}

		D[0][0] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0));

		int ret = Integer.MAX_VALUE;
		
		while(!pq.isEmpty()) {
			Node node = pq.remove();
			int d = -node.v;
			int r = node.n / N;
			int c = node.n % N;
			if(d != D[r][c]) continue;
			int dist = Math.abs(N - 1 - r) + Math.abs(N - 1 - c);
			if(dist <= 2) ret = Math.min(ret, d + dist * T);
			
			for(int i = 0; i < dr.length; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N ||
					D[nr][nc] < d + weights[nr][nc] + 3 * T) continue;
				D[nr][nc] = d + weights[nr][nc] + 3 * T;
				pq.add(new Node(nr * N + nc, -D[nr][nc]));
			}
		}

		System.out.println(ret);
		fout.println(ret);
		fout.close();


/*
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(Math.abs(N - i + N - j) < 5) { //in range of goal
					graph.get(i * N + j).add(graph.get(N * N - 1), (N - i + N - j - 2) * T);
				}

				for(int k = 0; k < dr.length; k++) {
					int nr = i + dr[k];
					int nc = j + dc[k];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					graph.get(i * N + j).add(graph.get(nr * N + nc), weights[nr][nc] + 3 * T);
				}
			
			}
		}
//		System.out.println(Arrays.deepToString(weights));
//		System.out.println(graph.toString());
		
		final int nil = -1;
		final int exit = N * N - 1;
		final int start = 0;
		boolean[] visited = new boolean[N * N];
		int[] distance = new int[N * N];
		int[] parent = new int[N * N];
		for(int i = 0; i < visited.length; i++) {
			visited[i] = false;
			distance[i] = Integer.MAX_VALUE;
			parent[i] = nil;
		}
		visited[start] = true;
		distance[start] = 0;
		int numvisited = 0;

		Queue<DiEdge> q = new PriorityQueue<DiEdge>();

		for(DiEdge e : graph.get(start).adj) {
			distance[e.second.r * N + e.second.c] = e.weight;
			q.add(e);
		}

		while(!q.isEmpty() && !visited[exit]) {
			DiEdge next = q.remove();
//			System.out.println(next);
			int s = next.first.r * N + next.first.c;
			int n = next.second.r * N + next.second.c;
			if(visited[n]) continue; //already visited or extraneous
			visited[n] = true;
			for(DiEdge e : graph.get(n).adj) {
				int x = e.second.r * N + e.second.c;
				if(visited[x]) continue;
				int dist = distance[n] + e.weight;
				if(dist < distance[x]) {
					distance[x] = dist;
					parent[x] = n;
					q.add(new DiEdge(e.first, e.second, dist));
				}
			}
//			System.out.println(q.toString());
//			System.out.println(Arrays.toString(distance));
			
		}

		System.out.println(distance[N*N-1]);
		fout.println(distance[N*N-1]);
		fout.close();
*/	
/*		
//		MAX *= 2 * N;
//		int MAX_S = N * N; 
//		System.out.println(Arrays.deepToString(weights));
		Node start = new Node(0, 0, 0, 0);
		Queue<Node> pq = new PriorityQueue<Node>();
		pq.add(start);
//		int pruned = 0;
		while(pq.size() > 0) {
			Node node = pq.remove();
//			System.out.println(node);
			if(N - node.r + N - node.c < 5) {
				int ans = node.w + (N - node.r + N - node.c - 2) * T;
				fout.println(ans);
				fout.close();
//				System.out.println(ans);
				System.exit(0);
			}
			if(node.r + 3 < N) {
				Node n = new Node(node.r + 3, node.c, node.s + 3, node.w + 3 * T);
				if(n.s % 3 == 0) n.w += weights[n.r][n.c];
				pq.add(n);
			}
			if(node.r + 2 < N && node.c + 1 < N) {
				Node n = new Node(node.r + 2, node.c + 1, node.s + 3, node.w + 3 * T);
				if(n.s % 3 == 0) n.w += weights[n.r][n.c];
				pq.add(n);
			}
			if(node.r + 2 < N && node.c - 1 >= 0) {
				Node n = new Node(node.r + 2, node.c - 1, node.s + 3, node.w + 3 * T);
				if(n.s % 3 == 0) n.w += weights[n.r][n.c];
				pq.add(n);
			}
			if(node.r + 1 < N && node.c + 2 < N) {
				Node n = new Node(node.r + 1, node.c + 2, node.s + 3, node.w + 3 * T);
				if(n.s % 3 == 0) n.w += weights[n.r][n.c];
				pq.add(n);
			}
			if(node.c + 3 < N) {
				Node n = new Node(node.r, node.c + 3, node.s + 3, node.w + 3 * T);
				if(n.s % 3 == 0) n.w += weights[n.r][n.c];
				pq.add(n);
			}
			if(node.r + 1 < N && node.c - 2 >= 0) {
				Node n = new Node(node.r + 1, node.c - 2, node.s + 3, node.w + 3 * T);
				if(n.s % 3 == 0) n.w += weights[n.r][n.c];
				pq.add(n);
			}
			if(node.c - 3 >= 0) {
				Node n = new Node(node.r, node.c - 3, node.s + 3, node.w + 3 * T);
				if(n.s % 3 == 0) n.w += weights[n.r][n.c];
				pq.add(n);
			}
			if(node.r - 1 >= 0 && node.c - 2 >= 0) {
				Node n = new Node(node.r - 1, node.c - 2, node.s + 3, node.w + 3 * T);
				if(n.s % 3 == 0) n.w += weights[n.r][n.c];
				pq.add(n);
			}
			if(node.r - 1 >= 0 && node.c + 2 < N) {
				Node n = new Node(node.r - 1, node.c + 2, node.s + 3, node.w + 3 * T);
				if(n.s % 3 == 0) n.w += weights[n.r][n.c];
				pq.add(n);
			}
			if(node.r - 2 >= 0 && node.c - 1 >= 0) {
				Node n = new Node(node.r - 2, node.c - 1, node.s + 3, node.w + 3 * T);
				if(n.s % 3 == 0) n.w += weights[n.r][n.c];
				pq.add(n);
			}
			if(node.r - 3 >= 0) {
				Node n = new Node(node.r - 3, node.c, node.s + 3, node.w + 3 * T);
				if(n.s % 3 == 0) n.w += weights[n.r][n.c];
				pq.add(n);
			}
			if(node.r - 2 >= 0 && node.c + 1 < N) {
				Node n = new Node(node.r - 2, node.c + 1, node.s + 3, node.w + 3 * T);
				if(n.s % 3 == 0) n.w with a priorityqueue java+= weights[n.r][n.c];
				pq.add(n);
			}
			
		}
		*/
	}
	private static class Node implements Comparable<Node> {
		int n, v;
		public Node(int n, int v) {
			this.n = n;
			this.v = v;
		}
		public int compareTo(Node that) {
			return this.v - that.v;
		}
		public String toString() {
			return "(" + this.n + ", " + this.v + ")";
		}
	}
	private static class Vertex {
		List<DiEdge> adj;
		int r, c;
		public Vertex(int r, int c) {
			adj = new LinkedList<>();
			this.r = r; this.c = c;
		}
		public void add(Vertex other, int weight) {
			adj.add(new DiEdge(this, other, weight));
		}
		public String toString() {
			return adj.toString();
		}
	}
	private static class DiEdge implements Comparable<DiEdge> {
		Vertex first, second;
		int weight;
		public DiEdge(Vertex a, Vertex b, int w) {
			first = a; second = b; weight = w;
		}
		public int compareTo(DiEdge that) {
			return this.weight - that.weight;
		}
		public String toString() {
			return "(" + first.r + ", " + first.c + ") => (" + second.r + ", " + second.c + "): " + weight;
		}
	}
}


/*
class Node implements Comparable<Node> {
	int w, r, c, s;
	public Node(int r, int c, int s, int w) {
		this.w = w;
		this.r = r;
		this.c = c;
		this.s = s;
	}
	public int compareTo(Node that) {
		return this.w - that.w;
	}
	public String toString() {
		return "(" + r + ", " + c + ") > s=" + s + ", w=" + w;
	}
	public boolean equals(Object o) {
		Node that = (Node) o;
		return this.r == that.r && this.c == that.c;
	}
	public int hashCode() {
		return this.toString().hashCode();
	}
}
*/
