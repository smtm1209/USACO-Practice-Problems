import java.util.*;
import java.io.*;
public class Prob01 {
   public static void main(String[] args) throws Exception {
      Scanner fin = new Scanner(new File("Prob01.in.txt"));
      int T = fin.nextInt();
      for(int count = 0; count < T; count++) {
         int N = fin.nextInt();
         for(int i = 0; i < N; i++) {
            char a = fin.next().toUpperCase().charAt(0);
            char b = fin.next().toUpperCase().charAt(0);
            char c = fin.next().toUpperCase().charAt(0);
            System.out.println("" + a + c + b);
         }
      }
   }
}