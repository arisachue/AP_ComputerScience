// Name: Arisa Chue
// Date: 11/13/2018

/*****************************************
Demonstrates many ways to reverse a list made of ListNodes.
******************************************/
public class ListLabReverse
{
   public static void main(String[] args)
   {
      ListNode head = new ListNode("hello", null);
      head = new ListNode("foo", head);
      head = new ListNode("boo", head);
      head = new ListNode("nonsense", head);
      head = new ListNode("computer",
         new ListNode("science",
         new ListNode("java",
         new ListNode("coffee", head))));
         
      System.out.print("print the original: \t\t\t\t");
      print(head);
         
      System.out.print("recur and print: \t\t\t\t\t");
      recurAndPrint(head);
      
      System.out.println();
      System.out.print("original is unchanged: \t\t\t\t");
      print(head);
      
      System.out.print("reverse by building a new list: \t");
      head = reverseBuildNew(head);
      print(head);      
      	
      System.out.print("iterate with 3 pointers:\t\t\t");
      head = iterateThreePointers(head);
      print(head);
      	
      System.out.print("recur with 2 pointers: \t\t\t\t");
      head = recurTwoPointers(null, head);
      print(head);
              
      System.out.print("recur with pointers and append: \t");
      head = recurPointersAppend(head);
      print(head);
      	
      System.out.print("Mind Bender reverse:\t\t\t\t\t");
      head = mindBender(head);
      print(head);
   }
   
   public static void print(ListNode head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   }
   
	/*********************************************
	1. This approach doesn't actually reverse the list.  It only prints
	the list in reverse order.  recurAndPrint() prints the square 
	brackets and calls helper().  helper() is recursive.
	********************************************************/
   public static void recurAndPrint(ListNode h)
   {
      System.out.print("[");
      ListNode p = h;
     //  while(p != null)
   //       {
   //       
      helper(p);
     //  if(p != null)
   //          System.out.print(", ");
   //      }
      System.out.print("]");
   
   }
   
   private static void helper(ListNode p)
   {
      if(p.getNext() == null)
      {
         System.out.print(p.getValue());
         return;
      }
      helper(p.getNext());
      System.out.print(", "+ p.getValue() );
   
   }
   
   /*********************************************
	2. This iterative method (for or while) produces a copy of the 
	reversed list.	 For each node going forward, make a new node and 
	link it to the list.	The list will naturally be in reverse. 
	***********************************************************/
   public static ListNode reverseBuildNew(ListNode head)
   {
      if(head == null)
         return null;
      ListNode p = head;
      ListNode first = new ListNode(p.getValue(), null);
      while(p.getNext() != null)
      {
         p = p.getNext();
         first = new ListNode(p.getValue(), first);
      }
      return first;
      
   }

   /*******************************************
	3a. This iterative method (while) uses 3 pointers to reverse 
	the list in place.   The two local pointers are called
	prev and next.
   ********************************************************/
   public static ListNode iterateThreePointers(ListNode head)
   {
      if(head == null) 
         return null;
      ListNode prev = null, next = head.getNext();
      /*  enter your code here   */
      while(next!= null)
      {
         head.setNext(prev);
         prev = head;
         head = next;
        //  if(next.getNext()!= null)
      //          {
       next = next.getNext();
       //  }
      }
      head.setNext(prev);
      prev =  head;  
   
      return prev;
   }
   	
	/**************************************************
	3b. This recursive method uses two pointers as arguments to reverse 
	the list in place. Each level creates and uses a third pointer, called "next".
   ********************************************************/
   public static ListNode recurTwoPointers(ListNode prev, ListNode head)
   {
      if(head == null)
         return prev;
      else
      {
      
         prev = new ListNode(head.getValue(), prev);
      //prev.setNext(head);
         ListNode next = head.getNext();
         return recurTwoPointers(prev, next);
      }
   } 
   
   /**********************************************
	3c. On each recursive level, find pointerToLast() and 
	nextToLast(). Make a new last. On way back, append() 
	one level to the other. 
	********************************************************/
   public static ListNode recurPointersAppend(ListNode head)
   {
      if(head.getNext() == null)
      {
         return head;
      }
      ListNode last = (pointerToLast(head));
      ListNode nlast = (nextToLast(head));
      ListNode b = recurPointersAppend(head);
      ListNode a = append(nlast, last);
      return a;
     
   }
   
   private static ListNode pointerToLast(ListNode head)
   {
      if(head == null)
         return null;
      ListNode p = head;
      while(p.getNext() != null)
      {
         p = p.getNext();
      }  
      return p; 
   }
   
   private static ListNode nextToLast(ListNode head)
   {
      if(head == null)
         return null;
      ListNode p = head;
      while(p.getNext().getNext() != null)
      {
         p = p.getNext();
      }
      p.setNext(null);
      return p;
   }
   
   private static ListNode append(ListNode h1, ListNode h2)
   {
      
      h2.setNext(h1);
      return h2;
   }

   /**********************************************
   3d. This difficult method reverses the list in place, using one
   local pointer. Start with pointerToLast(). The helper method
   is recursive.
	********************************************************/
   public static ListNode mindBender(ListNode head)
   {
      ListNode temp = pointerToLast(head);
      mindBenderHelper(head);
      head.setNext(null);
      return temp;
   }
   
   public static void mindBenderHelper(ListNode head)
   {
      if(head == null)
         return;
      if(head.getNext() == null)
         return;
      ListNode p = head.getNext();  
      head.setNext(null);
      mindBenderHelper(p);
      p.setNext(head);
   }
}

/********************************************
 print the original: 				[computer, science, java, coffee, nonsense, boo, foo, hello]
 recur and print: 					[hello, foo, boo, nonsense, coffee, java, science, computer]
 
 original is unchanged: 				[computer, science, java, coffee, nonsense, boo, foo, hello]
 reverse by building a new list: 	[hello, foo, boo, nonsense, coffee, java, science, computer]
 iterate with 3 pointers:			   [computer, science, java, coffee, nonsense, boo, foo, hello]
 recur with 2 pointers: 				[hello, foo, boo, nonsense, coffee, java, science, computer]
 recur with pointers and append: 	[computer, science, java, coffee, nonsense, boo, foo, hello]
 Mind Bender reverse:					[hello, foo, boo, nonsense, coffee, java, science, computer]

**************************************/