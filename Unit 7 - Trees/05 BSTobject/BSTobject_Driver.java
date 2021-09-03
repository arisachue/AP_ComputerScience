// Name: Arisa Chue
// Date: 2/23/2019

import java.util.*;
import java.io.*;

public class BSTobject_Driver
{
   public static BSTobject<String> tree = null;
   public static BSTobject<Widget> treeOfWidgets = null;
   public static int numberOfWidgets = 10;
   public static void main( String[] args ) 
   {
      // Day one 
      tree = new BSTobject<String>();
      tree = build(tree, "T");
      System.out.print(tree);
      System.out.println("Size: " + tree.size());
      System.out.println("Is empty: "+ tree.isEmpty());
   		
   	// Day two
   	// Your assignment the second day is to finish the BSTobject class.  
   	// Specifically, prompt the user for a string, put the characters into 
      // a BST, call toString on this tree, and print the size of the tree.
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter a String: ");
      String str = sc.nextLine();
      tree = new BSTobject<String>();
      tree = build(tree, str);
      System.out.print(tree.toString());
      System.out.println("Size: " + tree.size());
   	
      // Day two, Widgets			
   	// Next, fill your BST with 10 Widget objects from widgets.txt.  Display 
      // the tree. Then prompt the user to enter cubits and hands.  If the tree 
      // contains that Widget, delete it, of course restoring the BST order. 
      // Display the new tree and its size. If the tree does not contain that 
      // Widget, print "Not found".*/
      treeOfWidgets = new BSTobject<Widget>(); 
      treeOfWidgets = build(treeOfWidgets, new File("widgets.txt"));
      System.out.print(treeOfWidgets.toString());
      System.out.println("Size: " + treeOfWidgets.size());
      Scanner sc2 = new Scanner(System.in);
      System.out.print("Delete which object?: ");
      int cub = Integer.parseInt(sc2.next());
     // System.out.println();
     // System.out.print("Input Widget's Hands: ");
      int han = Integer.parseInt(sc2.next());
     // System.out.println();
      Widget wid = new Widget(cub, han);
      if(treeOfWidgets.contains(wid))
      {
         treeOfWidgets.delete(wid);   
         System.out.print(treeOfWidgets.toString());
         System.out.println("Size: " + treeOfWidgets.size());
      }
      else
         System.out.println("Not found.");
         
   
   	// Day three -- AVL tree  -----------------------
   }
  
   /* Build the tree for Strings, Day 1
    */
   public static BSTobject<String> build(BSTobject<String> tree, String str)
   {
      while(str.length() != 0)
      {
         String temp = str.substring(0, 1);
         tree.add(temp);
         str = str.substring(1);
      }
      return tree;
        
   }
   
   /* Build the tree for Widgets, Day 2
    */
   public static BSTobject<Widget> build(BSTobject<Widget> tree, File file)
   {
      Scanner infile = null;
      try{
         infile = new Scanner( file  );
      }
      catch (Exception e)
      {
         System.out.println("File not found.");
      }       // }
      
      for(int i = 0; i < numberOfWidgets; i++)   
      {
         int cub = Integer.parseInt(infile.nextLine());
         int han = Integer.parseInt(infile.nextLine());
         Widget wid = new Widget(cub, han);
         tree.add(wid);
      
      }
      return tree;
   }
}

interface BSTinterface<E extends Comparable<E>>
{
   public E add( E element );             //returns the object
   public boolean contains( E element );
   public boolean isEmpty();
   public E delete( E element );          //returns the object, not a Node<E>
   public int size();
   public String toString();
}

class BSTobject <E extends Comparable<E>> implements BSTinterface<E>
{ 
   // Declare 2 fields 
   private Node root;
   private int size;
   // Create a default constructor
   public BSTobject()
   {
      root = null;
      size = 0;
   }
      
   //instance methods
   public E add( E obj )
   {
      root = add( root, obj );
      size++;
      return obj;
   }
   
