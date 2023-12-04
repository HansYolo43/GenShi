package interface_adapter.main_menu;

import use_case.main_menu.MainMenuInputBoundary;
import use_case.main_menu.SwitchToGalleryInputBoundary;
import use_case.main_menu.SwitchToGamblingInputBoundary;
import use_case.main_menu.SwitchToGenerateCardInputBoundary;

public class MainMenuController {
    final SwitchToGalleryInputBoundary switchToGalleryInteractor;
    final SwitchToGamblingInputBoundary switchToGamblingInteractor;
    final SwitchToGenerateCardInputBoundary switchToGenerateCardInteractor;
    final MainMenuInputBoundary logoutInteractor;

    public MainMenuController(SwitchToGalleryInputBoundary switchToGalleryInteractor, SwitchToGamblingInputBoundary switchToGamblingInteractor, SwitchToGenerateCardInputBoundary SwitchToGenerateCardInteractor, MainMenuInputBoundary logoutInteractor) {

        this.switchToGalleryInteractor = switchToGalleryInteractor;
        this.switchToGamblingInteractor = switchToGamblingInteractor;
        this.switchToGenerateCardInteractor = SwitchToGenerateCardInteractor;
        this.logoutInteractor = logoutInteractor;
    }

    // for debugging
//    public MainMenuController(SwitchToGalleryInputBoundary switchToGalleryInteractor, SwitchToGamblingInputBoundary switchToGamblingInteractor) {
//
//        this.switchToGalleryInteractor = switchToGalleryInteractor;
//        this.switchToGamblingInteractor = switchToGamblingInteractor;
//        this.switchToGenerateCardInteractor = null;
//        this.logoutInteractor = null;
//    }

    // for debugging
    public MainMenuController(SwitchToGalleryInputBoundary switchToGalleryInteractor, SwitchToGamblingInputBoundary switchToGamblingInteractor, SwitchToGenerateCardInputBoundary SwitchToGenerateCardInteractor) {

        this.switchToGalleryInteractor = switchToGalleryInteractor;
        this.switchToGamblingInteractor = switchToGamblingInteractor;
        this.switchToGenerateCardInteractor = SwitchToGenerateCardInteractor;
        this.logoutInteractor = null;
    }

    public void executeGallery() {
        switchToGalleryInteractor.execute();
    }

    public void executeGambling() {
        switchToGamblingInteractor.execute();
    }

    public void executeGenerateCard() {
        switchToGenerateCardInteractor.execute();
    }

    public void executeLogout() {
        // TODO: Implement
    }
}
