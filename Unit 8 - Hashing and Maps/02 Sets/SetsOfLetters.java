// Name: Arisa Chue
// Date: 3/7/2019

import java.util.*;
import java.io.*;

public class SetsOfLetters
{
   public static void main(String[] args) throws FileNotFoundException
   {
      String fileName = "declarationLast.txt";
      fillTheSets(fileName);
   }
   
   public static void fillTheSets(String fn) throws FileNotFoundException
   {
      Scanner infile = new Scanner(new File(fn));
      /*  enter your code here  */
      Set<String> clc = new TreeSet<String>();
      Set<String> cuc = new TreeSet<String>();
      Set<String> cot = new TreeSet<String>();
      if(infile.hasNext())
      {
         Set<String> lc = new TreeSet<String>();
         Set<String> uc = new TreeSet<String>();
         Set<String> ot = new TreeSet<String>();
         String line = infile.nextLine();
         System.out.println(line);
         for(int x =0; x<line.length(); x++)
         {
            String temp = line.substring(x, x+1);
            if(Character.isLetter(temp.charAt(0)))
            {
               if(Character.isLowerCase(temp.charAt(0)))
               {
                  lc.add(temp);
                  clc.add(temp);
               }
               else
               {
                  cuc.add(temp);
                  uc.add(temp);
               }
            }
            else if(!temp.equals(" "))
            {
               cot.add(temp);
               ot.add(temp);
            }
         }
         clc.retainAll(lc);
         cuc.retainAll(uc);
         cot.retainAll(ot);
      
         String str = "Lower Case: [";
         for(String n : lc)
            str += n + ", ";
            if(lc.size() > 0)
         str = str.substring(0, str.length()-2);
         str += "]";
         System.out.println(str);
         
         str = "Upper Case: [";
         for(String n : uc)
            str += n + ", ";
            if(uc.size() > 0)
         str = str.substring(0, str.length()-2);
         str += "]";
         System.out.println(str);
         
         str = "Other: [";
         for(String n : ot)
            str += n + ", ";
             if(ot.size() > 0)
         str = str.substring(0, str.length()-2);
         str += "]";
         System.out.println(str);
         
         System.out.println();
      }
      
      while(infile.hasNextLine())
      {
         Set<String> lc = new TreeSet<String>();
         Set<String> uc = new TreeSet<String>();
         Set<String> ot = new TreeSet<String>();
         String line = infile.nextLine();
         System.out.println(line);
         for(int x =0; x<line.length(); x++)
         {
            String temp = line.substring(x, x+1);
            if(Character.isLetter(temp.charAt(0)))
            {
               if(Character.isLowerCase(temp.charAt(0)))
               {
                  lc.add(temp);
                  // if(!clc.contains(temp))
               //                      clc.remove(temp);
               }
               else
               {
                  // if(!clc.contains(temp))
               //                      clc.remove(temp);cuc.add(temp);
                  uc.add(temp);
               }
            }
            else if(!temp.equals(" "))
            {
               // if(!clc.contains(temp))
            //                   clc.remove(temp);
               ot.add(temp);
            }
         }
         clc.retainAll(lc);
         cuc.retainAll(uc);
         cot.retainAll(ot);
         
         String str = "Lower Case: [";
         for(String n : lc)
            str += n + ", ";
         if(lc.size() > 0)
            str = str.substring(0, str.length()-2);
         str += "]";
         System.out.println(str);
         
         str = "Upper Case: [";
         for(String n : uc)
            str += n + ", ";
         if(uc.size() > 0)
            str = str.substring(0, str.length()-2);
         str += "]";
         System.out.println(str);
         
         str = "Other: [";
         for(String n : ot)
            str += n + ", ";
         if(ot.size() > 0)
            str = str.substring(0, str.length()-2);
         str += "]";
         System.out.println(str);
         
         System.out.println();
      }
   
      String str = "Common Lower Case: [";
      for(String n : clc)
         str += n + ", ";
         if(clc.size() > 0)
      str = str.substring(0, str.length()-2);
      str += "]";
      System.out.println(str);
         
      str = "Common Upper Case: [";
      for(String n : cuc)
         str += n + ", ";
         if(cuc.size() > 0)
      str = str.substring(0, str.length()-2);
      str += "]";
      System.out.println(str);
         
      str = "Common Other: [";
      for(String n : cot)
         str += n + ", ";
          if(cot.size() > 0)
      str = str.substring(0, str.length()-2);
      str += "]";
      System.out.println(str);
     
   }
}

