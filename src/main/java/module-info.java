module MedicalAppointments {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    exports pao.medicalappointments;
    exports pao.medicalappointments.model;
    exports pao.medicalappointments.service;
    exports pao.view;

    opens pao.view to javafx.fxml;
    opens pao.medicalappointments.model to javafx.base;
}