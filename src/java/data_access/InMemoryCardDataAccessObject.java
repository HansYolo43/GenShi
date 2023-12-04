package data_access;

import Entities.Card;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCardDataAccessObject {
    private final Map<Integer, Card> Cards = new HashMap<>();

    public void addCard(Card card) {
        Cards.put(card.getId(), card);
    }


    public Card getCard(int cardId) {
        return Cards.get(cardId);
    }


    public void setDescription(int cardId, String newDescription) {
        if (Cards.containsKey(cardId)) {
            Card card = Cards.get(cardId);
            card.setDesc(newDescription);
        }
    }

    public void removeCard(int cardId) {
        Cards.remove(cardId);
    }


    public Map<Integer, Card> getAllCards() {
        return new HashMap<>(Cards);
    }
}
