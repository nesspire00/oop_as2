package sample;

/**
 * Created by Mykyta on 4/9/2017.
 */
public class Passenger {

    private String firstName, lastName, category;

    /**
     * A simple class to hold the passenger`s info.
     * @param firstName
     * @param lastName
     * @param category
     */
    public Passenger(String firstName, String lastName, String category) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.category = category;
    }

    /**
     * "Hey, passenger! What`s your name?"
     * @return Name and category of the passenger
     */
    @Override
    public String toString(){
        return firstName + " " + lastName + " " + "(" + category + ")" + " ";
    }

    public String getCategory() {
        return category;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
