/*
ID: sarkistm1
PROG: nocows
LANG: JAVA
*/
import java.util.*;
import java.io.*;
public class nocows {
	private static int[][] dp;
	private static int N, K;
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("nocows.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		dp = new int[11][11];
		for(int[] row : dp) for(int i = 0; i < row.length; i++) row[i] = -1;
		N = fin.nextInt();
		K = fin.nextInt();
      fout.println((9901+dp(N,K)-dp(N,K-1))%9901);
      System.out.println((9901+dp(N,K)-dp(N,K-1))%9901);
		fout.close();
	}
	private static int dp(int n, int k) {
      if(n == 0 || k == 0) return 0;
      if(n == 1) return 1;
      if(dp[n][k] != -1) return dp[n][k];
      int sum = 0;
      for(int m = 1; m < n - 1; m++) {
         sum = (sum+dp(m,k-1)*dp(n-m-1,k-1))%9901;
      }
      dp[n][k]=sum;
      return sum;
	}
}