/***********************************
  ----jGRASP exec: java SetsOfLetters_teacher
 
 We, therefore, the Representatives of the united States of 
 Lower Case: [a, d, e, f, h, i, n, o, p, r, s, t, u, v]
 Upper Case: [R, S, W]
 Other: [ , ,]
 
 America, in General Congress, Assembled, appealing to the 
 Lower Case: [a, b, c, d, e, g, h, i, l, m, n, o, p, r, s, t]
 Upper Case: [A, C, G]
 Other: [ , ,]
 
 Supreme Judge of the world for the rectitude of our intentions,
 Lower Case: [c, d, e, f, g, h, i, l, m, n, o, p, r, s, t, u, w]
 Upper Case: [J, S]
 Other: [ , ,]
 
 do, in the Name, and by the Authority of the good People of 
 Lower Case: [a, b, d, e, f, g, h, i, l, m, n, o, p, r, t, u, y]
 Upper Case: [A, N, P]
 Other: [ , ,]
 
 these Colonies, solemnly publish and declare, That these United 
 Lower Case: [a, b, c, d, e, h, i, l, m, n, o, p, r, s, t, u, y]
 Upper Case: [C, T, U]
 Other: [ , ,]
 
 Colonies are, and of Right ought to be Free and Independent 
 Lower Case: [a, b, d, e, f, g, h, i, l, n, o, p, r, s, t, u]
 Upper Case: [C, F, I, R]
 Other: [ , ,]
 
 States; that they are Absolved from all Allegiance to the 
 Lower Case: [a, b, c, d, e, f, g, h, i, l, m, n, o, r, s, t, v, y]
 Upper Case: [A, S]
 Other: [ , ;]
 
 British Crown, and that all political connection between them 
 Lower Case: [a, b, c, d, e, h, i, l, m, n, o, p, r, s, t, w]
 Upper Case: [B, C]
 Other: [ , ,]
 
 and the State of Great Britain, is and ought to be totally 
 Lower Case: [a, b, d, e, f, g, h, i, l, n, o, r, s, t, u, y]
 Upper Case: [B, G, S]
 Other: [ , ,]
 
 dissolved; and that as Free and Independent States, they have 
 Lower Case: [a, d, e, h, i, l, n, o, p, r, s, t, v, y]
 Upper Case: [F, I, S]
 Other: [ , ,, ;]
 
 full Power to levy War, conclude Peace, contract Alliances, 
 Lower Case: [a, c, d, e, f, i, l, n, o, r, s, t, u, v, w, y]
 Upper Case: [A, P, W]
 Other: [ , ,]
 
 establish Commerce, and to do all other Acts and Things which 
 Lower Case: [a, b, c, d, e, g, h, i, l, m, n, o, r, s, t, w]
 Upper Case: [A, C, T]
 Other: [ , ,]
 
 Independent States may of right do. And for the support of this 
 Lower Case: [a, d, e, f, g, h, i, m, n, o, p, r, s, t, u, y]
 Upper Case: [A, I, S]
 Other: [ , .]
 
 Declaration, with a firm reliance on the protection of divine 
 Lower Case: [a, c, d, e, f, h, i, l, m, n, o, p, r, t, v, w]
 Upper Case: [D]
 Other: [ , ,]
 
 Providence, we mutually pledge to each other our Lives, our 
 Lower Case: [a, c, d, e, g, h, i, l, m, n, o, p, r, s, t, u, v, w, y]
 Upper Case: [L, P]
 Other: [ , ,]
 
 Fortunes and our sacred Honor.
 Lower Case: [a, c, d, e, n, o, r, s, t, u]
 Upper Case: [F, H]
 Other: [ , .]
 
 Common Lower Case: [d, e, n, o, r, t]
 Common Upper Case: []
 Common Other: [ ]
  ----jGRASP: operation complete.
  ************************************/