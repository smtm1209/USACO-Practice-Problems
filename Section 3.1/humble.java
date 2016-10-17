/*
ID: sarkistm1
PROG: humble
LANG: JAVA
EFFICIENCY: O(NK)
*/
import java.util.*;
import java.io.*;
public class humble {
	static int MAXN = 100000 + 2;
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("humble.in"));
		PrintWriter fout = new PrintWriter(new FileWriter("humble.out")); 
		Stack<Integer> stack = new Stack<Integer>();
		int K = fin.nextInt(), N = fin.nextInt();
		int[] primes = new int[K];
		long[] humble = new long[MAXN];
		int[] pointers = new int[K];
		humble[0] = 1;
		for(int i = 0; i < K; i++) {
			primes[i] = fin.nextInt();
		}
		for(int pos = 0; pos < N + 1;) {
			long n = Long.MAX_VALUE;
			List<Integer> xd = new LinkedList<Integer>();
			for(int i = 0; i < K; i++) {
				long a = primes[i] * humble[pointers[i]];
				if (a > humble[pos] && a < n) {
					n = a;
					xd = new LinkedList<Integer>();
					xd.add(i);
				}
				else if(a > humble[pos] && a == n) {
					xd.add(i);
				}
			}
			humble[++pos] = n;
			for(int i : xd) pointers[i]++;
		}
		fout.println(humble[N]);
		fout.close();

	}
}
