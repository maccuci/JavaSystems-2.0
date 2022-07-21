package EHealthCare.model;

import EHealthCare.manager.HealthPlan;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class PatientModel {

    private static final Executor asyncExecutor = Executors.newSingleThreadExecutor((new ThreadFactoryBuilder()).setNameFormat("Async Thread").build());

    private int id;
    private UUID uniqueId;
    private String name, lastname, gender, email, bloodgroup;
    private int age;
    private String appointment;
    private HealthPlan plan;

    private static final List<PatientModel> PATIENTS = new ArrayList<>();
    private static final Map<Integer, PatientModel> PATIENTS_DATA = new HashMap<>();

    public PatientModel(String name, String lastname, String appointment) {
        this.id = this.id + 1;
        this.name = name;
        this.lastname = lastname;
        this.appointment = appointment;
    }

    public PatientModel() {
    }

    public boolean registerPatientInList() {
        AtomicBoolean register = new AtomicBoolean();

        asyncExecutor.execute(() -> {
            try {
                PATIENTS.add(this);
                PATIENTS_DATA.put(getId(), this);
                register.set(true);
            } catch (Exception e) {
                System.out.println("Impossible to execute a async register patient.");
                register.set(false);
            }
        });

        return register.get();
    }

    //TODO: btw, i don't know really if going add this
    public boolean registerPatientInSql() {
        AtomicBoolean register = new AtomicBoolean(false);

        return register.get();
    }

    public int getId() {
        return id;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }


    public String getAppointment() {
        return appointment;
    }

    public HealthPlan getPlan() {
        return plan;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public int getAge() {
        return age;
    }


    public void setPlan(HealthPlan plan) {
        this.plan = plan;
    }

    public static List<PatientModel> getPatients() {
        return PATIENTS;
    }

    public static Map<Integer, PatientModel> getPatientsData() {
        return PATIENTS_DATA;
    }
}
