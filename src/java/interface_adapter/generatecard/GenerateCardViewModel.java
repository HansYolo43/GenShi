package interface_adapter.generatecard;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeSupport;

public class GenerateCardViewModel extends ViewModel {
    private GenerateCardState state = new GenerateCardState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GenerateCardViewModel() {
        super("generate_card");
    }

    public GenerateCardState getState() {
        return state;
    }

    public void setState(GenerateCardState state) {
        this.state = state;
        firePropertyChanged();
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
