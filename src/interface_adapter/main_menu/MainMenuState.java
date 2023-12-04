package interface_adapter.main_menu;

public class MainMenuState {
    // we have to keep track of four possible errors: gallery, gambling, game, and logout
    private String galleryError = null;
    private String gamblingError = null;
    private String gameError = null;
    private String logoutError = null;

    String username = "";
    public MainMenuState(MainMenuState copy) {
        galleryError = copy.galleryError;
        gamblingError = copy.gamblingError;
        gameError = copy.gameError;
        logoutError = copy.logoutError;
    }
    public MainMenuState() {}
    public String getGalleryError() {
        return galleryError;
    }
    public String getGamblingError() {
        return gamblingError;
    }
    public String getGameError() {
        return gameError;
    }
    public String getLogoutError() {
        return logoutError;
    }
    public void setGalleryError(String galleryError) {
        this.galleryError = galleryError;
    }
    public void setGamblingError(String gamblingError) {
        this.gamblingError = gamblingError;
    }
    public void setGameError(String gameError) {
        this.gameError = gameError;
    }
    public void setLogoutError(String logoutError) {
        this.logoutError = logoutError;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}