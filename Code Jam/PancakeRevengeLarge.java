import java.util.*;
import java.io.*;
public class PancakeRevengeLarge {
   public static void main(String[] args) throws IOException {
      Scanner fin = new Scanner(new File("input.txt"));
      PrintWriter fout = new PrintWriter(new FileWriter("output2.txt"));
      int T = fin.nextInt();
      for(int count = 1; count <= T; count++) {
         String stack = fin.next();
         if(works(stack)) {
            fout.println("Case #" + count + ": 0");
            System.out.println("Case #" + count + ": 0");
            continue;
         }
         int flips = 0;
         stack = simplify(stack);
         System.out.println(stack);
         if(stack.charAt(0) == '-') {
            stack = stack.substring(1);
            flips++;
         } 
         System.out.println(stack);
         while(stack.contains("+-")) {
            stack = replaceOne(stack, "+-");
            flips+=2;
            System.out.println(stack);
         }
         System.out.println("Case #" + count + ": " + flips);
         fout.println("Case #" + count + ": " + flips);
      }
      fout.close();
   }
   public static String replaceOne(String s, String rem) {
      for(int i = 0; i < s.length() - rem.length(); i++) {
         if(s.substring(i,i+rem.length()).equals(rem)) {
            return s.substring(0,i) + s.substring(i+rem.length());
         }
      }
      return "";
   }  
   public static String simplify(String s) {
      String ns = "";
      int i = 0;
      while(i < s.length()) {
         char c = s.charAt(i);
         ns+=c;
         while(i < s.length() && s.charAt(i) == c)
            i++;
      }
      return ns;
   }
	public static boolean works(String s) {
		return !s.contains("-");
	}
}