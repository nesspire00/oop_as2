package sample;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class TicketTest {
    ArrayList<Passenger> passengersArrayList;
    Passenger pass1, pass2;
    Ticket ticket;
    
    public TicketTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.pass1 = new Passenger("Finn", "TheHuman", "Student");
        this.pass2 = new Passenger("Jake", "TheDog", "Adult");

        //Shenanigans with an ArrayList is used as a workaround just because it doesnt seem to like ObservableLists
        passengersArrayList = new ArrayList<>();
        passengersArrayList.add(pass1);
        passengersArrayList.add(pass2);
        ObservableList<Passenger> passengers = FXCollections.observableArrayList(passengersArrayList);


        ticket = new Ticket(LocalDate.of(2017, Month.AUGUST, 23), passengers, "Barrie, ON", "Montréal, QC");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDestination method, of class Ticket.
     */
    @Test
    public void testGetDestination() {
        System.out.println("getDestination");
        String expResult = "Barrie, ON";
        String result = ticket.getDestination();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDestination method, of class Ticket.
     */
    @Test
    public void testSetDestination() {
        System.out.println("setDestination");
        String destination = "Toronto, ON";
        ticket.setDestination(destination);
    }

    /**
     * Test of getOrigin method, of class Ticket.
     */
    @Test
    public void testGetOrigin() {
        System.out.println("getOrigin");
        String expResult = "Montréal, QC";
        String result = ticket.getOrigin();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrigin method, of class Ticket.
     */
    @Test
    public void testSetOrigin() {
        System.out.println("setOrigin");
        String origin = "London, ON";
        ticket.setOrigin(origin);
    }

    /**
     * Test of getIssueDate method, of class Ticket.
     */
    @Test
    public void testGetIssueDate() {
        System.out.println("getIssueDate");
        LocalDate expResult = LocalDate.now();
        LocalDate result = ticket.getIssueDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of calculatePrice method, of class Ticket.
     */
    @Test
    public void testCalculatePrice() {
        System.out.println("calculatePrice");
        double expResult = 17.0;
        double result = ticket.calculatePrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of listPassengers method, of class Ticket.
     */
    @Test
    public void testListPassengers() {
        System.out.println("listPassengers");
        String expResult = "Finn TheHuman (Student) \n" +
                "Jake TheDog (Adult) \n";
        String result = ticket.listPassengers();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Ticket.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "/*******************\n"  +
                          "* Transit Ticket    \n" +
                          "********************\n" +
                          "* Issued on: " + LocalDate.now() + "\n"+
                          "* To: Finn TheHuman (Student) \nJake TheDog (Adult) \n\n" +
                          "* FROM: Montréal, QC\n" +
                          "* TO: Barrie, ON\n" +
                          "* Price: 17.0\n" +
                          "/*******************\n";
        String result = ticket.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIssueDate method, of class Ticket.
     */
    @Test
    public void testSetIssueDate() {
        System.out.println("setIssueDate");
        LocalDate issueDate = LocalDate.now();
        ticket.setIssueDate(issueDate);
    }

    /**
     * Test of getDepartureDate method, of class Ticket.
     */
    @Test
    public void testGetDepartureDate() {
        System.out.println("getDepartureDate");
        LocalDate expResult = LocalDate.of(2017, Month.AUGUST, 23);
        LocalDate result = ticket.getDepartureDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassengers method, of class Ticket.
     */
    @Test
    public void testGetPassengers() {
        System.out.println("getPassengers");
        ObservableList<Passenger> passengers = FXCollections.observableArrayList(passengersArrayList);
        ObservableList<Passenger> expResult = passengers;
        ObservableList<Passenger> result = ticket.getPassengers();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassengers method, of class Ticket.
     */
    @Test
    public void testSetPassengers() {
        ArrayList<Passenger> newPassengers = new ArrayList<>();
        newPassengers.add(new Passenger("Lone", "Warrior", "Senior"));
        ObservableList<Passenger> passengers = FXCollections.observableArrayList(newPassengers);
        System.out.println("setPassengers");
        ticket.setPassengers(passengers);
    }
    
}
