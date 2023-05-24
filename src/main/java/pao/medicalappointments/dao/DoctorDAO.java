package pao.medicalappointments.dao;

import pao.medicalappointments.model.DoctorDTO;
import pao.medicalappointments.model.SpecialtyDTO;
import pao.medicalappointments.service.FileUtility;

import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private static DoctorDAO instance;
    private List<DoctorDTO> doctors;
    private FileUtility fileUtility;

    private DoctorDAO() {
        doctors = new ArrayList<>();
        fileUtility = FileUtility.getInstance();
    }

    public static DoctorDAO getInstance() {
        if (instance == null) {
            instance = new DoctorDAO();
        }
        return instance;
    }

    public List<DoctorDTO> getAllDoctors() {
        List<String[]> doctorData = fileUtility.readCSV("doctors.csv");
        doctors.clear();

        if (doctorData != null) {
            for (String[] data : doctorData) {
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String specialtyName = data[2];
                SpecialtyDTO specialty = SpecialtyDAO.getInstance().getSpecialtyByName(specialtyName);
                DoctorDTO doctor = new DoctorDTO(id, name, specialty);
                doctors.add(doctor);


//                System.out.println("Name: " + doctor.getName() + ", Specialty: " + doctor.getSpecialty());
            }
        }

        return doctors;
    }

    public void addDoctor(DoctorDTO doctor) {
        int nextId = doctors.size() + 1;
        doctor.setId(nextId);
        doctors.add(doctor);

        List<String[]> doctorData = new ArrayList<>();
        for (DoctorDTO d : doctors) {
            String[] data = { String.valueOf(d.getId()), d.getName(), d.getSpecialty().getName() };
            doctorData.add(data);
        }

        fileUtility.writeCSV("doctors.csv", doctorData);
    }

    // Other methods for updating, deleting, and retrieving doctors
}
