package use_case.lootbox;

import Entities.Card;

public class LootBoxOutputData {
    private Card card;

    public LootBoxOutputData(Card card){
        this.card = card;
    }

    public Card getCard() {
        return card;
    }
}
