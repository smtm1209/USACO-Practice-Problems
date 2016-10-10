/*
ID: sarkistm1
PROG: contact
LANG: JAVA
EFFICIENCY: O(n^2)
*/
import java.util.*;
import java.io.*;
public class contact {
   public static void main(String[] args) throws IOException {
      Scanner fin = new Scanner(new File("contact.in"));
      PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
      int A = fin.nextInt(), B = fin.nextInt(), N = fin.nextInt();
      StringBuilder sb = new StringBuilder();
      while(fin.hasNext()) {
         sb.append(fin.nextLine());
      }
      String expr = sb.toString();
      Map<String, Integer> map = new HashMap<String, Integer>();
      for(int len = A; len <= B; len++) {
         for(int i = 0; i <= expr.length() - len; i++) {
            String str = expr.substring(i, i + len);
            if(map.containsKey(str)) {
               map.put(str, map.get(str) + 1);
            }
            else {
               map.put(str, 1);
            }
         }
      }
      List<State> list = new ArrayList<State>(map.size());
      for(String s : map.keySet()) {
         list.add(new State(s, map.get(s)));
      }
      Collections.sort(list);
      int lvl = 0;
      int i = 0;
      while(lvl < N && i < list.size()) {
         int n = list.get(i).num;
         fout.print(n);
         List<String> vals = new LinkedList<String>();
         //Collections.sort(vals);
         while(i < list.size() && list.get(i).num == n) {
            vals.add(list.get(i).exp);
            i++;
         }
         int j = 0;
         for(j = 0; j < vals.size(); j++) {
            if(j % 6 == 0){
               fout.println();
               fout.print(vals.get(j));
            }
            else
               fout.print(" " + vals.get(j));
         }
         fout.println();
         lvl++;
      }
      fout.close();
   }
   private static class State implements Comparable<State> {
      String exp;
      int num;
      public State(String exp, int num) {
         this.exp = exp;
         this.num = num;
      }
      public int compareTo(State that) {
         int a = that.num - this.num;
         if(a != 0) 
            return a;
         int b = this.exp.length() - that.exp.length();
         if(b != 0) 
            return b;
         return this.exp.compareTo(that.exp);
      }
      public int hashCode() {
         return exp.hashCode();
      }
   }
}