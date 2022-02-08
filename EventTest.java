

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class EventTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class EventTest
{
    /**
     * Default constructor for test class EventTest
     */
    public EventTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void EventTestNulls()
    {
        Event event2 = new Event(1, null, null);
        assertEquals(null, event2.getDate());
    }
}

