package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_menu.GalleryViewModel;
import interface_adapter.main_menu.MainMenuController;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.main_menu.SwitchToGalleryPresenter;
import use_case.main_menu.MainMenuInputBoundary;
import use_case.main_menu.SwitchToGalleryInputBoundary;
import use_case.main_menu.SwitchToGalleryInteractor;
import use_case.main_menu.SwitchToGalleryOutputBoundary;
import view.MainMenuView;
import view.ViewManager;

import java.io.IOException;

public class MainMenuUseCaseFactory {
    public static MainMenuView create(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel, GalleryViewModel galleryViewModel) {
        MainMenuController mainMenuController = createMainMenuUseCase(viewManagerModel, mainMenuViewModel, galleryViewModel);
        return new MainMenuView(mainMenuViewModel, mainMenuController);
    }

    private static MainMenuController createMainMenuUseCase(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel, GalleryViewModel galleryViewModel) {
        SwitchToGalleryOutputBoundary switchToGalleryPresenter = new SwitchToGalleryPresenter(mainMenuViewModel, galleryViewModel, viewManagerModel);
        SwitchToGalleryInputBoundary switchToGalleryInteractor = new SwitchToGalleryInteractor(switchToGalleryPresenter);
        return new MainMenuController(switchToGalleryInteractor);
    }
}
