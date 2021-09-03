// Name: Arisa Chue
// Date: 1/16/2019

import java.util.*;

public class McRonald3
{
   public static final int TIME = 1080; //18 hrs * 60 min
   
   //if the customer that comes in second has a shorter waiting time then would the second customer be removed or the first one?
   
   public static void main(String[] args)
   {
      int min = 0;
      Queue<Integer> q = new LinkedList<Integer>();
      int total =0;
      double await = 0.0;
      int lwait = 0;
      int twait = 0;
      int lsize = 0;
      int sTime1 = -1;
      int sTime2 = -1;
      int sTime3 = -1;
   
      while(min < TIME)
      {
         display(q, min);
         double cus = (Math.random());
         if(cus < 0.5)
         {
            total++;
            q.add(min);
            if(q.size() > lsize)
               lsize = q.size();   
                  
         }
         if(!q.isEmpty())
         {
            if(sTime1==-1 || sTime2==-1 || sTime3==-1)
            {
               int wait = (int)(Math.random()*6 + 2);
            
               if(sTime1==-1 && q.size() >= 1)
               {
                 //  if(q.size() == 2 && sTime2 != -1 && sTime3 != -1)
//                   {
//                   }
//                   else
//                   {
                     int cusTime = min - q.remove();
                     cusTime += wait;
                     twait += cusTime;
                     if(cusTime > lwait)
                        lwait = cusTime;
                     sTime1 = min + wait;
                 // }
               }
               if(sTime2==-1 && q.size() > 1 && sTime1 != -1)
               {
                 //  if(q.size() == 2 && sTime1 != -1 && sTime3 != -1)
//                   {
//                   }
//                   else
//                   {
                     int cusTime = min - q.remove();
                     cusTime += wait;
                     twait += cusTime;
                     if(cusTime > lwait)
                        lwait = cusTime;
                     sTime2 = min + wait;
                //  }
               }
               if(sTime3==-1 && q.size() > 2 && sTime1 != -1 && sTime2 != -1)
               {
                 //  if(q.size() == 2 && sTime1 != -1 && sTime2 != -1)
//                   {
//                   }
//                   else
//                   {
                     int cusTime = min - q.remove();
                     cusTime += wait;
                     twait += cusTime;
                     if(cusTime > lwait)
                        lwait = cusTime;
                     sTime3 = min + wait;
                  //}
               }
            }
         }
         if(min == sTime1 || min == sTime2 || min == sTime3 )
         {
            if(min == sTime1)
            {
               sTime1 = -1;
            }
            if(min == sTime2)
            {
               sTime2 = -1;
            }
            if(min == sTime3)
            {
               sTime3 = -1;
            }
         }
         min++;
      }
      while(!q.isEmpty())
      {
         display(q, min);
         if(!q.isEmpty())
         {
            if(sTime1==-1 || sTime2==-1 || sTime3==-1)
            {
               int wait = (int)(Math.random()*6 + 2);
            
               if(sTime1==-1 && q.size() >= 1)
               {// 
//                   if(q.size() == 2 && sTime2 != -1 && sTime3 != -1)
//                   {
//                   }
//                   else
//                   {
                     int cusTime = min - q.remove();
                     cusTime += wait;
                     twait += cusTime;
                     if(cusTime > lwait)
                        lwait = cusTime;
                     sTime1 = min + wait;
                 // }
               }
               if(sTime2==-1 && q.size() > 1 && sTime1 != -1)
               {
                 //  if(q.size() == 2 && sTime1 != -1 && sTime3 != -1)
//                   {
//                   }
//                   else
//                   {
                     int cusTime = min - q.remove();
                     cusTime += wait;
                     twait += cusTime;
                     if(cusTime > lwait)
                        lwait = cusTime;
                     sTime2 = min + wait;
                 // }
               }
               if(sTime3==-1 && q.size() > 2 && sTime1 != -1 && sTime2 != -1)
               {
                 //  if(q.size() == 2 && sTime1 != -1 && sTime2 != -1)
//                   {
//                   }
//                   else
//                   {
                     int cusTime = min - q.remove();
                     cusTime += wait;
                     twait += cusTime;
                     if(cusTime > lwait)
                        lwait = cusTime;
                     sTime3 = min + wait;
                  //}
               }
            }
         
         }

         if(min == sTime1 || min == sTime2 || min == sTime3 )
         {
            if(min == sTime1)
            {

               sTime1 = -1;
            }
            if(min == sTime2)
            {

               sTime2 = -1;
            }
            if(min == sTime3)
            {
               sTime3 = -1;
            }
         }
         
      
         min++;
      }
      display(q, min);
      await = (double)twait/total;
      System.out.println("Total customers served = " + total);
      System.out.println("Average wait time = " + await);
      System.out.println("Longest wait time = " + lwait);
      System.out.println("Longest queue = " + lsize);
   }
   
   public static void display(Queue<Integer> q, int min)   //if you are storing arrival times
   //public static void display(Queue<Customer> q, int min) //if you have a Customer class
   {
      System.out.println( min + ": " + q.toString());
   }
}
