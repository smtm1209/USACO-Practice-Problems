/*
ID: sarkistm1
PROG: stamps
LANG: JAVA
EFFICIENCY: O(n^2)
*/
import java.util.*;
import java.io.*;
public class stamps {
	static int K, N;
	static int[] vals, s;
	public static void main(String[] args) throws Exception {
		Scanner fin = new Scanner(new File("stamps.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
		K = fin.nextInt();
		N = fin.nextInt();
		int highest_val = (K + 10) * 10000;
		s = new int[highest_val];
      for(int i = 0; i < s.length; i++) s[i] = K + 1;
		s[0] = 0;
		vals = new int[N];
		for(int i = 0; i < N; i++) vals[i] = fin.nextInt();
		for(int val : vals) {
			for(int j = 0; j < s.length; j++) {
				if(j + val < s.length) s[j + val] = Math.min(s[j + val], s[j] + 1);
			}
		}
      //System.out.println(Arrays.toString(s));
		for(int i = 1; i < s.length; i++) {
			if(s[i] > K) {
				fout.println("" + (i - 1));
				break;
			}
		}
		fout.close();
	}
}