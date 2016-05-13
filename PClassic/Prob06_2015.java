import java.util.*;
import java.io.*;
import java.text.*;
public class Prob06_2015 {
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("EvolutionIN.txt"));
		while(fin.hasNext()) {
			String targ1 = fin.next();
			String targ2 = fin.next();
			int a = 0, b = 0;
			Queue<String> p1 = new LinkedList<String>(), p2 = new LinkedList<String>();
			int[] parent = new int[101];
			for(int i = 0; i < 101; i++) parent[i] = -1;
			String[] name = new String[101];
			while(fin.hasNextInt()) {
				int c = fin.nextInt();
				String d = fin.next();
				int e = fin.nextInt(), f = fin.nextInt();
            if(e != -1) parent[e] = c;
            if(f != -1) parent[f] = c;
				name[c] = d;
				if(d.equals(targ1)) {
					a = c;
				}
				if(d.equals(targ2)) {
					b = c;
				}
			}
			int g = a;
			int h = b;
			while(g != -1) {
				p1.add(name[g]);
				g = parent[g];
			}
			while(h != -1) {
				p2.add(name[h]);
				h = parent[h];
			}
			while(p1.size() > p2.size()) p1.remove();
			while(p2.size() > p1.size()) p2.remove();
			String ans = "";
			while(!p1.isEmpty()) {
				String l = p1.remove();
				String m = p2.remove();
				if(l.equals(m)) {
					ans = l;
					break;
				}
			}
			System.out.println(ans);
         if(fin.hasNext())
            fin.next();
         else
            break;
		}
	}
}