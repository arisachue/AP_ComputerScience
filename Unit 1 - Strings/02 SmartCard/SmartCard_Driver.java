// Name: Arisa Chue
// Date: 8/30/2018
// J2-04
 
import java.text.DecimalFormat;
public class SmartCard_Driver
{
    public static void main(String[] args) 
    {
        Station downtown = new Station("Downtown", 1);
        Station center = new Station("Center City", 1);
        Station uptown = new Station("Uptown", 2);
        Station suburbia = new Station("Suburb", 4);
     
        SmartCard jimmy = new SmartCard(20.00); 
        jimmy.board(center);                    //Boarded at Center City.  SmartCard has $20.00
        jimmy.disembark(suburbia);              //From Center City to Suburb costs $2.75.  SmartCard has $17.25
        jimmy.disembark(uptown);				//Error:  did not board?!
        System.out.println();
   			
        SmartCard susie = new SmartCard(1.00); 
        susie.board(uptown);            		//Boarded at Uptown.  SmartCard has $1.00
        susie.disembark(suburbia);				//Insuffient funds to exit. Please add more money.
        System.out.println();
    
        SmartCard kim = new SmartCard(.25);    
        kim.board(uptown);            		    //Insuffient funds to board. Please add more money.
        System.out.println();
    
        SmartCard b = new SmartCard(10.00);   
        b.board(center);            		    //Boarded at Center City.  SmartCard has $10.00
        b.disembark(downtown);					//From Center City to Downtown costs $0.50.  SmartCard has $9.50
        System.out.println();
          
        SmartCard mc = new SmartCard(10.00);  
        mc.board(suburbia);            		    //Boarded at Suburbia.  SmartCard has $10.00
        mc.disembark(downtown);					//From Suburbia to Downtown costs $2.75.  SmartCard has $7.25
        System.out.println();
      
        //Make more test cases.  What else needs to be tested?
        // SmartCard arisa = new SmartCard(10.00);  
//         arisa.board(suburbia);            		    //Boarded at Suburbia.  SmartCard has $10.00
//         arisa.board(downtown);					//From Suburbia to Downtown costs $2.75.  SmartCard has $7.25
//         System.out.println();
           //  SmartCard arisa = new SmartCard(10.00);  
//         arisa.disembark(suburbia);            		    //Boarded at Suburbia.  SmartCard has $10.00
//         arisa.disembark(downtown);					//From Suburbia to Downtown costs $2.75.  SmartCard has $7.25
//         System.out.println();

    }
} 	

//Note SmartCard is not denoted as public.  Why?
class SmartCard 
{
    public final static DecimalFormat df = new DecimalFormat("$0.00");
    public final static double MIN_FARE = 0.5;
    public final static double OZ = 0.75;
    /* enter your code below */
    private Station station;
    private double rM;
    private boolean present;
    
    public SmartCard(double m)
    {
      this.rM = m;
    }
    public void addMoney(double d)
    {
      this.rM += d;
    }
    public String getBalance()
    {
      return df.format(this.rM);
    }
    public boolean isBoarded()
    {
      if(this.present == true)
         return true;
      else
         return false;
    }
    public int board(Station s)
    {
      if(isBoarded() == true)
      {
         System.out.println("Error: already boarded!");
         return -1;
      }
      else if(this.rM < 0.5)
      {
         System.out.println("Insufficient funds to board. Please add more money.");
         return -1;
      }
      else
      {
         this.station = s;
         this.present = true;
         System.out.println("Boarded at " + s.getName() + ".  SmartCard has " + getBalance());
         return 1;
      } 
    }
    public double cost(Station s)
    {
      if((s.getZone()) == (this.station.getZone()))
         return 0.5;
      else
      {
         int dZone = Math.abs((s.getZone())-(this.station.getZone()));
         return 0.75 * dZone;
      }
    }  
    
    public int disembark(Station s)
    {
      double cost = cost(s);
      String finC = df.format(cost);
      if(isBoarded() == false)
      {
         System.out.println("Error: did not board?!");
         return -1;
      }
      else if((this.rM - cost) < 0)
      {
         System.out.println("Insufficient funds to exit. Please add more money.");
         return -1;
      }
      else
      {
         this.rM -= cost;
         this.present = false;
         System.out.println("From " + this.station.getName() + " to " + s.getName() + " costs " + finC + ".  SmartCard has " + getBalance());
         return 1;
      }
    }  
      
}
   
//Note Station is not a public class.  Why?
class Station
{
      private String name;
      private int zone;
      
      public Station()
      {
         this.name = "Center City";
         this.zone = 1;
      }
      
      public Station(String n, int z)
      {
         this.name = n;
         this.zone = z;
      }
      public String getName()
      {
         return this.name;
      }
      public int getZone()
      {
         return this.zone;
      }
      public void setName(String n)
      {
         this.name = n;
      }
      public void setZone(int n)
      {
         this.zone = n;
      }
      public String toString()
      {
         return "Station is: " + this.name + "Zone is: " + this.zone;
      }

}

 /*******************  Sample Run ************

 Boarded at Center City.  SmartCard has $20.00
 From Center City to Suburb costs $2.75.  SmartCard has $17.25
 Error: did not board?!
 
 Boarded at Uptown.  SmartCard has $1.00
 Insufficient funds to exit. Please add more money.
 
 Insufficient funds to board. Please add more money.
 
 Boarded at Center City.  SmartCard has $10.00
 From Center City to Downtown costs $0.50.  SmartCard has $9.50
 
 Boarded at Suburb.  SmartCard has $10.00
 From Suburb to Downtown costs $2.75.  SmartCard has $7.25
 
 ************************************************/