package use_case.login;

import Entities.User;

public interface LoginUserDataAcesssInterface{
    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);
}
