package OnlineBank.model;

import OnlineBank.account.CardBrandType;

public class ModelCard {

    private final String cardNumber, cardExpires, cardHolder;
    private final int cardCVC;
    private final CardBrandType brandType;

    public ModelCard(String cardNumber, String cardExpires, String cardHolder, int cardCVC, CardBrandType brandType) {
        this.cardNumber = cardNumber;
        this.cardExpires = cardExpires;
        this.cardHolder = cardHolder;
        this.cardCVC = cardCVC;
        this.brandType = brandType;
    }

    public void createAndLoadCard() {

    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardExpires() {
        return cardExpires;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public int getCardCVC() {
        return cardCVC;
    }

    public CardBrandType getBrandType() {
        return brandType;
    }
}
