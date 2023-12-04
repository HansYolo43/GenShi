package Entities;

import java.util.ArrayList;

public class CommonUserFactory implements UserFactory {

    @Override
    public User create(Integer userid, ArrayList<Integer> cardsOwned, Integer userlevel, Integer currency,
                       String username, String password) {
        return new CommonUser(userid,cardsOwned,userlevel,currency,username,password);
    }
}