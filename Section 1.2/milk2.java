/*
ID: sarkistm1
PROG: milk2
LANG: JAVA
*/
import java.util.*;
import java.io.*;
public class milk2
{
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("milk2.in"));
      int N = infile.nextInt();
      List<MilkTime> milktimes = new ArrayList<MilkTime>(N);
      int End = 0;
      int Start = 1000000;
      for(int k = 0; k < N; k++)
      {
         int s = infile.nextInt();
         int e = infile.nextInt();
         if(s<Start)
            Start = s;
         if(e>End)
            End = e;
         milktimes.add(new MilkTime(s,e));
      }
      boolean[] arr = new boolean[End - Start];
      for(int k = 0; k < N; k++)
      {
         for(int i = milktimes.get(k).start - Start; i < milktimes.get(k).end - Start; i++)
         {
            arr[i] = true;
         }
      }
      int ldm = 0, ldnm = 0;
      for(int i = 0; i < arr.length; i++)
      {
         int cldm = 0, cldnm = 0;
         while(i < arr.length && arr[i])
         {
            i++;
            cldm++;
         }
         if(cldm>ldm)
            ldm = cldm;
         while(i < arr.length && !arr[i])
         {
            i++;
            cldnm++;
         }
         if(cldnm>ldnm)
            ldnm = cldnm;
      }
      BufferedWriter outfile = new BufferedWriter(new FileWriter("milk2.out"));
      outfile.write(ldm + " " + ldnm + "\n");
      outfile.close();
   }
}

class MilkTime implements Comparable<MilkTime>
{
   int start, end;
   public MilkTime(int s, int e)
   {
      start = s;
      end = e;
   }
   public boolean equals(Object o)
   {
      MilkTime m = (MilkTime) o;
      return this.start == m.start && this.end == m.end;
   }
   public int compareTo(MilkTime m)
   {
      return this.start - m.start;
   }  
   public String toString()
   {
      return start + ", " + end;
   }
}