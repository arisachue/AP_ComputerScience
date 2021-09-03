// Name: Arisa Chue              Date: 4/9/2019
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode root = new TreeNode("");
      
      while(huffLines.hasNext())
      {
         String line = huffLines.nextLine();
         TreeNode p = root; //keep pointer at root after each line
         if(line.length() != 0) //make sure that line is not empty
         {
            String letter = line.substring(0,1); //letter (the leaf)
            line = line.substring(1); //the rest of 0 and 1
            while(line.length() != 0) //keep going 
            {
               String num = line.substring(0, 1);
               line = line.substring(1);
               if(num.equals("0"))
               {
                  if(line.length() == 0) //if last number on the string then add the letter instead of null
                     p.setLeft(new TreeNode(letter));
                  else if(p != null && p.getLeft() == null)
                  
                     p.setLeft(new TreeNode("")); 
                     
                  p = p.getLeft();
                  
               }
               else
               {
                  if(line.length() == 0) //if last number on the string then add the letter instead of null
                     p.setRight(new TreeNode(letter));
                  else if(p != null && p.getRight() == null)
                  
                     p.setRight(new TreeNode(""));
                  p = p.getRight();
                  
               }  
            }
         }
      }
      return root;
   }
   public static String dehuff(String text, TreeNode root)
   {
      TreeNode pointer = root;
      String word = "";
      while(text.length() >0)
      {
         String val = (String)pointer.getValue();
         String n = "10";
         if(!n.contains(val))
         {
            word += val;
            pointer = root;
         }
         String num = text.substring(0,1);
         text = text.substring(1);
         if(num.equals("1"))
            pointer = pointer.getRight();
         else
            pointer = pointer.getLeft();
      }
      return word;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
