// Name: Arisa Chue
// Date: 10/1/2018

import java.util.*;
import java.io.*;

public class AreaFill
{
   private static char[][] grid = null;
   private static String filename = null;

   public static void main(String[] args) 
   {
      Scanner sc = new Scanner(System.in);
      while(true)  // what does this do?
      {
         System.out.print("Fill the Area of (-1 to exit): ");
         filename = sc.next();
         if(filename.equals("-1"))
         {
            sc.close();
            System.out.println("Good-bye");
            //System.exit(0);   
         }
         grid = read(filename);
         String theGrid = display(grid);
         System.out.println( theGrid );
         System.out.print( "1-Fill or 2-Fill-and-Count: ");
         int choice = sc.nextInt();
         switch(choice)
         {
            case 1:
               {
                  System.out.print("\nEnter ROW COL to fill from: ");
                  int row = sc.nextInt();
                  int col = sc.nextInt(); 
                  fill(grid, row, col, grid[row][col]);
                  System.out.println( display(grid) );
                  break;
               }
            case 2:
               {
                  System.out.print("\nEnter ROW COL to fill from: ");
                  int row = sc.nextInt();
                  int col = sc.nextInt();
                  int count = fillAndCount(grid, row, col, grid[row][col]);
                  System.out.println( display(grid) );
                  System.out.println("count = " + count);
                  System.out.println();
                  break;
               }
            default:
               System.out.print("\nTry again! ");
         }
      }
   }
   
