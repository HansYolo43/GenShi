package use_case.logout;

import use_case.login.LoginOutputData;
import use_case.signup.SignupOutputData;

public interface LogoutOutputBoundary {

    void prepareSuccessView(LogoutOutputData user);

    void prepareFailView(String error);
}
