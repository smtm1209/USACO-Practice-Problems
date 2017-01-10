import java.util.*;
import java.io.*;

public class prob2v2 {
	static String filename = "checklist";
	static int[] HolsteinX, HolsteinY, GuernseyX, GuernseyY;
	public static void main(String[] args) throws Exception {
		Scanner fin = new Scanner(new File(filename + ".in"));
		PrintWriter fout = new PrintWriter(new FileWriter(filename + ".out"));
		int H = fin.nextInt(), G = fin.nextInt();
		HolsteinX = new int[H+1]; HolsteinY = new int[H+1]; GuernseyX = new int[G+1]; GuernseyY = new int[G+1];
		for(int i = 1; i <= H; i++) {HolsteinX[i] = fin.nextInt(); HolsteinY[i] = fin.nextInt();}
		for(int i = 1; i <= G; i++) {GuernseyX[i] = fin.nextInt(); GuernseyY[i] = fin.nextInt();}
		int ans = solve(H, G);
		System.out.println(ans);
		fout.println(ans);
		fout.close();
	}
	public static int solve(int H, int G) {
		return recur(1, 0, HolsteinX[1], HolsteinY[1], H, G);
	} 
	public static int recur(int h, int g, int x, int y, int H, int G){
		if (h == H && g == G) return 0;
		if (h == H && g < G) return 1000000000;
		if (h < H && g == G) {
			return dist(HolsteinX[h+1], HolsteinY[h+1], x, y) + recur(h+1, g, HolsteinX[h+1], HolsteinY[h+1], H, G); 
		}
		return Math.min(dist(HolsteinX[h+1], HolsteinY[h+1], x, y) + recur(h+1, g, HolsteinX[h+1], HolsteinY[h+1], H, G), dist(GuernseyX[g+1], GuernseyY[g+1], x, y) + recur(h, g+1, GuernseyX[g+1], GuernseyY[g+1], H, G));
		
	}
	public static int dist(int xf, int yf, int xi, int yi) {
		try{
			return (int) (Math.pow(xf - xi, 2) + Math.pow(yf - yi, 2));
		}catch(Exception e){return -1;}
	}
}
