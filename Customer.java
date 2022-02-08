import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * Customer class for each customer that comes into coffee shop
 *
 * @Christian Hollar
 * @11/20/20
 */

public class Customer extends CustomerSettings
{
    Date enter;
    int profit;
    int xTime;

    /**
     * generates random time and profit
     * uses date for comparison
     * enter corresponds to entry date
     */
    public Customer(Date d)
    {
        this.enter = d;
        double p = Math.random() * 
        (highProfit - lowProfit + 1) + lowProfit;
        double x = Math.random() *
        (highTime - lowTime + 1) + lowTime;    
        this.profit = (int)p;
        this.xTime = (int)x;
    }

    /**
     * returns profit
     * @void
     * @1
     */
    public int getProfit()
    {
        return this.profit;
    }
    /**
     * returns time
     * @void
     * @1
     */
    public int getXTime()
    {
        return this.xTime;
    }
    /**
     * returns exit time
     * @"09:00:00"
     * @"09:10:00"
     */
    public Date exitTime(Date d)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.SECOND,getXTime());
        return c.getTime();
    }
}
