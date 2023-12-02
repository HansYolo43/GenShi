package use_case.signup;

import Entities.User_;

public interface SignupUserDataAcesssInterface {
    boolean existsByName(String identifier);

    void save(User_ user);
}