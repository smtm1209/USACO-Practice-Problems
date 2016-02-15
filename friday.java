/*
ID: sarkistm1
PROG: friday
LANG: JAVA
*/
import java.util.*;
import java.io.*;
public class friday
{
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("friday.in"));
      int N = infile.nextInt();
      int[] thirteenths = new int[7];
      final int mon = 0; 
      int currDay = mon;
      for(int year = 0; year < N; year++)
      {
         boolean leapyear = !(year == 0) && !(year == 300) && !(year == 200) && year % 4 == 0;
         for(int month = 0; month < 12; month++)
         {
            int numdays;
            if(leapyear && month == 1)
            {
               numdays = 29;
            }
            else if(month == 1)
            {
               numdays = 28;
            }
            else if(month == 3 || month == 5 || month == 8 || month == 10)
            {
               numdays = 30;
            }
            else
            {
               numdays = 31;
            }
            for(int day = 0; day < numdays; day++)
            {
               if(day == 12)
               {
                  thirteenths[currDay]++;
               }
               currDay++;
               if(currDay == 7)
               {
                  currDay = mon;
               }
            }
         }
      }
      BufferedWriter outfile = new BufferedWriter(new FileWriter("friday.out"));
      outfile.write(thirteenths[5] + " " + thirteenths[6] + " " + thirteenths[0] + " " + thirteenths[1] + " " + thirteenths[2] + " " + thirteenths[3] + " " + thirteenths[4] + "\n");
      outfile.close();
   }
}