/*
ID: sarkistm1
PROG: humble
LANG: JAVA
EFFICIENCY: O(NK^2)
*/
import java.util.*;
import java.io.*;
public class humble {
	/*
	static List<Integer> s;
	static PriorityQueue<Integer> hum;
   static Set<Integer> done;
	static int K, N;
	*/
	public static void main(String[] args) throws IOException {
      double start = System.nanoTime();
		Scanner fin = new Scanner(new File("humble.in"));
		PrintWriter fout = new PrintWriter(new FileWriter("humble.out"));
		int K = fin.nextInt(), N = fin.nextInt();
		int[] s = new int[K], m = new int[K];
      long[] h = new long[N+1];
		for(int i = 0; i < K; i++) s[i] = fin.nextInt();
      h[0] = 1;
		int i = 0;
		while(i < N + 1) {
			long next = Long.MAX_VALUE;
			List<Integer> n = new LinkedList<Integer>();
			for(int k = 0; k < K; k++) {
				long x = s[k] * h[m[k]];
				if(x > h[i] && x <= next) {
					if(x < next) {
						next = x;
						n = new LinkedList<Integer>();
						n.add(k);
					}
					else if(x == next) {
						n.add(k);
					}
				}
			}
			h[++i] = next;
			for(int j : n) m[j]++;
		}
		fout.println(h[N]);
		fout.close();
		/*
		K = fin.nextInt();
		N = fin.nextInt();
		s = new ArrayList<Integer>(K);
		hum = new PriorityQueue<Integer>();
      done = new HashSet<Integer>();
		for(int i = 0; i < K; i++) s.add(fin.nextInt());
      for(int x : s) {
         done.add(x);
         hum.add(x);
      }
		while(hum.size() <= N*2) {
         List<Integer> addit = new LinkedList<Integer>();
			for(int x : hum) {
				for(int y : s) {
					if((long) x * y < Integer.MAX_VALUE && !done.contains(x * y)) {
						addit.add(x * y);
						done.add(x * y);
					}
				}
			}
         if(addit.size() == 0) break;
         for(int x : addit) hum.add(x);
		}
      int zzz = 0;
		for(int i = 0; i < N - 1; i++) {
			zzz = hum.remove();
		}
		fout.println(zzz = hum.remove());
		fout.close();
		*/
      double end = System.nanoTime();
      System.out.println((end-start)/1E9);
	}
}