// Name: Arisa Chue
// Date: 1/16/2019

import java.util.*;

public class McRonald1000
{
   public static final int TIME = 1080;  //18 hrs * 60 min
   
   public static void main(String[] args)
   {
      
      Queue<Integer> q = new LinkedList<Integer>();
      int total =0;
      double await = 0.0;
      int lwait = 0;
      int twait = 0;
      int lsize = 0;
      boolean open = true;
      int sTime = 0;
   
      int ld = 0;
   
      for(int x=0; x<1000; x++)
      {
         int cusToday = 0;
         int min = 0;
         while(min < TIME)
         {
         //display(q, min);
            int cus = (int)(Math.random() * 5);
            if(cus == 0)
            {
               total++;
               cusToday++;
               q.add(min);
               if(q.size() > lsize)
                  lsize = q.size();         
            }
            if(!q.isEmpty() && open==true)
            {
               int wait = (int)(Math.random()*6 + 2);
               sTime = min + wait;
               open = false;
            }
            if(min == sTime && !q.isEmpty())
            {
               int cusTime = min - q.remove();
               twait += cusTime;
               if(cusTime > lwait)
                  lwait = cusTime;
               open = true;
            }
            min++;
         }
         while(!q.isEmpty())
         {
         //display(q, min);
            if(!q.isEmpty() && open==true)
            {
               int wait = (int)(Math.random()*6 + 2);
               sTime = min + wait;
               open = false;
            }
            if(min == sTime && !q.isEmpty())
            {
               int cusTime = min - q.remove();
               twait += cusTime;
               if(cusTime > lwait)
                  lwait = cusTime;
               open = true;
            }
            min++;
         }
         if(cusToday > ld)
            ld = cusToday;
      }
      //display(q, min);
      await = (double)twait/total;
      double acusperday = (double)total/1000;
      System.out.println("Total customers served = " + total);
      System.out.println("Average wait time = " + await);
      System.out.println("Longest wait time = " + lwait);
      System.out.println("Longest queue = " + lsize);
      System.out.println("Average served per day = " + acusperday);
      System.out.println("Largest day = " + ld);
   }
   
   public static void display(Queue<Integer> q, int min)   //if you are storing arrival times
   //public static void display(Queue<Customer> q, int min) //if you have a Customer class
   {
      System.out.println( min + ": " + q.toString());
   }
}

// class Customer      // if you want a Customer class
// {
//    private int waitingTime;
//    private int time;
//    public Customer(int t)
//    {
//       time = t;
//       waitingTime = 0;
//    }
//    public int get
// }


