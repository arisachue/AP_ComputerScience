// Name: Arisa Chue
// Date: 2/7/2019

import java.util.*;

public class TreeLab
{
   public static TreeNode root = null;
   public static String s = "XCOMPUTERSCIENCE";
   //public static String s = "XThomasJeffersonHighSchool"; 
   //public static String s = "XAComputerScienceTreeHasItsRootAtTheTop";
   //public static String s = "XA";   //comment out lines 44-46 below
   //public static String s = "XAF";  //comment out lines 44-46 below
   //public static String s = "XAFP";  //comment out lines 44-46 below
   //public static String s = "XDFZM";  //comment out lines 44-46 below 
   
   public static void main(String[] args)
   {
      root = buildTree( root, s );
      System.out.print( display( root, 0) );
   
      System.out.print("\nPreorder: " + preorderTraverse(root));
      System.out.print("\nInorder: " + inorderTraverse(root));
      System.out.print("\nPostorder: " + postorderTraverse(root));
   
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Only children = " + countOnlys(root));
      System.out.println("Grandparents = " + countGrandParents(root));
   
      System.out.println("\nHeight of tree = " + height(root));
      System.out.println("Longest path = " + longestPath(root));
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
   
      System.out.println("\nBy Level: ");
      System.out.println(displayLevelOrder(root));
   }
 
