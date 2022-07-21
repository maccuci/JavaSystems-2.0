package EHealthCare.manager;

import EHealthCare.model.DoctorModel;
import EHealthCare.model.PatientModel;

public class HealthPlan {

    private PatientModel patient;
    private DoctorModel doctor;
    private HealthPlanType type;
    private int fees;

    public HealthPlan(PatientModel patient, DoctorModel doctor, HealthPlanType type, int fees) {
        this.patient = patient;
        this.doctor = doctor;
        this.type = type;
        this.fees = fees;
    }

    public PatientModel getPatient() {
        return patient;
    }

    public DoctorModel getDoctor() {
        return doctor;
    }

    public HealthPlanType getType() {
        return type;
    }

    public int getFees() {
        return fees;
    }

    public void setDoctor(DoctorModel doctor) {
        this.doctor = doctor;
    }

    public enum HealthPlanType {
        FREE,
        PAID;
    }
}
