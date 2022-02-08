import java.io.File;
import java.util.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * For input, calculates profit and overflow based on coffee shop
 * parameters given from lab guide
 *
 * @Christian Hollar
 * @11/10/20
 */
public class Controller
{
    /*
     * PriorityQueue: event line
     * Queue LinkedList: wait line
     * Queue Dequeu: wait line
     * ArrayList: hold all valid customers
     * ArrayList: hold all cashiers
     */    
    ArrayList<Cashier> clist = new ArrayList<Cashier>();
    ArrayList<Customer> finalCust = new ArrayList<Customer>();
    ArrayList<Long> waitTime = new ArrayList<Long>();
    PriorityQueue<Event> pQueue = new PriorityQueue<Event>();
    Queue<Customer> waitlineLink = new LinkedList<Customer>();
    Queue<Customer> waitlineADeque = new ArrayDeque<Customer>();
    /*
     * cashierN number of Cashiers
     * cashierCost individual cost
     * 
     *timeOVA Overall Time 
     */
    int cashierN;
    double cashierCost;
    double cashierCTot;
    int overflow;
    long timeOVA;
    long maxWaitTime;

    /**
     * Runs methods: loadStudent() and cashierList() to load in all
     * attempted entries and all available cashiers
     * Calculates:
     * -Overflow
     * -Profit
     * -CashierCost
     * -Net Profit
     * -Average Time
     * -Max Time
     * -Algo Time
     */
    public Controller(int cashierN, String type,String report)
    {
        this.cashierN=cashierN;
        overflow = 0;
        loadStudent();
        cashierList();
        long start=0;
        long stop=0;
        long tottime = 0;
        /*
         * Type checks for what time of waitline should be utilized
         * 
         * start and stop calculate the time it took
         */
        if(type == "ArrayDeque")
        {
            start = System.currentTimeMillis();
            runThroughDeque();
            stop = System.currentTimeMillis();
            tottime = stop - start; 
        }
        else if(type == "LinkedList")
        {
            start = System.currentTimeMillis();
            runThroughLink();
            stop = System.currentTimeMillis();
            tottime = stop - start; 
        }
        else{
            System.out.println("unknown type requested");
        }
        /*
         * calculates total profit and total wait time then divides it by 
         * number of customers
         * 
         * same for overflow
         */
        this.cashierCTot = cashierCost*cashierN;    
        int total = overflow +finalCust.size();
        int rateO = 100*overflow/finalCust.size();
        double profit = 0;
        this.maxWaitTime = 0;
        for (int i = 0; i < finalCust.size(); i++)
        profit += finalCust.get(i).profit;
        for (int i = 0; i < waitTime.size(); i++)
        {
            timeOVA+=waitTime.get(i);
            if(waitTime.get(i)>maxWaitTime)
            maxWaitTime = waitTime.get(i);
        }
        double net = profit -cashierCTot;
        long timeAverage = (long)timeOVA/(waitTime.size()*(long)1000.0);
        System.out.println(timeAverage);
        long timeAverageM = timeAverage/60;
        long timeAverageS = timeAverage - timeAverageM*60;
        long maxTime = maxWaitTime/1000;
        int maxM = (int)maxTime/60;
        long maxS = maxTime-maxM*60;
        /*
         * max report reveals all statistics
         * 
         * other options for individual testing
         */
        if(report == "Full")
        {
            if(type == "LinkedList")
            System.out.println("Time of LinkedList: "+tottime);
            else
            System.out.println("Time of ArrayDeque: "+tottime);
            System.out.println("Overflow: "+overflow+" Customers");
            System.out.println("Number of Customers Served: "+finalCust.size()+
            " Customers");
            System.out.println("Total Attempted Customers: "+ total+" Customers");
            System.out.println("Rate of Overflow: "+rateO+"%");
            System.out.println("Customer Profit: $"+profit);
            System.out.println("Cashier Cost: $"+ cashierCost);  
            System.out.println("Cashier Amount: "+cashierN+" Cashiers");
            System.out.println("Total Cashier Cost $"+cashierCTot);
            System.out.println("Net Profit: $" + net);
            System.out.println("Average Wait Time: "+timeAverageM+" Minutes "+
            timeAverageS+" Seconds ");
            System.out.println("Max Wait Time: " + maxM + " Minutes " + maxS + " Seconds");
        }
        else if(report == "Profit")
        {
            System.out.println(net);
        }
        else if(report == "Overflow")
        {
            System.out.println(rateO);
        }
        else if(report == "Time")
        {
            System.out.println(tottime);
        }
        else
        {
            System.out.println("Unknown Report Requested");
        }
    }

