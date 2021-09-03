// Name: Arisa Chue
// Date: 1/16/2019

import java.io.*;
import java.util.*;

public class SongQueue
{
   private static Scanner keyboard;  //use this global Scanner for this lab only
   private static Queue<String> songQueue;
   
   public static void main(String[] args) throws Exception
   {
      keyboard = new Scanner(System.in);
      songQueue = readPlayList();
      printSongList();
      
      String prompt = "Add song (A), Play song (P), Delete song (D), Quit (Q):  ";
      String str = "";
      do{      
         System.out.print(prompt);
         str = keyboard.nextLine().toUpperCase();
         processRequest( str );
         System.out.println();
      }while(!str.equals("Q"));
   }
   
   public static Queue<String> readPlayList() throws IOException
   {
      Scanner infile = new Scanner (new File("songs.txt"));  
      Queue<String> play = new LinkedList<String>();
      while(infile.hasNextLine())
      {
         String str = infile.nextLine();
         int ind = 0;
         for(int x=0; x<str.length(); x++)
         {
            if(str.charAt(x) == '-')
               ind = x;
         }
         str = str.substring(0, ind);
         str = str.trim();
         play.add(str);
      }   
      return play;
       
   }
   
   public static void processRequest(String str)
   {
      if(str.equals("A"))
      {
         System.out.print("Song to add? ");
         String song = keyboard.nextLine();
         getQueue().add(song);
         printSongList();
      }
      else if(str.equals("P"))
      {
         if(getQueue().isEmpty())
         {
            System.out.println("Empty queue!");
            printSongList();
         }
         else
         {
            String song = getQueue().remove();
            System.out.println("Now playing: " + song);
            printSongList();
         }
      }
      else if(str.equals("D"))
      {
         printSongList();
         System.out.print("Delete which song (exact match)? ");
         str = keyboard.nextLine();
         int ind= -1;
         int counter = -1;
         Queue<String> copy = new LinkedList<String>();
         // int s = songQueue.size();
      //          for(int x=0; x<s; x++)
         while(!getQueue().isEmpty())
         {
            counter++;
            String song = getQueue().remove();
           // x--;
            copy.add(song);
            if(song.equals(str))
               ind = counter;
         }
        // songQueue = copy;
         if(ind == -1)
         {
            System.out.println("Error: Song not in list.");
            while(!copy.isEmpty())
            {
               String song = copy.remove();
               getQueue().add(song);
            
            }
            printSongList();
         }
         else
         {
            Queue<String> n = new LinkedList<String>();
            // int g = copy.size();
         //             for(int x=0; x<g; x++)
            int count = -1;
            while(!copy.isEmpty())
            {
               count++;
               if(count == ind)
               {
                  copy.remove();
               }
               else 
                  n.add(copy.remove());
            }
            // int v = n.size();
         //             for(int x=0; x<v; x++)
            while(!n.isEmpty())
            {
               getQueue().add(n.remove());
            }
            //songQueue = n;
            printSongList();
         }
      }
      else if(str.equals("Q"))
      {
         System.out.println();
         System.out.println("No more music for you today. Goodbye!");
      }
         
   }

   public static void printSongList()
   {
      System.out.print("Your music queue: ");
      String str = "[";
      if(getQueue().isEmpty())
      {
         System.out.println("[]");
      }
      else
      {
         Queue<String> copy = new LinkedList<String>();
         while(!getQueue().isEmpty())
         {
            String song = getQueue().remove();
            copy.add(song);
            str += song + ", "; 
         }
         while(!copy.isEmpty())
         {
            String d = copy.remove();
            getQueue().add(d);
         }
         str = str.substring(0, str.length()-2); 
         str += "]";
         System.out.println(str);
      }
   }
   
   public static Queue<String> getQueue()
   {
      return songQueue;
   }
}

/*********************************************

 Your music queue: [Really Love, Uptown Funk, Thinking Out Loud, Alright, Traveller, Alright]
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Really Love
 Your music queue: [Uptown Funk, Thinking Out Loud, Alright, Traveller, Alright]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Uptown Funk
 Your music queue: [Thinking Out Loud, Alright, Traveller, Alright]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  d
 Your music queue: [Thinking Out Loud, Alright, Traveller, Alright]
 Delete which song (exact match)?  Alright
 Your music queue: [Thinking Out Loud, Traveller]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  d
 Your music queue: [Thinking Out Loud, Traveller]
 Delete which song (exact match)?  xxx
 Error:  Song not in list.
 Your music queue: [Thinking Out Loud, Traveller]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  a
 Song to add? Girl Crush
 Your music queue: [Thinking Out Loud, Traveller, Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Thinking Out Loud
 Your music queue: [Traveller, Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Traveller
 Your music queue: [Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Girl Crush
 Your music queue: []
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Empty queue!
 Your music queue: []
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  q
 
 No more music today!

**********************************************/