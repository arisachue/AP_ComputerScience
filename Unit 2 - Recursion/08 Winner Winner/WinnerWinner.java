// Name: Arisa Chue
// Date: 10/18/2018
public class WinnerWinner
{
   public static void main(String[] args)
   {
      Board b = null;
      b = new Board(3,4,"W-S-------X-"); 
      b.display();
      System.out.println("Shortest path is " + b.win());  //2
      
      b = new Board(4,3,"S-W-----X-W-"); 
      b.display();
      System.out.println("Shortest path is " + b.win());  //4
      
      b = new Board(3,4,"X-WS--W-W---"); 
      b.display();
      System.out.println("Shortest path is " + b.win());  //7
      
      b = new Board(3,5,"W--WW-X----SWWW"); 
      b.display();
      System.out.println("Shortest path is " + b.win());  //1
      
      b = new Board(3,3,"-SW-W-W-X");     //no path exists
      b.display();
      System.out.println("Shortest path is " + b.win());  //-1
      
      b = new Board(5,7,"-W------W-W-WX--S----W----W-W--W---");     //Example Board 1
      b.display();
      System.out.println("Shortest path is " + b.win());  //5
      
      b = new Board(4,4,"-WX--W-W-WW-S---");     //Example Board -1
      b.display();
      System.out.println("Shortest path is " + b.win());  //5
   
      //what other test cases should you test?
      
   }
}

class Board
{
   private char[][] board;  
   private int maxPath;

   public Board(int rows, int cols, String line)  
   {
      maxPath = rows * cols;
      board = new char[rows][cols];
      for(int r = 0; r<rows; r++)
      {
         String str = line.substring(cols*r, (cols*r) +cols);
         for(int c = 0; c<cols; c++)
         {
            board[r][c] = str.charAt(c);
         }
      }
   
      // for(int r = 0; r<rows; r++)
//       {
//          for(int c=0; c<cols; c++)
//          {
//             for(int x = 0; x<line.length(); x++)
//             {
//                board[r][c] = line.charAt(x);
//             }
//          }
//       }
   }

	/**	returns the length of the longest possible path in the Board   */
   public int getMaxPath()		
   {  
      return maxPath; 
   }	
   
   public void display()
   {
      if(board==null) 
         return;
      System.out.println();
      for(int a = 0; a<board.length; a++)
      {
         for(int b = 0; b<board[0].length; b++)
         {
            System.out.print(board[a][b]);
         }
         System.out.println();
      }
   }

   /**	
    *  calculates and returns the shortest path from S to X, if it exists   
    *  @param r is the row of "S"
    *  @param c is the column of "S"
    */
   public int check(int r, int c)
   {	
      if(r > (board.length-1)||r<0||c<0||c>(board[0].length-1))
         return maxPath;
      if(board[r][c] == 'W' ||board[r][c] == 'o'  )
         return maxPath;
      if(board[r][c] == 'X')
         return 1;
      
      else
      {
         board[r][c] = 'o';
         
               }
            
      int d = 1+ check(r+1, c);

      int u = 1+check(r-1,c);

      int ri = 1+check(r, c+1);

      int l = 1 +check(r, c-1);

      if(board[r][c] == 'o')
         board[r][c] = '-';
      return Math.min(Math.min(d, u), Math.min(ri,l));
      
   }	
          
   /**	
    *  precondition:  S and X exist in board
    *  postcondition:  returns either the length of the path
    *                  from S to X, or -1, if no path exists.    
    */
   public int win()
   {
      int x = 0;
      int y = 0;
      for(int r = 0; r<board.length; r++)
      {
         for(int c=0; c<board[0].length; c++)
         {
            if(board[r][c] == 'S')
            {
               x = r;
               y = c;
            }
         }
      }
      int path = check(x, y);
      if(path < maxPath)
         return path-1;
      else
         return -1;
               
   }
}





/************************ output ************
 W-S-
 ----
 --X-
 Shortest path is 2
 
 S-W
 ---
 --X
 -W-
 Shortest path is 4
 
 X-WS
 --W-
 W---
 Shortest path is 7
 
 W--WW
 -X---
 -SWWW
 Shortest path is 1
 
 -SW
 -W-
 W-X
 Shortest path is -1
 
 -W-----
 -W-W-WX
 --S----
 W----W-
 W--W---
 Shortest path is 5
 
 -WX-
 -W-W
 -WW-
 S---
 Shortest path is -1 
 ***************************************/