package view;


import interface_adapter.main_menu.MainMenuController;
import interface_adapter.main_menu.MainMenuState;
import interface_adapter.main_menu.MainMenuViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainMenuView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "main menu";
    private final MainMenuViewModel viewModel;
    // we have to keep track of four possible buttons: gallery, gambling, game, and logout
    private final JButton galleryButton;
    private final JButton gamblingButton;
    private final JButton gameButton;
    private final JButton logoutButton;
    private final MainMenuController mainMenuController;

    public MainMenuView(MainMenuViewModel viewModel, MainMenuController mainMenuController) {
        this.viewModel = viewModel;
        JLabel title = new JLabel("main menu");
        title.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.mainMenuController = mainMenuController;
        JPanel buttons = new JPanel();
        // TODO: check if the order of addition to screen matters
        this.galleryButton = new JButton(MainMenuViewModel.GALLERY_BUTTON_LABEL);
        this.gamblingButton = new JButton(MainMenuViewModel.GAMBLING_BUTTON_LABEL);
        this.gameButton = new JButton(MainMenuViewModel.GAME_BUTTON_LABEL);
        this.logoutButton = new JButton(MainMenuViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(gamblingButton);
        buttons.add(galleryButton);
        buttons.add(gameButton);
        buttons.add(logoutButton);

        this.galleryButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(galleryButton)) {
                            MainMenuState currentState = viewModel.getState(); // not really needed here
                            System.out.println("Gallery button clicked");
                            mainMenuController.executeGallery();
                        }
                    }
                }
        );
        this.gamblingButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(gamblingButton)) {
                            MainMenuState currentState = viewModel.getState(); // not really needed here
                            mainMenuController.executeGambling();
                        }
                    }
                }
        );
        this.gameButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(gameButton)) {
                            MainMenuState currentState = viewModel.getState(); // not really needed here
                            mainMenuController.executeGame();
                        }
                    }
                }
        );
        this.logoutButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logoutButton)) {
                            MainMenuState currentState = viewModel.getState(); // not really needed here
                            mainMenuController.executeLogout();
                        }
                    }
                }
        );
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // this is a debug message for when the user clicks on one of the buttons
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // this is for the error messages
        MainMenuState state = (MainMenuState) evt.getNewValue();
        if (state.getGalleryError() != null) {
            JOptionPane.showMessageDialog(this, state.getGalleryError());
        }
        if (state.getGamblingError() != null) {
            JOptionPane.showMessageDialog(this, state.getGamblingError());
        }
        if (state.getGameError() != null) {
            JOptionPane.showMessageDialog(this, state.getGameError());
        }
        if (state.getLogoutError() != null) {
            JOptionPane.showMessageDialog(this, state.getLogoutError());
        }
    }
}