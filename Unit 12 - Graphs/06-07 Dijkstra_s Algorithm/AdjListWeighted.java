// Name:  Arisa Chue 
// Date: 5/28/2019
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs6: Dijkstra
 * and Graphs7: Dijkstra with Cities
 */

class Edge 
{
   //public fields not common on AP exam
   public final wVertex target;  
   public final double weight;   
  
   public Edge(wVertex argTarget, double argWeight) 
   {
      target = argTarget;
      weight = argWeight;
   }
}

interface wVertexInterface 
{
   String getName();
   double getMinDistance();
   void setMinDistance(double m);
   wVertex getPrevious();   //for Dijkstra 7
   void setPrevious(wVertex v);  //for Dijkstra 7
   ArrayList<Edge> getAdjacencies();
   void addEdge(wVertex v, double weight);   
   int compareTo(wVertex other);
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   private wVertex previous;  //for building the actual path in Dijkstra 7
   
   /*  enter your code for this class here   */
   public wVertex(String n)
   {
      name = n;
      adjacencies = new ArrayList<Edge>();
   } 
   public void setPrevious(wVertex v)
   {
      previous = v;
   }
   public wVertex getPrevious()
   {
      return previous;
   }
   public String getName()
   {
      return name;
   }
   public double getMinDistance()
   {
      return minDistance;
   }
   public void setMinDistance(double m)
   {
      minDistance = m;
   }
   public ArrayList<Edge> getAdjacencies()
   {
      return adjacencies;
   }
   public void addEdge(wVertex v, double weight)
   {
      //Edge e = new Edge(v, weight);
      adjacencies.add(new Edge(v, weight));
      
   }
   public int compareTo(wVertex other)
   {
      if(minDistance > other.getMinDistance())
         return 1;
      if(minDistance < other.getMinDistance())
         return -1;
      return 0;
   }
}

interface AdjListWeightedInterface 
{
   List<wVertex> getVertices();
   Map<String, Integer> getNameToIndex();
   wVertex getVertex(int i);   
   wVertex getVertex(String vertexName);
   void addVertex(String v);
   void addEdge(String source, String target, double weight);
   void minimumWeightPath(String vertexName);   //Dijkstra's
}

/* Interface for Graphs 7:  Dijkstra with Cities 
 */
 
interface AdjListWeightedInterfaceWithCities 
{       
   List<String> getShortestPathTo(wVertex v);
   AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException ;
}


public class AdjListWeighted implements AdjListWeightedInterface, AdjListWeightedInterfaceWithCities
{
   private List<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
   //the constructor is a no-arg constructor 
  
   /*  enter your code for Graphs 6 */ 
   public List<wVertex> getVertices()
   {
      return vertices;
   }
   
