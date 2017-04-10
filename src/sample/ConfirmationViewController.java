package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Mykyta on 4/9/2017.
 */
public class ConfirmationViewController implements Initializable {

    private Ticket ticket;

    @FXML private Label issuedAtLabel;
    @FXML private Label passengersLabel;
    @FXML private Label fromLabel;
    @FXML private Label toLabel;
    @FXML private Label departOnLabel;
    @FXML private Label priceLabel;
    @FXML private Button printButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Setting up buttons the old way
        printButton.setOnAction(e -> printButtonPushed(e));
        // ... because lambdas are cool!
    }

    /**
     * Receive the Ticket object, fill the labels and set the Ticket to the instance variable
     * @param ticket
     */
    public void initialData(Ticket ticket) {
        this.ticket = ticket;
        issuedAtLabel.setText(ticket.getIssueDate().toString());
        passengersLabel.setText(ticket.listPassengers());
        fromLabel.setText(ticket.getOrigin());
        toLabel.setText(ticket.getDestination());
        departOnLabel.setText(ticket.getDepartureDate().toString());
        priceLabel.setText(Double.toString(ticket.calculatePrice()));
    }

    /**
     * Show the file save window to pick the file`s location
     */
    private void printButtonPushed(ActionEvent e){
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if(file != null){
            SaveFile(ticket.toString(), file);
        }
    }

    /**
     * Save the file to the chosen location
     * @param content
     * @param file
     */
    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();

        } catch (IOException ex) {}
    }

    /**
     * Return to the first scene
     * @param event
     * @throws IOException
     */
    public void returnToBeginning(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PassengersView.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Tickets!");
        stage.setScene(scene);
        stage.show();
    }
}
