
/**
 * RunCheck - various methods for comparing LinkedList, ArrayDeque
 * Overflow,Profit, Cashier Recommendation
 *
 * @ChristianHollar
 * @11/10/20
 */
public class RunCheck
{

    public static void main(String[] args)
    {
        RunCheck r = new RunCheck(3);
    }
    
    
    public RunCheck(int n)
    {
        regular(n);
    }
    public RunCheck()
    {
        
    }
    /**
     * Regular Method
     * changes cashiers
     * param void
     * return void
     */
    public void regular(int n)
    {
        Controller c = new Controller(n,"LinkedList","Full");
    }
    public void overflowCheck()
    {
        for(int i = 1; i<8; i++)
        {
            System.out.println("Cashier Number" + i);
            for(int j = 0; j<10; j++)
            {
                Controller cProfit = new Controller(i,"LinkedList","Overflow");
            }
        }
    }
    public void allCheckLink()
    {
        for(int i = 1; i<8; i++)
        {
            System.out.println("Cashier Number" + i);
            for(int j = 0; j<10; j++)
            {
                Controller cProfit = new Controller(i,"LinkedList","Time");
            }
        }
    }
    public void allCheckDeque()
    {
        for(int i = 1; i<8; i++)
        {
            System.out.println("Cashier Number" + i);
            for(int j = 0; j<10; j++)
            {
                Controller cProfit = new Controller(i,"ArrayDeque","Time");
            }
        }
    }
    public void allCheck()
    {
        for(int i = 1; i<8; i++)
        {
            System.out.println("Cashier Number" + i);
            for(int j = 0; j<10; j++)
            {
                Controller cProfit = new Controller(i,"LinkedList","Profit");
            }
        }
    }
    public void fourCheck()
    {
        System.out.println("4");
        for(int i = 0; i<100; i++)
        {
            Controller cProfit = new Controller(4,"LinkedList","Profit");
        }        
    }
    public void fiveCheck()
    {
        System.out.println("5");
        for(int i = 0; i<100; i++)
        {
            Controller cProfit = new Controller(5,"LinkedList","Profit");
        }
    }


}
