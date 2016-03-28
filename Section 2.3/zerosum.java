/*
ID: sarkistm1
PROG: zerosum
LANG: JAVA
*/
import java.util.*;
import java.io.*;
public class zerosum {
	private static List<String> expressions;
	private static int N;
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("zerosum.in"));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		N = fin.nextInt();
		fin.close();
		expressions = new LinkedList<String>();
		LinkedList<String> solutions = new LinkedList<String>();
		recur("1", 2, 0);
		for(String s : expressions)
			if(works(s)) solutions.add(s);
      Collections.sort(solutions);
		for(String s : solutions)
			fout.println(s);
		fout.close();
	}
	private static void recur(String s, int curr, int index) {
		if(curr > N) expressions.add(s);
		else {
			recur(s+"+"+curr,curr+1,index+2);
			recur(s+"-"+curr,curr+1,index+2);
			if(index >= 2) {
            if(s.charAt(index-1) != ' ') {
               recur(s+" "+curr,curr+1,index+2);
            }
         }
			else {
				recur(s+" "+curr,curr+1,index+2);
			}
		}
	}
	private static boolean works(String s) {
		List<String> expr = new ArrayList<String>(s.length());
		for(int i = 0; i < s.length(); i++) {
			if(i < s.length() - 2 && s.charAt(i+1) == ' ') {
				expr.add("" + s.charAt(i) + s.charAt(i+2));
				i += 2;
			}
			else {
				expr.add(s.charAt(i) + "");
			}
		}
		int a = Integer.parseInt(expr.get(0));
		for(int i = 1; i < expr.size(); i+=2) {
			a = eval(a,Integer.parseInt(expr.get(i+1)),expr.get(i));
		}
		return a == 0;
	}
	private static int eval(int a, int b, String op) {
		if(op.equals("+")) return a + b;
		if(op.equals("-")) return a - b;
		return -1;
	}
}