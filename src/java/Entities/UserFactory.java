package Entities;

import java.util.ArrayList;

public interface UserFactory {
    /** Requires: password is valid. */
    User create(Integer userid, ArrayList<Integer> cardsOwned, Integer userlevel, Integer currency, String username, String password);
}