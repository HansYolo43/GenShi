package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.main_menu.MainMenuState;
import interface_adapter.main_menu.MainMenuViewModel;
import use_case.logout.LogoutOutputBoundary;


public class LogoutPresenter implements LogoutOutputBoundary {

    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;
    private final MainMenuViewModel mainMenuViewModel;

    public LogoutPresenter(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel,
                           MainMenuViewModel mainMenuViewModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.mainMenuViewModel = mainMenuViewModel;
    }

    @Override
    public void prepareSuccessView() { // todo: look here for bugs
        LoginState loginState = loginViewModel.getState();
        MainMenuState mainMenuState = mainMenuViewModel.getState();
        loginState.setUsername(mainMenuState.getUsername());
        mainMenuState.setUsername("");  // reset the loggedin state that nobody is logged in
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String error) { //todo: update that it uses view interface
        MainMenuState mainMenuState = mainMenuViewModel.getState();
        mainMenuViewModel.firePropertyChanged();
    }


}
