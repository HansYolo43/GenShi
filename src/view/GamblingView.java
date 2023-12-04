package view;

import interface_adapter.gallery.GalleryState;
import interface_adapter.gallery.GalleryViewModel;
import interface_adapter.gambling.GamblingState;
import interface_adapter.gambling.GamblingViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GamblingView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "gambling";
    private final GamblingViewModel viewModel;

    private final JButton gambleButton;
    // TODO: add controllers

    public GamblingView(GamblingViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel("gambling");
        title.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        JPanel buttons = new JPanel();
        // TODO: expand this so that the number of cardViewButtons is equal to the number of cards in the gallery
        this.gambleButton = new JButton(GamblingViewModel.GAMBLING_BUTTON_LABEL);
        buttons.add(gambleButton);
        this.gambleButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(gambleButton)) {
                            GamblingState currentState = viewModel.getState(); // not really needed here
                            // use your controller(s)
                        }
                    }
                }
        );
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this is a debug message for when the user clicks on one of the buttons
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // this is for error messages
        GamblingState state = (GamblingState) evt.getNewValue();
//        if (state.getBackError() != null) {
//            JOptionPane.showMessageDialog(this, state.getBackError());
//        }
//        if (state.getCardViewError() != null) {
//            JOptionPane.showMessageDialog(this, state.getCardViewError());
//        }
    }
}
