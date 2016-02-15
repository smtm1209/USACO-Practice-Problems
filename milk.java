/*
ID: sarkistm1
PROG: milk
LANG: JAVA
*/
import java.util.*;
import java.io.*;
public class milk
{
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("milk.in"));
      BufferedWriter outfile = new BufferedWriter(new FileWriter("milk.out"));
      int target = infile.nextInt();
      int num_farmers = infile.nextInt();
      int tcost = 0;
      List<Farmer> farmers = new ArrayList<Farmer>(num_farmers);
      for(int i = 0; i < num_farmers; i++)
      {
         farmers.add(new Farmer(infile.nextInt(),infile.nextInt()));
      }
      Collections.sort(farmers);
      int index = 0;
      while(target > 0)
      {
         if(farmers.get(0).units != 0)
         {
            tcost += farmers.get(0).cost;
            target--;
            farmers.get(0).units--;
         }
         else if(farmers.size() > 1)
         {
            farmers.remove(0);
         }
         else
         {
            outfile.write("NONE\n");
            outfile.close();
            System.exit(0);
         }
      }
      outfile.write(tcost + "\n");
      outfile.close();
   }
}
class Farmer implements Comparable<Farmer>
{
   int units, cost;
   public Farmer(int c, int u)
   {
      units = u; cost = c;
   }
   public int compareTo(Farmer f)
   {
      return cost - f.cost;
   }
   public String toString()
   {
      return units + "@" + cost;
   }
}