package pao.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AppointmentsController {
    public TextField patientNameField;
    public ComboBox specialtyComboBox;
    public DatePicker datePicker;

    @FXML
    private Button createButton;

    @FXML
    private Button cancelButton;

    public void createAppointment(ActionEvent actionEvent) {

    }

    public void cancelAppointment(ActionEvent actionEvent) {

        patientNameField.clear();
        specialtyComboBox.getSelectionModel().clearSelection();
        datePicker.setValue(null);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