   //recursive helper method
   private Node<E> add( Node<E> t, E obj )
   {
      if(t == null)
         return new Node(obj);
      if(obj.compareTo((E)t.getValue()) <= 0)
         t.setLeft(add(t.getLeft(), obj));
      else
         t.setRight(add(t.getRight(), obj));
      return t;
   
   }
   
   /* Implement the interface here.  Use TreeNode as an example,
    * but root is a field. You need add, contains, isEmpty, 
    * delete, size, and toString.  
    */
   public boolean contains( E element )
   {
      Node temp = root;
      while(temp != null)
      {
         int com = element.compareTo((E)temp.getValue());
         if(com == 0)
            return true;
         if(com < 0)
            temp = temp.getLeft();
         else
            temp = temp.getRight();
      }
      return false;
   }
   public boolean isEmpty()
   {
      if(root == null)
         return true;
      return false;
   }
   public E delete( E element )          //returns the object, not a Node<E>
   {
      Node current = root;  
      Node parent = null;
      size--;
      while(current !=null)
      {
         int compare = element.compareTo((E)current.getValue());
         
         if(compare == 0)
         {
            if(root.getRight() == null && root.getLeft() == null)
               return null;
            if(current.getRight() == null && current.getLeft() == null)
            {
               if((parent.getRight().getValue()).equals(current.getValue()))
                  parent.setRight(null);
               else
                  parent.setLeft(null);
               
               return (E)root.getValue();
            }
            if(current.getRight() == null || current.getLeft() == null)
            {
               if(current.getRight() == null)
               {
                  if(parent == null)
                     return (E)current.getLeft().getValue();
                  if((parent.getRight().getValue()).equals(current.getValue()))
                     parent.setRight(current.getLeft());
                  else
                     parent.setLeft(current.getLeft());
               }
               if(current.getLeft() == null)
               {
                  if(parent == null)
                     return (E)current.getRight().getValue();
                  if((parent.getRight().getValue()).equals(current.getValue()))
                     parent.setRight(current.getRight());
                  else
                     parent.setLeft(current.getRight());
               }
               return (E)root.getValue();
            }
            else
            {
               Node temp = current.getLeft();
               if(temp.getRight() == null)
               {
                  current.setValue(temp.getValue());
                  if(temp.getLeft() != null)
                  {
                     current.setLeft(temp.getLeft());
                  }
                  else
                     current.setLeft(null);
               }
               else
               {
                  while(temp.getRight() != null)
                  {
                     parent = temp;
                     temp = temp.getRight();
                  }
                  current.setValue(temp.getValue());
                  if(temp.getLeft() == null)
                     parent.setRight(null);
                  else
                     parent.setRight(temp.getLeft());
               }   
               return (E)root.getValue();
            }
             
         }
         if(compare < 0)
         {
            parent = current;
            current = current.getLeft();
         }
         else
         {
            parent = current;
            current = current.getRight();
         }
      }
      return (E)root.getValue();  //never reached
   }
   public int size()
   {
      return size;
   }
   public String toString()
   {
      return display(root, 0);
   }
   private String display(Node t, int level)
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


   /* Private inner class 
    */  
   private class Node<E>
   {
      // 3 fields 
      private E value;
      private Node left, right;
      // 2 constructors, one-arg and three-arg
      public Node(E n)
      { 
         value = n; 
         left = null; 
         right = null; 
      }
   
      public Node(E n, Node nL, Node nR)
      { 
         value = n; 
         left = nL; 
         right = nR; 
      }
   
      //methods--Use TreeNode as an example. See Quick Reference Guide.
      public E getValue()
      { 
         return value; 
      }
   
      public Node getLeft() 
      { 
         return left; 
      }
   
      public Node getRight() 
      { 
         return right; 
      }
   
      public void setValue(E theNewValue) 
      { 
         value = theNewValue; 
      }
   
      public void setLeft(Node theNewLeft) 
      { 
         left = theNewLeft;
      }
   
      public void setRight(Node theNewRight)
      { 
         right = theNewRight;
      }
   
   }
}
