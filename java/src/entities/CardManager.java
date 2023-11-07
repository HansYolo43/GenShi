package entities;

import java.util.ArrayList;

public class CardManager {
    private ArrayList<Card> cards;
    public CardManager() {
        cards = new ArrayList<Card>();
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
