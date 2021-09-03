// Name:   Arisa Chue
// Date: 4/22/2018
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 * Graph1 WarshallDriver,
 * and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   void addEdge(int source, int target);
   void removeEdge(int source, int target);
   boolean isEdge(int from, int to);
   String toString();   
   int edgeCount();
   List<Integer> getNeighbors(int source);
   //public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      
{
   boolean isEdge(String from, String to);
   Map<String, Integer> getVertices();     
   void readNames(String fileName) throws FileNotFoundException;
   void readGrid(String fileName) throws FileNotFoundException;
   void displayVertices();
   void allPairsReachability();  // Warshall's Algorithm
}

interface Floyd
{
   int getCost(int from, int to);
   int getCost(String from, String to);
   void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix 
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name-->index (for Warshall & Floyd)
     
   /*  enter your code here  */  
//    int getCost(int from, int to);
//    int getCost(String from, String to);
//    void allPairsWeighted();
   public AdjMat(int size)
   {
      grid = new int[size][size]; 
      vertices = new TreeMap<String, Integer>();
   }
   public void allPairsWeighted()
   {
      for(int r=0; r<grid.length; r++)
      {
         for(int c=0; c<grid[0].length; c++)
         {
            for(int v=0; v<grid[0].length; v++)
            {
               if(grid[c][r]+grid[r][v] < grid[c][v])
                  grid[c][v] = grid[c][r]+grid[r][v];
            }
         }
      }
   
   }
   public int getCost(String from, String to)
   {
      int f = (int)vertices.get(from);
      int t = (int)vertices.get(to);
      return grid[f][t];
   }
   public int getCost(int from, int to)
   {
      return grid[from][to];
   }
   public void allPairsReachability()
   {
      for(int r=0; r<grid.length; r++)
      {
         for(int c=0; c<grid[0].length; c++)
         {
            for(int v=0; v<grid[0].length; v++)
            {
               if(grid[c][r] != 0 & grid[r][v] != 0)
                  grid[c][v] = 1;
            }
         }
      }
   }
   public void readNames(String fileName) throws FileNotFoundException
   {
      Scanner infile = new Scanner(new File(fileName));
      int size = infile.nextInt();
      for(int x=0; x<size; x++)
      {
         String name = infile.next();
         vertices.put(name, x);
      }
   }
   public void displayVertices()
   {
      // String str = "";
   //       for(int r=0; r<grid.length; r++)
   //       {
   //          for(int c=0; c<grid[0].length; c++)
   //          {
   //             str+= " " + grid[r][c] + " ";
   //          }
   //          if(r < grid.length-1)
   //             str += "\n";
   //       }
   //       System.out.println(str);
      for(String n : vertices.keySet())
      {
         System.out.println(vertices.get(n) + "-" + n);
      }
      System.out.println();
   }
   public void readGrid(String fileName) throws FileNotFoundException
   {
      Scanner infile = new Scanner(new File(fileName));
      int size = infile.nextInt();
      for(int x=0; x<size; x++)
      {
         for(int y=0; y<size; y++)
         {
            int bin = infile.nextInt();
            grid[x][y] = bin;
         }
      }
   }
   public void addEdge(int source, int target)
   {
      grid[source][target] = 1;
   }
   public Map<String, Integer> getVertices()
   {
      return vertices;
   }
   public void removeEdge(int source, int target)
   {
      grid[source][target] = 0;
   }
   public boolean isEdge(String from, String to)
   {
      int f = (int)vertices.get(from);
      int t = (int)vertices.get(to);
      if(grid[f][t] != 0)
         return true;
      return false;
   }
   public boolean isEdge(int from, int to)
   {
      if(grid[from][to] != 0 & grid[from][to] != 9999)
         return true;
      return false;
   }
   public String toString()
   {
      String str = "";
      for(int r=0; r<grid.length; r++)
      {
         for(int c=0; c<grid[0].length; c++)
         {
            str+= " " + grid[r][c] + " ";
         }
         if(r < grid.length-1)
            str += "\n";
      }
      return str;
   }
   public int edgeCount()
   {
      int count = 0;
      for(int r=0; r<grid.length; r++)
      {
         for(int c=0; c<grid[0].length; c++)
         {
            if(grid[r][c] != 0 & grid[r][c] != 9999)
               count++;
         }
      }
      return count;
   }
   public List<Integer> getNeighbors(int source)
   {
      List<Integer> li = new ArrayList<Integer>();
      for(int c=0; c<grid[0].length; c++)
      {
         if(grid[source][c] == 1)
            li.add(c);
      }
      return li;
   }
}