    /**
     * returns a filled PriorityQueue of Customer Arrival Events
     * 
     * param void
     * returns void
     */
    public void loadStudent()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        File filename = new File
        ("/Users/christianhollar/Desktop/Computer Science/"+
        "CS150/Project2Hollar/input.txt");
        
        ArrayList<String> a = new ArrayList<String>();
        
        Scanner s = null;
        int i = 0;
        
        ArrayList<String> asetter = new ArrayList<String>();
        ArrayList<Date> dates = new ArrayList<Date>();
        /*
         * loads each file input time into a string
         */
        try{
            s = new Scanner(filename);
            while(s.hasNext())
            {
                asetter.add(s.next());
            }
            s.close();
        }catch(Exception e){
            System.out.println("Input Error Timing");
        }    
        /*
         * converts string to date
         */

        while(i<5)
        {
            if(i==2)
            this.cashierCost = Double.parseDouble(asetter.get(0));
            asetter.remove(0);
            i++;
        }
        /*
         * creates customer using date
         * creates event using customer
         * adds event of type 1 (entry)
         * to priority queue
         */
        try{
            for(int j = 0; j<asetter.size(); j++)
            {
                dates.add(sdf.parse(asetter.get(j)));
            }
        }catch(Exception e){
            System.out.println("error");
        }
        
        for(int k = 0; k<dates.size(); k++)
        {
            Customer c = new Customer(dates.get(k));
            Event e = new Event(1,c,c.enter);
            pQueue.add(e);
        }        
    }
    /**
     * sets number of cashiers in cashier arrayList
     * 
     * param void
     * return void
     */
    public void cashierList()
    {
        for(int i = 0; i<cashierN; i++)
        clist.add(new Cashier(true));
    }
    /**
      * using LinkedList
      * checks if pQueue is empty
      * Cycles through cashiers to fill from waiting line at beginning
      * 
      * if type 1
      * solves for overflow
      * adds to waiting line
      * cycles to next event
      * 
      * if type 2
      * frees cashier
      * cycles to next event
      * 
      * param void
      * returns void
      */
    public void runThroughLink()
    {
        
        while(!pQueue.isEmpty())
        {
            
            Event current = pQueue.poll();
            //cashiers check
            for(int l = 0; l<clist.size(); l++)
            {
                if(clist.get(l).status==true&&!waitlineLink.isEmpty())
                {
                    clist.get(l).status=false;
                    Customer cust = waitlineLink.poll();
                    clist.get(l).c = cust;
                    Event e =new Event(2,cust,cust.exitTime(current.d));
                    e.cashV = l;
                    pQueue.add(e);
                    long totalWaitTime = current.d.getTime()-cust.enter.getTime();
                    waitTime.add(totalWaitTime);
                }
            }
            //event one cycling
            if(current.type == 1)
            {
                
                if(cashierN*8<waitlineLink.size())
                {
                    overflow++;
                    continue;
                }
                waitlineLink.add(current.c);
                finalCust.add(current.c);
            }
            //event two cycling
            if(current.type == 2)
            {
                clist.get(current.cashV).status=true;
            }
            
        }
    }
    /**
      * using ArrayDeque
      * checks if pQueue is empty
      * Cycles through cashiers to fill from waiting line at beginning
      * 
      * if type 1
      * solves for overflow
      * adds to waiting line
      * cycles to next event
      * 
      * if type 2
      * frees cashier
      * cycles to next event
      * 
      * param void
      * returns void
      */
    public void runThroughDeque()
    {
        
        while(!pQueue.isEmpty())
        {
            
            Event current = pQueue.poll();
            //cashier check
            for(int l = 0; l<clist.size(); l++)
            {
                if(clist.get(l).status==true&&!waitlineADeque.isEmpty())
                {
                    clist.get(l).status=false;
                    Customer cust = waitlineADeque.poll();
                    clist.get(l).c = cust;
                    Event e =new Event(2,cust,cust.exitTime(current.d));
                    e.cashV = l;
                    pQueue.add(e);
                }
            }
            //event 1 cycling
            if(current.type == 1)
            {
                
                if(cashierN*8<waitlineADeque.size())
                {
                    overflow++;
                    continue;
                }
                waitlineADeque.add(current.c);
                finalCust.add(current.c);
            }
            //event 2 cycling
            if(current.type == 2)
            {
                clist.get(current.cashV).status=true;
            }
            
        }
    }
}
