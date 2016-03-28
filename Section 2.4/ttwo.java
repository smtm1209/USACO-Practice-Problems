/*
ID: sarkistm1
PROG: ttwo
LANG: JAVA
*/
import java.io.*;
import java.util.*;
public class ttwo {
	private static char[][] gameboard = new char[10][10];
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("ttwo.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
		Cow cows = new Cow(0,0), farmer = new Cow(0,0);
		for(int i = 0; i < 10; i++) {
			String nl = fin.next();
			char[] row = nl.toCharArray();
			for(int j = 0; j < 10; j++) {
				gameboard[i][j] = row[j];
				if(gameboard[i][j] == 'F') 
					farmer = new Cow(i,j);
				if(gameboard[i][j] == 'C')
					cows = new Cow(i,j);
			}
		}
		int ret = 0;
		for(;ret < 100000;ret++) {
			if(cows.row == farmer.row && cows.col == farmer.col) break;
			cows.move();
			farmer.move();
		}
		if(ret != 100000) {
			fout.println(ret);
			System.out.println(ret);
		}
		else {
			fout.println(0);
			System.out.println(0);
		}
		fout.close();
	}
	private static class Cow {
		int row, col, dx, dy;
      	static int up = -1, down = 1, right = 1, left = -1, still = 0;
		public Cow(int row, int col) {
			this.row = row;
			this.col = col;
			this.dx = 0;
			this.dy = -1;
		}
		public void move() {
			int newx = col+dx, newy = row+dy;
			if(newx < 0 || newx > 9 || newy < 0 || newy > 9) {
				rotate();
			}
			else if(gameboard[newy][newx] == '*') {
				rotate();
			}
			else {
				row = newy;
				col = newx;
			}
		}
		private void rotate() {
			if(dx == right && dy == still) {
				dy = down;
				dx = still;
			}
			else if(dx == left && dy == still) {
				dy = up;
				dx = still;
			}
			else if(dx == still && dy == up) {
				dy = still;
				dx = right;
			}
			else if(dx == still && dy == down) {
				dy = still;
				dx = left;
			}
		}
	}
}