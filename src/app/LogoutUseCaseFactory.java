//package app;
//
//import interface_adapter.ViewManagerModel;
//import interface_adapter.logged_in.LoggedInViewModel;
//import interface_adapter.login.LoginViewModel;
//import interface_adapter.logout.LogoutController;
//import interface_adapter.logout.LogoutPresenter;
//import interface_adapter.main_menu.MainMenuController;
//import interface_adapter.main_menu.MainMenuViewModel;
//import use_case.logout.LogoutInputBoundary;
//import use_case.logout.LogoutInteractor;
//import use_case.logout.LogoutOutputBoundary;
//import use_case.main_menu.MainMenuInputBoundary;
//import view.LoggedInView;
//import view.MainMenuView;
//
//import javax.swing.*;
//import java.io.IOException;
//
//public class LogoutUseCaseFactory {
//
//    public static LoggedInView create(
//            ViewManagerModel viewManagerModel,
//            LoginViewModel loginViewModel,
//            MainMenuViewModel mainMenuViewModel) {
//
//        try {
//             MainMenuController mainMenuController = createLogoutUseCase(viewManagerModel, loginViewModel,
//                    mainMenuViewModel);
//            return new MainMenuView(mainMenuViewModel, mainMenuController);
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Could not logout.");
//        }
//
//        return null;
//    }
//
//    private static MainMenuController createLogoutUseCase(ViewManagerModel viewManagerModel,
//                                                        LoginViewModel loginViewModel,
//                                                        MainMenuViewModel mainMenuViewModel
//    )
//            throws IOException {
//
//        // Notice how we pass this method's parameters to the Presenter.
//
//        LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(loginViewModel,viewManagerModel,mainMenuViewModel);
//
//
//        MainMenuInputBoundary logoutUserInteractor = new LogoutInteractor(logoutOutputBoundary);
//
//        return new MainMenuController(logoutUserInteractor);
//        // click a button, controller executes, then to interactor executes then to presenter executes
//        // to change the view from logged in to log in. Basically a chain of execution.
//    }
//}
