package Entities;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private ArrayList<Integer> cards_owned;

    private Integer Currency ;

    private String Username;

    private String password;

    private HashMap<Integer, Card> Card_level;

    public User(ArrayList<Integer> cardsOwned, Integer currency, String username, String password, HashMap<Integer, Card> cardLevel) {
        this.cards_owned = cardsOwned;
        this.Currency = currency;
        this.Username = username;
        this.password = password;
        this.Card_level = cardLevel;
    }
}
