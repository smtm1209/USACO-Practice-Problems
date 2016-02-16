/*
ID: sarkistm1
PROG: frac1
LANG: JAVA
*/
import java.io.*;
import java.util.*;
public class frac1
{
   static int MAXD;
   static List<Fraction> fracs;
   static Set<Fraction> test;
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("frac1.in"));
      MAXD = infile.nextInt();
      fracs = new ArrayList<Fraction>(MAXD * MAXD);
      test = new HashSet<Fraction>();
      BufferedWriter outfile = new BufferedWriter(new FileWriter("frac1.out"));
      for(int den = 1; den <= MAXD; den++)
         for(int num = 0; num <= den; num++) {
            Fraction f = new Fraction(num + "/" + den);
            if(!test.contains(f)) {
               fracs.add(f);
               test.add(f);
            }
         }
      Collections.sort(fracs);
      for(Fraction f : fracs)
         outfile.write(f.frac + "\n");
      outfile.close();
   }
}
class Fraction implements Comparable<Fraction>
{
   double value;
   String frac;
   public Fraction(String val)
   {
      frac = val;
      double part1 = Double.parseDouble(val.substring(0,val.indexOf("/")));
      double part2 = Double.parseDouble(val.substring(val.indexOf("/")+1));
      value = part1/part2;
   } 
   public boolean equals(Object o)
   {
      Fraction f = (Fraction) o;
      return value == f.value;
   }
   public int compareTo(Fraction f)
   { 
      if(value > f.value)
         return 1;
      else if(value < f.value)
         return -1;
      return 0; //=
   }
   public int hashCode()
   {
      return Double.valueOf(value).hashCode();
   }
}