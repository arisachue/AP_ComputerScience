// Name: Arisa Chue
// Date: 1/10/2019

import java.util.*;

public class Infix
{
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
     /*build your list of Infix expressions here  */
      ArrayList<String> infixExp = new ArrayList<String>();
      infixExp.add("3 + 4 * 5");			
      infixExp.add("3 * 4 + 5");
            
      infixExp.add("( -5 + 15 ) - 6 / 3");			
      infixExp.add("( 3 + 4 ) * ( 5 + 6 )");			
      infixExp.add("( 3 * ( 4 + 5 ) - 2 ) / 5");			
      infixExp.add("8 + -1 * 2 - 9 / 3");			
      infixExp.add("3 * ( 4 * 5 + 6 )");			
   
       
   
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + Postfix.eval(pf));  //Postfix must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> infixParts = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      /* enter your code here  */
      Stack<String> stk = new Stack<String>();
      String result = "";
      String op = "()*/+-";
      
      for(int x=0; x<infixParts.size(); x++)
      {
         if(!op.contains(infixParts.get(x))) //number
         {
            result += infixParts.get(x) + " ";
         }
         else if(infixParts.get(x).equals("("))
            stk.push(infixParts.get(x));
         else if(infixParts.get(x).equals(")"))
         {
            String b = stk.pop();
            while(!b.equals("("))
            {
               result += b + " ";
               b = stk.pop();
            }
            //stk.pop();
         }
         else //character is operator (not number)
         {
            if(!stk.isEmpty())
            {
               String prev = stk.pop();
               if(prev.equals("("))
               {
                  stk.push(prev);
                  stk.push(infixParts.get(x));
               }
               else if(isLower(prev.charAt(0), infixParts.get(x).charAt(0)) == false) //+*
               {
                  result += prev + " ";
                  //result += prev + " ";
                  String s1 = "+-";
                  String s2 = "*/";
                  boolean eq = false;
                  if(!stk.isEmpty())
                  {
                     prev = stk.pop();
                     if(s1.contains(prev) && s1.contains(infixParts.get(x)))
                        eq = true;
                     if(s2.contains(prev) && s2.contains(infixParts.get(x)))
                        eq = true;
                     if(stk.isEmpty() && eq == true)
                        result += prev + " ";
                     while(!prev.equals("(") && eq == true && !stk.isEmpty())
                     {
                        
                        result += prev + " ";
                        prev = stk.pop();
                        eq = false;
                        if(s1.contains(prev) && s1.contains(infixParts.get(x)))
                           eq = true;
                        if(s2.contains(prev) && s2.contains(infixParts.get(x)))
                           eq = true;
                     
                     }
                     if(eq == false)
                        stk.push(prev);
                  }
                  stk.push(infixParts.get(x));
                  
                  //while
               }
               else if(isLower(prev.charAt(0), infixParts.get(x).charAt(0)) == true) //+*
               {
                  stk.push(prev);
                  stk.push(infixParts.get(x));
               }
                                 
            }
            else if(stk.isEmpty())
               stk.push(infixParts.get(x));
            else
               result += infixParts.get(x) + " ";
         }
      }
      while(!stk.isEmpty())
         result += stk.pop() + " ";
      result = result.substring(0, result.length()-1);
      return result;
   }
   
	//returns true if c1 has lower or equal precedence than c2
   public static boolean isLower(char c1, char c2)
   {
      String high = "*/";
      String low = "+-";
      String c11 = Character.toString(c1);
      String c22 = Character.toString(c2);
      if(high.contains(c11) && low.contains(c22))
         return false;
      if(low.contains(c11) && high.contains(c22))
         return true;
      else
         return false;
      
   }
}
	
/********************************************

 Infix  	-->	Postfix		-->	Evaluate
 3 + 4 * 5			3 4 5 * +			23
 3 * 4 + 5			3 4 * 5 +			17
 ( -5 + 15 ) - 6 / 3			-5 15 + 6 3 / -			8
 ( 3 + 4 ) * ( 5 + 6 )			3 4 + 5 6 + *			77
 ( 3 * ( 4 + 5 ) - 2 ) / 5			3 4 5 + * 2 - 5 /			5
 8 + -1 * 2 - 9 / 3			8 -1 2 * + 9 3 / -			3
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78
 
***********************************************/