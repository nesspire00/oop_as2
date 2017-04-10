package sample;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mykyta
 */
public class PassengerTest {

    Passenger passenger;
    
    public PassengerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.passenger = new Passenger("Adam", "Apple", "Adult");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class Passenger.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Adam Apple (Adult) ";
        String result = passenger.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCategory method, of class Passenger.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        String expResult = "Adult";
        String result = passenger.getCategory();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFirstName method, of class Passenger.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        String expResult = "Adam";
        String result = passenger.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstName method, of class Passenger.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "Alex";
        passenger.setFirstName(firstName);
        String result = passenger.getFirstName();
        assertEquals(firstName, result);
    }

    /**
     * Test of getLastName method, of class Passenger.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        String expResult = "Apple";
        String result = passenger.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastName method, of class Passenger.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "Bauer";
        passenger.setLastName(lastName);
        String result = passenger.getLastName();
        assertEquals(lastName, result);
    }

    /**
     * Test of setCategory method, of class Passenger.
     */
    @Test
    public void testSetCategory() {
        System.out.println("setCategory");
        String category = "Adult";
        passenger.setCategory(category);
        String result = passenger.getCategory();
        assertEquals(category, result);
    }
    
}
