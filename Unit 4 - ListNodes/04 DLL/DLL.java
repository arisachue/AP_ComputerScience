// Name: Arisa Chue
// Date: 11/27/2018

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   public int size()
   {
     //  DLNode p = head;
   //       n =0;
   //       while(!head.equals(p))
   //       {
   //          n++;
   //          p= p.getNext();
   //       }
   //       return n;
      return size;
         
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index.  increments size. 	*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      if(index == 0)
      {
         DLNode a = new DLNode(obj, head, head.getNext());
         head.getNext().setPrev(a);
         head.setNext(a);
      }
      else
      {
         DLNode p = head;
         for(int x = 0; x<index; x++)
         {
            
            p = p.getNext();
         }
         DLNode a = new DLNode(obj, p, p.getNext());
         p.getNext().setPrev(a);
         p.setNext(a);
      }
      size++;            
                    
   }
   
   /* return obj at position index. 	*/
   public Object get(int index)
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode p = head.getNext();
      for(int x =0; x<index; x++)
         p = p.getNext();
      return p.getValue();
   }
   
   /* replaces obj at position index. 
        returns the obj that was replaced*/
   public Object set(int index, Object obj)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode p = head.getNext();
      for(int x =0; x<index; x++)
         p = p.getNext();
      Object temp = p.getValue();
      p.setValue(obj);
      return temp;
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object of the node that was removed.    */
   public Object remove(int index)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode p = head.getNext();
      for(int x = 0; x<index; x++)
         p = p.getNext();
      Object temp = p.getValue();
      p.getNext().setPrev(p.getPrev());
      p.getPrev().setNext(p.getNext());
      size--;
      return temp;
   }
   
   /* inserts obj at front of list, increases size   */
   public void addFirst(Object obj)
   {
      DLNode p = head.getNext();
      DLNode a = new DLNode(obj, head, p);
      p.setPrev(a);
      head.setNext(a);
      size++;
   }
   
   /* appends obj to end of list, increases size    */
   public void addLast(Object obj)
   {
      DLNode p = head;
      for(int x =0; x<size; x++)
      {
         p = p.getNext();
      }
      DLNode n = new DLNode(obj, p, head);
      p.setNext(n);
      head.setPrev(n);
      size++;
   
   }
   
   /* returns the first element in this list  */
   public Object getFirst()
   {
      DLNode p = head;
      return p.getNext().getValue();
   }
   
   /* returns the last element in this list  */
   public Object getLast()
   {
      DLNode p = head;
      for(int x =0; x<size; x++)
      {
         p = p.getNext();
      }
      return p.getValue();
   }
   
   /* returns and removes the first element in this list, or
       returns null if the list is empty  */
   public Object removeFirst()
   {
      if(head.getNext().getValue() == head.getValue())
         return null;
      DLNode p = head;
      p=p.getNext();
      head.setNext(p.getNext());
      p.getNext().setPrev(head);
      size--;
      return p.getValue();
   
   }
   
   /* returns and removes the last element in this list, or
       returns null if the list is empty  */
   public Object removeLast()
   {
      if(head.getNext().getValue() == head.getValue())
         return null;
      DLNode p = head;
      for(int x =0; x<size; x++)
      {
         p = p.getNext();
      }
      p.getPrev().setNext(head);
      head.setPrev(p.getPrev());
      size--;
      return p.getValue();
   
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
      String str = "[";
      DLNode p = head;
      for(int x =0; x<size-1; x++)
      {
         p = p.getNext();
         str += "" +p.getValue() + ", ";
      }
      p = p.getNext();
      str += p.getValue() + "]";
      return str;
   
   }
}