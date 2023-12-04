package interface_adapter.card_stats;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeSupport;

public class CardStatsViewModel extends ViewModel {
    private CardStatsState state = new CardStatsState();

    public CardStatsViewModel() {
        super("card_stats");
    }

    public void setState(CardStatsState state) {
        this.state = state;
    }

    public CardStatsState getState() {
        return state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    @Override
    public void firePropertyChanged() {
        System.out.println(this.state.getDescription());
        System.out.println("hello");
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
