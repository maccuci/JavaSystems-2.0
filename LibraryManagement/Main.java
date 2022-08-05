package LibraryManagement;

import LibraryManagement.backend.queries.LibrarySqlQueries;
import LibraryManagement.backend.sql.LibrarySqlDatabase;

import java.util.Scanner;

public class Main {

    private static final LibrarySqlDatabase database = new LibrarySqlDatabase("localhost", LibrarySqlQueries.DATABASE.toString(), "root", "", 3306);

    public static void main(String[] args) {
        try {
            database.connect();
        } catch (Exception e) {
                System.out.println(e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("************ Library Management ************");
        System.out.println("*            1) ADD BOOK                  *");
        System.out.println("*            2) REMOVE BOOK               *");
        System.out.println("*            3) EDIT BOOK                 *");
        System.out.println("*            4) INFO BOOK                 *");
        System.out.println("*            5) LIST BOOKS                *");
        System.out.println("*            6) EXIT                      *");
        System.out.println("************ Library Management ************");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                break;
            case 6:
                System.out.println("The LibraryManagement was closed.");
                scanner.close();
                break;
        }
    }
}
