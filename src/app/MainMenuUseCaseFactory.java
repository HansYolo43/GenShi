package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.gallery.GalleryViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.main_menu.MainMenuController;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.main_menu.SwitchToGalleryPresenter;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.main_menu.*;
import view.MainMenuView;

public class MainMenuUseCaseFactory {
    public static MainMenuView create(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel, GalleryViewModel galleryViewModel, LoginViewModel loginViewModel) {
        MainMenuController mainMenuController = createMainMenuUseCase(viewManagerModel, mainMenuViewModel, galleryViewModel, loginViewModel);
        return new MainMenuView(mainMenuViewModel, mainMenuController);
    }

    private static MainMenuController createMainMenuUseCase(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel, GalleryViewModel galleryViewModel, LoginViewModel loginViewModel) {
        SwitchToGalleryOutputBoundary switchToGalleryPresenter = new SwitchToGalleryPresenter(mainMenuViewModel, galleryViewModel, viewManagerModel);
        SwitchToGalleryInputBoundary switchToGalleryInteractor = new SwitchToGalleryInteractor(switchToGalleryPresenter);

//        SwitchToGamblingOutputBoundary switchToGamblingPresenter = new SwitchToGamblingPresenter(mainMenuViewModel, gamblingViewModel, viewManagerModel);
//        SwitchToGamblingInputBoundary switchToGamblingInteractor = new SwitchToGamblingInteractor(switchToGamblingPresenter);

        LogoutOutputBoundary logoutPresenter = new LogoutPresenter(loginViewModel,viewManagerModel,mainMenuViewModel);
        MainMenuInputBoundary logoutInteractor = new LogoutInteractor(logoutPresenter);
        return new MainMenuController(switchToGalleryInteractor, logoutInteractor); // put my stiff in
    }
}