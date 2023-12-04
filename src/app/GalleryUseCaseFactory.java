package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.card_stats.CardStatsViewModel;
import interface_adapter.gallery.GalleryController;
import interface_adapter.gallery.GalleryPresenter;
import interface_adapter.gallery.GalleryViewModel;
import interface_adapter.main_menu.MainMenuController;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.main_menu.SwitchToGalleryPresenter;
import use_case.StatsGallery.StatsGalleryDataAccessInterface;
import use_case.gallery.GalleryInputBoundary;
import use_case.gallery.GalleryInteractor;
import use_case.gallery.GalleryOutputBoundary;
import use_case.main_menu.SwitchToGalleryInputBoundary;
import use_case.main_menu.SwitchToGalleryInteractor;
import use_case.main_menu.SwitchToGalleryOutputBoundary;
import view.GalleryView;

import java.io.IOException;

public class GalleryUseCaseFactory {
    public static GalleryView create(ViewManagerModel viewManagerModel, GalleryViewModel galleryViewModel, CardStatsViewModel cardStatsViewModel, StatsGalleryDataAccessInterface StatsGalleryDataAccessInterface) throws IOException {
        GalleryController galleryController = createGalleryUseCase(viewManagerModel, galleryViewModel, cardStatsViewModel, StatsGalleryDataAccessInterface);
        return new GalleryView(galleryViewModel, galleryController);
    }

    private static GalleryController createGalleryUseCase(ViewManagerModel viewManagerModel, GalleryViewModel galleryViewModel, CardStatsViewModel cardStatsViewModel, StatsGalleryDataAccessInterface StatsGalleryDataAccessInterface) {

        // Notice how we pass this method's parameters to the Presenter.
        GalleryOutputBoundary galleryPresenter = new GalleryPresenter(viewManagerModel, galleryViewModel, cardStatsViewModel);
        GalleryInputBoundary galleryInteractor = new GalleryInteractor(galleryPresenter);

        return new GalleryController(galleryInteractor);
    }
}