   public static TreeNode buildTree(TreeNode root, String s)
   {
      root = new TreeNode("" + s.charAt(1), null, null);
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, 
            (int)(1 + Math.log(pos) / Math.log(2)));
      insert(root, "A", 17, 5); 
      insert(root, "B", 18, 5); 
      insert(root, "C", 37, 6); //B's right child
      return root;
   }

   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
      {
         if((pos & (1 << k)) == 0)
            p = p.getLeft();
         else
            p = p.getRight();
      }
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   }
   
   private static String display(TreeNode t, int level)
   {
      // turn your head towards left shoulder visualize tree
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
   
   public static String preorderTraverse(TreeNode t)
   { 
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(t.getLeft());   //recurse left
      toReturn += preorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public static String inorderTraverse(TreeNode t)
   {
      String str = "";
      if(t == null) 
         return "";
      str += inorderTraverse(t.getLeft());	       						 		//recurse left
      str += t.getValue() + " ";      				 					//inorder visit
      str += inorderTraverse(t.getRight());          								//recurse right
      return str;
   }
   
   public static String postorderTraverse(TreeNode t)
   {
      String str = "";
      if(t == null)
         return "";
      str += postorderTraverse(t.getLeft());
      str += postorderTraverse(t.getRight());
      str += t.getValue() + " ";
      return str;
   }
   
   public static int countNodes(TreeNode t)
   {
      if(t == null)
         return 0;
      return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
   }
   
   public static int countLeaves(TreeNode t)
   {
      if(t.getRight() == null)
         return 1;
      if(t.getLeft() == null)
         return 1;
      return countLeaves(t.getRight()) + countLeaves(t.getLeft());
   }
   
   /*  there are clever ways and hard ways to count grandparents  */ 
   public static int countGrandParents(TreeNode t)
   {
      if(t == null)
         return 0;
      if(t.getRight() != null)
      {
         if(t.getRight().getLeft() != null || t.getRight().getRight() != null)
            return 1 + countGrandParents(t.getRight()) + countGrandParents(t.getLeft());
      }
      if(t.getLeft() != null)
      {
         if(t.getLeft().getLeft() != null || t.getLeft().getRight() != null)
            return 1 + countGrandParents(t.getRight()) + countGrandParents(t.getLeft());
      }
      
      return countGrandParents(t.getRight()) + countGrandParents(t.getLeft());
   }
   
   public static int countOnlys(TreeNode t)
   {
      if(t == null)
         return 0;
      if(t.getLeft() == null && t.getRight() != null)
         return 1 + countOnlys(t.getRight());
      if(t.getRight() == null && t.getLeft() != null)
         return 1 + countOnlys(t.getLeft());
      return countOnlys(t.getRight()) + countOnlys(t.getLeft());
   }
   
   /* Returns the max of the heights on both sides of the tree
	 */
   public static int height(TreeNode t)
   {
      if(t == null)
         return -1;
      
      return Math.max(1 + height(t.getRight()), 1 + height(t.getLeft()));
   }
   
   /* Returns the max of sum of heights on both sides of tree
	 */   
   public static int longestPath(TreeNode t)
   {
      // if(t == null)
   //          return -1;
   //       if(t.getLeft() == null)
   //          return 1;
   //       if(t.getRight() == null)
   //          return 1;
   //       int left = Math.max(1 + longestPath(t.getLeft().getLeft()), 1 + longestPath(t.getLeft().getRight()));
   //       int right = Math.max(1 + longestPath(t.getRight().getLeft()), 1+ longestPath(t.getRight().getRight()));
   //       return left + right;
      if(t == null)
         return -1;
      if(t.getRight() == null)
         return -1;
      if(t.getLeft() == null)
         return -1;
      int root = height(t.getLeft()) + height(t.getRight()) + 2;
      int right = Math.max(1 + longestPath(t.getRight().getLeft()), 1 + longestPath(t.getRight().getRight()));
      int left = Math.max(1 + longestPath(t.getLeft().getLeft()), 1 + longestPath(t.getLeft().getRight()));
      return Math.max( root, Math.max(right, left));
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object min(TreeNode t)
   {
      if(t == null)
         return null;
      if(t.getRight() == null)
      {
         if(t.getLeft() == null)
            return t.getValue();
      }   
      Comparable root = (Comparable)t.getValue();
      Comparable right = (Comparable)min(t.getRight());
      Comparable left = (Comparable)min(t.getLeft());
         
      if(left != null && right == null)
      {
         if(left.compareTo(root) < 0)
            return left;
         else 
            return root;
      }
      if(right != null && left == null)
      {
         if(right.compareTo(root) < 0)
            return right;
         else
            return root;
      }
      if(left != null && right != null)
      {
         if(left.compareTo(root) < 0 && left.compareTo(right) < 0)
            return left;
         if(right.compareTo(root) < 0 && right.compareTo(left) < 0)
            return right;
      }
   
      return root;
   //return null;
   
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object max(TreeNode t)
   {
   //     if(t.getValue() == null)
   //          return null;
   //       if(t.getLeft() == null && t.getRight() != null)
   //       {
   //          Comparable root = (Comparable)t.getValue();
   //          Comparable right = (Comparable)min(t.getRight());
   //          if(root.compareTo(right) >= 0 )
   //             return root;
   //          return right;
   //       
   //       }
   //       else if(t.getRight() == null && t.getLeft() != null)
   //       {
   //          Comparable root = (Comparable)t.getValue();
   //          Comparable left = (Comparable)min(t.getLeft());
   //          if(root.compareTo(left) >= 0)
   //             return root;
   //          return left;
   //       }
   //       else if(t.getRight() == null && t.getLeft() == null)
   //       {
   //          Comparable root = (Comparable)t.getValue();
   //          return root;
   //       }
   //       
   //       Comparable root = (Comparable)t.getValue();
   //       Comparable left = (Comparable)min(t.getLeft());
   //       Comparable right = (Comparable)min(t.getRight());
   //       
   //       if(root.compareTo(left) >= 0 && root.compareTo(right) >= 0)
   //          return root;
   //       if(left.compareTo(right) >= 0 && left.compareTo(root) >= 0)
   //          return left;
   //       return right;
      if(t == null)
         return null;
      if(t.getRight() == null)
      {
         if(t.getLeft() == null)
            return t.getValue();
      }   
      Comparable root = (Comparable)t.getValue();
      Comparable right = (Comparable)max(t.getRight());
      Comparable left = (Comparable)max(t.getLeft());
         
      if(left != null && right == null)
      {
         if(left.compareTo(root) > 0)
            return left;
         else 
            return root;
      }
      if(right != null && left == null)
      {
         if(right.compareTo(root) > 0)
            return right;
         else
            return root;
      }
      if(left != null && right != null)
      {
         if(left.compareTo(root) > 0 && left.compareTo(right) > 0)
            return left;
         if(right.compareTo(root) > 0 && right.compareTo(left) > 0)
            return right;
      }
   
      return root;
   }
      
   /* This method is not recursive.  Use a local queue
    * to store the children of the current TreeNode.
    */
   public static String displayLevelOrder(TreeNode t)
   {
      Queue<TreeNode> q = new LinkedList<TreeNode>();
      q.add(t);
      String str = "";
      while(q.isEmpty() == false)
      {
         TreeNode node = q.remove();
         
         if(node.getLeft() != null)
            q.add(node.getLeft());
         if(node.getRight() != null)
            q.add(node.getRight());
         str += node.getValue();
      }
      //str = str.substring(0, str.length()-1);
      return str;
   }
}

/***************************************************
    ----jGRASP exec: java TreeLab
 		  	E
 		E
 			C
 	M
 			N
 		T
 			E
 C
 			I
 		U
 			C
 	O
 			S
 					C
 				B
 		P
 				A
 			R
 
 Preorder: C O P R A S B C U C I M T E N E C E 
 Inorder: R A P B C S O C U I C E T N M C E E 
 Postorder: A R C B S P C I U O E N T C E E M C 
 
 Nodes = 18
 Leaves = 8
 Only children = 3
 Grandparents = 5
 
 Height of tree = 5
 Longest path = 8
 Min = A
 Max = U
 
 By Level: 
 COMPUTERSCIENCEABC    
 *******************************************************/
