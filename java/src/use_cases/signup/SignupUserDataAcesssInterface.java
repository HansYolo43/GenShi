package use_cases.signup;
import entities.User;

public interface SignupUserDataAcesssInterface {
    boolean existsByName(String identifier);

    void save(User user);
}
