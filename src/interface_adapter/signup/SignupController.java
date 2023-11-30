package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SignupController {

    final SignupInputBoundary userSignupUseCaseInteractor;
    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String password1, String password2) {
        SignupInputData signupInputData = new SignupInputData(
                username, password1, password2);

        userSignupUseCaseInteractor.execute(signupInputData); // question: why
        // it doesn't return anything yes, but what does this line of code even do?
    }
}
