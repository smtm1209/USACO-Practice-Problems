import java.util.*;
import java.io.*;

public class prob3 {
	private static int X, Y, K, M;
	private static int[][] milks;
	public static void main(String[] args) throws IOException {
		String filename = "pails";
		Scanner fin = new Scanner(new File(filename + ".in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		X = fin.nextInt();
		Y = fin.nextInt();
	   K = fin.nextInt();
		M = fin.nextInt();
		milks = new int[X+1][Y+1];
		recur(0,0,0);
		int solution = Integer.MAX_VALUE;
		for(int i = 0; i <= X; i++) {
			for(int j = 0; j <= Y; j++) {
				if(milks[i][j] == 1) {
					int diff = (int) Math.abs(M - (i + j));
					if(diff < solution)
						solution = diff;
				}
			}
		}
		fout.println(solution);
		fout.close();
	}
	private static void recur(int x, int y, int depth) {
		if(depth > K) return;
		if(milks[x][y] == 1) return;
		milks[x][y] = 1;
		//fill
		recur(X, y, depth+1);
		recur(x, Y, depth+1);
		//empty
		recur(0, y, depth+1);
		recur(x, 0, depth+1);
		//pour X -> Y
		if(x > Y - y) recur(x-Y+y,Y, depth+1);
		else recur(0, x+y, depth+1);
		//pour Y -> X
		if(y > X - x) recur(X, y-X+x, depth+1);
		else recur(x+y, 0, depth+1);
	}
}