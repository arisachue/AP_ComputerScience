// Name:  Arisa Chue      Date: 4/11/2019
import java.util.*;
import java.io.*;
public class Huffman
{
   public static Scanner keyboard = new Scanner(System.in);
   public static void main(String[] args) throws IOException
   {
      //Prompt for two strings 
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat message? ");
      String message = keyboard.nextLine();
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.next();
   
      huffmanize( message, middlePart );
   }
   public static void huffmanize(String message, String middlePart) throws IOException
   {
         //Make a frequency table of the letters
      	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
   		//        node into a priority queue (or a min-heap).     
      	//Use the priority queue of nodes to build the Huffman tree
      	//Process the string letter-by-letter and search the tree for the 
   		//       letter. It's recursive. As you recur, build the path through the tree, 
   		//       where going left is 0 and going right is 1.
         //System.out.println the binary message 
      	//Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
         //System.out.println the scheme from the tree--needs a recursive helper method
      	//Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")
         
         // Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
   //       String binaryCode = sc.next();
   //       Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt"));
      Map<String, Integer> freq = new HashMap<String, Integer>();
      String tempm = "";
      while(message.length() >0)
      {
         String letter = message.substring(0, 1);
         tempm += letter;
         message = message.substring(1);
         if(freq.containsKey(letter))
         {
            int val = (int)freq.get(letter);
            val++;
            freq.put(letter, val);
         }
         else
            freq.put(letter, 1);
      }
      PriorityQueue<HuffmanTreeNode> pq = new PriorityQueue<HuffmanTreeNode>();
      for(String m : freq.keySet())
      {
         int f = (int)(freq.get(m));
         pq.add(new HuffmanTreeNode(m, f));
      }
      pq = makeTree(pq);
      HuffmanTreeNode root = pq.peek();
      String binaryCode = "";
      String scheme = "";
      while(tempm.length() > 0)
      {
         String letter = tempm.substring(0,1);
         tempm = tempm.substring(1);
         String m = mes(root, letter, "");
               //String s = sch(root, letter);
         binaryCode += m;
         scheme = inputScheme(letter, m, scheme);
        // scheme += m + "\n";
      }
            //use helper to make tree
      System.out.println(binaryCode);
      PrintWriter outputFile = new PrintWriter(new FileWriter("message." + middlePart + ".txt"));
      outputFile.println(binaryCode);
      System.out.println(scheme);
      PrintWriter outputFile2 = new PrintWriter(new FileWriter("scheme." + middlePart + ".txt"));
      outputFile2.println(scheme);
     // inputFile.close(); 						
      outputFile.close();
      outputFile2.close();
   
            
   }
   public static String inputScheme(String letter, String m, String sch)
   {
      if(sch.contains(letter))
         return sch;
      sch += letter + m + "\n";
      return sch;
   }
   public static String mes(HuffmanTreeNode root, String letter, String bin)
   {
      if(root == null)
         return "";
      if(root.getValue().equals(letter))
         return bin;
      String l = mes(root.getLeft(), letter, bin + "0");
      String r = mes(root.getRight(), letter, bin + "1");
      if(r.equals(""))
         return l;
      return r;
      // if(mes(root.getLeft(), letter).equals(letter))
   //          message+= "0" + mes(root.getLeft(), letter);
   //       else if(mes(root.getRight(), letter).equals(letter))
   //          message+= "1" + mes(root.getRight(), letter);
   //       return message;
   }
   public static PriorityQueue<HuffmanTreeNode> makeTree(PriorityQueue<HuffmanTreeNode> p)
   {
      while(p.size() > 1)
      {
         HuffmanTreeNode left = p.remove();
         HuffmanTreeNode right = p.remove();
         int lf = left.getFreq();
         int lr = right.getFreq();
         HuffmanTreeNode parent = new HuffmanTreeNode("*", lf+lr, left, right);
         p.add(parent);  
      }
      return p;
   }
   
}
	/*
	  * This tree node stores two values.  Look at TreeNode's API for some help.
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private String letter;
   private int freq;
   private HuffmanTreeNode left, right;
   
   public HuffmanTreeNode(String l, int f)
   {
      letter = l;
      freq = f;
      left = null;
      right = null;
   }
   public HuffmanTreeNode(String l, int f, HuffmanTreeNode le, HuffmanTreeNode r)
   {
      letter = l;
      freq = f;
      left = le;
      right = r;
   }
   public String getValue()
   {
      return letter;
   }
   public HuffmanTreeNode getLeft()
   {
      return left;
   }
   public HuffmanTreeNode getRight()
   {
      return right;
   }
   public int getFreq()
   {
      return freq;
   }
   public int compareTo(HuffmanTreeNode other)
   {
      if(freq < other.freq)
         return -1;
      if(freq == other.freq)
         return 0;
      else
         return 1;
   }
}