package pao.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    public Button makeAppointmentButton;
    public Button viewDoctorsButton;
    @FXML
    private ImageView imageView;

    public void initialize() {
        // Load the image from the classpath
        Image image = new Image(getClass().getResourceAsStream("/medical-logo2.jpg"));

        // Set the image to the ImageView
        imageView.setImage(image);
    }

    @FXML
    public void viewDoctorsButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pao/doctors.fxml"));
        Parent doctorsRoot = loader.load();
        Scene doctorsScene = new Scene(doctorsRoot);

        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(doctorsScene);
        primaryStage.show();
    }

    @FXML
    private void handleMakeAppointment(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pao/appointments.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        // Close the main screen if needed
//        Stage mainStage = (Stage) makeAppointmentButton.getScene().getWindow();
//        mainStage.close();
    }

}