   /**
    * Reads the contents of the file into a matrix.
    * Uses try-catch.
    * @param filename The string representing the filename.
    * @returns A 2D array of chars.
    */
   public static char[][] read(String filename)
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(filename));
      }
      catch (Exception e)
      {
         System.out.println("File not found");
         return null;
      }
      /* enter your code here */ //base case is not same
      int row = infile.nextInt();
      int col = infile.nextInt();
      infile.nextLine();
      char[][] array = new char[row][col];
      for( int r = 0; r < row; r++)
      {
         if(infile.hasNextLine())
         {
            String str = infile.nextLine();
            if(!str.equals(""))
            {
               for(int c = 0; c < col; c++)
               {
                  array[r][c] = str.charAt(c);
               }
            }
         
         }
      }
      return array;
      
   }
   
   /**
    * @param g A 2-D array of chars.
    * @returns A string representing the 2D array.
    */
   public static String display(char[][] g)
   {
      String str = "";
      
      for(int r = 0; r < g.length; r++)
      {
         for(int c = 0; c < g[r].length; c++)
         {
            str += g[r][c];
         }
         str += "\n";
      }
      
      return str;
   }
   
   /**
    * Fills part of the matrix with a different character.
    * @param g A 2D char array.
    * @param r An int representing the row of the cell to be filled.
    * @param c An int representing the column of the cell to be filled.
    * @param ch A char representing the replacement character.
    */
   public static void fill(char[][] g, int r, int c, char ch)
   {
      int row = g.length;
      int col = g[0].length;
      if(ch==('*'))
         return;
      if(r >= row || c >= col || r < 0 || c<0)
         return;
      if(g[r][c] == '*')
         return;
      if(g[r][c] != ch)
         return;
      else
      {
         g[r][c] = '*';
         fill(g, r-1, c, ch);
         fill(g, r+1, c, ch);
         fill(g, r, c+1, ch);
         fill(g, r, c-1, ch);
         
      }
         
      // else
   //       {
   //          //char[r][c] = '*';
   //          if( r == 0 && c == 0)
   //          {
   //             if(g[r+1][c]==(g[r][c]))
   //             {
   //                g[r+1][c] = '*';
   //                fill(g, r+1, c, ch);
   //             }
   //             if(g[r][c+1]==(g[r][c]))
   //             {
   //                g[r][c+1] = '*';
   //                fill(g, r, c+1, ch);
   //             }
   //          }
   //          else if(r==(row-1) && c==0)
   //          {
   //             if(g[r-1][c]==(g[r][c]))
   //             {
   //                g[r-1][c] = '*';
   //                fill(g, r-1, c, ch);
   //             }
   //             if(g[r][c+1]==(g[r][c]))
   //             {
   //                g[r][c+1] = '*';
   //                fill(g, r, c+1, ch);
   //             }
   // 
   //          }
   //          else if(r==0 && c==(col-1))
   //          {
   //             if(g[r+1][c]==(g[r][c]))
   //             {
   //                g[r+1][c] = '*';
   //                fill(g, r+1, c, ch);
   //             }
   //             if(g[r][c-1]==(g[r][c]))
   //             {
   //                g[r][c-1] = '*';
   //                fill(g, r, c-1, ch);
   //             }
   // 
   //          }
   //          else if(r==(row-1) && c==(col-1))
   //          {
   //             if(g[r-1][c]==(g[r][c]))
   //             {
   //                g[r-1][c] = '*';
   //                fill(g, r-1, c, ch);
   //             }
   //             if(g[r][c-1]==(g[r][c]))
   //             {
   //                g[r][c-1] = '*';
   //                fill(g, r, c-1, ch);
   //             }
   // 
   //          }
   // 
   //          else if(r> 0 && c== 0)
   //          {
   //             if(g[r-1][c]==(g[r][c]))
   //             {
   //                g[r-1][c] = '*';
   //                fill(g, r-1, c, ch);
   //             }
   //             if(g[r][c+1]==(g[r][c]))
   //             {
   //                g[r][c+1] = '*';
   //                fill(g, r, c+1, ch);
   //             }
   //             if(g[r+1][c]==(g[r][c]))
   //             {
   //                g[r+1][c] = '*';
   //                fill(g, r+1, c, ch);
   //             }
   //          }
   //          else if(r==0 && c> 0)
   //          {
   //             if(g[r][c-1]==(g[r][c]))
   //             {
   //                g[r][c-1] = '*';
   //                fill(g, r, c-1, ch);
   //             }
   //             if(g[r][c+1]==(g[r][c]))
   //             {
   //                g[r][c+1] = '*';
   //                fill(g, r, c+1, ch);
   //             }
   //             if(g[r+1][c]==(g[r][c]))
   //             {
   //                g[r+1][c] = '*';
   //                fill(g, r+1, c, ch);
   //             }
   //          }
   //           else if(r==(row-1) && c> 0)
   //          {
   //             if(g[r][c-1]==(g[r][c]))
   //             {
   //                g[r][c-1] = '*';
   //                fill(g, r, c-1, ch);
   //             }
   //             if(g[r][c+1]==(g[r][c]))
   //             {
   //                g[r][c+1] = '*';
   //                fill(g, r, c+1, ch);
   //             }
   //             if(g[r-1][c]==(g[r][c]))
   //             {
   //                g[r-1][c] = '*';
   //                fill(g, r-1, c, ch);
   //             }
   //          }
   //          else if(r>0 && c==(col-1))
   //          {
   //             if(g[r][c-1]==(g[r][c]))
   //             {
   //                g[r][c-1] = '*';
   //                fill(g, r, c-1, ch);
   //             }
   //             if(g[r-1][c]==(g[r][c]))
   //             {
   //                g[r-1][c] = '*';
   //                fill(g, r-1, c, ch);
   //             }
   //             if(g[r+1][c]==(g[r][c]))
   //             {
   //                g[r+1][c] = '*';
   //                fill(g, r+1, c, ch);
   //             }
   //          }
   //          else
   //          {
   //       }
   //         g[r][c] = '*';
      
         
      //return;
   }
   
   /**
    * Fills part of the matrix with a different character.
    * Counts as you go.  Does not use a static variable.
    * @param g A 2D char array.
    * @param r An int representing the row of the cell to be filled.
    * @param c An int representing the column of the cell to be filled.
    * @param ch A char representing the replacement character.
    * @return An int representing the number of characters that were replaced.
    */
   public static int fillAndCount(char[][] g, int r, int c, char ch)
   {
      int row = g.length;
      int col = g[0].length;
      if(ch==('*'))
         return 0;
      if(r >= row || c >= col || r < 0 || c<0)
         return 0;
      if(g[r][c] == '*')
         return 0;
      if(g[r][c] != ch)
         return 0;
      else
      {
         g[r][c] = '*';
         return 1 +fillAndCount(g, r-1, c, ch) + fillAndCount(g, r+1, c, ch) + fillAndCount(g, r, c+1, ch) + fillAndCount(g, r, c-1, ch);
            // return 1+ fillAndCount(g, r+1, c, ch);
      //             return 1 +fillAndCount(g, r, c+1, ch);
      //             return 1+fillAndCount(g, r, c-1, ch);
         
      }
   
      //return 0; //never reached
   }
}