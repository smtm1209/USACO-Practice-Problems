// O(2n * n^2) ~ 40 * 800 =  32000
// dp table DP[i][j] where i = capacity, j = height where i <= 2n and j <= n
import java.util.*;
import java.io.*;
public class prob07_2015 {
	public static void main(String[] args) throws Exception {
		Scanner fin = new Scanner(new File("RocketBoxesIN.txt"));
		while(fin.hasNextInt()) {
			int a = fin.nextInt();
			long ans = 0;
			for(int cap = 0; cap <= 2*a; cap+=2) {
				long[][] dp = new long[2*a+1][a];
				for(int i = 0; i < dp.length; i++)
					for(int j = 0; j < dp[i].length; j++)
						dp[i][j] = -1; //unvisited
				ans += dp(cap, a-1, dp);
			}
			System.out.println(ans);
		}
	}
	//return number of ways
	private static long dp(int cap, int height, long[][] dp) {
		if(dp[cap][height] != -1) {
			return dp[cap][height];
		}
		else if(height == 0 && cap <= 2) {
			return 1;
		}
      else if(height == 0 && cap > 2) {
         return 0;
      }
		else {
			long ans = 0;
			for(int l = 0; l <= cap; l++) {
				int r = cap - l;
				ans += dp(l, height-1, dp) * dp(r, height-1, dp);
			}
			return dp[cap][height] = ans;
		}
	}
}