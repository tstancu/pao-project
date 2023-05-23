package pao.medicalappointments.dao;

import pao.medicalappointments.model.SpecialtyDTO;
import pao.medicalappointments.service.FileUtility;

import java.util.ArrayList;
import java.util.List;

public class SpecialtyDAO {
    private static SpecialtyDAO instance;
    private List<SpecialtyDTO> specialties;
    private FileUtility fileUtility;

    private SpecialtyDAO() {
        specialties = new ArrayList<>();
        fileUtility = FileUtility.getInstance();
        loadSpecialtiesFromFile();
    }

    public static SpecialtyDAO getInstance() {
        if (instance == null) {
            instance = new SpecialtyDAO();
        }
        return instance;
    }

    public void addSpecialty(SpecialtyDTO specialty) {
        specialties.add(specialty);
        saveSpecialtiesToFile();
    }

    public List<SpecialtyDTO> getAllSpecialties() {
        return new ArrayList<>(specialties);
    }

    public SpecialtyDTO getSpecialtyById(int id) {
        for (SpecialtyDTO specialty : specialties) {
            if (specialty.getId() == id) {
                return specialty;
            }
        }
        return null;
    }

    public SpecialtyDTO getSpecialtyByName(String name) {
        for (SpecialtyDTO specialty : specialties) {
            if (specialty.getName().equals(name)) {
                return specialty;
            }
        }
        return null;
    }

    public void deleteSpecialty(int id) {
        SpecialtyDTO specialty = getSpecialtyById(id);
        if (specialty != null) {
            specialties.remove(specialty);
            saveSpecialtiesToFile();
        }
    }

    public void updateSpecialty(SpecialtyDTO updatedSpecialty) {
        for (int i = 0; i < specialties.size(); i++) {
            SpecialtyDTO specialty = specialties.get(i);
            if (specialty.getId() == updatedSpecialty.getId()) {
                specialties.set(i, updatedSpecialty);
                saveSpecialtiesToFile();
                return;
            }
        }
    }

    private void loadSpecialtiesFromFile() {
        List<String[]> specialtyData = fileUtility.readCSV("specialties.csv");
        if (specialtyData != null) {
            for (String[] data : specialtyData) {
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                SpecialtyDTO specialty = new SpecialtyDTO(id, name);
                specialties.add(specialty);

                System.out.println("Specialty: " + data[1]);
            }


        }
    }

    private void saveSpecialtiesToFile() {
        List<String[]> specialtyData = new ArrayList<>();
        for (SpecialtyDTO specialty : specialties) {
            String[] data = { String.valueOf(specialty.getId()), specialty.getName() };
            specialtyData.add(data);
        }
        fileUtility.writeCSV("specialties.csv", specialtyData);
    }
}