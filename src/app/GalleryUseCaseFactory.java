package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.gallery.GalleryViewModel;
import interface_adapter.main_menu.MainMenuController;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.main_menu.SwitchToGalleryPresenter;
import use_case.main_menu.SwitchToGalleryInputBoundary;
import use_case.main_menu.SwitchToGalleryInteractor;
import use_case.main_menu.SwitchToGalleryOutputBoundary;
import view.GalleryView;

import java.io.IOException;

public class GalleryUseCaseFactory {
    public static GalleryView create(ViewManagerModel viewManagerModel, GalleryViewModel galleryViewModel) throws IOException {
        //GalleryController galleryUseCase = createGalleryUseCase(viewManagerModel, mainMenuViewModel, galleryViewModel);
        return new GalleryView(galleryViewModel);
    }

    // TODO: not finished, controller not created.
    // Temporarily using MainMenuController for it to compile.
    private static MainMenuController createGalleryUseCase(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel, GalleryViewModel galleryViewModel) {

        // Notice how we pass this method's parameters to the Presenter.
        SwitchToGalleryOutputBoundary switchToGalleryPresenter = new SwitchToGalleryPresenter(mainMenuViewModel, galleryViewModel, viewManagerModel);
        SwitchToGalleryInputBoundary switchToGalleryInteractor = new SwitchToGalleryInteractor(switchToGalleryPresenter);

        return new MainMenuController(switchToGalleryInteractor);
    }
}
