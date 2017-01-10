/*
ID: sarkistm1
PROG: msquare
LANG: JAVA
Algo: A*
*/


import java.util.*;
import java.io.*;

public class msquare {
	static HashMap<Integer, int[]> ref_map = new HashMap<Integer, int[]>();
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("msquare.in"));
		PrintWriter fout = new PrintWriter(new FileWriter("msquare.out"));
		Queue<State> pq = new PriorityQueue<State>();
		Set<State> closed = new HashSet<State>();
		int[] one = {0, 0}, two = {0, 1}, three = {0, 2}, four = {0, 3}, five = {1, 3}, six = {1, 2}, seven = {1, 1}, eight = {1, 0};
		ref_map.put(1, one); ref_map.put(2, two); ref_map.put(3, three); ref_map.put(4, four); ref_map.put(5, five); ref_map.put(6, six); ref_map.put(7, seven); ref_map.put(8, eight);
		for(int key : ref_map.keySet()) {
			System.out.println(key + Arrays.toString(ref_map.get(key)));
		}
		int[][] start = new int[2][4];
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 4; j++) {
				start[i][j] = fin.nextInt();
			}
		}
		State s = new State(start, 0, "");
		pq.add(s);
		while(pq.size() > 0){
			State n = pq.remove();
		//	System.out.println(n);
			if(n.heur == 0) {
				fout.println(n.path.length());
				for(int i = 0; i < n.path.length(); i++) {
					fout.println(n.path.substring(i, Math.min(i+60, n.path.length())));
				}
				fout.close();
				System.exit(0);
			}
			if(!closed.contains(n)) {
				closed.add(n);
				pq.add(n.op_A());
//				System.out.println(n.op_A() != null);
				pq.add(n.op_B());
//				System.out.println(n.op_B() != null);
				pq.add(n.op_C());
			}	
		}
	}

	public static class State implements Comparable<State>{
		int[][] board;
		int depth, heur;
		String path;
		public State(int[][] x, int d, String path) {
			board = x;
			depth = d;
			heur = F();
			this.path = path;
		}

		public State op_A() {
			int[] a = board[0];
			int[] b = board[1];
			int[][] nb = new int[2][];
			nb[0] = b.clone();
			nb[1] = a.clone();
			return new State(nb, depth+1, path + "A");
		}

		public State op_B() {
			int[] a = new int[4];
			int[] b = new int[4];
			int c  = board[0][3];
			int d = board[1][3];
			for(int i = 0; i < 3; i++) {
				a[i+1] = board[0][i];
				b[i+1] = board[1][i];
			}
			a[0] = c;
			b[0] = d;
			int[][] nb = new int[][]{a, b};
		//	System.out.println(Arrays.deepToString(nb));
			return new State(nb, depth+1, path + "B");
		}

		public State op_C() {
			int[] a = new int[4];
			int[] b = new int[4];
			a[0] = board[0][0];
			a[3] = board[0][3];
			b[0] = board[1][0];
			b[3] = board[1][3];
			a[1] = board[1][1];
			a[2] = board[0][1];
			b[1] = board[1][2];
			b[2] = board[0][2];
			int[][] nb = new int[][]{a, b};
			return new State(nb, depth+1, path + "C");
		}

		public boolean equals(Object o) {
			return this.hashCode() == o.hashCode();
		}

		public int hashCode() {
			return Arrays.hashCode(board);
		}

		public int F() {
			int dist = 0;
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < 4; j++) {
					dist += Math.abs(i - ref_map.get(board[i][j])[0]) + Math.abs(j - ref_map.get(board[i][j])[1]);
				}
			}
			return dist + depth;
		}

		public int compareTo(State other) {
			return this.heur - other.heur;
		}
		
		public String toString() {
			return Arrays.deepToString(board);	
		}
	}
}
