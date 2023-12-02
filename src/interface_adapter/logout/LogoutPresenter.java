package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.logout.LogoutOutputBoundary;


public class LogoutPresenter implements LogoutOutputBoundary {

    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public LogoutPresenter(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel,
                           LoggedInViewModel loggedInViewModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView() {
        LoginState loginState = loginViewModel.getState();
        LoggedInState loggedInState = loggedInViewModel.getState();
        loginState.setUsername(loggedInState.getUsername());
        loggedInState.setUsername("");  // reset the loggedin state that nobody is logged in
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    // I don't think there needs to be a fail view to log out

}
