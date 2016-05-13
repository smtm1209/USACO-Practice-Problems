import java.util.*;
import java.io.*;
public class Prob11_2015 {
	public static void main(String[] args) throws IOException {
      double sstart = System.nanoTime();
		Scanner fin = new Scanner(new File("PokemonTrainingIN.txt"));
		while(fin.hasNextInt()) {
         double start = System.nanoTime();
			int n = fin.nextInt();
			int hp = fin.nextInt();
			int[] dmg = new int[n];
			int[] xpg = new int[n];
			for(int i = 0; i < n; i++) {
				dmg[i] = fin.nextInt();
				xpg[i] = fin.nextInt();
			}
			int[] dp = new int[hp+1];
			for(int i = 0; i < dp.length; i++) {
				dp[i] = -1;
			}
			int ans = dp(hp, dp, dmg, xpg);
         double end = System.nanoTime();
         System.out.println(ans + " Millis: " + (end-start)/1E6);
		}
      double send = System.nanoTime();
      System.out.println("Millis: " + (send-sstart)/1E6);
	}
	static int dp(int hp, int[] dp, int[] dmg, int[] xpg) {
		if(hp <= 0) {
			return Integer.MIN_VALUE;
		}
		else if(dp[hp] != -1) {
			return dp[hp];
		}
		else {
			int xp = Integer.MIN_VALUE;
			for(int i = 0; i < dmg.length; i++) {
				int newxp = xpg[i] + dp(hp-dmg[i],dp,dmg,xpg);
				if(newxp < 0) newxp = 0;
            if(newxp > xp) {
               xp = newxp;
            }
			}
			return dp[hp] = xp;
		}

	}
}