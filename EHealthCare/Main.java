package EHealthCare;

import EHealthCare.manager.HealthPlan;
import EHealthCare.model.DoctorModel;
import EHealthCare.model.PatientModel;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n******************E-HealthCare Management******************\n");
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n************************************");
        System.out.print("*          1. ADMIN - LOGIN        *\n");
        System.out.print("*          2. DOCTOR - LOGIN       *\n");
        System.out.print("*          3. PATIENT - LOGIN      *\n");
        System.out.print("*          4. PATIENT - SIGN-UP    *\n");
        System.out.print("*          5. EXIT                 *");
        System.out.println("\n************************************\n");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1: //admin
                AtomicBoolean failed = new AtomicBoolean(true);
                System.out.println("***************** Welcome to Admin Portal ***********************");
                String user, password;
                System.out.println("USER: "); user = scanner.next();
                System.out.println("PASSWORD: "); password = scanner.next();
                if(user.compareTo("admin") == 0 && password.compareTo("admin") == 0) {
                    failed.set(false);
                    if(!failed.get()) {
                        System.out.println("\n************************************");
                        System.out.print("*          1. ADD PATIENT          *\n");
                        System.out.print("*          2. DELETE PATIENT       *\n");
                        System.out.print("*          3. LIST PATIENTS       *\n");
                        System.out.print("*          5. LOGOUT               *");
                        System.out.println("\n************************************\n");
                        int ch = scanner.nextInt();

                        switch (ch) {
                            case 1:
                                String name, lastname, appointment;
                                System.out.println("***************** Admin Portal ***********************");
                                System.out.println("Name: "); name = scanner.next();
                                System.out.println("Last Name: "); lastname = scanner.next();
                                System.out.println("Appointment: "); appointment = scanner.next();
                                PatientModel patient = new PatientModel(name, lastname, appointment);
                                patient.setPlan(new HealthPlan(patient, null, HealthPlan.HealthPlanType.FREE, 0));
                                if(!patient.registerPatientInList()) {
                                    System.out.println("The patient was added!");
                                    main(args);
                                }
                                break;
                            case 3:

                                PatientModel.getPatients().forEach(p -> {
                                            System.out.println(String.format("Name: %s\nLast Name: %s\nAppointment: %s\nHealth Plan: %s",
                                            p.getName(), p.getLastname(), p.getAppointment(), p.getPlan().getType()));
                                });
                                break;
                            case 5:
                                main(args);
                                break;
                        }
                    }
                } else {
                    failed.set(true);
                    System.out.println("Invalid Username or Password");
                    break;
                }
                break;
            case 3: { //patient
                AtomicBoolean flag = new AtomicBoolean(true);
                System.out.println("***************** Welcome to Patient Portal ***********************");
                int id;
                String ps;
                System.out.print("ID: ");id=scanner.nextInt();
                System.out.print("Password: ");ps=scanner.next();
                try {
                    PatientModel.getPatients().forEach(patient -> {
                        if(patient.getId() == id && ps.compareTo("123") == 0) {
                            flag.set(true);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(flag.get()) {
                    PatientModel patient = PatientModel.getPatientsData().get(id);
                    if(patient != null) {
                        System.out.println("\n************************************");
                        System.out.println("\n************************************\n");
                    } else {
                        System.out.println("Patient is not exists.");
                        break;
                    }
                }
                break;
            }
        }
    }
}
