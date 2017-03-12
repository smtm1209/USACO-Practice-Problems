import java.util.*;
import java.io.*;

public class art {
	static int[][] canvas;
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("art.in"));
		PrintWriter fout = new PrintWriter(new FileWriter("art.out"));
		int N = fin.nextInt();
		canvas = new int[N][N];
		Map<Integer, Rect> colors = new HashMap<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				canvas[i][j] = fin.nextInt();
				if(canvas[i][j] == 0) continue;
				if(colors.containsKey(canvas[i][j])) {
					Rect rect = colors.get(canvas[i][j]);
					rect.lbound = Math.min(j, rect.lbound);
					rect.rbound = Math.max(j, rect.rbound);
					rect.ubound = Math.min(i, rect.ubound);
					rect.bbound = Math.max(i, rect.bbound);
				}
				else {
					colors.put(canvas[i][j], new Rect(canvas[i][j], j, j, i, i));
				}
			}
		}

//		System.out.println(colors);

		List<Rect> rects = new ArrayList<>(colors.size());
		for(int key : colors.keySet()) {
			rects.add(colors.get(key));
		}
/*
		System.out.println(rects);
		for(Rect a : rects) {
			for(Rect b : rects) {
				System.out.println(a + ", " + b + ": " + a.overlaps(b) + ", " + a.compareTo(b));
			}
		}
*/
//		Collections.shuffle(rects, new Random());

//		System.out.println(rects);

		for(int i = 0; i < rects.size(); i++) {
			int min = i;
			for(int j = i + 1; j < rects.size(); j++) {
				if(rects.get(j).compareTo(rects.get(min)) < 0) {
					min = j;
				}
			}
			if(min != i) {
				Rect t = rects.get(i);
				rects.set(i, rects.get(min));
				rects.set(min, t);
			}
		}

//		System.out.println(rects);

		int layer = rects.size() > 0 ? 1 : 0;
		List<Rect> lastlayer = new LinkedList<Rect>();
		for(Rect u : rects) {
//			System.out.println(u);
			for(Rect v : lastlayer) {
//				System.out.println(u + " | " + v + " | " + u.overlaps(v));
				if(u.overlaps(v)) {
					layer++;
//					System.out.println(lastlayer);
					lastlayer = new LinkedList<Rect>();
					break;
				}
			}
			lastlayer.add(u);
//			System.out.println(lastlayer);
		}
		System.out.println(layer);
		fout.println(layer);
		fout.close();
	}
	private static class Rect implements Comparable<Rect> {
		int color, lbound, rbound, ubound, bbound;
		public Rect(int c, int l, int r, int u, int b) {
			color = c;
			lbound = l;
			rbound = r;
			ubound = u;
			bbound = b;
		}
		public boolean overlaps(Rect that) {
			return this.lbound <= that.rbound && that.lbound <= this.rbound && this.ubound <= that.bbound && that.ubound <= this.bbound;
		}
		public int compareTo(Rect that) {
			if(!this.overlaps(that)) return 0;
			if(this.color == that.color) return 0;
			//else, return -1 if that is on top of this or 1 if this is on top of that
//			System.out.println(canvas[Math.max(this.ubound, that.ubound)][Math.max(this.lbound, that.lbound)]);
			//return canvas[Math.max(this.ubound, that.ubound)][Math.max(this.lbound, that.lbound)] == this.color ? 1 : -1;
			int ret = 0;
			for(int i = Math.max(this.ubound, that.ubound); i <= Math.min(this.bbound, that.bbound); i++) {
				for(int j = Math.max(this.lbound, that.lbound); j <= Math.min(this.rbound, that.rbound); j++) {
					if(canvas[i][j] == this.color) ret++;
					else if(canvas[i][j] == that.color) ret--;
				}
			}
			return ret;
		}
		public String toString() {
			return color + ": x=[" + lbound + ", " + rbound + "] y=[" + ubound + ", " + bbound + "]";
		}
	}
}