   public Map<String, Integer> getNameToIndex()
   {
      return nameToIndex;
   }
   public wVertex getVertex(int i)
   {
      return vertices.get(i);
   }   
   public wVertex getVertex(String vertexName)
   {
      int index = nameToIndex.get(vertexName);
      return vertices.get(index);
   }
   public void addVertex(String v)
   {
      wVertex ver = new wVertex(v);
      vertices.add(ver);
      nameToIndex.put(v, vertices.size()-1);
   }
   public void addEdge(String source, String target, double weight)
   {
      int s,t;
      if(nameToIndex.containsKey(target))
         getVertex(source).addEdge(getVertex(target), weight);
      else
      {
         addVertex(target);
         getVertex(source).addEdge(getVertex(target), weight);
      }
         // s = nameToIndex.get(source);
   //       else
   //       {
   //          addVertex(source);
   //          s = nameToIndex.get(source);
   //       }
   //       if(nameToIndex.containsKey(target))
   //          t = nameToIndex.get(target);
   //       else
   //       {
   //          addVertex(target);
   //          t = nameToIndex.get(target);
   //       }
   //    //       Vertex v = ;
   //    //       Vertex tar = ;
   //       (vertices.get(s)).addEdge(vertices.get(t), weight);
   
   }
   public void minimumWeightPath(String vertexName)
   {
      // PriorityQueue<wVertex> q = new PriorityQueue<wVertex>();
   //      // q.add(getVertex(vertexName));
   //       wVertex v = getVertex(vertexName);
   //       double min = 0.0;
   //       v.setMinDistance(min);
   //       q.add(v);
   //       
   //       while(!q.isEmpty())
   //       {
   //          wVertex ver = q.remove();
   //          ArrayList<Edge> a = ver.getAdjacencies();
   //          for(int x=0; x< a.size(); x++)
   //          {
   //             wVertex vert = a.get(x).target;
   //             if(vert.getMinDistance() > (ver.getMinDistance() + a.get(x).weight))
   //                vert.setMinDistance(ver.getMinDistance() + a.get(x).weight);
   //             q.add(vert);
   //          }
   //       }
   
      PriorityQueue<wVertex> q = new PriorityQueue<wVertex>();
     // q.add(getVertex(vertexName));
      wVertex v = getVertex(vertexName);
      double min = 0.0;
      v.setMinDistance(min);
      q.add(v);
      
      while(!q.isEmpty())
      {
         wVertex ver = q.remove();
         ArrayList<Edge> a = ver.getAdjacencies();
         for(int x=0; x< a.size(); x++)
         {
            wVertex vert = a.get(x).target;
            double dis = vert.getMinDistance();
            double max = Double.POSITIVE_INFINITY;
            double vdis = ver.getMinDistance();
            if(dis == max)//> (ver.getMinDistance() + a.get(x).weight))
               q.add(vert);
            if(dis > vdis + a.get(x).weight)
            {
               vert.setMinDistance(ver.getMinDistance() + a.get(x).weight);
               vert.setPrevious(ver);
            }
           // q.add(vert);
         }
      }
   
   }
   /*  enter your code for two new methods in Graphs 7 */
   public List<String> getShortestPathTo(wVertex v)
   {
      // PriorityQueue<wVertex> q = new PriorityQueue<wVertex>();
   //      // q.add(getVertex(vertexName));
   //       wVertex v = getVertex(vertexName);
   //       double min = 0.0;
   //       v.setMinDistance(min);
   //       q.add(v);
   //       
   //       while(!q.isEmpty())
   //       {
   //          wVertex ver = q.remove();
   //          ArrayList<Edge> a = ver.getAdjacencies();
   //          for(int x=0; x< a.size(); x++)
   //          {
   //             wVertex vert = a.get(x).target;
   //             double dis = vert.getMinDistance();
   //             double max = Double.POSITIVE_INFINITY;
   //             double vdis = ver.getMinDistance();
   //             if(dis == max)//> (ver.getMinDistance() + a.get(x).weight))
   //                q.add(vert);
   //             if(dis > vdis + a.get(x).weight)
   //             {
   //                vert.setMinDistance(ver.getMinDistance() + a.get(x).weight);
   //                vert.setPrevious(q);
   //             }
   //             q.add(vert);
   //          }
   //       }
      List<String> list = new ArrayList<String>();
      List<String> result = new ArrayList<String>();
      
      while(! (v==null))
      {
         list.add(v.getName());
         v =v.getPrevious();
      }
      int size = list.size()-1;
      for(int x= size; x > -1; x--)
      {
         String ver = list.get(x);
         result.add(ver);
      }
      return result;
   }
   public AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException
   {
      Scanner vScanner = new Scanner((vertexNames));
      Scanner eScanner = new Scanner((edgeListData));
      int s = vScanner.nextInt();
      AdjListWeighted a = new AdjListWeighted();
      for(int x=0; x<s; x++)
      {
         a.addVertex(vScanner.next());
      }
   
      while(eScanner.hasNext())
      {
         String v = eScanner.next();
         String e = eScanner.next();
         double w = eScanner.nextDouble();
        //  if(!nameToIndex.containsKey(v))
      //             a.addVertex(v);
         a.addEdge(v, e, w);
        //  if(!nameToIndex.containsKey(e))
      //             a.addVertex(e);
      }
      return a;
   
   }
   
}