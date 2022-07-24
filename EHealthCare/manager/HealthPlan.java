package EHealthCare.manager;

import EHealthCare.model.ModelDoctor;
import EHealthCare.model.ModelPatient;

public class HealthPlan {

    private ModelPatient patient;
    private ModelDoctor doctor;
    private HealthPlanType type;
    private int fees;

    public HealthPlan(ModelPatient patient, ModelDoctor doctor, HealthPlanType type, int fees) {
        this.patient = patient;
        this.doctor = doctor;
        this.type = type;
        this.fees = fees;
    }

    public ModelPatient getPatient() {
        return patient;
    }

    public ModelDoctor getDoctor() {
        return doctor;
    }

    public HealthPlanType getType() {
        return type;
    }

    public int getFees() {
        return fees;
    }

    public void setDoctor(ModelDoctor doctor) {
        this.doctor = doctor;
    }

    public enum HealthPlanType {
        FREE,
        PAID;
    }
}
