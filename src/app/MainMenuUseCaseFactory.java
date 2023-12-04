package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.gallery.GalleryViewModel;
import interface_adapter.gambling.GamblingViewModel;
import interface_adapter.generatecard.GenerateCardViewModel;
import interface_adapter.main_menu.MainMenuController;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.main_menu.SwitchToGalleryPresenter;
import interface_adapter.main_menu.SwitchToGamblingPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutPresenter;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.main_menu.*;
import view.MainMenuView;

public class MainMenuUseCaseFactory {
    public static MainMenuView create(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel, GalleryViewModel galleryViewModel,LoginViewModel loginViewModel GamblingViewModel gamblingViewModel) {
        MainMenuController mainMenuController = createMainMenuUseCase(viewManagerModel, mainMenuViewModel, galleryViewModel,loginViewModel, gamblingViewModel);
        return new MainMenuView(mainMenuViewModel, mainMenuController);
    }

    private static MainMenuController createMainMenuUseCase(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel, GalleryViewModel galleryViewModel,LoginViewModel loginViewModel, GamblingViewModel gamblingViewModel) {
        SwitchToGalleryOutputBoundary switchToGalleryPresenter = new SwitchToGalleryPresenter(viewManagerModel, mainMenuViewModel, galleryViewModel);
        SwitchToGalleryInputBoundary switchToGalleryInteractor = new SwitchToGalleryInteractor(switchToGalleryPresenter);

        SwitchToGamblingOutputBoundary switchToGamblingPresenter = new SwitchToGamblingPresenter(mainMenuViewModel, gamblingViewModel, viewManagerModel);
        SwitchToGamblingInputBoundary switchToGamblingInteractor = new SwitchToGamblingInteractor(switchToGamblingPresenter);

        SwitchToGenerateCardOutputBoundary switchToGenerateCardPresenter = new SwitchToGenerateCardPresenter(mainMenuViewModel, generateCardViewModel, viewManagerModel);
        SwitchToGenerateCardInputBoundary switchToGenerateCardInteractor = new SwitchToGenerateCardInteractor(switchToGenerateCardPresenter);

        LogoutOutputBoundary logoutPresenter = new LogoutPresenter(loginViewModel,viewManagerModel,mainMenuViewModel);
        MainMenuInputBoundary logoutInteractor = new LogoutInteractor(logoutPresenter);
        return new MainMenuController(switchToGalleryInteractor, switchToGamblingInteractor,switchToGenerateCardInteractor,logoutInteractor);
    }
}
