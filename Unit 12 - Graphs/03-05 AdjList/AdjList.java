// Name:   Arisa Chue
// Date: 4/27/2019
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 * Graphs4: DFS-BFS
 * and Graphs5: EdgeListCities
 */

/* Graphs 3: EdgeList 
 */
interface VertexInterface
{
   String toString(); // Don't use commas in the list.  Example: "C [C D]"
   String getName();
   ArrayList<Vertex> getAdjacencies();
   void addEdge(Vertex v);
} 

class Vertex implements VertexInterface 
{
   private final String name;
   private ArrayList<Vertex> adjacencies;
  
  /* enter your code here  */
   public Vertex(String n)
   {
      name = n;
      adjacencies = new ArrayList<Vertex>();
   }
   public Vertex(String n, ArrayList<Vertex> a)
   {
      name = n;
      adjacencies = a;
   }
   public String toString()
   {
      String str = "";
      str += name + " [";
      for(Vertex v : adjacencies)
      {
         String temp = v.getName();
         str+= temp + " ";
      }
      str = str.substring(0, str.length()-1);
      str+= "]";
      return str;
   }
   public String getName()
   {
      return name;
   }
   public ArrayList<Vertex> getAdjacencies()
   {
      return adjacencies;
   }
   public void addEdge(Vertex v)
   {
      adjacencies.add(v);
   }
 
}   

interface AdjListInterface 
{ 
   List<Vertex> getVertices();
   Vertex getVertex(int i) ;
   Vertex getVertex(String vertexName);
   Map<String, Integer> getVertexMap();
   void addVertex(String v);
   void addEdge(String source, String target);
   String toString();  //returns all vertices with their edges (omit commas)
}

  
/* Graphs 4: DFS and BFS 
 */
interface DFS_BFS
{
   List<Vertex> depthFirstSearch(String name);
   List<Vertex> depthFirstSearch(Vertex v);
   List<Vertex> breadthFirstSearch(String name);
   List<Vertex> breadthFirstSearch(Vertex v);
   //List<Vertex> depthFirstRecur(String name);
   //List<Vertex> depthFirstRecur(Vertex v);
   //void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/* Graphs 5: Edgelist with Cities 
 */
interface EdgeListWithCities
{
   void graphFromEdgeListData(String fileName) throws FileNotFoundException;
   int edgeCount();
   boolean isReachable(String source, String target);
   boolean isFullyReachable();
}


public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();
  
