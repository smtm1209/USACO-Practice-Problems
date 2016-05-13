//O(n^n)
import java.util.*;
import java.io.*;
public class Prob13 {
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("Prob13.in.txt"));
		int TTT = fin.nextInt();
		for(int overallCount = 0; overallCount < TTT; overallCount++) {
			int N = fin.nextInt();
			double start = System.nanoTime();
			List<Cube> pool = new ArrayList<Cube>(N*3);
			for(int i = 0; i < N; i++) {
				String[] line = fin.next().split("x");
				int a = Integer.parseInt(line[0]);
				int b = Integer.parseInt(line[1]);
				int c = Integer.parseInt(line[2]);
				pool.add(new Cube(a,b,c));
				pool.add(new Cube(c,a,b));
				pool.add(new Cube(b,c,a));
			}
			boolean[] used = new boolean[N];
			int max = recur(pool, used, null);
			double end = System.nanoTime();
         System.out.println(max + ", time: " + (end-start)/1E9);
		}
	}
	private static int recur(List<Cube> pool, boolean[] used, Cube prev) {
		int ret = 0;
		for(int c = 0; c < pool.size(); c++) {
			if(!used[c/3] && pool.get(c).fitsOn(prev)) {
				used[c/3] = true; // div3 to prevent using the same block twice
				ret = Math.max(ret, pool.get(c).h + recur(pool, used, pool.get(c)));
				used[c/3] = false; // see above
			}
		}
		return ret;
	}
	private static class Cube {
		int l, w, h;
		public Cube(int l, int w, int h) {
			this.l = l;
			this.w = w;
			this.h = h;
		}
		public boolean fitsOn(Cube other) {
			if(other == null) return true;
			else return this.l <= other.l && this.w <= other.w || this.l <= other.w && this.w <= other.l;
		}
	}
}