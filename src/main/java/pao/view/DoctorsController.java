package pao.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pao.medicalappointments.dao.DoctorDAO;
import pao.medicalappointments.model.DoctorDTO;
import pao.medicalappointments.model.SpecialtyDTO;

import java.io.IOException;

public class DoctorsController {

    @FXML
    private VBox doctorsContainer;

    @FXML
    private TableView<DoctorDTO> doctorTableView;

    @FXML
    private TableColumn<DoctorDTO, Integer> idColumn;

    @FXML
    private TableColumn<DoctorDTO, String> nameColumn;

    @FXML
    private TableColumn<DoctorDTO, String> specialtyColumn;

    @FXML
    private Button addButton;

    private DoctorDAO doctorDAO;

    @FXML
    public void initialize() {


        doctorsContainer.setPrefWidth(600);
        doctorsContainer.setPrefHeight(600);

        // Set preferred dimensions for the doctorTableView

        doctorTableView.setPrefHeight(500);


        doctorDAO = DoctorDAO.getInstance();

        setupTableView();
        updateDoctorTable();

        addButton.setOnAction(event -> openDoctorForm());
    }

    private void setupTableView() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        SpecialtyStringConverter converter = new SpecialtyStringConverter();
        specialtyColumn.setCellValueFactory(data -> {
            SpecialtyDTO specialty = data.getValue().getSpecialty();
            return new SimpleStringProperty(converter.toString(specialty));
        });
    }

    public void updateDoctorTable() {
        doctorTableView.setItems(FXCollections.observableArrayList(doctorDAO.getAllDoctors()));
    }

    @FXML
    private void goToMainScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pao/main-test.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage currentStage = (Stage) doctorsContainer.getScene().getWindow();

        double width = currentStage.getWidth();
        double height = currentStage.getHeight();

        Stage mainStage = new Stage();
        mainStage.setScene(scene);
        mainStage.setWidth(width);
        mainStage.setHeight(height);

        mainStage.show();
    }

    @FXML
    private void openDoctorForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pao/doctor-form.fxml"));
            Parent root = fxmlLoader.load();

            DoctorFormController doctorFormController = fxmlLoader.getController();
            doctorFormController.initialize(this);

            Stage mainStage = (Stage) addButton.getScene().getWindow();
            double mainStageX = mainStage.getX();
            double mainStageY = mainStage.getY();
            double mainStageWidth = mainStage.getWidth();
            double mainStageHeight = mainStage.getHeight();

            Stage stage = new Stage();
            stage.setTitle("Add Doctor");
            stage.initModality(Modality.APPLICATION_MODAL);

            double formWidth = 400; // Adjust the width as needed
            double formHeight = mainStageHeight / 2;

            double formX = mainStageX + mainStageWidth + 10; // Position it to the right of the main stage with a 10 pixel gap
            double formY = mainStageY;

            stage.setX(formX);
            stage.setY(formY);
            stage.setWidth(formWidth);
            stage.setHeight(formHeight);

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.showAndWait();

            updateDoctorTable();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}