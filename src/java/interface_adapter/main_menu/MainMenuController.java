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

    public MainMenuController(SwitchToGalleryInputBoundary switchToGalleryInteractor,
                              SwitchToGamblingInputBoundary switchToGamblingInteractor,
                              SwitchToGenerateCardInputBoundary SwitchToGenerateCardInteractor,
                              MainMenuInputBoundary logoutInteractor) {

        this.switchToGalleryInteractor = switchToGalleryInteractor;
        this.switchToGamblingInteractor = switchToGamblingInteractor;
        this.switchToGenerateCardInteractor = SwitchToGenerateCardInteractor;
        this.logoutInteractor = logoutInteractor;
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
        logoutInteractor.execute();
    }
}
