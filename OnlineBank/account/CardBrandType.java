package OnlineBank.account;

public enum CardBrandType {

    AMEX("Amex"),
    MASTERCARD("MasterCard"),
    VISA("Visa"),
    ALL("MasterCard Visa Amex"),
    UNKNOWN("Unknown");

    private final String name;

    CardBrandType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
