import java.util.*;
import java.io.*;
import java.math.*;
public class Prob02 {
   public static void main(String[] args) throws Exception {
      Scanner fin = new Scanner(new File("Prob02.in.txt"));
      int T = fin.nextInt();
      for(int count1 = 0; count1 < T; count1++) {
         int N = fin.nextInt();
         BigInteger a = BigInteger.valueOf(0);
         for(int count2 = 0; count2 < N; count2++) {
            a = a.add(new BigInteger(fin.next()));
         }
         System.out.println(a.toString());
      }
   }
}