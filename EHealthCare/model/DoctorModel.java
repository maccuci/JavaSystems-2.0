package EHealthCare.model;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class DoctorModel {

    private static final Executor asyncExecutor = Executors.newSingleThreadExecutor((new ThreadFactoryBuilder()).setNameFormat("Async Thread").build());

    private final UUID uniqueId;
    private String name, lastname;
    private DoctorType type;
    private static final List<DoctorModel> DOCTORS = new ArrayList<>();

    public DoctorModel(String name, String lastname, DoctorType type) {
        this.uniqueId = UUID.randomUUID();
        this.name = name;
        this.lastname = lastname;
        this.type = type;
    }

    public boolean registerDoctorInList() {
        AtomicBoolean ab = new AtomicBoolean(false);

        asyncExecutor.execute(() -> {
            try {
                DOCTORS.add(this);
                ab.set(true);
            } catch (Exception e) {
                System.out.println("Impossible to execute a async register doctor.");
            }
        });

        return ab.get();
    }

    public boolean registerDoctorInSql() {
        AtomicBoolean ab = new AtomicBoolean();

        return ab.get();
    }

    public String showDoctorInformations() {
        StringBuilder sb = new StringBuilder();

        sb.append("==============[DOCTOR INFO]==============\n")
                .append(String.format("UID: %s\nName: %s\nLast Name: %s\nType: %s\n", getUniqueIdId().toString(), getName(), getLastname(), getType().name()))
                .append("==============[DOCTOR INFO]==============");;
        return sb.toString();
    }

    public UUID getUniqueIdId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public DoctorType getType() {
        return type;
    }

    public static List<DoctorModel> getDoctors() {
        return DOCTORS;
    }

    public enum DoctorType {
        EYES,
        EAR,
        HEART,
        BONE,
        LUNGS,
        KIDNEY,
        GENERAL;
    }
}
