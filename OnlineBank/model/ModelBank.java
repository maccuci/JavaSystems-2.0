package OnlineBank.model;

import OnlineBank.account.Bank;
import OnlineBank.account.CardBrandType;
import OnlineBank.account.CustomerAccount;

import java.util.Set;
import java.util.UUID;

public class ModelBank {

    private String name;
    private UUID uniqueId;

    private Set<CustomerAccount> customers;

    private Set<ModelCard> cards;
    private CardBrandType brand;

    public ModelBank(Bank account) {
        this.name = account.getName();
        this.uniqueId = account.getUniqueId();
        this.customers = account.getCustomers();
        this.cards = account.getCards();
        this.brand = account.getBrand();
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
