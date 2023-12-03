package use_case.signup;

import Entities.User;
import Entities.User_;

public interface SignupUserDataAcesssInterface {
    User getUser(String username);

    void addUser(User user);

}