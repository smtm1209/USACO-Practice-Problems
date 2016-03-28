/*
ID: sarkistm1
PROG: money
LANG: JAVA
*/
import java.util.*;
import java.io.*;
public class money {
	private static int V, N;
	private static long[] dp;
	private static int[] currencies;
	public static void main(String[] args) throws IOException {
		Scanner infile = new Scanner(new File("money.in"));
		PrintWriter outfile = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		V = infile.nextInt();
		N = infile.nextInt();
		dp = new long[N+1];
      currencies = new int[V];
		for(int i = 0; i < V; i++) currencies[i] = infile.nextInt();
		solve();
		outfile.println(dp[N]);
		outfile.close();
	}
	private static void solve() {
		dp[0] = 1;
      for(int denom : currencies) 
         for(int i = denom; i <= N; i++)
            dp[i] += dp[i-denom];
	}
}