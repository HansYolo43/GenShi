package Entities;

import java.util.ArrayList;

public class CardManager {
    private final ArrayList<Card> cards;

    private final ArrayList<Integer> deck;

    public CardManager(ArrayList<Integer> deckids) {
        this.deck = deckids;
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public int getSize() {
        return cards.size();
    }

    public String toString() {
        String result = "";
        for (Card card : cards) {
            result += card + "\n";
        }
        return result;
    }
}
