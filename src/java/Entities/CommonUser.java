package Entities;


import java.util.ArrayList;

class CommonUser extends User{
    Integer userid;
    ArrayList<Integer> cardsOwned;
    Integer userlevel;
    Integer currency;
    String username;
    String password;

    CommonUser(Integer userid, ArrayList<Integer> cardsOwned, Integer userlevel, Integer currency, String username, String password) {
        super(userid,cardsOwned,userlevel,currency,username,password);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    // we want to be able to add cards when we get a card, and possibly get cards so we can view em

    public void setUsername(String username) {
        this.username=username;
    }

    public ArrayList<Integer> getCards_owned() {
        return super.getCards_owned();
    }

    public void setCards_owned(ArrayList<Integer> cards_owned) {
        this.cardsOwned = cards_owned;
    }

    public Integer getUserid() {
        return super.getUserid();
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getUserlevel() {
        return super.getUserlevel();
    }

    public void setUserlevel(Integer userlevel) {
        this.userlevel = userlevel;
    }

    public Integer getCurrency() {
        return super.getCurrency();
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}