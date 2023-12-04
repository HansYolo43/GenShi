package interface_adapter.main_menu;

public class MainMenuState {
    private String username = "";

    public MainMenuState(MainMenuState copy) {
        this.username = copy.username;
    }
    public MainMenuState() {}
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
