package OnlineBank;

import OnlineBank.account.Bank;
import OnlineBank.account.CardBrandType;
import OnlineBank.account.CustomerAccount;
import OnlineBank.backend.queries.CoreQueries;
import OnlineBank.backend.sql.SqlDatabase;

import java.util.Date;
import java.util.UUID;

public class Main {

   private static final SqlDatabase database = new SqlDatabase("localhost", CoreQueries.DATABASE.toString(), "root", "", 3306);

    public static void main(String[] args) {
        try {
            database.connect();
            CustomerAccount account = new CustomerAccount("Testing");
            account.createAndLoadCustomerAccount();
            System.out.println("The connection of mysql was created!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Impossible to create the connection of mysql.");
        }
    }

    public static SqlDatabase getSqlDatabase() {
        return database;
    }
}
