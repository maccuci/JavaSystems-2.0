package EHealthCare.manager;

public interface Payment {

    void fillCredentials(String holderName, String[] cardNumber, String expireDate, Integer cvcNumber);
}
