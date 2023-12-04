package interface_adapter.login;

import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.main_menu.MainMenuState;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.signup.SignupState;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    //private final LoggedInViewModel loggedInViewModel;

    private final MainMenuViewModel mainMenuViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          MainMenuViewModel mainMenuViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        //this.loggedInViewModel = loggedInViewModel;
        this.mainMenuViewModel = mainMenuViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) { // change this to mainmenuview
        // On success, switch to the logged in view.

//        LoggedInState loggedInState = loggedInViewModel.getState(); // the state is initially empty
//        loggedInState.setUsername(response.getUsername()); // then i set it with login data to use a username
//        this.loggedInViewModel.setState(loggedInState); // the view model looks at state. I have changed it now
//        this.loggedInViewModel.firePropertyChanged(); // fire changed. view change to this new state

        MainMenuState loggedInState = mainMenuViewModel.getState(); // the state is initially empty
        loggedInState.setUsername(response.getUsername()); // then i set it with login data to use a username
        this.mainMenuViewModel.setState(loggedInState); // the view model looks at state. I have changed it now
        this.mainMenuViewModel.firePropertyChanged(); //

        this.viewManagerModel.setActiveView(mainMenuViewModel.getViewName()); // view for logged in has changed. set
        // it to active. it is actually the view manager that will show. hopefully.
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error); // used in main to show something when there is an error
        loginViewModel.firePropertyChanged();
        // it won't change view. it will stay on view. However it swill show an error because pf the error part.
    }
}
