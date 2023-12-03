package use_case.signup;

import Entities.User;
import Entities.UserFactory;

import java.util.ArrayList;
import java.util.Random;

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
        if (userDataAccessObject.getUser(signupInputData.getUsername()) != null) { // not empty, there is a user
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            ArrayList<Integer> card_ids = new ArrayList<>();
            card_ids.add(1122496);
            card_ids.add(1297133);
            card_ids.add(1342773);


            User user = userFactory.create(signupInputData.getUserid(),card_ids,1,5,signupInputData.getUsername(), signupInputData.getPassword());

            userDataAccessObject.addUser(user); // change this save. This should work cos duain db

            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
