/*
ID: sarkistm1
PROG: prefix
LANG: JAVA
*/
import java.io.*;
import java.util.*;

public class prefix {
   private static List<String> prims;
   private static String seq = "";
   private static boolean[] visit;
   public static void main(String[] args) throws IOException {
      Scanner infile = new Scanner(new File("prefix.in"));
      PrintWriter outfile = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
      prims = new LinkedList<String>();
      while(true) {
         String x = infile.next();
         if(x.equals(".")) 
            break;
         else prims.add(x);
      }
      infile.nextLine(); //move scanner to right place
      StringBuilder seqBuilder = new StringBuilder();
      while(infile.hasNextLine()) {
         seqBuilder.append(infile.nextLine());
      }
      seq = seqBuilder.toString();
      visit = new boolean[seq.length()];
      for(int i = 0; i < seq.length(); i++) {
         if(i != 0 && !visit[i-1]) 
            continue;
         for(String prim : prims) {
            if(i + prim.length() <= seq.length() && seq.substring(i, i+prim.length()).equals(prim)) {
               if(i == 0) visit[i] = true;
               int a = i + prim.length();
               if(prim.length() > 1) --a;
               try{visit[a] = true;}
               catch(Exception e){}
            }
         }
      }
      int solution = 0;
      for(solution = visit.length - 1; solution >= 0; solution--) 
         if(visit[solution])
            break;
      if(solution == 0) outfile.println(0);
      else outfile.println(solution+1);
      outfile.close();
   }
}