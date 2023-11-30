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

}
