package use_case.signup;

import Entities.User;

public interface SignupUserDataAcesssInterface {
    User getUser(String username);

    void addUser(User user);

    void setActiveUser(User user);

}