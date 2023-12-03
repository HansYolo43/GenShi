package interface_adapter.main_menu;


import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MainMenuViewModel extends ViewModel {
    public static final String GALLERY_BUTTON_LABEL = "Go to card gallery";
    public static final String GAMBLING_BUTTON_LABEL = "Go to gambling screen";
    public static final String GAME_BUTTON_LABEL = "Go to game screen";
    public static final String LOGOUT_BUTTON_LABEL = "Log out";

    private MainMenuState state = new MainMenuState();
    public MainMenuViewModel() {
        super("main menu");
    }

    public void setState(MainMenuState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public MainMenuState getState() {
        return state;
    }
}