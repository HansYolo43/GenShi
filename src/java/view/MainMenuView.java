package view;

import interface_adapter.main_menu.MainMenuController;
import interface_adapter.main_menu.MainMenuState;
import interface_adapter.main_menu.MainMenuViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;


public class MainMenuView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "main_menu";

    private final MainMenuViewModel viewModel;
    private final MainMenuController mainMenuController;

    public MainMenuView(MainMenuViewModel viewModel, MainMenuController mainMenuController) {
        this.viewModel = viewModel;
        this.mainMenuController = mainMenuController;

        // Buttons panel with vertical BoxLayout
        // Set the main layout manager
        setLayout(new BorderLayout());

        // Create a title label
        JLabel title = new JLabel("Main Menu", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        // Create a panel that will hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Create the buttons and add them to the button panel
        JButton galleryButton = createButton(MainMenuViewModel.GALLERY_BUTTON_LABEL, e -> mainMenuController.executeGallery());
        JButton gamblingButton = createButton(MainMenuViewModel.GAMBLING_BUTTON_LABEL, e -> mainMenuController.executeGambling());
        JButton gameButton = createButton(MainMenuViewModel.GENERATE_CARD_BUTTON_LABEL, e -> mainMenuController.executeGenerateCard());
        JButton logoutButton = createButton(MainMenuViewModel.LOGOUT_BUTTON_LABEL, e -> mainMenuController.executeLogout());

        // Add each button to the panel with some vertical glue to push them together
        buttonPanel.add(Box.createVerticalGlue());
        addButtonToPanel(galleryButton, buttonPanel);
        addButtonToPanel(gamblingButton, buttonPanel);
        addButtonToPanel(gameButton, buttonPanel);
        addButtonToPanel(logoutButton, buttonPanel);
        buttonPanel.add(Box.createVerticalGlue());

        // Create a container panel that will center the button panel
        JPanel containerPanel = new JPanel(new GridBagLayout());
        containerPanel.add(buttonPanel);

        // Add the title and container panel to the main panel
        add(title, BorderLayout.NORTH);
        add(containerPanel, BorderLayout.CENTER);
    }

    private void addButtonToPanel(JButton button, JPanel panel) {
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height)); // Make buttons expand to full width
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between buttons
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // Debug message
        System.out.println("Button Clicked: " + evt.getActionCommand());
    }
    @Override
    public void propertyChange(java.beans.PropertyChangeEvent evt) {
            MainMenuState state = (MainMenuState) evt.getNewValue();
    }
}
