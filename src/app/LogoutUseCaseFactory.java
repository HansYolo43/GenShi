package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import view.LoggedInView;
import javax.swing.*;
import java.io.IOException;

public class LogoutUseCaseFactory {

    public static LoggedInView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel) {

        try {
            LogoutController logoutController = createLogoutUseCase(viewManagerModel, loginViewModel,
                    loggedInViewModel);
            return new LoggedInView(loggedInViewModel, logoutController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not logout.");
        }

        return null;
    }

    private static LogoutController createLogoutUseCase(ViewManagerModel viewManagerModel,
                                                        LoginViewModel loginViewModel,
                                                        LoggedInViewModel loggedInViewModel
    )
            throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(loginViewModel,viewManagerModel,loggedInViewModel);


        LogoutInputBoundary logoutUserInteractor = new LogoutInteractor(logoutOutputBoundary);

        return new LogoutController(logoutUserInteractor);
        // click a button, controller executes, then to interactor executes then to presenter executes
        // to change the view from logged in to log in. Basically a chain of execution.
    }
}
