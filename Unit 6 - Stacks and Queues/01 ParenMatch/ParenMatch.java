// Name: Arisa Chue
// Date: 1/7/2019

import java.util.*;

public class ParenMatch
{
   public static final String left  = "([{<";
   public static final String right = ")]}>";
   
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter test cases here */
      parenExp.add("5+7");
      parenExp.add("(5+7)");
      parenExp.add(")5+7(");
      parenExp.add("((5+7)*3)");
      parenExp.add("<{5+7}*3>");
      parenExp.add("[(5+7)*]3");
      parenExp.add("(5+7)*3");
      parenExp.add("5+(7*3)");
      parenExp.add("((5+7)*3");
      parenExp.add("[(5+7]*3)");
      parenExp.add("[(5+7)*3])");
      parenExp.add("([(5+7)*3]");
   
   
      for( String s : parenExp )
      {
         boolean good = checkParen(s);
         if(good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }
   
   public static boolean checkParen(String exp)
   {
      Stack<String> str = new Stack<String>();
      for(int x=0; x<exp.length(); x++)
      {
         for(int l=0; l<left.length(); l++)
         {   
            if(left.charAt(l)==(exp.charAt(x)))
               str.push(Character.toString(exp.charAt(x)));
         
         }
         for(int r=0; r<right.length(); r++)
         {
            if(right.charAt(r)==(exp.charAt(x)))
            {
               if(str.isEmpty())
               {
                  return false;
               }
               if(!str.peek().equals(Character.toString(left.charAt(r))))
                  return false;
               str.pop();
                  
            }
         }
      }
      if(str.isEmpty())
         return true;
      return false;     
   
         
   }
}

/*
 Parentheses Match
 5+7	 good!
 (5+7)	 good!
 )5+7(	 BAD
 ((5+7)*3)	 good!
 <{5+7}*3>	 good!
 [(5+7)*]3	 good!
 (5+7)*3	 good!
 5+(7*3)	 good!
 ((5+7)*3	 BAD
 [(5+7]*3)	 BAD
 [(5+7)*3])	 BAD
 ([(5+7)*3]	 BAD
 */