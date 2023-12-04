package view;

import app.MainMenuUseCaseFactory;
import interface_adapter.card_stats.CardStatsViewModel;
import interface_adapter.gambling.GamblingViewModel;
import interface_adapter.main_menu.MainMenuController;
import interface_adapter.main_menu.MainMenuViewModel;
import org.junit.jupiter.api.Test;
import use_case.StatsGallery.StatsGalleryInteractor;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainMenuViewTest {

    @Test
    public void testMainMenuViewComponents() {
        // Create a dummy MainMenuViewModel and MainMenuController
        MainMenuViewModel viewModel = new MainMenuViewModel();
        GamblingViewModel gamblingViewModel = new GamblingViewModel();
        CardStatsViewModel statsGalleryViewModel = new CardStatsViewModel();

        // Create a MainMenuView instance
        MainMenuView mainMenuView = MainMenuUseCaseFactory.create(null, viewModel, null, null, null, null);


        // Test that the mainMenuView is not null
        assertNotNull(mainMenuView);

        // Test that the title label is created and set correctly
        JLabel titleLabel = (JLabel) mainMenuView.getComponent(0);
        assertNotNull(titleLabel);
        assertEquals("Main Menu", titleLabel.getText());


    }

    // Additional test methods can be added as needed
}