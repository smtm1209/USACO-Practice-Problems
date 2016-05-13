import java.util.*;
import java.io.*;
public class CountingSheep {
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("input.txt"));
		int T = fin.nextInt();
		for(int count = 0; count < T; count++) {
			boolean[] seen = new boolean[10];
			int N = fin.nextInt();
			if(N == 0) {
				System.out.println("Case #" + (count+1) + ": INSOMNIA");
				continue;
			}
			int i = 1;
			for(;; i++) {
				int x = N * i;
				String exp = "" + x;
				for(char c : exp.toCharArray()) {
					seen[c-'0'] = true;
				}
            if(done(seen)) break;
			}
			System.out.println("Case #" + (count+1) + ": " + (i*N));
		}
	}
	static boolean done(boolean[] arr) {
		for(int i = 0; i < 10; i++) {
			if(!arr[i]) return false;
		}
		return true;
	}
}