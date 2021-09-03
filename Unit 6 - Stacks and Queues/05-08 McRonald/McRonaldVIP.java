// Name: Arisa Chue
// Date: 1/16/2019

import java.util.*;

public class McRonaldVIP
{
   public static final int TIME = 1080;  //18 hrs * 60 min
   
   public static void main(String[] args)
   {
      int min = 0;
      Queue<Integer> q = new LinkedList<Integer>();
      Queue<Integer> vip = new LinkedList<Integer>();
      int total =0;
      int totalvip = 0;
      double await = 0.0;
      int lwait = 0;
      int twait = 0;
      int twaitvip = 0;
      int lsize = 0;
      int sTime = -1;
   
      while(min < TIME)
      {
         System.out.print(min + ": ");
         System.out.print("VIP queue:");
         display(vip, min);
         System.out.print("\t" + "\t" + "Normal queue: ");
         display(q, min);
         double cus = (Math.random());
         double v = Math.random();
         if(v < 0.05)
         {
            totalvip++;
            vip.add(min);
         }
         if(cus < 0.2)
         {
            total++;
            q.add(min);
            if(q.size() > lsize)
               lsize = q.size();         
         }
         if(!q.isEmpty() && sTime == -1)
         {
            int wait = (int)(Math.random()*6 + 2);
            if(!vip.isEmpty())
            {
               int vipTime = min - vip.remove();
               vipTime += wait;
               twaitvip += vipTime;
            }
            else
            {
               int cusTime = min - q.remove();
               cusTime += wait;
               twait += cusTime;
               if(cusTime > lwait)
                  lwait = cusTime;
            }
            sTime = min + wait;
         }
         if(min == sTime)
         {
            sTime = -1;
         }
         min++;
      }
      while(!q.isEmpty())
      {
         System.out.print(min + ": ");
         System.out.print("VIP queue:");
         display(vip, min);
         System.out.print("\t" + "\t" + "Normal queue: ");
         display(q, min);
         
         if(!q.isEmpty() && sTime == -1)
         {
            int wait = (int)(Math.random()*6 + 2);
         
            if(!vip.isEmpty())
            {
               int vipTime = min - vip.remove();
               vipTime += wait;
               twaitvip += vipTime;
            }
            else
            {
               int cusTime = min - q.remove();
               cusTime += wait;
               twait += cusTime;
               if(cusTime > lwait)
                  lwait = cusTime;
            }
            sTime = min + wait;
         }
         if(min == sTime)
         {
            sTime = -1;
         }
         min++;
      }
      System.out.print(min + ": ");
      System.out.print("VIP queue:");
      display(vip, min);
      System.out.print("\t" + "\t" + "Normal queue: ");
      display(q, min);
   
      await = (double)twait/total;
      System.out.println("Total customers served (normal) = " + total);
      System.out.println("Average wait time (normal) = " + await);
      System.out.println("Longest wait time = " + lwait);
      System.out.println("Longest queue = " + lsize);
      System.out.println("Total customers served (VIP) = " + totalvip);
      System.out.println("Average wait time (VIP) = " + (double)twaitvip/totalvip);
   }
   
   public static void display(Queue<Integer> q, int min)   //if you are storing arrival times
   //public static void display(Queue<Customer> q, int min) //if you have a Customer class
   {
      System.out.println(q.toString());
   }
}
