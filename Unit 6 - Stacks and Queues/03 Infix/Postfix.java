// Name: Arisa Chue
// Date: 1/8/2019

import java.util.*;

public class Postfix
{
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
      postfixExp.add("3 4 5 * +");		
      postfixExp.add("3 4 * 5 +");		
      postfixExp.add("10 20 + -6 6 * +");		
      postfixExp.add("3 4 + 5 6 + *");		
      postfixExp.add("3 4 5 + * 2 - 5 /");		
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("2 3 ^");		
      postfixExp.add("20 3 %");		
      postfixExp.add("21 3 %");		
      postfixExp.add("22 3 %");		
      postfixExp.add("23 3 %");		
      postfixExp.add("5 !");		
      postfixExp.add("1 1 1 1 1 + + + + !");		
   
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static int eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      /*  enter your code here  */
      Stack<Integer> stk = new Stack<Integer>();
      for(int x=0; x<postfixParts.size(); x++)
      {
         if(!isOperator(postfixParts.get(x)))
            stk.push(Integer.parseInt(postfixParts.get(x)));
         else if(postfixParts.get(x).equals("!"))
         {
            int a = stk.pop();
            int ans = a;
            for(int y=(a-1); y>0; y--)
            {
               ans = ans*y;
            }
            stk.push(ans);
         }
         else
         {
            int b = stk.pop();
            int a = stk.pop();
            int ans = eval(a, b, postfixParts.get(x));
            stk.push(ans);
         }
      }
      return stk.pop();
   }
   
   public static int eval(int a, int b, String ch)
   {
      if(ch.equals("+"))
         return a+b;
      if(ch.equals("-"))
         return a-b;
      if(ch.equals("*"))
         return a*b;
      if(ch.equals("/"))
         return a/b;
      if(ch.equals("^"))
      {
         double aa = (double)a;
         double bb = (double)b;
         return (int)Math.pow(aa,bb);
      }
      else
      {
         return a%b;
      }
   
   }
   
   public static boolean isOperator(String op)
   {
      String str = "+*-/^%!";
      if(str.contains(op))
         return true;
      return false;
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/