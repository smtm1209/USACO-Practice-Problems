/*
ID: sarkistm1
PROG: inflate
LANG: JAVA
ALGO: Knapsack w/ repetition (DP) Bottom-up
EFFICIENCY: O(NM) time, O(M) space
*/
import java.util.*;
import java.io.*;
import static java.lang.Math.max;
public class inflate {
	static int[] dp , gain, cost;
	static int N, M;
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("inflate.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
		M = fin.nextInt();
		N = fin.nextInt();
		dp = new int[M+1];
		gain = new int[N];
		cost = new int[N];
		for(int i = 0; i < N; i++) {
			gain[i] = fin.nextInt();
			cost[i] = fin.nextInt();
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j <= M; j++) {
				if(j >= cost[i]) dp[j] = max(dp[j], dp[j-cost[i]] + gain[i]);
			}
		}
		fout.println(dp[M]);
		fout.close();
	}
	/*
	private static int dp(int time) {
		if(time < 0) return Integer.MIN_VALUE; //fail
		if(dp[time] != -1) return dp[time];
		int ret = 0;
		for(int i = 0; i < N; i++) {
			int n = dp(time - cost[i]) + gain[i];
			if(n >= 0)
				ret = max(n, ret);
		}
		return dp[time] = ret;
	}
	private static int dpIter() {
		int[][] dp = new int[N][M+1];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j <= M; j++) {
				if(i > 0) dp[i][j]= dp[i-1][j];
				if(j - cost[i] >= 0) dp[i][j] = max(dp[i][j], dp[i][j - cost[i]] + gain[i]);
			}
		}
		return dp[N-1][M];
	}
	*/
}