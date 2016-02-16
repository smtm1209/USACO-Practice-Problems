/*
ID: sarkistm1
PROG: gift1
LANG: JAVA
*/
import java.util.*;
import java.io.*;
public class gift1
{
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner(new File("gift1.in"));
      int N = infile.nextInt();
      List<Person> people = new ArrayList<Person>(N);
      for(int k = 0; k < N; k++)
      {
         people.add(new Person(infile.next()));
      }
      while(infile.hasNext())
      {
         Person curr = people.get(people.indexOf(new Person(infile.next())));
         try{
            int funds = infile.nextInt();
            int numpeople = infile.nextInt();
            if(!(funds == 0 || numpeople == 0))
            {
               int gifts = funds/numpeople;
               curr.changeFunds(-1 * gifts * numpeople);
               for(int k = 0; k < numpeople; k++)
               {
                  people.get(people.indexOf(new Person(infile.next()))).changeFunds(gifts);
               }
            }
         }
         catch(Exception e){
            break;
         }
      }
      BufferedWriter outfile = new BufferedWriter(new FileWriter("gift1.out"));
      for(Person p : people)
      {
         outfile.write(p.myName + " " + p.myMoney + "\n");
      }
      outfile.close();
   }
}
class Person
{
   String myName;
   int myMoney;
   public Person(String name)
   {
      myName = name;
      myMoney = 0;
   }
   public boolean equals(Object o)
   {
      Person p = (Person) o;
      return myName.equals(p.myName);
   }
   public void changeFunds(int change)
   {
      myMoney += change;
   }
   public int getFunds()
   {
      return myMoney;
   }
   public String toString()
   {
      return myName + " w/ $" + myMoney;
   }
}