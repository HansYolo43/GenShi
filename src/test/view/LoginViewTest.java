package view;

import Entities.User;
import app.LoginUseCaseFactory;
import data_access.FileCardDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginViewTest {

    @Test
    public void testMainMenuViewComponents() throws IOException {
        FileCardDataAccessObject fileCardDAO = new FileCardDataAccessObject("test_cards.csv", "test/test_test.db");

        User testUser = new User(1, new ArrayList<>(), 1, 100, "TestUser1", "password");
        fileCardDAO.setActiveUser(testUser);
        // Create a dummy MainMenuViewModel and MainMenuController
        LoginViewModel viewModel = new LoginViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();

        // Create a MainMenuView instance
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, viewModel, mainMenuViewModel, fileCardDAO);

        // Test that the mainMenuView is not null
        assertNotNull(loginView);

        // Test that the title label is created and set correctly
        JLabel titleLabel = (JLabel) loginView.getComponent(0);
        assertNotNull(titleLabel);
        assertEquals("Login Screen", titleLabel.getText());
    }
}
