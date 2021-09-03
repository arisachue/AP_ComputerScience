 //name:  Arisa Chue   date: 10/28/2018
import java.util.*;
import java.io.*;
public class InsertionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
     //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      //double[] array = {3.0, 1.0, 4.0, 1.0, 5.0, 9.0, 2.0, 6.0};
      Insertion.sort(array);
      print(array);
      
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
      System.out.println();
      
      //Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      Insertion.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }
   public static void print(double[] a)
   {
      for(double d: a)         //for-each
         System.out.print(d+" ");
      System.out.println();
   }
   public static void print(Object[] papaya)
   {
      for(Object abc : papaya)     //for-each
         System.out.print(abc+" ");
   }
   public static boolean isAscending(double[] a)
   {
      for(int x=0; x<a.length-1; x++)
      {
         if(a[x] > a[x+1])
            return false;
      }
      return true;
   
   }
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static boolean isAscending(Comparable[] a)
   {
      for(int x=0; x<a.length-1; x++)
      {
         if(a[x].compareTo(a[x+1])>0)
            return false;
      }
      return true;
   
   }
}

//**********************************************************
 //name:  Arisa Chue   date: 10/28/2018
class Insertion
{
   public static double[] sort(double[] array)
   { 
      for(int x =1; x<array.length; x++)
      {
         int index = shift(array, x, array[x]);
         double temp = array[x];
         for(int y =x; y> index; y--)
         {
         
            array[y] = array[y-1];
         }
         array[index] = temp;
      }
      return array;
   }
   private static int shift(double[] array, int index, double value)
   {
      for(int x=index-1; x >= 0; x--)
      {
         if(value > array[x])
            return x+1;
      }
      return 0;
   }
   @SuppressWarnings("unchecked")
    public static Comparable[] sort(Comparable[] array)
   { 
      for(int x =1; x<array.length; x++)
      {
         int index = shift(array, x, array[x]);
         Comparable temp = array[x];
         for(int y =x; y> index; y--)
         {
            array[y] = array[y-1];
         }
         array[index] = temp;
      }
      return array;
   
   }
   @SuppressWarnings("unchecked")
    private static int shift(Comparable[] array, int index, Comparable value)
   {
      for(int x=index-1; x >= 0; x--)
      {
         if(value.compareTo(array[x]) >0)
            return x+1;
      }
      return 0;
   
   }
}
