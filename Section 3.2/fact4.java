/*
ID: sarkistm1
PROG: fact4
LANG: JAVA
EFFICIENCY: O(NK)
*/

import java.util.*;
import java.io.*;
import java.math.*;

public class fact4 {
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("fact4.in"));
		PrintWriter fout = new PrintWriter(new FileWriter("fact4.out"));

		int N = fin.nextInt();
		System.out.println(N);

		BigInteger fact = new BigInteger("1");		

		for(int i = 2; i <= N; i++) {
			fact = fact.multiply(new BigInteger(i + ""));
		}

		String s = fact.toString();
		int i = s.length() - 1;
		for(; i >= 0; i--) {
			if(s.charAt(i) != '0') break;
		}
		fout.println("" + s.charAt(i));
		fout.close();
	}
}

