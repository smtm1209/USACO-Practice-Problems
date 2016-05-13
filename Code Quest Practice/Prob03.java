import java.util.*;
import java.io.*;
public class Prob03 {
   public static void main(String[] args) throws Exception {
      Scanner fin = new Scanner(new File("Prob03.in.txt"));
      int T = fin.nextInt();
      for(int count = 0; count < T; count++) {
         int N = fin.nextInt();
         for(int count2 = 0; count2 < N; count2++) {
            int year = fin.nextInt();
            if(year < 1582) System.out.println("No");
            else if(year % 4 != 0) System.out.println("No");
            else if(year % 100 != 0) System.out.println("Yes");
            else if(year % 400 != 0) System.out.println("No");
            else System.out.println("Yes");
         }
      }
   }
}