package use_case.signup;

import Entities.User;

public interface SignupUserDataAcesssInterface {
    boolean existsByName(String identifier);

    void save(User user);
}