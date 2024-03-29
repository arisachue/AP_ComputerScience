// Name: Arisa Chue
// Date: 12/8/2018

import java.io.File;     
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This working code uses raw arrays.
 * Refactor code to use ArrayList<E> instead.
 */      
public class SortingWidgets
{
   public static void main(String[] args) throws Exception
   {
      ArrayList<Comparable> apple = input("widgets.txt");  
      sort(apple);
      output(apple);
   }
   
   public static ArrayList<Comparable> input(String filename) throws Exception
   {
      Scanner infile = new Scanner( new File(filename) );
      ArrayList<Comparable> array = new ArrayList<Comparable>();	
      while(infile.hasNext())// && infile.nextInt().hasNext())
      //for(int k =0; k<57; k++)    		    //use a while-loop
      {								  
         int x = infile.nextInt();
         if(infile.hasNext())
         {
            int y = infile.nextInt();
            array.add(new Widget(x, y));   
         }   //don't use an index number
      }
      infile.close();
      return array;
   }
   
   public static void sort(ArrayList<Comparable> array)
   {
      int maxPos;
      for(int k = 0; k < array.size(); k++)		
      {
         maxPos = findMax(array, array.size() - k - 1);
         swap(array, maxPos, array.size() - k - 1);
      }
   }
   
   public static int findMax(ArrayList<Comparable> array, int upper)
   {
      int maxPos = 0;
      for(int j = 1; j <= upper; j++)			
         if(array.get(j).compareTo(array.get(maxPos)) > 0)	
            maxPos = j;					
      return maxPos;
   }
   
   public static void swap(ArrayList<Comparable> array, int a, int b)
   {
      Comparable temp = array.get(a);				
      array.set(a, array.get(b));
      array.set(b, temp);
   }
   
   public static void output(ArrayList<Comparable> array)
   {
      for(Comparable n : array)		//use the for-each loop
         System.out.println(n);
   }
}

///////////////////////////////////////////////////////////////

class Widget implements Comparable<Widget>
{
   // fields
   private int myCubits, myHands;
   
   // constructors
   public Widget()
   {
      myCubits = myHands = 0;
   }
   
   public Widget(int x)
   {
      myCubits = x;
      myHands = 0;
   }
   
   public Widget(int x, int y)
   {
      myCubits = x;
      myHands = y;
   }
   
   public Widget(Widget arg)    //copy constructor
   {
      myCubits = arg.getCubits();
      myHands = arg.getHands(); 
   }
   
   //accessors and modifiers
   public int getCubits()
   {
      return myCubits;
   }
   
   public int getHands()
   {
      return myHands;
   }
   
   public void setCubits(int x)
   {
      myCubits = x;
   }
   
   public void setHands(int x)
   {
      myHands = x;
   }
   
   //other methods
   public int compareTo(Widget other)
   {
      if(myCubits < other.getCubits())
         return -1;
      if(myCubits > other.myCubits)
         return 1;
      if(myHands < other.myHands)    //"private" is at the class level
         return -1;
      if(myHands > other.getHands())
         return 1;
      return 0;
   }
   
   public boolean equals(Widget other)    //not equals(Object arg)
   {
      return compareTo(other) == 0;
   }
   
   public String toString()
   {
      return myCubits + " cubits " + myHands + " hands";
   }
}