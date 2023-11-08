package use_cases.login;

import entities.User;

public interface LoginUserDataAcesssInterface{
    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);
}