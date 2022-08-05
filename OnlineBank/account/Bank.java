package OnlineBank.account;

import OnlineBank.Main;
import OnlineBank.backend.queries.BankSqlQueries;
import OnlineBank.model.ModelBank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class Bank {

    private int id;
    private String name;
    private UUID uniqueId;

    private int customers, cards;
    private CardBrandType brand;

    public Bank(ModelBank account) {
        this.name = account.getName();
        this.uniqueId = account.getUniqueId();
        this.customers = account.getCustomers();
        this.cards = account.getCards();
        this.brand = account.getBrand();
    }

    public Bank(String name, UUID uniqueId, CardBrandType brand) {
        this.name = name;
        this.uniqueId = uniqueId;
        this.brand = brand;
    }

    public Bank() {}

    public void createAndLoadBank() {
        try {
            Connection connection = Main.getSqlDatabase().getConnection();
            PreparedStatement bankQuery = connection.prepareStatement(BankSqlQueries.BANK_QUERY.toString());

            bankQuery.setString(1, getName());
            ResultSet bankData = bankQuery.executeQuery();

            if(bankData.next()) {
                setBrand(CardBrandType.valueOf(bankData.getString(4).toUpperCase()));
                setCustomers(bankData.getInt(5));
                setCards(bankData.getInt(6));
            } else {
                this.brand = CardBrandType.UNKNOWN;
                this.cards = 0;
                this.customers = 0;

                PreparedStatement bankInsert = connection.prepareStatement(BankSqlQueries.BANK_INSERT.toString());

                bankInsert.setString(1, uniqueId.toString());
                bankInsert.setString(2, name);
                bankInsert.setString(3, brand.getName());
                bankInsert.setInt(4, customers);
                bankInsert.setInt(5, cards);
                bankInsert.execute();
                bankInsert.close();

                PreparedStatement idQuery = Main.getSqlDatabase().getConnection().prepareStatement(BankSqlQueries.CUSTOMER_QUERY.toString());
                idQuery.setString(1, uniqueId.toString());
                ResultSet idData = bankQuery.executeQuery();

                if (idData.next()) {
                    this.id = idData.getInt(1);
                }
                idData.close();
                idQuery.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("Error when the OnlineBank tried to load the data of bank with the uuid: %s", uniqueId.toString());
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public int getCustomers() {
        return customers;
    }

    public int getCards() {
        return cards;
    }

    public CardBrandType getBrand() {
        return brand;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }

    public void setCards(int cards) {
        this.cards = cards;
    }

    public void setBrand(CardBrandType brand) {
        this.brand = brand;
    }

    public Bank getBankByName(String name) {
        Bank bank;
        try {
            Connection connection = Main.getSqlDatabase().getConnection();
            PreparedStatement bankQuery = connection.prepareStatement(BankSqlQueries.BANK_QUERY.toString());

            bankQuery.setString(1, name);
            ResultSet bankData = bankQuery.executeQuery();

            if(bankData.next()) {
                bank = new Bank(bankData.getString(2), UUID.fromString(bankData.getString(3)), CardBrandType.valueOf(bankData.getString(4)));
                return bank;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
