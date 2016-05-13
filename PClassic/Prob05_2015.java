import java.util.*;
import java.io.*;
import java.text.*;
public class Prob05_2015 {
   public static void main(String[] args) throws IOException {
      Scanner fin = new Scanner(new File("CatchingPokemonIN.txt"));
      while(fin.hasNext()) {
         int N = fin.nextInt();
         double[][] odds = new double[N][N];
         for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
               odds[i][j] = fin.nextDouble();
            }
         }
         Set<Integer> balls = new HashSet<Integer>();
         Set<Integer> pokemon = new HashSet<Integer>();
         for(int i = 0; i < N; i++)  {
            balls.add(i);
            pokemon.add(i);
         }
      
         double ans = recur(balls, pokemon, odds);
         DecimalFormat f = new DecimalFormat("#.000");
         System.out.println(f.format(ans));
      }
   }
   private static double recur(Set<Integer> balls, Set<Integer> pokemon, double[][] odds) {
      double result = Double.NEGATIVE_INFINITY;
   	/*
   	for(int i = 0; i < pokemon.length; i++) {
   		if(!pokemon[i]) { //not attempted
   			for(int j = 0; j < balls.length; j++) {
   				if(!balls[j]) {
   					pokemon[i] = balls[j] = true;
   					result = Math.max(result, odds[i][j] * recur(balls, pokemon, odds));
                  pokemon[i] = balls[j] = false;
   				}
   			}
   		}
   	}
   	*/
      Set<Integer> p = new HashSet<Integer>(pokemon);
      Set<Integer> b = new HashSet<Integer>(balls);
      for(int i : pokemon) {
         for(int j : balls) {
            p.remove(i);
            b.remove(j);
            result = Math.max(result, odds[j][i] * recur(b, p, odds));
            p.add(i);
            b.add(j);
         }
      }
      if(result == Double.NEGATIVE_INFINITY)
         return 1.0;
      else
         return result;
   }
}