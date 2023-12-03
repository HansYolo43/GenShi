package interface_adapter.main_menu;

import use_case.main_menu.MainMenuInputBoundary;
import use_case.main_menu.SwitchToGalleryInputBoundary;

public class MainMenuController {
    final SwitchToGalleryInputBoundary switchToGalleryInteractor;
    final MainMenuInputBoundary switchToGamblingInteractor;
    final MainMenuInputBoundary switchToGameInteractor;
    final MainMenuInputBoundary logoutInteractor;

    public MainMenuController(SwitchToGalleryInputBoundary switchToGalleryInteractor, MainMenuInputBoundary switchToGamblingInteractor, MainMenuInputBoundary switchToGameInteractor, MainMenuInputBoundary logoutInteractor) {

        this.switchToGalleryInteractor = switchToGalleryInteractor;
        this.switchToGamblingInteractor = switchToGamblingInteractor;
        this.switchToGameInteractor = switchToGameInteractor;
        this.logoutInteractor = logoutInteractor;
    }

    public MainMenuController(SwitchToGalleryInputBoundary switchToGalleryInteractor, MainMenuInputBoundary logoutInteractor) {

        this.switchToGalleryInteractor = switchToGalleryInteractor;
        this.switchToGamblingInteractor = null;
        this.switchToGameInteractor = null;
        this.logoutInteractor = logoutInteractor;
    }

    public void executeGallery() {
        switchToGalleryInteractor.execute();
    }

    public void executeGambling() {
        // TODO: Implement
    }

    public void executeGame() {
        // TODO: Implement
    }

    public void executeLogout() {
        logoutInteractor.execute();
    }
}
