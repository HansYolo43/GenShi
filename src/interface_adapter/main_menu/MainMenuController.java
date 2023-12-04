package interface_adapter.main_menu;

import use_case.main_menu.MainMenuInputBoundary;
import use_case.main_menu.SwitchToGalleryInputBoundary;
import use_case.main_menu.SwitchToGamblingInputBoundary;

public class MainMenuController {
    final SwitchToGalleryInputBoundary switchToGalleryInteractor;
    final SwitchToGamblingInputBoundary switchToGamblingInteractor;
    final MainMenuInputBoundary switchToGameInteractor;
    final MainMenuInputBoundary logoutInteractor;

    public MainMenuController(SwitchToGalleryInputBoundary switchToGalleryInteractor, SwitchToGamblingInputBoundary switchToGamblingInteractor, MainMenuInputBoundary switchToGameInteractor, MainMenuInputBoundary logoutInteractor) {

        this.switchToGalleryInteractor = switchToGalleryInteractor;
        this.switchToGamblingInteractor = switchToGamblingInteractor;
        this.switchToGameInteractor = switchToGameInteractor;
        this.logoutInteractor = logoutInteractor;
    }

    // for debugging
    public MainMenuController(SwitchToGalleryInputBoundary switchToGalleryInteractor, SwitchToGamblingInputBoundary switchToGamblingInteractor) {

        this.switchToGalleryInteractor = switchToGalleryInteractor;
        this.switchToGamblingInteractor = switchToGamblingInteractor;
        this.switchToGameInteractor = null;
        this.logoutInteractor = null;
    }

    // for debugging
    public MainMenuController(SwitchToGalleryInputBoundary switchToGalleryInteractor) {

        this.switchToGalleryInteractor = switchToGalleryInteractor;
        this.switchToGamblingInteractor = null;
        this.switchToGameInteractor = null;
        this.logoutInteractor = null;
    }

    public void executeGallery() {
        switchToGalleryInteractor.execute();
    }

    public void executeGambling() {
        switchToGamblingInteractor.execute();
    }

    public void executeGame() {
        // TODO: Implement
    }

    public void executeLogout() {
        // TODO: Implement
    }
}
