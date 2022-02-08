import java.util.Date;
import java.util.Calendar;
/**
 * Event class - represnets new or leaving customer
 *
 * @ChristianHollar
 * @11/10/20
 */
public class Event implements Comparable<Event>
{
    int type;
    Customer c;
    Date d;
    int cashV;

    /**
     * Event
     * 
     * Holds type (1 means new customer, 2 mean customer exit)
     * holds customer
     * holds time of event
     * holds cashiernumber where customer visited
     */
    public Event(int type, Customer c, Date d)
    {
        this.type = type;
        this.c = c;
        this.d = d;
        cashV = -1;
    }
    /**
     * CompareTo used to sort by date
     * @new Event(1,null,"09:00:00)
     * @1
     */
    @Override
    public int compareTo(Event e)
    {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(this.d);
        cal2.setTime(e.d);
        if(cal1.before(cal2))
        return -1;
        if(cal1.equals(cal2))
        return 0;
        else
        return 1;
    }
    /**
     * Returns date of event
     * @void
     * @"09:00:00"
     */
    public Date getDate()
    { return this.d;}

}
