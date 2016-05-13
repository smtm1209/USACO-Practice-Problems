import java.util.*;
import java.io.*;
public class Prob13_2015 {
   public static void main(String[] args) throws IOException {
      Scanner fin = new Scanner(new File("ProfessorOakIN.txt"));
      while(fin.hasNextInt()) {
         int lim = fin.nextInt();
         int ret = 0;
         for(int y = 1; y <= lim; y++) {
            for(int x = 1; x < y; x++) {
               if(x % 2 != 0 && y % 2 != 0)
                  continue;
               else {
                  int xy = x * y;
                  int xpy = x + y;
                  if(xy != 0 && xy/xpy == ((double) xy / xpy)) {
                     System.out.println(x + " " + y + " " + xy / xpy);
                     ret++;
                  }
               }
            }
         }
         System.out.println(ret);
      }
   }
}