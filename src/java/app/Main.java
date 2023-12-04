package app;

import Entities.User;
import data_access.FileCardDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.card_stats.CardStatsViewModel;
import interface_adapter.gallery.GalleryViewModel;
import interface_adapter.gambling.GamblingViewModel;
import interface_adapter.generatecard.GenerateCardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginUserDataAcesssInterface;
import use_case.signup.SignupUserDataAcesssInterface;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello, world!");
        JFrame application = new JFrame("Main Menu Test");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setDefaultLookAndFeelDecorated(true);
        application.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.cyan));
        application.setPreferredSize(new Dimension(800, 600));


        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        //temp until overwritten by login
        FileCardDataAccessObject cardDAO = new FileCardDataAccessObject("src/DB/cards.txt", "src/db/cards.db");
        User user = cardDAO.getUser("TestUser");
        cardDAO.setActiveUser(user);


        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
        GalleryViewModel galleryViewModel = new GalleryViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        GamblingViewModel gamblingViewModel = new GamblingViewModel();
        CardStatsViewModel cardStatsViewModel = new CardStatsViewModel();
        GenerateCardViewModel generateCardViewModel = new GenerateCardViewModel();

        MainMenuView mainMenuView = MainMenuUseCaseFactory.create(viewManagerModel, mainMenuViewModel, galleryViewModel, loginViewModel, gamblingViewModel, generateCardViewModel);
        views.add(mainMenuView, mainMenuView.viewName);
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                cardDAO, mainMenuViewModel);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, mainMenuViewModel, cardDAO);
        views.add(loginView, loginView.viewName);
        //TODO: remove the temp optimisation
        GalleryView galleryView = GalleryUseCaseFactory.create(viewManagerModel, galleryViewModel, cardStatsViewModel, cardDAO);
        views.add(galleryView, galleryView.viewName);
        GamblingView gamblingView = GamblingUseCaseFactory.create(viewManagerModel, gamblingViewModel, cardDAO);
        views.add(gamblingView, gamblingView.viewName);
        CardStatsView cardStatsView = CardStatsUseCaseFactory.create(viewManagerModel, cardStatsViewModel);
        views.add(cardStatsView, cardStatsView.viewName);
        GenerateCardView generateCardView = GenerateCardUseCaseFactory.createGenerateCardView(cardDAO, viewManagerModel);
        views.add(generateCardView, generateCardView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setLocationRelativeTo(null); // Center the frame on screen
        application.setVisible(true);

    }
}

