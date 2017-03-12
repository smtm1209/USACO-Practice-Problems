import java.util.*;
import java.io.*;

public class art2v2 {
	static int[] BT, painting;
	static int N;
	static int MAXN = 1 << 17;
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("art2.in"));
		PrintWriter fout = new PrintWriter(new FileWriter("art2.out"));
		N = fin.nextInt();
		BT = new int[MAXN];
		painting = new int[N];
		for(int i = 0; i < N; i++) painting[i] = fin.nextInt();
		Stack<Integer> openIntervals = new Stack<>();
		Set<Integer> checker = new HashSet<>();
		int[] ftab1 = new int[N+1];
		for(int a : painting) {
			ftab1[a]++;
		}
		int ret = 0, depth = 0;
		for(int i = 0; i < N; i++) {
			int c = painting[i];
			if(c == 0) {
				if(!openIntervals.empty()) {
					ret = -1;
					break;
				}
				else continue;
			}
/*
			System.out.println(c);
			System.out.println(Arrays.toString(ftab1));
			System.out.println(checker);
			System.out.println(openIntervals);
*/
			if(!checker.contains(c)) {
				//not opened yet
				checker.add(c); //open it
				openIntervals.push(c); //open it some more
				depth++;
			}
			ftab1[c]--;
			if(c != openIntervals.peek()) {
				ret = -1;
				break;
			}
			if(ftab1[c] == 0 && checker.contains(c)) { //open interval needs closing
				openIntervals.pop();
				checker.remove(c);
				ret = Math.max(ret, depth);
				depth--;
			}
		}
		System.out.println(ret);
		fout.println(ret);
		fout.close();

/*
		System.out.println(Arrays.toString(ftab1));
		Map<Integer, IntPair> A = new HashMap<>();
		for(int i = 0; i < N; i++) {
			if(!A.containsKey(painting[i])) {
				A.put(painting[i], new IntPair(i, -1));
			}
			ftab1[painting[i]]--;
			if(A.containsKey(painting[i]) && ftab1[painting[i]] == 0) {
				A.get(painting[i]).second = i;
			}	
			System.out.println(i);
			System.out.println(Arrays.toString(ftab1));
			System.out.println(A);
		}
		List<IntPair> B = new ArrayList<>(A.size());
		for(int key : A.keySet()) {
			IntPair a = A.get(key);
			if(a.second == -1) { //not authentic
				System.out.println(-1);
				fout.println(-1);
				fout.close();
				System.exit(0);
			}
			B.add(a);
		}
		Collections.sort(B);
		int ret = 0, depth = 0;
		Stack<Integer> openIntervals = new Stack<Integer>();
		Set<Integer> checker = new HashSet<Integer>();
		for(int i = 0; i < B.size(); i++) {
			int c = B.get(i).color;
			if(!checker.contains(c)) {
				checker.add(c);
				openIntervals.add(
			}
		}
		System.out.println(ret);
		fout.println(ret);
		fout.close();
*/	
	}
/*
	private static void bit_add(int x, int v) {
        for(int i = x | MAXN; i < (MAXN << 1); i += i & -i) {
            BT[i ^ MAXN] += v;
        }
    }

    private static int bit_get(int x) {
        int ret = 0;
        for(int i = x - 1; x != 0; i &= i - 1) {
            ret += BT[i];
            if(i == 0) break;
        }
        return ret;
    }
	

	private static class IntPair implements Comparable<IntPair> {
		public int color, first, second;
		public IntPair(int c, int v, int w) {
			color = c; first = v; second = w;
		}
		public int compareTo(IntPair that) {
			return this.first - that.first;
		}
		public String toString() {
			return color + ": (" + first + ", " + second + ")";
		}
	}
	*/
}
