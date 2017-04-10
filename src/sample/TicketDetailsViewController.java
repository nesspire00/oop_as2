package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Mykyta on 4/9/2017.
 */
public class TicketDetailsViewController implements Initializable {

    @FXML private ComboBox<String> destinationComboBox;
    @FXML private ComboBox<String> originComboBox;
    @FXML private Label passengerListLabel;
    @FXML private DatePicker departureDatePicker;

    // The received list of Passenger objects
    private ObservableList<Passenger> passengers;

    /**
     * Populate ChoiceBoxes with items and set a placeholder text
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        destinationComboBox.getItems().addAll("Barrie, ON", "Toronto, ON", "Ottawa, ON", "Montréal, QC", "Québec City, QC", "London, ON");
        originComboBox.getItems().addAll("Barrie, ON", "Toronto, ON", "Ottawa, ON", "Montréal, QC", "Québec City, QC", "London, ON");
        destinationComboBox.setValue("Choose destination...");
        originComboBox.setValue("Choose origin city...");
    }

    /**
     * Receive the list from previous scene, save it as an instance variable and update a label at the bottom
     *
     * @param passengers
     */
    public void initialData(ObservableList<Passenger> passengers) {
        this.passengers = passengers;
        String list = "";
        for (Passenger passenger : passengers) {
            list += passenger.toString();
        }
        passengerListLabel.setText(list);
    }

    /**
     * Attempt to create the Ticket object, load next scene and pass Ticket to next scene
     *
     * @param event
     * @throws IOException
     */
    public void goToDetailedPerson(ActionEvent event) throws IOException {

        //Clear the error borders
        originComboBox.getStyleClass().remove("error");
        destinationComboBox.getStyleClass().remove("error");
        departureDatePicker.getStyleClass().remove("error");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ConfirmationView.fxml"));
        Parent parent = loader.load();
        ConfirmationViewController controller = loader.getController();

        try {
            // Check if ComboBoxes don`t have the placeholder values
            if (destinationComboBox.getSelectionModel().getSelectedItem().equals("Choose destination..."))
                throw new IllegalArgumentException("destination");
            else if (originComboBox.getSelectionModel().getSelectedItem().equals("Choose origin city..."))
                throw new IllegalArgumentException("origin");

            Ticket ticket = new Ticket(departureDatePicker.getValue(), passengers, destinationComboBox.getSelectionModel().getSelectedItem(),
                    originComboBox.getSelectionModel().getSelectedItem());

            // Only proceed if Ticket has been successfully created
            if (!ticket.equals(null)) {

                controller.initialData(ticket);
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Tickets!");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            if (e.getMessage() == "origin")
                originComboBox.getStyleClass().add("error");
            else if (e.getMessage() == "destination")
                destinationComboBox.getStyleClass().add("error");
            else if (e.getMessage() == "date")
                departureDatePicker.getStyleClass().add("error");
            else if (e.getMessage() == "same") {
                originComboBox.getStyleClass().add("error");
                destinationComboBox.getStyleClass().add("error");
            }
        }
    }
}
