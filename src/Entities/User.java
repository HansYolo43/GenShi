package Entities;

import java.util.ArrayList;
import java.util.HashMap;

public class User {

    private Integer userid;

    private ArrayList<Integer> cards_owned;

    private Integer userlevel;

    private Integer Currency;

    private String Username;

    private String password;

//    private HashMap<Integer, Integer> Card_level; //Cardlevel : CardID

    public User(Integer userid, ArrayList<Integer> cardsOwned, Integer userlevel, Integer currency, String username, String password) {
        this.userid = userid;
        this.userlevel = userlevel;
        this.cards_owned = cardsOwned;
        this.Currency = currency;
        this.Username = username;
        this.password = password;
//        this.Card_level = cardLevel;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public ArrayList<Integer> getCards_owned() {
        return cards_owned;
    }

    public void setCards_owned(ArrayList<Integer> cards_owned) {
        this.cards_owned = cards_owned;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(Integer userlevel) {
        this.userlevel = userlevel;
    }

    public Integer getCurrency() {
        return Currency;
    }

    public void setCurrency(Integer currency) {
        Currency = currency;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addownedcard(Integer cardid){cards_owned.add(cardid);}
}
//    public HashMap<Integer, Card> getCard_level() {
//        return Card_level;
//    }
//
//    public void setCard_level(HashMap<Integer, Card> card_level) {
//        Card_level = card_level;
//    }
//}
