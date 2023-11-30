package use_case.signup;

import Entities.User;
import Entities.UserFactory;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAcesssInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter; //signuppRESENTER
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAcesssInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
            userDataAccessObject.
                    save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getName(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
