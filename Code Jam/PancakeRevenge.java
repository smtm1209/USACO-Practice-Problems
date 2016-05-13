import java.util.*;
import java.io.*;
public class PancakeRevenge {
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("input.txt"));
		PrintWriter fout = new PrintWriter(new FileWriter("output.txt"));
		int T = fin.nextInt();
		for(int count = 1; count <= T; count++) {
			String stack = fin.next();
			if(works(stack)) {
				fout.println("Case #" + count + ": 0");
            System.out.println("Case #" + count + ": 0");
				continue;
			}
			Queue<String> queue = new LinkedList<String>();
			queue.add(stack);
			Set<String> processed = new HashSet<String>();
			processed.add(stack);
			int flips = -1;
			while(!queue.isEmpty()) {
				flips++;
				int y = queue.size();
				for(int k = 0; k < y; k++) {
					String str = queue.remove();
					if(works(str)) {
						queue.clear();
						break;
					}
					for(int d = 1; d <= str.length(); d++) {
						String nstr = flip(str, d);
						if(!processed.contains(nstr)) {
							processed.add(nstr);
							queue.add(nstr);
						}
					}
				}
			}
			fout.println("Case #" + count + ": " + flips);
         System.out.println("Case #" + count + ": " + flips);
		}
		fout.close();
	}
	public static String flip(String s, int depth) {
		String end = s.substring(depth);
		String start = s.substring(0, depth);
		String rev = (new StringBuilder(start)).reverse().toString();
		String newStart = "";
		for(char c : rev.toCharArray()) {
			newStart += opposite(c);
		}
		return newStart + end;
	}
	public static char opposite(char c) {
		if(c == '+') return '-';
		else return '+';
	}
	public static boolean works(String s) {
		return !s.contains("-");
	}
}