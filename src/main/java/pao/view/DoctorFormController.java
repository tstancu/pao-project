package pao.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pao.medicalappointments.dao.DoctorDAO;
import pao.medicalappointments.dao.SpecialtyDAO;
import pao.medicalappointments.model.DoctorDTO;
import pao.medicalappointments.model.SpecialtyDTO;

public class DoctorFormController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<SpecialtyDTO> specialtyComboBox;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private DoctorDAO doctorDAO;
    private SpecialtyDAO specialtyDAO;
    private DoctorsController doctorsController;

    public void initialize(DoctorsController doctorsController) {
        doctorDAO = DoctorDAO.getInstance();
        specialtyDAO = SpecialtyDAO.getInstance();
        this.doctorsController = doctorsController;

        initializeSpecialtyComboBox();

        saveButton.setOnAction(event -> saveDoctor());
        cancelButton.setOnAction(event -> closeForm());
    }

    private void initializeSpecialtyComboBox() {
        specialtyComboBox.setItems(FXCollections.observableArrayList(specialtyDAO.getAllSpecialties()));
        specialtyComboBox.setConverter(new SpecialtyStringConverter());
    }

    private void saveDoctor() {
        String idText = idField.getText();
        String name = nameField.getText();
        SpecialtyDTO specialty = specialtyComboBox.getValue();

//        System.out.println(specialty.getName());

        if (idText.isEmpty() || name.isEmpty() || specialty == null) {
            showErrorMessage("Please fill in all fields.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            showErrorMessage("Invalid ID format. Please enter a valid number.");
            return;
        }

        DoctorDTO doctor = new DoctorDTO(id, name, specialty);
        doctorDAO.addDoctor(doctor);

        showSuccessMessage("Doctor successfully added.");
        closeForm();
    }

    private void closeForm() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}