 /* enter your code here  */
   public AdjList()
   {
      vertices = new ArrayList<Vertex>();
      nameToIndex = new TreeMap<String, Integer>();
   }
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException
   {
      Scanner infile = new Scanner(new File(fileName));
      while(infile.hasNext())
      {
         String v = infile.next();
         String e = infile.next();
         if(!nameToIndex.containsKey(v))
            addVertex(v);
         addEdge(v, e);
         if(!nameToIndex.containsKey(e))
            addVertex(e);
      }
      
   }
   public int edgeCount()
   {
      int count = 0;
      for(int x=0; x<vertices.size(); x++)
      {
         ArrayList<Vertex> a = (vertices.get(x)).getAdjacencies();
         count += a.size();
      }
      return count;
   }
   public boolean isReachable(String source, String target)
   {
      List<Vertex> a = depthFirstSearch(source);
      for(Vertex v : a)
      {
         if((v.getName()).equals(target))
            return true;
      }
      return false;
   }
   public boolean isFullyReachable()
   {
      for(int x=0; x<vertices.size(); x++)
      {
         for(int y =0; y<vertices.size(); y++)
         {
            if(!isReachable((vertices.get(x)).getName(), (vertices.get(y)).getName()))
               return false;
         }
      }
      return true;
   }
   public List<Vertex> depthFirstSearch(String name)
   {
      Stack<Vertex> stk = new Stack<Vertex>();
      List<Vertex> list = new ArrayList<Vertex>();
      // String n = v.getName();
   //       int index = nameToIndex.get(n);
   //       stk.push(vertices.get(index));
      // stk.push(v);
   //       stk.pop();
      Vertex v = getVertex(name);
      list.add(v);
      ArrayList<Vertex> a = v.getAdjacencies();
      for(int x =0; x<a.size(); x++)
      {
         stk.push(getVertex((a.get(x)).getName()));
      }
   
      while(!stk.isEmpty())
      {
         Vertex ver = stk.pop();
         if(!list.contains(ver))
         {
            list.add(ver);
            ArrayList<Vertex> al = ver.getAdjacencies();
            for(int x =0; x<al.size(); x++)
            {
               stk.push(getVertex((al.get(x)).getName()));
            }
         }
      }
      return list;
   }
   public List<Vertex> depthFirstSearch(Vertex v)
   {
      Stack<Vertex> stk = new Stack<Vertex>();
      List<Vertex> list = new ArrayList<Vertex>();
      // String n = v.getName();
   //       int index = nameToIndex.get(n);
   //       stk.push(vertices.get(index));
      // stk.push(v);
   //       stk.pop();
      list.add(v);
      ArrayList<Vertex> a = v.getAdjacencies();
      for(int x =0; x<a.size(); x++)
      {
         stk.push(getVertex((a.get(x)).getName()));
      }
   
      while(!stk.isEmpty())
      {
         Vertex ver = stk.pop();
         if(!list.contains(ver))
         {
            list.add(ver);
            ArrayList<Vertex> al = ver.getAdjacencies();
            for(int x =0; x<al.size(); x++)
            {
               stk.push(getVertex((al.get(x)).getName()));
            }
         }
      }
      return list;
   }
   public List<Vertex> breadthFirstSearch(String name)
   {
      Queue<Vertex> q = new LinkedList<Vertex>();
      List<Vertex> list = new ArrayList<Vertex>();
      q.add(getVertex(name));
      ArrayList<Vertex> a = (getVertex(name)).getAdjacencies();
      for(int x =0; x<a.size(); x++)
      {
         q.add(getVertex((a.get(x)).getName()));
      }
      while(!q.isEmpty())
      {
         Vertex ver = q.remove();
         if(!list.contains(ver))
         {
            list.add(ver);
            ArrayList<Vertex> al = ver.getAdjacencies();
            for(int x =0; x<al.size(); x++)
            {
               q.add(getVertex((al.get(x)).getName()));
            }
         }
      }
      return list;
   }
   public List<Vertex> breadthFirstSearch(Vertex v)
   {
      // String name = v.getName();
   //       int index = nameToIndex.get(name);
      Queue<Vertex> q = new LinkedList<Vertex>();
      List<Vertex> list = new ArrayList<Vertex>();
      q.add(v);
      ArrayList<Vertex> a = v.getAdjacencies();
      for(int x =0; x<a.size(); x++)
      {
         q.add(getVertex((a.get(x)).getName()));
      }
      while(!q.isEmpty())
      {
         Vertex ver = q.remove();
         if(!list.contains(ver))
         {
            list.add(ver);
            ArrayList<Vertex> al = ver.getAdjacencies();
            for(int x =0; x<al.size(); x++)
            {
               q.add(getVertex((al.get(x)).getName()));
            }
         }
      }
      return list;
   }

   public List<Vertex> getVertices()
   {
      return vertices;
   }
   public Vertex getVertex(int i)
   {
      return vertices.get(i);
   }
   public Vertex getVertex(String vertexName)
   {
      int index = nameToIndex.get(vertexName);
      return vertices.get(index);
   }
   public Map<String, Integer> getVertexMap()
   {
      return nameToIndex;
   }
   public void addVertex(String v)
   {
      Vertex ver = new Vertex(v);
      vertices.add(ver);
      nameToIndex.put(v, vertices.size()-1);
   }
   public void addEdge(String source, String target)
   {
      int s,t;
      if(nameToIndex.containsKey(source))
         s = nameToIndex.get(source);
      else
      {
         addVertex(source);
         s = nameToIndex.get(source);
      }
      if(nameToIndex.containsKey(target))
         t = nameToIndex.get(target);
      else
      {
         addVertex(target);
         t = nameToIndex.get(target);
      }
   //       Vertex v = ;
   //       Vertex tar = ;
      (vertices.get(s)).addEdge(vertices.get(t));
    //   Vertex v = new Vertex(target);
   //       getVertex(source).addEdge(v);
   }
         
   public String toString()
   {
      String str = "";
      for(String s : nameToIndex.keySet())
      {
         // int index = nameToIndex.get(s);
      //          Vertex v = vertices.get(index);
         str += getVertex(s).toString();
         str += "\n";
      }
      return str;
   }
}


