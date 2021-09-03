//Name: Arisa Chue
 //Date: 3/29/2019
 
import java.util.*;


/* implement the API for java.util.PriorityQueue
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public boolean add(E obj)
   {
      myHeap.add(obj);
      heapUp(myHeap.size()-1);
      return true;
   }
   
   public E remove()
   {
      if(isEmpty())
         return null;
      E temp = (E)myHeap.get(1);
      swap(1, myHeap.size()-1);
      myHeap.remove(myHeap.size()-1);
      int si = myHeap.size();
      heapDown(1, myHeap.size());
      return temp;
   }
   
   public E peek()
   {
      if(isEmpty())
         return null;
      return (E)myHeap.get(1);
   }
   
   public boolean isEmpty()
   {
      if(myHeap.size() == 1)
         return true;
      return false;
   }
   
   private void heapUp(int k)
   {
      int parent = k/2;
      if(k == 1)
         return;
      if(parent < 1)
         return;
      else
      {
         if((myHeap.get(k).compareTo((E)myHeap.get(parent))) < 0)  
         {
            swap(k, parent);
            heapUp(parent);
         }
      }
   }
   
   private void swap(int a, int b)
   {
      E temp = (E)myHeap.get(a);
      myHeap.set(a, (E)myHeap.get(b));
      myHeap.set(b, temp);
   }
   
   private void heapDown(int k, int size)
   {
      int left = 2*k;
      int right = 2*k+1;
      if(left >= size)
         return;
      else
      {
         int min = left;
         if(right < size && (myHeap.get(right).compareTo(myHeap.get(left)) < 0))
         {
            min = right;
         }
         if(myHeap.get(k).compareTo((E)myHeap.get(min)) > 0)
         {
            swap(k, min);
            heapDown(min, size);
         }
      }
   
   }
   public boolean isSorted()
   {
      for(int x=1; x<myHeap.size()/2; x++)
      {
         if(myHeap.get(x).compareTo((E)myHeap.get(2*x)) > 0 && myHeap.get(x).compareTo((E)myHeap.get(2*x+1)) >0)
            return false;
      }
      return true;
   }
   public String toString()
   {
      return myHeap.toString();
   }  
}