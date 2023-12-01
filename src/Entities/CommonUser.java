package Entities;


import java.util.ArrayList;

class CommonUser implements User {

    private final String name;
    private final String password;
    private ArrayList<Card> cards = new ArrayList<Card>();
    // the user puts in a name and password. They ideally don't start with any cards

//    /**
//     * Requires: password is valid.
//     * @param name
//     * @param password
//     */

    CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    // we want to be able to add cards when we get a card, and possibly get cards so we can view em
    public void add_card(Card card) {
        cards.add(card);
    }

    public ArrayList<Card> get_cards(){
        return cards;
    }

    // for testing purposes for how a sample user would behave with a predetermined set of cards
    public void set_cards(ArrayList<Card> pre_cards){
        this.cards = pre_cards;
    }

}
