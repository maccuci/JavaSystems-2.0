package EHealthCare.model;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class PatientModel {

    private static final Executor asyncExecutor = Executors.newSingleThreadExecutor((new ThreadFactoryBuilder()).setNameFormat("Async Thread").build());

    private UUID uniqueId;
    private String name, lastname;
    private DoctorModel doctorReference;
    private String appointment;

    private static final List<PatientModel> PATIENTS = new ArrayList<>();

    public PatientModel(String name, String lastname, DoctorModel doctorReference, String appointment) {
        this.name = name;
        this.lastname = lastname;
        this.doctorReference = doctorReference;
        this.appointment = appointment;
    }

    public boolean registerPatientInList() {
        AtomicBoolean register = new AtomicBoolean(false);

        asyncExecutor.execute(() -> {
            try {
                PATIENTS.add(this);
                register.set(true);
            } catch (Exception e) {
                System.out.println("Impossible to execute a async register patient.");
            }
        });

        return register.get();
    }

    //TODO: btw, i don't know really if going add this
    public boolean registerPatientInSql() {
        AtomicBoolean register = new AtomicBoolean(false);

        return register.get();
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

    public DoctorModel getDoctorReference() {
        return doctorReference;
    }

    public String getAppointment() {
        return appointment;
    }

    public static List<PatientModel> getPatients() {
        return PATIENTS;
    }
}
