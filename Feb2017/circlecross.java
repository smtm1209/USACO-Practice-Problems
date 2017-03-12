import java.util.*;
import java.io.*;

public class circlecross {
	private static int[] BT;
	private static int N;
	private static int MAXN = 1 << 17;
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("circlecross.in"));
		PrintWriter fout = new PrintWriter(new FileWriter("circlecross.out"));
		N = fin.nextInt();
		BT = new int[MAXN];
		List<IntPair> A = new ArrayList<>(N);
		for(int i = 0; i < N; i++) A.add(new IntPair(-1, -1));			
		for(int i = 0; i < 2 * N; i++) {
			int x = fin.nextInt() - 1;
			if(A.get(x).first == -1) A.get(x).first = i;
			else A.get(x).second = i;
		}
		Collections.sort(A);
		int ret = 0;
		for(int i = 0; i < A.size(); i++) { 
			ret += bit_get(A.get(i).second) - bit_get(A.get(i).first);
			bit_add(A.get(i).second, 1);	
		}
		fout.println(ret);
		fout.close();
	}

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
		public int first;
		public int second;
		public IntPair(int v, int w) {first = v; second = w;}
		public int compareTo(IntPair that) {
			return this.first - that.first;
		}
	}
}
