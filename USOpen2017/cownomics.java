import java.util.*;
import java.io.*;

public class cownomics {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		Scanner fin =  new Scanner(new File("cownomics.in"));
		PrintWriter fout = new PrintWriter(new FileWriter("cownomics.out"));
		int N = fin.nextInt(), M = fin.nextInt();
		
		//spotty[0][1] is a set of all substrings of S with starting index 0 and length 1
//		HashSet[][] spotty = new HashSet[M][M+1], plain = new HashSet[M][M+1];
		
		Set<String> S = new HashSet<>(), P = new HashSet<>();
		for(int i = 0; i < N; i++) S.add(fin.next());
		for(int i = 0; i < N; i++) P.add(fin.next());


		int ret = Integer.MAX_VALUE;
		for(int len = 1; len <= M; len++) {
			for(int i = 0; i <= M-len; i++) {
				Set<String> spotty = new HashSet<>();
				Set<String> plain = new HashSet<>();
				for(String s : S) {
					spotty.add(s.substring(i, i+len));
				}
				for(String p : P) {
					plain.add(p.substring(i, i+len));
				}
				boolean b = true;
				for(String s : spotty) {
					b = b && !plain.contains(s);
				}
				if(b) {
					ret = len;
					break;
				}
			}
			if(ret != Integer.MAX_VALUE) break;
		}	
/*
		for(int len = 1; len <= M; len++) {
			for(int i = 0; i <= M-len; i++) {
				spotty[i][len] = new HashSet<>();
				plain[i][len] = new HashSet<>();
				for(String s : S) {
					spotty[i][len].add(s.substring(i, i+len));
				}
				for(String p : P) {
					plain[i][len].add(p.substring(i, i+len));
				}	
			}		
		}
		System.out.println(Arrays.deepToString(spotty));
		System.out.println(Arrays.deepToString(plain));
		for(int i = 0; i < plain.length; i++) {
			for(int j = 0; j < plain[i].length; j++) {
				System.out.println(i + ", " + j + ": " + plain[i][j]);
			}
		}

		int ret = Integer.MAX_VALUE;
		for(int len = 1; len <= M; len++) {
			for(int i = 0; i <= M-len; i++) {
				boolean b = true;
				for(Object s : spotty[i][len]) {
					b = b && !plain[i][len].contains(s);
				}
//				System.out.println(i + ", " + len + ": " + b);
				if(b) {
					ret = len;
					break;
				}
			}
			if(ret != Integer.MAX_VALUE) break;
		}
*/

		System.out.println(ret);
		fout.println(ret);
		fout.close();
		
	}
}
