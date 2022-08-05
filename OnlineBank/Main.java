package OnlineBank;

import OnlineBank.account.Bank;
import OnlineBank.account.CardBrandType;
import OnlineBank.account.CustomerAccount;
import OnlineBank.backend.queries.BankSqlQueries;
import OnlineBank.backend.sql.SqlDatabase;

import java.util.UUID;

public class Main {

   private static final SqlDatabase database = new SqlDatabase("localhost", BankSqlQueries.DATABASE.toString(), "root", "", 3306);

    public static void main(String[] args) {
        try {
            database.connect();
            System.out.println("The connection of mysql was created!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Impossible to create the connection of mysql.");
        }

        CustomerAccount account = new CustomerAccount("Testing");
        account.createAndLoadCustomerAccount();

        Bank bank = new Bank("Lasvoit", UUID.randomUUID(), CardBrandType.ALL);
        bank.createAndLoadBank();
    }

    public static SqlDatabase getSqlDatabase() {
        return database;
    }
}
