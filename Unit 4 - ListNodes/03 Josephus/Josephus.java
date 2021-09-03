// Name: Arisa Chue
// Date: 11/20/2018

import java.util.*;
import java.io.*;

public class Josephus
{
   private static String WINNER = "Josephus";
   
   public static void main(String[] args) throws FileNotFoundException
   {
      /* run numberCircle first with J_numbers.txt  */
      Scanner sc = new Scanner(System.in);
      System.out.print("How many names (2-20)? ");
      int n = Integer.parseInt(sc.next());
      System.out.print("How many names to count off each time?"  );
      int countOff = Integer.parseInt(sc.next());
      ListNode winningPos = numberCircle(n, countOff, "J_numbers.txt");
      System.out.println(winningPos.getValue() + " wins!");  
     
      /* run josephusCircle next with J_names.txt  */
      System.out.println("\n ****  Now start all over. **** \n");
      System.out.print("Where should "+WINNER+" stand?  ");
      int winPos = Integer.parseInt(sc.next());        
      ListNode theWinner = josephusCircle(n, countOff, "J_names.txt", winPos);
      System.out.println(theWinner.getValue() + " wins!");  
   }
   
   public static ListNode numberCircle(int n, int countOff, String filename) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      p = countingOff(p, countOff, n);
      return p;
   }
   
   public static ListNode josephusCircle(int n, int countOff, String filename, int winPos) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      replaceAt(p, WINNER, winPos);
      p = countingOff(p, countOff, n);
      return p;
   }

   /* reads the names, calls insert(), builds the linked list.
	 */
   public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException
   {
      Scanner infile = new Scanner(f);
      ListNode p = null;
      for(int x = 0; x< n; x++)
      {
         p = insert(p, infile.next());
      }
      return p;
         
   }
   
   /* helper method to build the list.  Creates the node, then
    * inserts it in the circular linked list.
	 */
   public static ListNode insert(ListNode p, Object obj)
   {
      if(p == null)
      {
         p = new ListNode(obj, p);
         p.setNext(p);
         return p;
      }
      ListNode a = p;
      ListNode f = p;
      if(a.getNext() != null)
      {
         while(f.getValue() != a.getNext().getValue())
         {
            a = a.getNext();
         }
      }
       
      ListNode d = new ListNode(obj, p);
      a.setNext(d);
      return p;
   }
   
   /* Runs a Josephus game, counting off and removing each name. Prints after each removal.
      Ends with one remaining name, who is the winner. 
	 */
   public static ListNode countingOff(ListNode p, int count, int n)
   {
      print(p);
      for(int x = 1; x<n; x++)
      {
         
         p = remove(p, count);
         print(p);
      
      }
      return p;
   
   }
   
   /* removes the node after counting off count-1 nodes.
	 */
   public static ListNode remove(ListNode p, int count)
   {
      ListNode a = p;
      if(count == 1)
      {
         while(p.getValue() != a.getNext().getValue())
         {
            a = a.getNext();
         }
         a.setNext(p.getNext());
         p = p.getNext();
         return p;
      }
      
           
      for(int x = 1; x<count-1; x++)
      {
         p = p.getNext();
         a = a.getNext();
      }
      a.setNext(a.getNext().getNext());
      //p.setNext(null);
      p = p.getNext();
      return p;
   }
   
   /* prints the circular linked list.
	 */
   public static void print(ListNode p)
   {
      if(p == null)
         return;
      //ListNode a = p;
      ListNode f = p;
      if(p.getNext() != null)
      {
         while(f.getValue() != p.getNext().getValue())
         {
            System.out.print(p.getValue() + " ");
            p = p.getNext();
         }
      }
      
      System.out.println(p.getValue() + " ");
   
   }
	
   /* replaces the value (the string) at the winning node.
	 */
   public static void replaceAt(ListNode p, Object obj, int pos)
   {
      ListNode prev = p;
      ListNode last = p;
      if(pos == 1)
      {
         p.setValue(obj);
         //p = new ListNode(obj, p.getNext());
        //  while(last.getValue() != prev.getNext().getValue())
      //          {
      //            prev = prev.getNext();
      //          }
      //          last = last.getNext();
      //         p = new ListNode(obj, last);
      //          prev.setNext(p);
        // p = prev;
       //p= p.getNext();
       //prev.setNext(null);
         return;
      }
      
      for(int x =1; x<pos-1; x++)
      {
         prev = prev.getNext();
         last = last.getNext();
      }
      last = last.getNext();
      last = last.getNext();
     //   if(pos == 1)
   //       {
   //          last = last.getNext();
   //       }
      //last = last.getNext();
      ListNode nn = new ListNode(obj, last);
      prev.setNext(nn);
      
   }
}