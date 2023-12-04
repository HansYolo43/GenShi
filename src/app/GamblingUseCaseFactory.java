package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.gallery.GalleryViewModel;
import interface_adapter.gambling.GamblingViewModel;
import interface_adapter.main_menu.MainMenuController;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.main_menu.SwitchToGalleryPresenter;
import use_case.main_menu.SwitchToGalleryInputBoundary;
import use_case.main_menu.SwitchToGalleryInteractor;
import use_case.main_menu.SwitchToGalleryOutputBoundary;
import view.GalleryView;
import view.GamblingView;

public class GamblingUseCaseFactory {
    public static GamblingView create(ViewManagerModel viewManagerModel, GamblingViewModel gamblingViewModel) {
        //GalleryController galleryUseCase = createGalleryUseCase(viewManagerModel, mainMenuViewModel, galleryViewModel);
        return new GamblingView(gamblingViewModel);
    }

    // TODO: not finished, controller not created.
    // Temporarily using MainMenuController for it to compile.
    private static MainMenuController createGalleryUseCase(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel, GalleryViewModel galleryViewModel) {

        // Notice how we pass this method's parameters to the Presenter.
        SwitchToGalleryOutputBoundary switchToGalleryPresenter = new SwitchToGalleryPresenter(viewManagerModel, mainMenuViewModel, galleryViewModel);
        SwitchToGalleryInputBoundary switchToGalleryInteractor = new SwitchToGalleryInteractor(switchToGalleryPresenter);

        return new MainMenuController(switchToGalleryInteractor);
    }
}
