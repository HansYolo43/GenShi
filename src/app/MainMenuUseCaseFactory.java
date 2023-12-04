package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.gallery.GalleryViewModel;
import interface_adapter.gambling.GamblingViewModel;
import interface_adapter.main_menu.MainMenuController;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.main_menu.SwitchToGalleryPresenter;
import interface_adapter.main_menu.SwitchToGamblingPresenter;
import use_case.main_menu.*;
import view.MainMenuView;

public class MainMenuUseCaseFactory {
    public static MainMenuView create(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel, GalleryViewModel galleryViewModel, GamblingViewModel gamblingViewModel) {
        MainMenuController mainMenuController = createMainMenuUseCase(viewManagerModel, mainMenuViewModel, galleryViewModel, gamblingViewModel);
        return new MainMenuView(mainMenuViewModel, mainMenuController);
    }

    private static MainMenuController createMainMenuUseCase(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel, GalleryViewModel galleryViewModel, GamblingViewModel gamblingViewModel) {
        SwitchToGalleryOutputBoundary switchToGalleryPresenter = new SwitchToGalleryPresenter(viewManagerModel, mainMenuViewModel, galleryViewModel);
        SwitchToGalleryInputBoundary switchToGalleryInteractor = new SwitchToGalleryInteractor(switchToGalleryPresenter);

        SwitchToGamblingOutputBoundary switchToGamblingPresenter = new SwitchToGamblingPresenter(mainMenuViewModel, gamblingViewModel, viewManagerModel);
        SwitchToGamblingInputBoundary switchToGamblingInteractor = new SwitchToGamblingInteractor(switchToGamblingPresenter);
        return new MainMenuController(switchToGalleryInteractor, switchToGamblingInteractor);
    }
}
