package pao.medicalappointments.model;

public class AppointmentDTO {

    private String patientName;
    private DoctorDTO doctor;

    public AppointmentDTO(String patientName, DoctorDTO doctor) {
        this.patientName = patientName;
        this.doctor = doctor;
    }

    // Getters and setters

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDTO doctor) {
        this.doctor = doctor;
    }
}
