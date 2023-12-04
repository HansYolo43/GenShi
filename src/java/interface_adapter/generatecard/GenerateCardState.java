package interface_adapter.generatecard;

import Entities.Card;

public class GenerateCardState {
    private Card generatedCard;
    private String error;

    public GenerateCardState() {}

    public Card getGeneratedCard() {
        return generatedCard;
    }

    public void setGeneratedCard(Card generatedCard) {
        this.generatedCard = generatedCard;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
