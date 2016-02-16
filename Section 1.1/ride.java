/*
ID: sarkistm1
PROG: ride
LANG: JAVA
*/
import java.util.*;
import java.io.*;
public class ride
{
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("ride.in"));
      String comet = infile.next();
      String group = infile.next();
      int cometVal = 1;
      for(int k = 0; k < comet.length(); k++)
      {
         char ch = comet.charAt(k);
         int pos = ch - 'A' + 1;
         cometVal *= pos;
      }
      int groupVal = 1;
      for(int k = 0; k < group.length(); k++)
      {
         char ch = group.charAt(k);
         int pos = ch - 'A' + 1;
         groupVal *= pos;
      }
      boolean go = cometVal % 47 == groupVal % 47;
      BufferedWriter outfile = new BufferedWriter(new FileWriter("ride.out"));
      if(go)
      {
         outfile.write("GO\n");
      }
      else
      {
         outfile.write("STAY\n");
      }
      outfile.close();
   }
}