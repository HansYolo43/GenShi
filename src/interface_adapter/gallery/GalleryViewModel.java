package interface_adapter.gallery;

import interface_adapter.ViewModel;
import interface_adapter.gallery.GalleryState;

import java.beans.PropertyChangeSupport;

public class GalleryViewModel extends ViewModel {
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String CARD_VIEW_BUTTON_LABEL = "View card";

    private GalleryState state = new GalleryState();
    public GalleryViewModel() {
        super("gallery");
    }

    public void setState(GalleryState state) {
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

    public GalleryState getState() {
        return state;
    }
}
