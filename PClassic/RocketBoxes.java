import java.io.*;
import java.util.*;
public class RocketBoxes
{
   public static void main(String[] args) throws Exception
   {
      double start = System.nanoTime();
      int n = 20;
      ArrayList<ArrayList<Integer>> lastAnswer = new ArrayList<ArrayList<Integer>>();
      {
         ArrayList<Integer> k = new ArrayList<Integer>();
         k.add(2); k.add(2);
         lastAnswer.add(k);
      }
      {
         ArrayList<Integer> k = new ArrayList<Integer>();
         k.add(1); k.add(1);
         lastAnswer.add(k);
      }
      while(lastAnswer.get(0).size()!=n)
      {
         ArrayList<ArrayList<Integer>> currAnswer = new ArrayList<ArrayList<Integer>>();
         Set<ArrayList<Integer>> check = new HashSet<ArrayList<Integer>>();
         for( ArrayList<Integer> a : lastAnswer )
         {
            int[] bottom = new int[lastAnswer.get(0).size()+1];
            bottom[0] = 1;
            int[] bottom2 = new int[lastAnswer.get(0).size()+1];
            bottom2[0] = 2;
            boolean[] second = new boolean[lastAnswer.get(0).size()];
            for(int b=0; b<a.size(); b++)
               second[b] = a.get(b)%2==0;
            for(int b=0; b<second.length; b++)
            {
               if(second[b])
               {
                  if(bottom[b]==1)
                     bottom[b+1]=1;
                  if(bottom[b]==2)
                     bottom[b+1]=2;
                  if(bottom2[b]==1)
                     bottom2[b+1]=1;
                  if(bottom2[b]==2)
                     bottom2[b+1]=2;
               }
               else
               {
                  if(bottom[b]==1)
                     bottom[b+1]=2;
                  if(bottom[b]==2)
                     bottom[b+1]=1;
                  if(bottom2[b]==1)
                     bottom2[b+1]=2;
                  if(bottom2[b]==2)
                     bottom2[b+1]=1;
               }
            }
            ArrayList<Integer> one = new ArrayList<Integer>(21);
            ArrayList<Integer> two = new ArrayList<Integer>(21);
            for(int b=0; b<bottom.length; b++)
            {
               one.add(bottom[b]);
               two.add(bottom2[b]);
            }
            if(!check.contains(one))
            {
               currAnswer.add(one);
               check.add(one);
            }
            if(!check.contains(two))
            {
               currAnswer.add(two);
               check.add(two);
            }
         }
         lastAnswer = currAnswer;
      }
      int sum = 0;
      for( ArrayList<Integer> a : lastAnswer )
      {
         int count = 0;
         for(int b=0; b<a.size(); b++)
            if(a.get(b)==2)
               count++;
         sum += Math.pow(2, count);
      }
      double end = System.nanoTime();
      System.out.println(sum);
      System.out.println("in " + (end-start)/1E9 + " seconds");
   }
}