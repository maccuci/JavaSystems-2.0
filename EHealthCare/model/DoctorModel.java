package EHealthCare.model;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class DoctorModel {

    private int id;
    private String name, lastname;
    private DoctorType type;
    private final Scanner READER = new Scanner(System.in);

    public DoctorModel(String name, String lastname, DoctorType type) {
        this.name = name;
        this.lastname = lastname;
        this.type = type;
    }

    public boolean registerDoctorInList() {
        AtomicBoolean ab = new AtomicBoolean();

        return ab.get();
    }

    public boolean registerDoctorInSql() {
        AtomicBoolean ab = new AtomicBoolean();

        return ab.get();
    }


    public int getId() {
        return id;
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
