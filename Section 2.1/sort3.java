/*
ID: sarkistm1
PROG: sort3
LANG: JAVA
*/
import java.io.*;
import java.util.*;
public class sort3
{
   static int numOps = 0, num3s = 0, num2s = 0, num1s = 0;
   static int[] all, ones, twos, threes;
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("sort3.in"));
      BufferedWriter outfile = new BufferedWriter(new FileWriter("sort3.out"));
      int ALL = infile.nextInt();
      all = new int[ALL];
      for(int i = 0; i < ALL; i++) {
         all[i] = infile.nextInt();
      }
      for(int i : all) {
         if(i == 1)
            num1s++;
         if(i == 2)
            num2s++;
         if(i == 3)
            num3s++;
      }
      ones = new int[num1s]; twos = new int[num2s]; threes = new int[num3s];
      int i = 0;
      for(; i < num1s; i++) {ones[i] = all[i];}
      for(int k = 0; k < num2s; k++, i++) {twos[k] = all[i];}
      for(int k = 0; k < num3s; k++, i++) {threes[k] = all[i];}
      Arrays.sort(ones); Arrays.sort(twos); Arrays.sort(threes); //needed for Bin-search
      /* facilitate two-way switcheroo */
      int indexOf1in2 = indexOf(twos,1), indexOf2in1 = indexOf(ones,2);
         
      while(indexOf1in2 >= 0 && indexOf2in1 >= 0) { //2[..1..] -> 1[..1..], 1[..2..] -> 2[..2..]
         numOps++;
         int t1 = twos[indexOf1in2], t2 = ones[indexOf2in1];
         twos[indexOf1in2] = t2;
         ones[indexOf2in1] = t1;
         Arrays.sort(ones); Arrays.sort(twos);
         indexOf1in2 = indexOf(twos,1);
         indexOf2in1 = indexOf(ones,2);
      }
      int indexOf3in1 = indexOf(ones,3), indexOf1in3 = indexOf(threes,1);
      while(indexOf1in3 >= 0 && indexOf3in1 >= 0) { //1[..3..] -> 3[..3..], 3[..1..] -> 1[..1..]
         numOps++;
         int t1 = threes[indexOf1in3], t2 = ones[indexOf3in1];
         threes[indexOf1in3] = t2;
         ones[indexOf3in1] = t1;
         Arrays.sort(ones); Arrays.sort(threes);
         indexOf1in3 = indexOf(threes,1);
         indexOf3in1 = indexOf(ones,3);
      }
      int indexOf2in3 = indexOf(threes,2), indexOf3in2 = indexOf(twos, 3);
      while(indexOf2in3 >= 0 && indexOf3in2 >= 0) { //2[..3..] -> 3[..3..], 3[..2..] -> 2[..2..]
         numOps++;
         int t1 = threes[indexOf2in3], t2 = twos[indexOf3in2];
         threes[indexOf2in3] = t2;
         twos[indexOf3in2] = t1;
         Arrays.sort(twos); Arrays.sort(threes);
         indexOf2in3 = indexOf(threes,2);
         indexOf3in2 = indexOf(twos,3);
      }
      Arrays.sort(ones); Arrays.sort(twos); Arrays.sort(threes); //needed for Bin-search
      /* start of less-greedy portion */
      
      //finish threes
      indexOf3in1 = indexOf(ones,3); indexOf2in3 = indexOf(threes,2);
      while(indexOf3in1 >= 0 && indexOf2in3 >= 0) { //1[..3..] <-> 3[..2..]
         numOps++;
         int t1 = ones[indexOf3in1], t2 = threes[indexOf2in3];
         threes[indexOf2in3] = t1;
         ones[indexOf3in1] = t2;
         Arrays.sort(threes); Arrays.sort(ones);
         indexOf3in1 = indexOf(ones,3); indexOf2in3 = indexOf(threes,2);
      }
      indexOf3in2 = indexOf(twos,3); indexOf1in3 = indexOf(threes,1);
      while(indexOf3in2 >= 0 && indexOf1in3 >= 0) { //2[..3..] <-> 3[..1..]
         numOps++;
         int t1 = twos[indexOf3in2], t2 = threes[indexOf1in3];
         threes[indexOf1in3] = t1;
         twos[indexOf3in2] = t2;
         Arrays.sort(twos); Arrays.sort(threes);
         indexOf3in2 = indexOf(twos,3); indexOf1in3 = indexOf(threes,1);
      }
      //finish twos
      indexOf2in1 = indexOf(ones,2);
      while(indexOf2in1 >= 0) { //1[..2..] <-> 2[..1..]
         numOps++;
         int t1 = ones[indexOf2in1], ind = indexOf(twos,1);
         twos[ind] = t1;
         ones[indexOf2in1] = 1;
         Arrays.sort(ones); Arrays.sort(twos);
         indexOf2in1 = indexOf(ones,2);
      }
      
         
      
      //done!
      outfile.write(numOps + "\n");
      outfile.close();
      
   }
   public static int indexOf(int[] arr, int key)
   {
      return Arrays.binarySearch(arr,key);
   }
   public static boolean done(int[] arr, int val)
   {
      for(int i : arr)
         if(i != val)
            return false;
      return true;
   }
}