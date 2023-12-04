package use_case.signup;

import Entities.User;
import Entities.UserFactory;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import java.util.ArrayList;
import java.util.Random;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAcesssInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter; //signuppRESENTER
    final UserFactory userFactory;

    final LoginOutputBoundary loginpresenter;



    public SignupInteractor(SignupUserDataAcesssInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory, LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
        this.loginpresenter = loginOutputBoundary;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.getUser(signupInputData.getUsername()) != null) {
            if ((userDataAccessObject.getUser(signupInputData.getUsername()).getPassword()).
                    equals(signupInputData.getPassword())) {
                ;
                // not empty, there is a user
                //userPresenter.prepareFailView("User already exists.");
                LoginOutputData loginOutputData = new LoginOutputData(signupInputData.getUsername(), false);
                loginpresenter.prepareSuccessView(loginOutputData);
                userDataAccessObject.setActiveUser((userDataAccessObject.getUser(signupInputData.getUsername())));

            }else {userPresenter.prepareFailView("wrong password");}


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

            userDataAccessObject.setActiveUser((userDataAccessObject.getUser(signupInputData.getUsername())));
        }
    }
}
