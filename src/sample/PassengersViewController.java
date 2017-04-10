package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PassengersViewController implements Initializable {


    @FXML private TableView<Passenger> passengersTable;
    @FXML private TableColumn<Passenger, String> firstNameColumn;
    @FXML private TableColumn<Passenger, String> lastNameColumn;
    @FXML private TableColumn<Passenger, String> categoryColumn;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;

    //Radiobuttons
    @FXML private RadioButton childRadio;
    @FXML private RadioButton studentRadio;
    @FXML private RadioButton adultRadio;
    @FXML private RadioButton seniorRadio;
    private ToggleGroup catToggleGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Table view
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Passenger, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Passenger, String>("lastName"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Passenger, String>("category"));
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Setting up the ToggleGroup
        catToggleGroup = new ToggleGroup();
        catToggleGroup.getToggles().addAll(childRadio, studentRadio, adultRadio, seniorRadio);
    }

    /**
     * Add the person if the button is pushed
     */
    public void newPersonButtonPushed() {
        try {
            String cat = null;
            if (catToggleGroup.getSelectedToggle().equals(childRadio)) {
                cat = "Child";
            } else if (catToggleGroup.getSelectedToggle().equals(studentRadio)) {
                cat = "Student";
            } else if (catToggleGroup.getSelectedToggle().equals(adultRadio)) {
                cat = "Adult";
            } else if (catToggleGroup.getSelectedToggle().equals(seniorRadio)) {
                cat = "Senior";
            }

            Passenger passenger = new Passenger(firstNameTextField.getText(), lastNameTextField.getText(), cat);
            passengersTable.getItems().add(passenger);

            // Clear fields and clean any error borders
            firstNameTextField.clear();
            lastNameTextField.clear();
            firstNameTextField.getStyleClass().remove("error");
            lastNameTextField.getStyleClass().remove("error");

        } catch (Exception e) {
            System.out.println(e);
            firstNameTextField.getStyleClass().add("error");
            lastNameTextField.getStyleClass().add("error");
        }
    }

    /**
     * Remove the selected person
     */
    public void deleteButtonPushed() {
        ObservableList<Passenger> selectedRows;
        selectedRows = passengersTable.getSelectionModel().getSelectedItems();
        passengersTable.getItems().removeAll(selectedRows);
    }

    /**
     * An overly complicated set of steps to change the scene and pass on an array with Passenger objects
     * @param event
     * @throws IOException
     */
    public void goToNextStop(ActionEvent event) throws IOException {
        // See if there is at least one person in the list
        if (passengersTable.getItems().isEmpty()) {
            firstNameTextField.getStyleClass().add("error");
            lastNameTextField.getStyleClass().add("error");
            adultRadio.getStyleClass().add("error");
            seniorRadio.getStyleClass().add("error");
            studentRadio.getStyleClass().add("error");
            childRadio.getStyleClass().add("error");
        } else {
            // Remove red borders
            firstNameTextField.getStyleClass().remove("error");
            lastNameTextField.getStyleClass().remove("error");
            adultRadio.getStyleClass().remove("error");
            seniorRadio.getStyleClass().remove("error");
            studentRadio.getStyleClass().remove("error");
            childRadio.getStyleClass().remove("error");


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("TicketDetails.fxml"));
            Parent parent = loader.load();

            TicketDetailsViewController controller = loader.getController();

            controller.initialData(passengersTable.getItems());

            Scene scene = new Scene(parent);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Tickets!");
            stage.setScene(scene);
            stage.show();
        }
    }
}
