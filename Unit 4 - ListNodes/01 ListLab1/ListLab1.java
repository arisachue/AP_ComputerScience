// Name: Arisa Chue    
// Date: 11/11/2018
import java.util.*;
public class ListLab1
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
         			new ListNode("coffee", head)
         		)
         	)
         );
      print(head);
      print(head);
     //  System.out.println(copyNode(head));
     //  print(copyList(head));
   //       print(rest(head));
      
      /**** uncomment the code below for ListLab1 Extension  ****/
      
      System.out.println("First = " + first(head));
      System.out.println("Second = " + second(head));
      ListNode p = pointerToLast(head);
      System.out.println("Pointer to Last = " + p.getValue()+ " at " + p);
      ListNode c = copyOfLast(head);
      System.out.println("Copy of Last =    " + c.getValue()+ " at " + c);
      		
      Scanner in = new Scanner(System.in);
      System.out.print("Insert what? ");
      String x = in.next();
      head = insertFirst(head, x);
      head = insertLast(head, x);
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
   
   /* enter your code here */
   public static ListNode copyNode(ListNode arg)
   {
      return new ListNode(arg.getValue(), arg.getNext());
   }
   
   public static ListNode copyList(ListNode arg)
   {
      if(arg.getNext() == null)
         return arg;
      else
      {
         ListNode head = arg;
         head.setNext(copyNode(copyList(arg.getNext())));
         return head;
      }   
      
   }
   public static ListNode rest(ListNode h)
   {
   
      if(h.getNext() == null)
         return h;
    // ListNode headd = h;   
   //       headd.setNext(copyNode(rest(h.getNext())));
      ListNode headd = new ListNode(copyNode(rest(h.getNext())), h);
     // head = head.getNext();
    //headd = headd.getNext();
      return headd.getNext().getNext();
   
   }
   
   public static Object first(ListNode head)
   {
      return head.getValue();
   }
   public static Object second(ListNode head)
   {
      if(head== null)
         return null;
      if(head.getNext() == null)
         return null;
      ListNode pointer = head;
      pointer = pointer.getNext();
      return pointer.getValue();
   }
   public static ListNode pointerToLast(ListNode head)
   {
      if(head == null)
         return null;
      ListNode pointer = head;
      while(pointer.getNext()!=null)
      {
         pointer = pointer.getNext();
      }
      return pointer;
   }
   public static ListNode copyOfLast(ListNode head)
   {
      if(head == null)
         return null;
      if(head.getNext() == null)
         return copyNode(head);
      return copyOfLast(head.getNext());
   }
   public static ListNode insertFirst(ListNode head, Object arg)
   {
      ListNode pointer = new ListNode(arg, head);
      return pointer;
   }
   public static ListNode insertLast(ListNode head, Object arg)
   {   
      ListNode prev = head;
      while(prev.getNext()!= null)
      {
         prev = prev.getNext();
      }
      ListNode last = new ListNode(arg, null);
      prev.setNext(last);
      return head;
   }
         
}

/*****************************************
 
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 First = computer
 Second = science
 Pointer to Last = hello at ListNode@18767ad
 Copy of Last =    hello at ListNode@a7bdcd
 Insert what? abc
 [abc, computer, science, java, coffee, nonsense, boo, foo, hello, abc]
 

  **********************************************/