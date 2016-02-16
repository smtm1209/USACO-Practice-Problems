/*
ID: sarkistm1
PROB: namenum
LANG: JAVA
*/
import java.util.*;
import java.io.*;
public class namenum
{
   static List<String> posNames;
   static Set<String> dict;
   @SuppressWarnings("unchecked")
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("namenum.in"));
      List<String> dictt = new ArrayList<String>(5000);
      Scanner dictin = new Scanner(new File("dict.txt"));
      while(dictin.hasNext())
      {
         dictt.add(dictin.next());
      }
      dict = new HashSet<String>(dictt);
      posNames = new LinkedList<String>();
      BufferedWriter outfile = new BufferedWriter(new FileWriter("namenum.out"));
      try{
         String nums = infile.next();
         recur(nums.toCharArray(), 0, "");
         if(posNames.size() == 0)
         {
            outfile.write("NONE\n");
         }
         else
         {
            for(String name : posNames)
            {
               outfile.write(name+"\n");
            }
         }
      }catch(Exception e){
         outfile.write("NONE\n");
      }
      outfile.close();
      
   }
   static void recur(char[] nums, int currIndex, String currStr)
   {
      if(nums.length == 0)
         return;
      if(currIndex == nums.length - 1)
      {
         if(nums[currIndex] == '2')
         {
            if(attempt(currStr + "A"))
               posNames.add(currStr + "A");
            if(attempt(currStr + "B"))
               posNames.add(currStr + "B");
            if(attempt(currStr + "C"))
               posNames.add(currStr + "C");
         }
         if(nums[currIndex] == '3')
         {
            if(attempt(currStr + "D"))
               posNames.add(currStr + "D");
            if(attempt(currStr + "E"))
               posNames.add(currStr + "E");
            if(attempt(currStr + "F"))
               posNames.add(currStr + "F");
         }
         if(nums[currIndex] == '4')
         {
            if(attempt(currStr + "G"))
               posNames.add(currStr + "G");
            if(attempt(currStr + "H"))
               posNames.add(currStr + "H");
            if(attempt(currStr + "I"))
               posNames.add(currStr + "I");
         }
         if(nums[currIndex] == '5')
         {
            if(attempt(currStr + "J"))
               posNames.add(currStr + "J");
            if(attempt(currStr + "K"))
               posNames.add(currStr + "K");
            if(attempt(currStr + "L"))
               posNames.add(currStr + "L");
         }
         if(nums[currIndex] == '6')
         {
            if(attempt(currStr + "M"))
               posNames.add(currStr + "M");
            if(attempt(currStr + "N"))
               posNames.add(currStr + "N");
            if(attempt(currStr + "O"))
               posNames.add(currStr + "O");
         }
         if(nums[currIndex] == '7')
         {
            if(attempt(currStr + "P"))
               posNames.add(currStr + "P");
            if(attempt(currStr + "R"))
               posNames.add(currStr + "R");
            if(attempt(currStr + "S"))
               posNames.add(currStr + "S");
         }
         if(nums[currIndex] == '8')
         {
            if(attempt(currStr + "T"))
               posNames.add(currStr + "T");
            if(attempt(currStr + "U"))
               posNames.add(currStr + "U");
            if(attempt(currStr + "V"))
               posNames.add(currStr + "V");
         }
         if(nums[currIndex] == '9')
         {
            if(attempt(currStr + "W"))
               posNames.add(currStr + "W");
            if(attempt(currStr + "X"))
               posNames.add(currStr + "X");
            if(attempt(currStr + "Y"))
               posNames.add(currStr + "Y");
         }
      }
      else
      {
         if(nums[currIndex] == '2')
         {
            recur(nums, currIndex+1, currStr + "A");
            recur(nums, currIndex+1, currStr + "B");
            recur(nums, currIndex+1, currStr + "C");
         }
         if(nums[currIndex] == '3')
         {
            recur(nums, currIndex+1, currStr + "D");
            recur(nums, currIndex+1, currStr + "E");
            recur(nums, currIndex+1, currStr + "F");
         }
         if(nums[currIndex] == '4')
         {
            recur(nums, currIndex+1, currStr + "G");
            recur(nums, currIndex+1, currStr + "H");
            recur(nums, currIndex+1, currStr + "I");
         }
         if(nums[currIndex] == '5')
         {
            recur(nums, currIndex+1, currStr + "J");
            recur(nums, currIndex+1, currStr + "K");
            recur(nums, currIndex+1, currStr + "L");
         }
         if(nums[currIndex] == '6')
         {
            recur(nums, currIndex+1, currStr + "M");
            recur(nums, currIndex+1, currStr + "N");
            recur(nums, currIndex+1, currStr + "O");
         }
         if(nums[currIndex] == '7')
         {
            recur(nums, currIndex+1, currStr + "P");
            recur(nums, currIndex+1, currStr + "R");
            recur(nums, currIndex+1, currStr + "S");
         }
         if(nums[currIndex] == '8')
         {
            recur(nums, currIndex+1, currStr + "T");
            recur(nums, currIndex+1, currStr + "U");
            recur(nums, currIndex+1, currStr + "V");
         }
         if(nums[currIndex] == '9')
         {
            recur(nums, currIndex+1, currStr + "W");
            recur(nums, currIndex+1, currStr + "X");
            recur(nums, currIndex+1, currStr + "Y");
         }

      }
   }
   static boolean attempt(String s)
   {
      return dict.contains(s);
   }
}