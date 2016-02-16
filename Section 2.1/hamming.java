/*
ID: sarkistm1
PROG: hamming
LANG: JAVA
*/
import java.io.*;
import java.util.*;

public class hamming
{
	private static int B;
	public static void main(String[] args) throws Exception {
		Scanner infile = new Scanner(new File("hamming.in"));
		PrintWriter outfile = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		int N = infile.nextInt();
		B = infile.nextInt();
		int D = infile.nextInt();
		List<Integer> currHams = new ArrayList<Integer>(N);
		for(int a = 0; a < Math.pow(2, B) && currHams.size() < N; a++) {
			if(currHams.isEmpty())
				currHams.add(a);
			else {
				boolean can = true;
				for(int i : currHams) {
					if(findHammingDistance(a,i) < D) {
						can = false;
						break;
					}
				}
				if(can)
					currHams.add(a);
			}
		}
		int rowCount = 1;
		int totalCount = 1;
		for(Integer i : currHams) {
			outfile.print(i);
			if(rowCount == 10 || totalCount == N) {
				outfile.println();
				rowCount = 1;
			}
			else {
				outfile.print(" ");
				rowCount++;
			}
			totalCount++;
		}
      outfile.close();
	}
	private static int findHammingDistance(int a, int b) {
		String alpha = String.format("%0" + B + "d", Integer.parseInt(Integer.toBinaryString(a)));
		String beta = String.format("%0" + B + "d", Integer.parseInt(Integer.toBinaryString(b)));
		int hamDist = 0;
		for(int i = 0; i < B; i++)
			if(alpha.charAt(i) != beta.charAt(i))
				hamDist++;
		return hamDist;
	}
}