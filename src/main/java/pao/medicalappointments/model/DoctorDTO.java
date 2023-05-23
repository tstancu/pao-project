package pao.medicalappointments.model;

public class DoctorDTO {
    private int id;
    private String name;
    private SpecialtyDTO specialty;

    public DoctorDTO(int id, String name, SpecialtyDTO specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpecialtyDTO getSpecialty() {
        return specialty;
    }

    public void setSpecialty(SpecialtyDTO specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "DoctorDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialty=" + specialty +
                '}';
    }
}
