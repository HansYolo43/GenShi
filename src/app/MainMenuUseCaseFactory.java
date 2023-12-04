package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.gallery.GalleryViewModel;
import interface_adapter.gambling.GamblingViewModel;
import interface_adapter.generatecard.GenerateCardViewModel;
import interface_adapter.main_menu.*;
import use_case.main_menu.*;
import view.MainMenuView;

public class MainMenuUseCaseFactory {
    public static MainMenuView create(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel, GalleryViewModel galleryViewModel, GamblingViewModel gamblingViewModel, GenerateCardViewModel generateCardViewModel) {
        MainMenuController mainMenuController = createMainMenuUseCase(viewManagerModel, mainMenuViewModel, galleryViewModel, gamblingViewModel, generateCardViewModel);
        return new MainMenuView(mainMenuViewModel, mainMenuController);
    }

    private static MainMenuController createMainMenuUseCase(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel, GalleryViewModel galleryViewModel, GamblingViewModel gamblingViewModel, GenerateCardViewModel generateCardViewModel) {
        SwitchToGalleryOutputBoundary switchToGalleryPresenter = new SwitchToGalleryPresenter(viewManagerModel, mainMenuViewModel, galleryViewModel);
        SwitchToGalleryInputBoundary switchToGalleryInteractor = new SwitchToGalleryInteractor(switchToGalleryPresenter);

        SwitchToGamblingOutputBoundary switchToGamblingPresenter = new SwitchToGamblingPresenter(mainMenuViewModel, gamblingViewModel, viewManagerModel);
        SwitchToGamblingInputBoundary switchToGamblingInteractor = new SwitchToGamblingInteractor(switchToGamblingPresenter);

        SwitchToGenerateCardOutputBoundary switchToGenerateCardPresenter = new SwitchToGenerateCardPresenter(mainMenuViewModel, generateCardViewModel, viewManagerModel);
        SwitchToGenerateCardInputBoundary switchToGenerateCardInteractor = new SwitchToGenerateCardInteractor(switchToGenerateCardPresenter);
        return new MainMenuController(switchToGalleryInteractor, switchToGamblingInteractor, switchToGenerateCardInteractor);
    }
}
