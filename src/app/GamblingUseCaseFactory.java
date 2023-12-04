package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.card_stats.CardStatsViewModel;
import interface_adapter.gallery.GalleryController;
import interface_adapter.gallery.GalleryPresenter;
import interface_adapter.gallery.GalleryViewModel;
import interface_adapter.gambling.GamblingController;
import interface_adapter.gambling.GamblingPresenter;
import interface_adapter.gambling.GamblingViewModel;
import interface_adapter.main_menu.MainMenuController;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.main_menu.SwitchToGalleryPresenter;
import use_case.StatsGallery.StatsGalleryDataAccessInterface;
import use_case.gallery.GalleryInputBoundary;
import use_case.gallery.GalleryInteractor;
import use_case.gallery.GalleryOutputBoundary;
import use_case.lootbox.LootBoxInputBoundary;
import use_case.lootbox.LootBoxOutputBoundary;
import use_case.lootbox.LootboxInteractor;
import use_case.lootbox.LootboxUserDataAccessInterface;
import use_case.main_menu.SwitchToGalleryInputBoundary;
import use_case.main_menu.SwitchToGalleryInteractor;
import use_case.main_menu.SwitchToGalleryOutputBoundary;
import view.GalleryView;
import view.GamblingView;

import java.io.IOException;

public class GamblingUseCaseFactory {
    public static GamblingView create(ViewManagerModel viewManagerModel, GamblingViewModel gamblingViewModel, LootboxUserDataAccessInterface dao) throws IOException {
        GamblingController gamblingController = createGamblingUseCase(viewManagerModel, gamblingViewModel, dao);
        return new GamblingView(gamblingViewModel, gamblingController);
    }

    private static GamblingController createGamblingUseCase(ViewManagerModel viewManagerModel, GamblingViewModel gamblingViewModel, LootboxUserDataAccessInterface dao) {

        // Notice how we pass this method's parameters to the Presenter.
        LootBoxOutputBoundary gamblingPresenter = new GamblingPresenter(gamblingViewModel, viewManagerModel);
        LootBoxInputBoundary gamblingInteractor = new LootboxInteractor(dao, gamblingPresenter);
        return new GamblingController(gamblingInteractor);
    }
}
