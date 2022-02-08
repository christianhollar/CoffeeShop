import java.io.File;
import java.util.*;
/**
 * Cashier Object - holds boolean and customer
 *
 * @ChristianHollar
 * @11/10/20
 */
public class Cashier
{
    boolean status;
    Customer c;
    /*
     * status true means ready for customer
     */
    public Cashier(boolean s)
    {
        this.status = s;
    }
}
