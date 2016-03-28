/*
ID: sarkistm1
PROG: maze1
LANG: JAVA
*/
/*
Final Grader Output:
   Test 1: TEST OK [0.173 secs, 522120 KB]
   Test 2: TEST OK [0.173 secs, 522120 KB]
   Test 3: TEST OK [0.166 secs, 522120 KB]
   Test 4: TEST OK [0.166 secs, 488832 KB]
   Test 5: TEST OK [0.245 secs, 523144 KB]
   Test 6: TEST OK [0.576 secs, 524168 KB]
   Test 7: TEST OK [0.396 secs, 488832 KB]
   Test 8: TEST OK [0.569 secs, 490880 KB]
   Test 9: TEST OK [0.511 secs, 523144 KB]
   Test 10: TEST OK [0.540 secs, 524168 KB]

All tests OK.

Your program ('maze1') produced all correct answers!  This is your
submission #6 for this problem.  Congratulations!

*/

import java.util.*;
import java.io.*;

public class maze1 {
	public static void main(String[] args) throws IOException {

		/* Boiler-plate init */
		Scanner fin = new Scanner(new File("maze1.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		int W = fin.nextInt();
		int H = fin.nextInt(); 
      fin.nextLine();
		GraphNode[] graph = new GraphNode[H*W];
		char[][] board = new char[2*H+1][2*W+1];
		for(int i = 0; i < 2*H+1; i++) {
			char[] row = fin.nextLine().toCharArray();
			for(int j = 0; j < 2*W+1; j++) 
				board[i][j] = row[j];
		}
		for(int i = 0; i < graph.length; i++)
			graph[i] = new GraphNode();

		/* Build-up of graph */
		List<Integer> exits = new ArrayList<Integer>();
		int nodeCount = 0;
		for(int i = 1; i < board.length; i+=2) {
			for(int j = 1; j < board[i].length; j+=2) {
				if(board[i-1][j] == ' ') { //up
					if(i-1==0) exits.add(nodeCount);
					else graph[nodeCount].add(nodeCount-W,1);
				}
				if(board[i+1][j] == ' ') { //down
					if(i+1==board.length-1) exits.add(nodeCount);
					else graph[nodeCount].add(nodeCount+W,1);
				}
				if(board[i][j+1] == ' ') { // right
					if(j+1==board[0].length-1) exits.add(nodeCount);
					else graph[nodeCount].add(nodeCount+1,1);
				}
				if(board[i][j-1] == ' ') { //left
					if(j-1==0) exits.add(nodeCount);
					else graph[nodeCount].add(nodeCount-1,1);
				}
				nodeCount++;
			}
		}
		for(int i = 0; i < graph.length; i++) {
			Collections.sort(graph[i].adj);
		}

		/* Dijkstra's Algo in O(2*(H*W)^2) ~ O(28880000) vs Floyd-Warshal in O((H*W)^3) ~ O(54872000000) */

		int[] retArr = new int[H*W];
      for(int i = 0; i < H*W; i++)
         retArr[i] = Integer.MAX_VALUE;
		/* init */
		for(int exitNum = 0; exitNum <= 1; exitNum++) {
			int exit = exits.get(exitNum);
			final int nil = -1;
			boolean[] visited = new boolean[H*W];
			int[] distance = new int[H*W], parent = new int[H*W];
			for(int i = 0; i < H*W; i++) {
				visited[i] = false;
				distance[i] = Integer.MAX_VALUE / 2;
				parent[i] = nil;
			}
			visited[exit] = true;
			distance[exit] = 0;
			int numvisited = 1;
			for(Tuple t : graph[exit].adj) {
				distance[t.num] = t.weight;
			}
			/* Solve */
			while(numvisited < graph.length) {
				//determine next node "I"
				int I = nil;
				int weight = Integer.MAX_VALUE;
				for(int i = 0; i < H*W; i++) {
					if(distance[i] < weight && !visited[i]) {
						I = i;
						weight = distance[i];
					}
				}
				//solve for next node "i"
				visited[I] = true;
				numvisited++;
				for(Tuple J : graph[I].adj) {
					distance[J.num] = Math.min(distance[J.num], distance[I] + J.weight);
					parent[J.num] = I;
				}
			}
			for(int i = 0; i < retArr.length; i++) {
				if(!exits.contains(i))
				   retArr[i] = Math.min(retArr[i], distance[i]);
			}
		}
		int ret = 0; 
		for(int i = 0; i < retArr.length; i++) {
         if(!exits.contains(i)) {
            ret = Math.max(ret, retArr[i]);
         }
      }
		ret++;
		System.out.println(ret);
		fout.println(ret);
		fout.close();
	}
	private static class GraphNode {
		private List<Tuple> adj;
		public GraphNode() {
			adj = new ArrayList<Tuple>();
		}
		public void add(int num, int weight) {
			adj.add(new Tuple(num, weight));
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
	}
}
/* Floyd-Warshall Algo */

		// /* Non-boiler-plate init */
		// int nodeCount = 0;
		// List<Integer> exits = new ArrayList<Integer>(); //simply easier to implement as an array-list
		// 																//instead of two seperate ints ( still O(1) )

		// /* build up adj-mat */
		// for(int i = 1; i < board.length; i+=2) {
		// 	for(int j = 1; j < board[i].length; j+=2) {
		// 		if(board[i-1][j] == ' ') { //up
		// 			if(i-1==0)
		// 				exits.add(nodeCount);
		// 			else
		// 				adj[nodeCount][nodeCount-W] = 1;
		// 		}
		// 		if(board[i+1][j] == ' ') { //down
		// 			if(i+1==board.length-1)
		// 				exits.add(nodeCount);
		// 			else
		// 				adj[nodeCount][nodeCount+W] = 1;
		// 		}
		// 		if(board[i][j-1] == ' ') { //left
		// 			if(j-1==0)
		// 				exits.add(nodeCount);
		// 			else
		// 				adj[nodeCount][nodeCount-1] = 1;
		// 		}
		// 		if(board[i][j+1] == ' ') { //right
		// 			if(j+1==board[i].length-1)
		// 				exits.add(nodeCount);
		// 			else
		// 				adj[nodeCount][nodeCount+1] = 1;
		// 		}
  //           adj[nodeCount][nodeCount] = 0;
		// 		nodeCount++;
		// 	}
		// }

		// /* solve adj mat in 4 lines */
		// for(int k = 0; k < adj.length; k++) { //intermediate
		// 	for(int i = 0; i < adj.length; i++) { //start
		// 		for(int j = 0; j < adj.length; j++) { //finish
		// 			adj[i][j] = (short) Math.min(adj[i][j], adj[i][k] + adj[k][j]); //evaluate
		// 		}
		// 	}
		// }

		// /* determine solution */
		// short ret = 0;
		// for(int i = 0; i < adj.length; i++) {
		// 	ret = (short) Math.max(ret, Math.min(adj[i][exits.get(0)], adj[i][exits.get(1)]));
		// }
		// ret++; //extra one for leaving
		// fout.println(ret);
		// System.out.println(ret);
		// fout.close();