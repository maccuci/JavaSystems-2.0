package EHealthCare;

import EHealthCare.model.DoctorModel;

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
            case 1:
                System.out.println("***************** Welcome to Admin Portal ***********************");
                AtomicBoolean failed = new AtomicBoolean(true);
                String user, password;
                System.out.println("USER: "); user = scanner.next();
                System.out.println("PASSWORD: "); password = scanner.next();
                if(user.compareTo("abc") == 0 && password.compareTo("1234") == 0) {
                    failed.set(false);
                    if(!failed.get()) {
                        System.out.println("\n************************************");
                        System.out.println("\n************************************\n");
                    }
                } else {
                    failed.set(true);
                    System.out.println("Invalid Username or Password");
                    break;
                }
                break;
        }
    }
}
