package use_case.login;

import Entities.User_;

public interface LoginUserDataAcesssInterface{
    boolean existsByName(String identifier);

    void save(User_ user);

    User_ get(String username);
}
