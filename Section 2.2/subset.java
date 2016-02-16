/*
ID: sarkistm1
PROG: subset
LANG: JAVA
*/
import java.io.*;
import java.util.*;

public class subset {
	private static int N, S;
	private static long[][] DP;
	public static void main(String[] args) throws IOException {
		Scanner infile = new Scanner(new File("subset.in"));
		PrintWriter outfile = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		N = infile.nextInt();
      infile.close();
		S = (N+1)*N/2;
      if(S % 2 != 0) {
         outfile.println(0);
      }
      else {
         S /= 2;
         DP = new long[S+1][N+1];
         init();
         outfile.println(solve(S,N) / 2);
      }
      outfile.close();
	}
   public static void init() {
      for(int i = 0; i < DP.length; i++) {
         Arrays.fill(DP[i],-1);
      }
   }
   public static long solve(int n, int k) {
      if(n < 0 || k < 0) return 0;
      if(DP[n][k] != -1) return DP[n][k];
      if(n == 0 && k == 0) return DP[n][k] = 1;
      return DP[n][k] = solve(n, k-1) + solve(n-k,k-1);
   }
}

