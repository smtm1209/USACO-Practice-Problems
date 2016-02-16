/*
ID: sarkistm1
PROG: castle
LANG: JAVA
*/
import java.io.*;
import java.util.*;
public class castle
{
   static Space[][] castle;
   static List<Room> rooms;
   static int N,M;
   public static void main(String[] args) throws Exception
   {
      double Start = System.nanoTime();
      Scanner infile = new Scanner(new File("castle.in"));
      BufferedWriter outfile = new BufferedWriter(new FileWriter("castle.out"));
      M = infile.nextInt();
      N = infile.nextInt();
      castle = new Space[N][M];
      rooms = new ArrayList<Room>(2500);
      
      //generate castle
      for(int n = 0; n < N; n++)
      {
         for(int m = 0; m < M; m++)
         {
            castle[n][m] = determine(infile.nextInt(),n,m);
         }
      }
      
      //generate rooms
      int roomNum = 1;
      for(int i = 0; i < N; i++)
      {
         for(int j = 0; j < M; j++)
         {
            if(castle[i][j].roomNum < 0)
            {
               formRoom(i,j,roomNum);
               roomNum++;
            }
         }
      }
      int numRooms = roomNum - 1;
      outfile.write(numRooms + "\n");
      int maxSize = 0;
      for(Room r : rooms)
         if(r.size() > maxSize)
            maxSize = r.size();
      outfile.write(maxSize + "\n");
      printArray();
      
      //breaking down the wall
      int maxRow = 0, maxCol = 0, maxSum = 0;
      String wall = "E";
      for(int r = 0; r < N; r++)
      {
         for(int c = 0; c < M; c++)
         {
            if(r > 0 && !castle[r][c].north) //north 
            {
               if(castle[r-1][c].roomNum != castle[r][c].roomNum) //verify not in same room
               {
                  int sum = rooms.get(castle[r-1][c].roomNum - 1).size() + rooms.get(castle[r][c].roomNum - 1).size();
                  if(sum > maxSum)
                  {
                     maxRow = r + 1;
                     maxCol = c + 1;
                     maxSum = sum;
                     wall = "N";
                  }
                  if(sum == maxSum)
                  {
                     if(c < maxCol - 1)
                     {
                        maxRow = r + 1;
                        maxCol = c + 1;
                        maxSum = sum;
                        wall = "N";
                     }
                     else if(r > maxRow - 1 && c == maxCol - 1)
                     {
                        maxRow = r + 1;
                        maxCol = c + 1;
                        maxSum = sum;
                        wall = "N";
                     }
                  }
               }
            }
            if(r < castle.length - 1 && !castle[r][c].south) //south
            {
               if(castle[r+1][c].roomNum != castle[r][c].roomNum)
               {
                  int sum = rooms.get(castle[r+1][c].roomNum - 1).size() + rooms.get(castle[r][c].roomNum - 1).size();
                  if(sum > maxSum)
                  {
                     maxRow = r + 1;
                     maxCol = c + 1;
                     maxSum = sum;
                     wall = "S";
                  }
                  if(sum == maxSum)
                  {
                     if(c < maxCol - 1)
                     {
                        maxRow = r + 1;
                        maxCol = c + 1;
                        maxSum = sum;
                        wall = "S";
                     }
                     else if(r > maxRow - 1 && c == maxCol - 1)
                     {
                        maxRow = r + 1;
                        maxCol = c + 1;
                        maxSum = sum;
                        wall = "S";
                     }
                  }
               }  
            }
            if(c < castle[r].length - 1 && !castle[r][c].east) //east
            {
               if(castle[r][c+1].roomNum != castle[r][c].roomNum)
               {
                  int sum = rooms.get(castle[r][c+1].roomNum - 1).size() + rooms.get(castle[r][c].roomNum - 1).size();
                  if(sum > maxSum)
                  {
                     maxRow = r + 1;
                     maxCol = c + 1;
                     maxSum = sum;
                     wall = "E";
                  }
                  if(sum == maxSum)
                  {
                     if(c < maxCol - 1)
                     {
                        maxRow = r + 1;
                        maxCol = c + 1;
                        maxSum = sum;
                        wall = "E";
                     }
                     else if(r > maxRow - 1 && c == maxCol - 1)
                     {
                        maxRow = r + 1;
                        maxCol = c + 1;
                        maxSum = sum;
                        wall = "E";
                     }
                  }
               }
            }
            if(c > 0 && !castle[r][c].west) //west
            {
               if(castle[r][c-1].roomNum != castle[r][c].roomNum)
               {
                  int sum = rooms.get(castle[r][c-1].roomNum - 1).size() + rooms.get(castle[r][c].roomNum - 1).size();
                  if(sum > maxSum)
                  {
                     maxRow = r + 1;
                     maxCol = c + 1;
                     maxSum = sum;
                     wall = "W";
                  }
                  if(sum == maxSum)
                  {
                     if(c < maxCol - 1)
                     {
                        maxRow = r + 1;
                        maxCol = c + 1;
                        maxSum = sum;
                        wall = "W";
                     }
                     else if(r > maxRow - 1 && c == maxCol - 1)
                     {
                        maxRow = r + 1;
                        maxCol = c + 1;
                        maxSum = sum;
                        wall = "W";
                     }
                  }
               }  
            }
         }
      }
      outfile.write(maxSum + "\n");
      outfile.write(maxRow + " " + maxCol + " " + wall + "\n");
      outfile.close();
      
      
      double End = System.nanoTime();
      //System.out.println((End-Start)/1E9 + "");
        
   }
   public static void printArray()
   {
      for(int i = 0; i < N; i++)
      {
         for(int j = 0; j < M; j++)
            System.out.print(castle[i][j].toString() + " ");
         System.out.print("\n");
      }
   }
   public static void formRoom(int row, int col, int roomNum)
   {
      Room r = new Room(roomNum);
      addSpace(r,row,col,roomNum);
      rooms.add(r);
   }
   private static void addSpace(Room room, int row, int col, int rn)
   {
      if(castle[row][col].roomNum > 0) //already visited
         return;
      castle[row][col].roomNum = rn;
      room.add(castle[row][col]);
      if(castle[row][col].north)
      {
         addSpace(room, row-1, col, rn);
      }
      if(castle[row][col].south)
      {
         addSpace(room, row+1, col, rn);
      }
      if(castle[row][col].east)
      {
         addSpace(room, row, col+1, rn);
      }
      if(castle[row][col].west)
      {
         addSpace(room, row, col-1, rn);
      }
   }
   public static Space determine(int val, int i, int j)
   {
      Space t = new Space(true,true,true,true,i,j);
      if(val >= 8)
      {
         t.south = false;
         val -= 8;
      }  
      if(val >= 4)
      {
         t.east = false;
         val -= 4;
      }
      if(val >= 2)
      {
         t.north = false;
         val -= 2;
      }
      if(val >= 1)
      {
         t.west = false;
         val -= 1;
      }
      return t;
   }
}

class Room
{
   List<Space> spaces;
   int roomNum;
   public Room(int rn)
   {
      roomNum = rn;
      spaces = new ArrayList<Space>(2500);
   }
   public int size()
   {
      return spaces.size();
   }
   public void add(Space s)
   {
      spaces.add(s);
   }
}

class Space
{
   int roomNum;
   boolean north, south, east, west;
   int row, col;
   public Space(boolean n, boolean s, boolean e, boolean w, int i, int j)
   {
      north = n; south = s; east = e; west = w;
      roomNum = -1;
      row = i; col = j;
   }
   public Space()
   {
      north = true; south = true; east = true; west = true;
      roomNum = -1;
   }
   public String toString()
   {
      /*
      String out = "";
      if(!north)
         out += "###";
      else
         out += "#-#";
      out += "\n";
      if(!west)
         out += "#";
      else
         out += "|";
      out += roomNum + "";
      if(!east)
         out += "#";
      else
         out += "|";
      out += "\n";
      if(!south)
         out += "###";
      else
         out += "#-#";
      return out;
      */
      return "" + roomNum;
   }
}

