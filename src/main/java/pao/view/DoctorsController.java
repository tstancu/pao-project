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
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pao.medicalappointments.dao.DoctorDAO;
import pao.medicalappointments.model.DoctorDTO;
import pao.medicalappointments.model.SpecialtyDTO;

import java.io.IOException;

public class DoctorsController {

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
    private void openDoctorForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pao/doctor-form.fxml"));
            Parent root = fxmlLoader.load();

            DoctorFormController doctorFormController = fxmlLoader.getController();
            doctorFormController.initialize(this);

            Stage stage = new Stage();
            stage.setTitle("Add Doctor");


            Scene scene = new Scene(root);
            stage.setScene(scene);
//            stage.sizeToScene();

            // move it a bit to the right from the parent stage
            Stage mainStage = (Stage) addButton.getScene().getWindow();

            double mainStageWidth = mainStage.getWidth();
            double mainStageHeight = mainStage.getHeight();
            System.out.println("mainStageWidth: " + mainStageWidth + " / mainStageHeight: " + mainStageHeight);
//            double popUpWidth = 400; // Adjust the width as needed
//            double popUpHeight = 200; // Adjust the height as needed
//
//            double popUpX = mainStage.getX() + (mainStageWidth - popUpWidth) / 2; // Center horizontally relative to the main stage
//            double popUpY = mainStage.getY() + (mainStageHeight - popUpHeight) / 2; // Center vertically relative to the main stage
//
//            stage.setX(popUpX);
//            stage.setY(popUpY);

            stage.initModality(Modality.APPLICATION_MODAL);

            // Set the size of the pop-up window's scene
//            scene.setRoot(root);
//            scene.setRoot(new Group(root));
//            scene.getWindow().setWidth(popUpWidth);
//            scene.getWindow().setHeight(popUpHeight);


            stage.showAndWait();

            updateDoctorTable(); // Refresh the table after closing the form
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}