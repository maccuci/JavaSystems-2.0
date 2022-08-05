package OnlineBank.account;

import OnlineBank.Main;
import OnlineBank.backend.queries.BankSqlQueries;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CustomerAccount {

    private static final Executor saveAsyncExecutor = Executors
            .newSingleThreadExecutor((new ThreadFactoryBuilder()).setNameFormat("Save Async Thread").build());

    private int id;
    private String name;
    private UUID uniqueId;

    private String bank;
    private CardBrandType brandType;
    private String cardNumber, cardExpires, cardHolder;
    private int cardCVC;

    public CustomerAccount(String name) {
        this.name = name;
        this.uniqueId = UUID.randomUUID();
    }

    public void createAndLoadCustomerAccount() {
        try {
            Connection connection = Main.getSqlDatabase().getConnection();
            PreparedStatement accountQuery = connection.prepareStatement(BankSqlQueries.CUSTOMER_QUERY.toString());

            accountQuery.setString(1, getUniqueId().toString());
            ResultSet accountData = accountQuery.executeQuery();

            if(accountData.next()) {
                setName(accountData.getString(2));
                setBank(accountData.getString(3));
                setBrandType(CardBrandType.valueOf(accountData.getString(4)));
                setCardNumber(accountData.getString(5));
                setCardExpires(accountData.getString(6));
                setCardHolder(accountData.getString(7));
                setCardCVC(accountData.getInt(8));
            } else {
                this.bank = "";
                this.brandType = CardBrandType.UNKNOWN;
                this.cardNumber = "";
                this.cardExpires = "";
                this.cardHolder = "";
                this.cardCVC = 0;

                PreparedStatement accountInsert = connection.prepareStatement(BankSqlQueries.CUSTOMER_INSERT.toString());

                accountInsert.setString(1, uniqueId.toString());
                accountInsert.setString(2, name);
                accountInsert.setString(3, bank);
                accountInsert.setString(4, brandType.getName());
                accountInsert.setString(5, cardNumber);
                accountInsert.setString(6, cardExpires);
                accountInsert.setString(7, cardHolder);
                accountInsert.setInt(8, cardCVC);
                accountInsert.execute();
                accountInsert.close();

                PreparedStatement idQuery = Main.getSqlDatabase().getConnection().prepareStatement(BankSqlQueries.CUSTOMER_QUERY.toString());
                idQuery.setString(1, uniqueId.toString());
                ResultSet idData = accountQuery.executeQuery();

                if (idData.next()) {
                    this.id = idData.getInt(1);
                }
                idData.close();
                idQuery.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("Error when the OnlineBank tried to load the data of customer with the uuid: %s", uniqueId.toString());
        }
    }

    public void update() {
        saveAsyncExecutor.execute(() -> {
            try {
                Connection connection = Main.getSqlDatabase().getConnection();
                PreparedStatement statement = connection.prepareStatement(BankSqlQueries.CUSTOMER_UPDATE.toString());

                statement.setString(1, name);
                statement.setString(2, bank);
                statement.setString(3, brandType.getName());
                statement.setString(4, cardNumber);
                statement.setString(5, cardExpires);
                statement.setString(6, cardHolder);
                statement.setInt(7, cardCVC);
                statement.setInt(8, id);
                statement.execute();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public CardBrandType getBrandType() {
        return brandType;
    }

    public void setBrandType(CardBrandType brandType) {
        this.brandType = brandType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public int getCardCVC() {
        return cardCVC;
    }

    public void setCardCVC(int cardCVC) {
        this.cardCVC = cardCVC;
    }

    public String getCardExpires() {
        return cardExpires;
    }

    public void setCardExpires(String cardExpires) {
        this.cardExpires = cardExpires;
    }
}
