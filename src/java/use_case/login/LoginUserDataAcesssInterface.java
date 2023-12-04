package use_case.login;

import Entities.User;


public interface LoginUserDataAcesssInterface{

    void addUser(User user); //save

    User getUser(String username); // if it returns null, user does not exist in the systenm

    void setActiveUser(User user);
    // else we return user and their information
}
