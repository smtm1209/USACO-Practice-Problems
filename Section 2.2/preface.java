/*
ID: sarkistm1
PROG: preface
LANG: JAVA
*/
import java.io.*;
import java.util.*;

public class preface {
	private static Map<Integer, String> romanMap = new HashMap<Integer, String>();
	private static  int[] indivNums = {0, 0, 0, 0, 0, 0, 0};
	private static int N;
	private static PrintWriter outfile;
	public static void main(String[] args) throws IOException {
		Scanner infile = new Scanner(new File("preface.in"));
		outfile = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		N = infile.nextInt();
		infile.close();
		initMap();
		generateVals();
		printVals();
		outfile.close();
	}
	private static void initMap() {
		//1s
		romanMap.put(0, ""); romanMap.put(1, "I"); romanMap.put(2, "II"); romanMap.put(3, "III");
		romanMap.put(4, "IV"); romanMap.put(5, "V"); romanMap.put(6, "VI"); romanMap.put(7, "VII");
		romanMap.put(8, "VIII"); romanMap.put(9, "IX");
		//10s
		romanMap.put(10, "X"); romanMap.put(20, "XX"); romanMap.put(30, "XXX"); romanMap.put(40, "XL");
		romanMap.put(50, "L"); romanMap.put(60, "LX"); romanMap.put(70, "LXX"); romanMap.put(80, "LXXX");
		romanMap.put(90, "XC");
		//100s
		romanMap.put(100, "C"); romanMap.put(200, "CC"); romanMap.put(300, "CCC"); romanMap.put(400, "CD");
		romanMap.put(500, "D"); romanMap.put(600, "DC"); romanMap.put(700, "DCC"); romanMap.put(800, "DCCC");
		romanMap.put(900, "CM");
		//1000s
		romanMap.put(1000, "M"); romanMap.put(2000, "MM"); romanMap.put(3000, "MMM"); 
		//further values out of scope of problem
	}
	private static String romanNumeral(int i) {
		String output = "";
		if(romanMap.containsKey(i)) {
			output = romanMap.get(i); //to prevent overcomplication and save time
		}
		else {
			//break down the answer into Mapped chunks
			if(i > 999) {
				output += romanMap.get(i / 1000 * 1000);
				i %= 1000;
			}
			if(i > 99) {
				output += romanMap.get(i / 100 * 100);
				i %= 100;
			}
			if(i > 9) {
				output += romanMap.get(i / 10 * 10);
				i %= 10;
			}
			output += romanMap.get(i);
		}
		return output;
	}
	private static void generateVals() {
		for(int x = 1; x <= N; x++) {
			String romanNum = romanNumeral(x);
			for(char c : romanNum.toCharArray()) {
				switch(c) {
					case 'I': indivNums[0]++; break;
					case 'V': indivNums[1]++; break;
					case 'X': indivNums[2]++; break;
					case 'L': indivNums[3]++; break;
					case 'C': indivNums[4]++; break;
					case 'D': indivNums[5]++; break;
					case 'M': indivNums[6]++; break;
					default: System.exit(0); //fundamental flaw emerges
				}
			}
		}
	}
	private static void printVals() {
		for(int i = 0; i < 7; i++) {
			int n = indivNums[i];
			if(n > 0) {
				switch(i) {
					case 0: outfile.println("I " + n); break;
					case 1: outfile.println("V " + n); break;
					case 2: outfile.println("X " + n); break;
					case 3: outfile.println("L " + n); break;
					case 4: outfile.println("C " + n); break;
					case 5: outfile.println("D " + n); break;
					case 6: outfile.println("M " + n); break;
					default: outfile.println();
				}
			}
		}
	}
}