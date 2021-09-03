// Name: Arisa Chue
// Date: 2/9/2019

import java.util.*;

/*  Driver for a binary expression tree class.
 *  Input: a postfix string, space delimited tokens. 
 */
public class BXT_Driver
{
   public static void main(String[] args)
   {
      ArrayList<String> postExp = new ArrayList<String>();
      postExp.add("14 -5 /");
      postExp.add("20.0 3.0 -4.0 + *");
      postExp.add("2 3 + 5 / 4 5 - *");
      postExp.add("5.6");
   
      for( String postfix : postExp )
      {
         System.out.println("Postfix Exp: "  + postfix);
         BXT tree = new BXT();
         tree.buildTree( postfix );
         System.out.println("BXT: "); 
         System.out.println( tree.display() );
         System.out.print("Infix order:  ");
         System.out.println( tree.inorderTraverse() );
         System.out.print("Prefix order:  ");
         System.out.println( tree.preorderTraverse() );
         System.out.print("Evaluates to " + tree.evaluateTree());
         System.out.println( "\n------------------------");
      }
   }
}

/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
class BXT
{
   private TreeNode root;
   
   public BXT()
   {
      root = null;
   }
    
   public void buildTree(String str)
   {
      String[] array = str.split(" ");
      if(array.length == 1)
         root = new TreeNode(str);
         
      Stack<TreeNode> stk = new Stack<TreeNode>();
      for(String s : array)
      {
         if(isOperator(s) == false)
            stk.push(new TreeNode(s));
         else
         {
            TreeNode right = stk.pop();
            TreeNode left = stk.pop();
            stk.push(new TreeNode(s, left, right));
         }
      }
      root = stk.pop();
      
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      if(t.getLeft() == null && t.getRight() == null)
         return Double.parseDouble(t.getValue().toString());
      if(isOperator(root.getValue().toString()))
         return computeTerm(t.getValue().toString(), evaluateNode(t.getLeft()), evaluateNode(t.getRight()));
      else
         return Double.parseDouble(t.getValue().toString());
      
     
   }
   
   private double computeTerm(String s, double a, double b)
   {
      if(s.equals("+"))
         return a+b;
      if(s.equals("/"))
         return a/b;
      if(s.equals("-"))
         return a-b;
         
      return a*b;
     
   }
   
   private boolean isOperator(String s)
   {
      String str = "*/+-";
      if(str.contains(s))
         return true;
      return false;
    
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   
   }
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      String str = "";
      if(t == null) 
         return "";
      str += inorderTraverse(t.getLeft());	       						 		//recurse left
      str += t.getValue() + " ";      				 					//inorder visit
      str += inorderTraverse(t.getRight());          								//recurse right
      return str;
   
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toReturn = "";
      if(root == null)
         return "";
      toReturn += root.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(root.getLeft());   //recurse left
      toReturn += preorderTraverse(root.getRight());  //recurse right
      return toReturn;
   
   }

}

/***************************************

 Postfix Exp: 14 -5 /
 	-5
 /
 	14
 Infix order:  14 / -5 
 Prefix order:  / 14 -5 
 Evaluates to -2.8
 ------------------------
 Postfix Exp: 20.0 3.0 -4.0 + *
 		-4.0
 	+
 		3.0
 *
 	20.0
 Infix order:  20.0 * 3.0 + -4.0 
 Prefix order:  * 20.0 + 3.0 -4.0 
 Evaluates to -20.0
 ------------------------
 Postfix Exp: 2 3 + 5 / 4 5 - *
 		5
 	-
 		4
 *
 		5
 	/
 			3
 		+
 			2
 Infix order:  2 + 3 / 5 * 4 - 5 
 Prefix order:  * / + 2 3 5 - 4 5 
 Evaluates to -1.0
 ------------------------
 Postfix Exp: 5.6
 5.6
 Infix order:  5.6 
 Prefix order:  5.6 
 Evaluates to 5.6
 ------------------------
 
 *******************************************/