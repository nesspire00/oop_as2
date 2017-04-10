package sample;

import javafx.collections.ObservableList;
import java.time.LocalDate;

/**
 * Created by Mykyta on 4/9/2017.
 */
public class Ticket {

    private LocalDate issueDate, departureDate;
    private String destination, origin;
    private ObservableList<Passenger> passengers;

    public Ticket(LocalDate departureDate, ObservableList<Passenger> passengers, String destination, String origin) {
        this.issueDate = LocalDate.now();
        setDepartureDate(departureDate);
        setOrigin(origin);
        setDestination(destination);
        this.passengers = passengers;
    }

    public String getDestination() {
        return destination;
    }

    /**
     * If destination is empty or is the same as origin - throw exception
     * (error text is used to identify the exact error by the view)
     * @param destination
     */
    public void setDestination(String destination) {
        if(this.origin.equals(destination))
            throw new IllegalArgumentException("same");
        else if(destination.isEmpty()){
            throw new IllegalArgumentException("destination");
        }else{
            this.destination = destination;
        }
    }

    public String getOrigin() {
        return origin;
    }

    /**
     * If empty throw an exception
     * @param origin
     */
    public void setOrigin(String origin) {
        if(!origin.isEmpty())
            this.origin = origin;
        else
            throw new IllegalArgumentException("origin");
    }

    /**
     * Has to be greater than or equal to today`s date
     * @param date
     */
    private void setDepartureDate(LocalDate date) {

        if (LocalDate.now().toEpochDay() <= date.toEpochDay()) {
            this.departureDate = date;
        } else {
            throw new IllegalArgumentException("date");
        }
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    /**
     * Count up total price based on age category.
     * (Physical distance between the cities doesn`t affect the price though,
     * Ride while it lasts!)
     * @return
     */
    public double calculatePrice() {
        double total = 0;

        for (Passenger passenger : passengers) {
            if (passenger.getCategory().equals("Child")) {
                total += 5;
            } else if (passenger.getCategory().equals("Student") || passenger.getCategory().equals("Senior")) {
                total += 7;
            } else if (passenger.getCategory().equals("Adult")) {
                total += 10;
            }
        }
        return total;
    }

    /**
     * Aggregate passengers in one string using passenger`s toString() method
     * @return
     */
    public String listPassengers(){

        String passengerString = "";

        for(Passenger passenger : passengers){
            passengerString += passenger.toString() + "\n";
        }
        return passengerString;
    }

    /**
     * Display the ticket info in a fancy way
     * @return
     */
    @Override
    public String toString(){
        String printString = "";
        printString += "/*******************\n" +
                       "* Transit Ticket    \n" +
                       "********************\n" +
                       "* Issued on: " + getIssueDate() + "\n" +
                       "* To: " + listPassengers() + "\n" +
                       "* FROM: " + getOrigin() + "\n" +
                       "* TO: " + getDestination() + "\n" +
                       "* Price: " + calculatePrice() + "\n" +
                       "/*******************\n";
        return printString;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public ObservableList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ObservableList<Passenger> passengers) {
        this.passengers = passengers;
    }
}
