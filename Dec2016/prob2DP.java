import java.util.*;
import java.io.*;

public class prob2DP {
	static long[][][] cache;	
	static Point[] hpoints, gpoints;
	public static void main(String[] args) throws Exception {
		Scanner fin = new Scanner(new File("checklist.in"));
		PrintWriter fout = new PrintWriter(new FileWriter("checklist.out"));
		int H = fin.nextInt(), G = fin.nextInt();
		cache = new long[2][H+1][G+1];
		hpoints = new Point[H]; gpoints = new Point[G];
		for(int i = 0; i < H; i++){hpoints[i] = new Point(fin.nextInt(), fin.nextInt());}
		for(int i = 0; i < G; i++){gpoints[i] = new Point(fin.nextInt(), fin.nextInt());}
		for(int i = 0; i < 2; i++) for(int j = 0; j <= H; j++) for(int k = 0; k <= G; k++) cache[i][j][k] = 1L << 60; 
		cache[0][1][0] = 0;
		for(int i = 0; i <= H; i++) {
			for(int j = 0; j <= G; j++) {
				for(int k = 0; k <= 1; k++) {
					if(k == 0 && i == 0) continue;
					if(k == 1 && j == 0) continue;
					Point source;
					if(k == 0) source = hpoints[i-1];
					else source = gpoints[j-1];
					if(i < H) {
						cache[0][i+1][j] = Math.min(cache[0][i+1][j], cache[k][i][j] + source.dist(hpoints[i]));
					}
					if(j < G) {
						cache[1][i][j+1] = Math.min(cache[1][i][j+1], cache[k][i][j] + source.dist(gpoints[j]));
					}
				}
			}
		}	
		fout.println(cache[0][H][G]);
		fout.close();
	}


	public static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int dist(Point that) {
			return (this.x - that.x)*(this.x - that.x) + (this.y-that.y)*(this.y-that.y);
		}
	}

}
