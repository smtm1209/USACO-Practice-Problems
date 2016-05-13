import java.io.*;
import java.util.*;
public class Prob14 {
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("Prob14.in.txt"));
		String n = "";
		List<int[][]> boards = new ArrayList<int[][]>();
		while(fin.hasNextInt()) {
			int[][] brd = new int[5][5];
			for(int i = 0; i < 5; i++)
				for(int j = 0; j < 5; j++)
					brd[i][j] = fin.nextInt();
			boards.add(brd);	
		}
		List<int[][][]> cubes = new ArrayList<int[][][]>(1000);
		boolean[] used = new boolean[boards.size()];
		for(int a = 0; a < boards.size(); a++) { //O(n!)
			int[][][] cube = new int[5][][];
			used[a] = true;
			cube[0] = boards.get(a);
			for(int b = 0; b < boards.size(); b++) {
				if(used[b]) continue;
				used[b] = true;
				cube[1] = boards.get(b);
				for(int c = 0; c < boards.size(); c++) {
					if(used[c]) continue;
					used[c] = true;
					cube[2] = boards.get(c);
					for(int d = 0; d < boards.size(); d++) {
						if(used[d]) continue;
						used[d] = true;
						cube[3] = boards.get(d);
						for(int e = 0; e < boards.size(); e++) {
							if(used[e]) continue;
							cube[4] = boards.get(e);
							cubes.add(cube);
						}
						used[d] = false;
					}
					used[c] = false;
				}
				used[b] = false;
			}
			used[a] = false;
		}
		List<boolean[][][]> marking = new ArrayList<boolean[][][]>(cubes.size());
		for(int i = 0; i < marking; i++) marking.add(new boolean[5][5][5]);
		fin.next(); //PLAY
		boolean cont = true;
		int numbingo = 0;
		while(cont) {
			String next = fin.next();
			for(int i = 0; i < marking.size(); i++) {
				mark(next, marking.get(i), cubes.get(i));
			}
			for(int i = 0; i < marking.size(); i++) {
				if(hasBingo(marking.get(i))) {
					cont = false;
					numbingo++;
				}
			}
		}
		System.out.println("Number of bingos: " + numbingo);
		fin.close();
	}
	public static void mark(String coord, boolean[][][] mrk, int[][][] brd) {
		int j = 0;
		if(coord.charAt(0)=='B') j = 0;
		else if(coord.charAt(0)=='I') j = 1;
		else if(coord.charAt(0)=='N') j = 2;
		else if(coord.charAt(0)=='G') j = 3;
		else if(coord.charAt(0)=='O') j = 4;
		int targ = Integer.parseInt(coord.substring(1));
		for(int k = 0; k < 5; k++) {
			for(int i = 0; i < 5; i++) {
				if(brd[k][i][j] == targ) {
					mrk[k][i][j] = true;
				}
			}
		}
	}
	public static boolean hasBingo(boolean[][][] mrk) {
		return false;
	}
}