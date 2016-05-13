import java.util.*;
import java.io.*;
public class CSI {
   public static void main(String[] args) throws IOException {
      Scanner fin = new Scanner(new File("Prob14.in.txt"));
      int T = fin.nextInt();
      for(int i = 0; i < T; i++) {
         char[] key = fin.next().toCharArray();
         int N = fin.nextInt();
         List<Person> ppl = new ArrayList<Person>(N);
         for(int j = 0; j < N; j++) {
            String nl = fin.next();
            ppl.add(new Person(nl.substring(0,nl.indexOf("=")),nl.substring(nl.indexOf("=")+1).toCharArray(),key));
         }
         Collections.sort(ppl);
         List<Person> ans = new LinkedList<Person>();
         int ideal = ppl.get(0).val;
         int index = 0;
         while(index < ppl.size() && ppl.get(index).val == ideal) { 
            ans.add(ppl.get(index));
            index++;
         }
         String ts = ans.toString();
         ts = ts.substring(1,ts.length()-1);
         ts = ts.replaceAll(" ","");
         System.out.println(ts);
      }
   }
   private static class Person implements Comparable<Person> {
      int val;
      int[][] dp;
      String name;
      char[] seq;
      char[] key;
      public Person(String name, char[] seq, char[] key) {
         this.name = name;
         this.seq = seq;
         this.key = key;
         dp = new int[seq.length + 1][key.length + 1];
         eval();
         val = dp[0][0];
      }  
      private void eval() {
         for(int i = seq.length - 1; i >= 0; i--) {
            for(int j = key.length - 1; j >= 0; j--) {
               if(seq[i] == key[j]) 
                  dp[i][j] = dp[i+1][j+1] + 1;
               else 
                  dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
            }  
         }
      }
      public int compareTo(Person per) {
         if(per.val == this.val) 
            return this.name.compareTo(per.name);
         return per.val - this.val;
      }
      public String toString() {
         return this.name;
      }
   }
}