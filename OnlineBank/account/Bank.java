package OnlineBank.account;

import OnlineBank.model.ModelBank;
import OnlineBank.model.ModelCard;

import java.util.Set;
import java.util.UUID;

public class Bank {

    private String name;
    private UUID uniqueId;

    private Set<CustomerAccount> customers;

    private Set<ModelCard> cards;
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

    public void createBank() {

    }

    public String getName() {
        return name;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public Set<CustomerAccount> getCustomers() {
        return customers;
    }

    public Set<ModelCard> getCards() {
        return cards;
    }

    public CardBrandType getBrand() {
        return brand;
    }
}
