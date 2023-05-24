package pao.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pao.medicalappointments.dao.SpecialtyDAO;
import pao.medicalappointments.model.SpecialtyDTO;

public class AppointmentsController {
    public TextField patientNameField;
    @FXML
    private ComboBox<SpecialtyDTO> specialtyComboBox;
    public DatePicker datePicker;

    @FXML
    private Button createButton;

    @FXML
    private Button cancelButton;

    private SpecialtyDAO specialtyDAO;

    public void initialize() {
        specialtyDAO = SpecialtyDAO.getInstance();

        initializeSpecialtyComboBox();

        createButton.setOnAction(event -> createAppointment());
        cancelButton.setOnAction(event -> cancelAppointment());
    }

    private void initializeSpecialtyComboBox() {
        specialtyComboBox.setItems(FXCollections.observableArrayList(specialtyDAO.getAllSpecialties()));
        specialtyComboBox.setConverter(new SpecialtyStringConverter());
    }

    public void createAppointment() {

    }

    public void cancelAppointment() {

        patientNameField.clear();
        specialtyComboBox.getSelectionModel().clearSelection();
        datePicker.setValue(null);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
