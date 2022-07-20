package EHealthCare.model;

import java.util.UUID;

public class PatientModel {

    private UUID uniqueId;
    private String name, lastname;
    private DoctorModel doctorReference;
    private String appointment;

}
