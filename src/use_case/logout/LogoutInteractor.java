package use_case.logout;

import use_case.main_menu.MainMenuInputBoundary;

public class LogoutInteractor implements MainMenuInputBoundary {
    final LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutOutputBoundary logoutOutputBoundary
    ) {
        this.logoutPresenter = logoutOutputBoundary;
    }
    @Override
    public void execute() {
        logoutPresenter.prepareSuccessView();
    } // todo: write something where
    // i can fail to log out
}
