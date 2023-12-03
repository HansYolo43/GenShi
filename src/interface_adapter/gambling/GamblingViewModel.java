package interface_adapter.gambling;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeSupport;

public class GamblingViewModel extends ViewModel {
    public static final String GAMBLING_BUTTON_LABEL = "Gamble";
    private GamblingState state = new GamblingState();
    public GamblingViewModel() {
        super("gambling");
    }

    public void setState(GamblingState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public GamblingState getState() {
        return state;
    }
}