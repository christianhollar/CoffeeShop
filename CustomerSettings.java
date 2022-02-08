import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * Customer Setting - generates preset variables for each customer
 *
 * @Christian Hollar
 * @11/10/20
 */
public abstract class CustomerSettings
{
    // instance variables - replace the example below with your own
    double lowProfit;
    double highProfit;
    double lowTime;
    double highTime;
    ArrayList<String> a = new ArrayList<String>();

    /**
     * Retrieves Profit, Time bounds to help 
     * with random number generation
     * with each individual customer
     */
    public CustomerSettings()
    {
        File filename = new File
        ("/Users/christianhollar/Desktop/Computer Science/"+
        "CS150/Project2Hollar/input.txt");
        Scanner s = null;
        int i = 0;
        try{
            s = new Scanner(filename);
            while(s.hasNext())
            {
                if( i == 5) { break; }
                a.add(s.next());
                i++;
            }
        }catch(Exception e){
            System.out.println("Input Error");
        }
        this.lowProfit = Double.parseDouble(a.get(0)); 
        this.highProfit = Double.parseDouble(a.get(1));
        this.lowTime = Double.parseDouble(a.get(3));
        this.highTime = Double.parseDouble(a.get(4));
    }
}
