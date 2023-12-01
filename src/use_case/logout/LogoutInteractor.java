package use_case.logout;

import Entities.User;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.login.LoginUserDataAcesssInterface;

public class LogoutInteractor implements LogoutInputBoundary{

    final LoginUserDataAcesssInterface userDataAccessObject;
    final LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutOutputBoundary logoutOutputBoundary,LoginUserDataAcesssInterface userDataAccessInterface
    ) {
        this.logoutPresenter = logoutOutputBoundary;
        this.userDataAccessObject = userDataAccessInterface;
    }
    @Override
    public void execute(LogoutData logoutData) {
        User user = userDataAccessObject.get(logoutData.getUsername());

        LogoutOutputData logoutOutputData = new LogoutOutputData(user.getName(), false);
        logoutPresenter.prepareSuccessView(logoutOutputData);

    }
}
