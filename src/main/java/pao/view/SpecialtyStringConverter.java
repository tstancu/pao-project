package pao.view;

import javafx.util.StringConverter;
import pao.medicalappointments.model.SpecialtyDTO;

public class SpecialtyStringConverter extends StringConverter<SpecialtyDTO> {

    @Override
    public String toString(SpecialtyDTO specialty) {
        if (specialty == null) {
            return "";
        }
        return specialty.getName();
    }

    @Override
    public SpecialtyDTO fromString(String name) {
        // This method is not used in this context, so it can be left empty
        return null;
    }
}
