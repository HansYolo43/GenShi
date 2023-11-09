package interface_adapter.main_menu;

import interface_adapter.ViewManagerModel;

public class MainMenuController {
    private final ViewManagerModel viewManagerModel;

    public MainMenuController(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    // Not sure if separating execute is legal in Clean Architecture
    public void executeGallery() {
        // TODO: Implement
    }

    public void executeGambling() {
        // TODO: Implement
    }

    public void executeGame() {
        // TODO: Implement
    }

    public void executeLogout() {
        // TODO: Implement
    }
}
