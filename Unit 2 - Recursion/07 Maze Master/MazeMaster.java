//name: Arisa Chue   date: 10/16/2018
import java.util.*;
import java.io.*;
public class MazeMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename (no .txt): ");
      Maze m = new Maze(sc.next());
      // Maze m = new Maze();     //extension:  generate a random maze
      m.display();      //display maze
      System.out.println("Options: ");
      System.out.println("1: Mark all paths.");
      System.out.println("2: Mark all paths, and display the count of all STEPs.");
      System.out.println("3: Mark only the correct path.");
      System.out.println("4: Mark only the correct path. If no path exists, say so.");
      System.out.println("5: Mark only the correct path and display the count of STEPs.\n\tIf no path exists, say so.");
      System.out.print("Please make a selection: ");
      m.solve(sc.nextInt());
      m.display();      //display solved maze
   
   } 
}


class Maze
{
   //Constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char STEP = '*';
   //fields
   private char[][] maze;
   private int startRow, startCol;
  
  //constructors
   public Maze()  //extension:  generate a random maze
   {
   }
   public Maze(char[][] m)  //copy constructor
   {
      maze = m;
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         { 
            if(maze[r][c] == START)      //identify start
            {
               startRow = r;
               startCol = c;
            }
         }
      }
   } 
   public Maze(String filename)    
   {
       //use a try-catch block
       //use next(), not nextLine()
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(filename + ".txt"));
      }
      catch (Exception e)
      {
         System.out.println("File not found");
      }
      /* enter your code here */ //base case is not same
      int row = infile.nextInt();
      int col = infile.nextInt();
      infile.nextLine();
      maze = new char[row][col];
      for( int r = 0; r < row; r++)
      {
         String str = infile.next();
         for(int c = 0; c<col; c++)
         {
         
            maze[r][c] = str.charAt(c);
         }  
      }
   
      infile.close();
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         { 
            if(maze[r][c] == START)      //identify start
            {
               startRow = r;
               startCol = c;
            }
         }
      }
   
      
   }
   public char[][] getMaze()
   {
      return maze;
   }
   public void display()
   {
      if(maze==null) 
         return;
      for(int a = 0; a<maze.length; a++)
      {
         for(int b = 0; b<maze[0].length; b++)
         {
            System.out.print(maze[a][b]);
         }
         System.out.println("");
      }
      System.out.println("");
   }
   
   public void solve(int n)
   {
      switch(n)
      {
         case 1:
            {
               markAllPaths(startRow, startCol);
               break;
            }
         case 2:
            {
               int count = markAllPathsAndCountStars(startRow, startCol);
               System.out.println("Number of steps = " + count);
               break;
            }
         case 3:
            {
               markTheCorrectPath(startRow, startCol);
               break;
            }
         case 4:         //use mazeNoPath.txt 
            {
               if( !markTheCorrectPath(startRow, startCol) )
                  System.out.println("No path exists."); 
               break;
            }
         case 5:
            {
               if( !markCorrectPathAndCountSteps(startRow, startCol, 0) )
                  System.out.println("No path exists."); 
               break;
            }
         default:
            System.out.println("File not found");   
      }
   }
   
    /*  1  almost like AreaFill*/
   public void markAllPaths(int r, int c)
   {
      int row = maze.length;
      int col = maze[0].length;
      if(r >= row || c >= col || r < 0 || c<0)
         return;
      if(maze[r][c] == 'W' ||maze[r][c] == 'E')
         return;
      if(maze[r][c] == '*')
         return;
      if(maze[r][c] == 'S')
      {
         markAllPaths(r-1, c);
         markAllPaths(r+1, c);
         markAllPaths(r, c+1);
         markAllPaths(r, c-1);
      }
      else
      {
         maze[r][c] = '*';
         markAllPaths(r-1, c);
         markAllPaths(r+1, c);
         markAllPaths(r, c+1);
         markAllPaths(r, c-1);
         
      }
   
   }
   
    /*  2  like AreaFill's counting without a static variable */  
   public int markAllPathsAndCountStars(int r, int c)
   {
      int row = maze.length;
      int col = maze[0].length;
      if(r >= row || c >= col || r < 0 || c<0)
         return 0;
      if(maze[r][c] == 'W' ||maze[r][c] == 'E')
         return 0;
      if(maze[r][c] == '*')
         return 0;
      if(maze[r][c] == 'S')
      {
         return markAllPathsAndCountStars(r-1, c) +markAllPathsAndCountStars(r+1, c)+markAllPathsAndCountStars(r, c+1)+markAllPathsAndCountStars(r, c-1);
      }
      else
      {
         maze[r][c] = '*';
         return 1 +markAllPathsAndCountStars(r-1, c) + markAllPathsAndCountStars(r+1, c) + markAllPathsAndCountStars(r, c+1) + markAllPathsAndCountStars(r, c-1);
         
      }   
   }

   /*  3   recur until you find E, then mark the True path */
   public boolean markTheCorrectPath(int r, int c)
   {
      boolean start = false;
      if(r >= maze.length || c >= maze[0].length || r < 0 || c<0)
         return false;
      if(maze[r][c] == 'W')
         return false;
      if(maze[r][c] == 'E')
         return true;
      if(maze[r][c] == 'o')
         return false;
      // if(maze[r][c] == 'S')
      //          start = true;
      
      else
      {
         if(maze[r][c] == 'S')
            start = true;
      
         maze[r][c] = 'o';
               
         if(markTheCorrectPath(r+1, c) || markTheCorrectPath(r-1, c) || markTheCorrectPath(r, c-1) || markTheCorrectPath(r, c+1)) 
         {
            maze[r][c] = '*';
            if(start == true)
            {
               maze[r][c] = 'S';
            }
            return true;
         }
         else
         {
            maze[r][c] = '.';
            if(start == true)
            {
               maze[r][c] = 'S';
            }
            return false;
         }
         
      }
   //    if(start == true)
   //          {
   //             maze[r][c] = 'S';
   //          }
   
      //return true;
      
      
   }
   /*  4   Mark only the correct path. If no path exists, say so.
           Hint:  the method above returns the boolean that you need.  */
      

   /*  5  Mark only the correct path and display the count of STEPs.
          If no path exists, say so. */
   public boolean markCorrectPathAndCountSteps(int r, int c, int count)
   {
      boolean start = false;
      int cnt = 0;
      if(r >= maze.length || c >= maze[0].length || r < 0 || c<0)
         return false;
      if(maze[r][c] == 'W')
         return false;
      if(maze[r][c] == 'E')
      {
         System.out.println("Number of STEPs = " + count);
         return true;
      }
      if(maze[r][c] == 'o')
         return false;
      
      else
      {
         if(maze[r][c] == 'S')
            start = true;
      
         maze[r][c] = 'o';
         count++;
         // for(int row = 0; row < maze.length; row++)
      //          {
      //             for(int col = 0; col < maze[0].length; col++)
      //             {
      //                if(maze[row][col] == STEP)
      //                   cnt++;
      //             }
      //          }
            
         if(markCorrectPathAndCountSteps(r+1, c, count) || markCorrectPathAndCountSteps(r-1, c, count) || markCorrectPathAndCountSteps(r, c-1, count) || markCorrectPathAndCountSteps(r, c+1, count)) 
         {
            maze[r][c] = '*';
            //count++;
            if(start == true)
            {
               maze[r][c] = 'S';
            }
            
            return true;
         }
         else
         {
            maze[r][c] = '.';
            if(start == true)
            {
               maze[r][c] = 'S';
            }
            return false;
         }
         
      } 
              
   
   }
}
