import java.util.*;
public class PracticeIt
{
   public static void main(String[] args)
   {
      Map<String, String> s = new HashMap<String, String>();
      s.put("Mary", "Perkins");
      s.put("Joan", "Reges");
      s.put("Dennis", "Miller");
      s.put("Bob", "Reges");
      s.put("MaryAnn", "Perkins");
      boolean re = isUnique(s);
      System.out.print(re);
   }
   public static boolean isUnique(Map<String, String> m)
   {
      if(m == null)
         return true;
      String[] array = new String[m.size()];
      int x =0;
      for(String s : m.keySet())
      {
         array[x] = s;
         x++;
      }
      for(int t=0; t<array.length; t++)
      {
         String temp = array[t];
         array[t] = "";
         for(int y=0; y<m.size(); y++)
         {
            if(array[y].equals(temp))
               return false;
         }
         array[t] = temp;
      }
      return true;
   }
}