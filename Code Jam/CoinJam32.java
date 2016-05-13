import java.util.*;
import java.io.*;
import java.math.*;
public class CoinJam32 {
   static List<JamCoin> sixteens;
   static int N, J;
   public static void main(String[] args) throws IOException {
      Scanner fin = new Scanner(new File("input.txt"));
      PrintWriter fout = new PrintWriter(new FileWriter("outputBig.txt"));
      int T = fin.nextInt();
      N = fin.nextInt() / 2;
      J = fin.nextInt();
      sixteens = new ArrayList<JamCoin>(J);
      buildJamCoin("1");
      fout.println("Case #1:");
      for(JamCoin coin : sixteens) {
         fout.print(coin.me + coin.me + " ");
         for(BigInteger x : coin.divs) {
            fout.print(x + " ");
         }
         fout.println();
      }
      fout.close();
   }
   private static void buildJamCoin(String coin) {
      if(sixteens.size() == J) 
         return;
      else if(coin.length() == N) {
         JamCoin j = new JamCoin(coin);
         if(!j.isPrime()){
            j.calcDivs();
            sixteens.add(j);
            System.out.println(j + " " + sixteens.size());
         }
      }
      else if(coin.length() == N-1) {
         buildJamCoin(coin + "1");
      }
      else {
         buildJamCoin(coin+"0");
         buildJamCoin(coin+"1");
      }
   }
}
class JamCoin {
   public String me;
   public BigInteger[] bases;
   public BigInteger[] divs;
   public JamCoin(String s) {
      me = s;
      bases = new BigInteger[9];
      divs = new BigInteger[9];
      for(int base = 2; base <= 10; base++) {
         bases[base-2] = new BigInteger(me, base);
      }
   }
   public boolean isPrime() {
      for(BigInteger x : bases) {
         if(isPrime(x)) 
            return true;
      }
      return false;
   }
   public void calcDivs() {
      for(int i = 0; i < 9; i++) {
         for(BigInteger j = BigInteger.valueOf(2); j.compareTo(bases[i]) < 0; j = j.add(BigInteger.ONE)) {
            if(bases[i].mod(j).equals(BigInteger.ZERO)) {
               divs[i] = j;
               break;  
            }
         }
      }
   }
   private static boolean isPrime(BigInteger x) {
      BigInteger i = BigInteger.valueOf(2);
      for(; i.multiply(i).compareTo(x) <= 0; i = i.add(BigInteger.ONE)) {
         BigInteger mod = x.mod(i);
         if(mod.equals(BigInteger.ZERO)) return false;
      }
      return true;
   }
   public String toString() {
      return me;
   }
}