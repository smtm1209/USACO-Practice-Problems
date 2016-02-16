/*
ID: sarkistm1
PROG: runround
LANG: JAVA
*/
import java.io.*;
import java.util.*;

public class runround {
   public static void main(String[] args) throws IOException {
      Scanner infile = new Scanner(new File("runround.in"));
      PrintWriter outfile = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
      int M = infile.nextInt();
      infile.close();
      long X;
      for(X = M+1; !isRunaround(X); X++) { }
      outfile.println(X);
      outfile.close();
   }
   private static boolean isRunaround(long X) {
      String str = ""+X;
      if(str.contains("0")) return false;
      for(int i = 0; i < str.length() - 1; i++) {
         if(str.substring(i+1).contains(str.charAt(i) + "")) return false;
      }
      int sum = sumDigits(str);
      int[] expand = new int[Math.max(sum, (int) Math.pow(str.length(),2)) + 1 + 1]; //one indexed as well as additional ending val
      int[] visited = new int[str.length()]; 
      for(int i = 0; i < sum / str.length(); i++) {
         for(int j = 1; j <= str.length(); j++) {
            expand[i * str.length() + j] = Integer.parseInt(""+str.charAt(j-1));
         }
      }
      expand[expand.length - 1] = Integer.parseInt(""+str.charAt(0));
      int currIndex = 1;
      int goal = expand[currIndex]; //1 indexed
      while(true) {
         for(int i = 0; i < goal; i++, currIndex++);
         int status = getStatus(visited); //0=not done, 1=works, 2=overflow
         if(status == 1) 
            return true;
         if(status == 2) 
            return false;
         else {
            visited[currIndex % str.length()]++;
            goal = expand[currIndex];
         }
      }
   }
   private static int getStatus(int[] visit) {
      if(contains(visit,2)) return 2;
      if(contains(visit,0)) return 0;
      else return 1;
   }
   private static boolean contains(int[] arr, int val) {
      for(int i : arr) {
         if(i == val) {
            return true;
         }
      }
      return false;
   }
   private static int sumDigits(String s) {
      int sum = 0;
      for(char c : s.toCharArray()) {
         sum += Integer.parseInt(c + "");
      }
      return sum;
   }
}