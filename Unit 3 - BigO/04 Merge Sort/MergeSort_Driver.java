// Name: Arisa Chue
// Date: 10/29/2018

import java.util.*;
import java.io.*;

public class MergeSort_Driver
{
   public static void main(String[] args) throws Exception
   {
      //Part 1, for doubles
      //double[] array = {3,1,4,1,5,9,2,6};    // small example array from the MergeSort handout
      int n = (int)(Math.random()*50+10);
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random();
         	
      MergeSort.sort(array);
   
      print(array);
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("oops!");
         
      //Part 2, for Comparables
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      MergeSort.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }

   
   public static void print(double[] a)   
   {                             
      for(double number : a)    
         System.out.print(number+" ");
      System.out.println();
   }
  
   public static boolean isAscending(double[] a)
   {
      for(int x =0; x< a.length-1; x++)
      {
         if(a[x] > a[x+1])
            return false;
      }
      return true;
   }
  
   public static void print(Object[] peach)
   {
      for(Object b : peach)
         System.out.print(b+" ");
     // System.out.println();
   }
   
   @SuppressWarnings("unchecked")
   public static boolean isAscending(Comparable[] a)
   {
      for(int x=0; x<a.length-1; x++)
      {
         if(a[x].compareTo(a[x+1]) > 0)
            return false;
      }
      return true;
   }
}
/////////////////////////////////////////////
// 15 Oct 07
// MergeSort Code Handout
// from Lambert & Osborne, p. 482 - 485

class MergeSort
{
   private static final int CUTOFF = 10; // for small lists, recursion isn't worth it
  
   public static void sort(double[] array)
   { 
      double[] copyBuffer = new double[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1);
   }
   
   /*  array			array that is being sorted
       copyBuffer		temp space needed during the merge process
       low, high		bounds of subarray
       middle			midpoint of subarray   
   */
   private static void mergeSortHelper(double[] array, double[] copyBuffer,
                                                      int low, int high)
   {  
      // if ( high - low  < CUTOFF )                  //switch to selection sort  when
         // SelectionLowHigh.sort(array, low, high);        //the list gets small enough 
      // else
      if (low < high)
      {
         int middle = (low + high) / 2;
         mergeSortHelper(array, copyBuffer, low, middle);
         mergeSortHelper(array, copyBuffer, middle + 1, high);
         merge(array, copyBuffer, low, middle, high);
      }
   }
   
   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low				beginning of first sorted subarray
      middle			end of first sorted subarray
      middle + 1		beginning of second sorted subarray
      high				end of second sorted subarray   
   */
   public static void merge(double[] array, double[] copyBuffer,
                                   int low, int middle, int high)
   {
      // Initialize i1 and i2 to the first items in each subarray  
      // Interleave items from the subarrays into the copyBuffer in such 
      // a way that order is maintained.
      
      int i1 = low;
         int i2 = middle+1;

      
      for(int x=low; x<=high; x++)
      {
         if(i1 > middle)
         {
            copyBuffer[x] = array[i2];
            i2++;
         }
         else if(i2>high)
         {
            copyBuffer[x] = array[i1];
            i1++;
         }   
         else if(array[i1] < array[i2])
         {
            copyBuffer[x] = array[i1];
            i1++;
         }
         else
         {
            copyBuffer[x] = array[i2];
            i2++;
         }
      }
      for(int x=low; x<=high; x++)
      {
         array[x] = copyBuffer[x];
      }    
     //  if(low > middle)
//       {
//          for(int x=middle+1; x<high; x++)
//          {
//             copyBuffer[x] = array[x];
//          }
//       }
//       else if(middle+1 > high)
//       {
//          for(int x=low; x<middle+1; x++)
//          {
//             copyBuffer[x] = array[x];
//          }
//       }
//       else
//       {
//          if(i1 < i2)
//          {
//             copyBuffer[i1] = array[i1];
//             merge(array, copyBuffer, 
//          }
//          else 
//          {
//             copyBuffer[i2] = array[i2];
//          }
         
      
      
      //then copy the just-sorted values (from low to high)
      // from the copyBuffer back to the array.
   	
   
      
   }	
      
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static void sort(Comparable[] array)
   { 
    Comparable[] copyBuffer = new Comparable[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1);

   }

   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low, high		bounds of subarray
      middle			midpoint of subarray  */
   @SuppressWarnings("unchecked")
   private static void mergeSortHelper(Comparable[] array, Comparable[] copyBuffer, int low, int high)
   {
     if (low < high)
      {
         int middle = (low + high) / 2;
         mergeSortHelper(array, copyBuffer, low, middle);
         mergeSortHelper(array, copyBuffer, middle + 1, high);
         merge(array, copyBuffer, low, middle, high);
      }

   

   }
   
   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low				beginning of first sorted subarray
      middle			end of first sorted subarray
      middle + 1		beginning of second sorted subarray
      high				end of second sorted subarray   */
   @SuppressWarnings("unchecked")
   public static void merge(Comparable[] array, Comparable[] copyBuffer,
                                   int low, int middle, int high)
   {
     int i1 = low;
         int i2 = middle+1;

      
      for(int x=low; x<=high; x++)
      {
         if(i1 > middle)
         {
            copyBuffer[x] = array[i2];
            i2++;
         }
         else if(i2>high)
         {
            copyBuffer[x] = array[i1];
            i1++;
         }   
         else if(array[i1].compareTo(array[i2]) < 0)
         {
            copyBuffer[x] = array[i1];
            i1++;
         }
         else
         {
            copyBuffer[x] = array[i2];
            i2++;
         }
      }
      for(int x=low; x<=high; x++)
      {
         array[x] = copyBuffer[x];
      }    

   }    	
}

/***************************************************
This is the extension.  You will have to uncomment Lines 54-56 above.
***************************************************/

class SelectionLowHigh
{
   public static void sort(double[] array, int low, int high)
   {  
   
   }
   private static int findMax(double[] array, int low, int upper)
   {
      return 0;
   }
   private static void swap(double[] array, int a, int b)
   {
   
   } 
}