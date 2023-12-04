package app;

import Entities.User;
import data_access.FileCardDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.card_stats.CardStatsViewModel;
import interface_adapter.gallery.GalleryViewModel;
import interface_adapter.gambling.GamblingViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
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

        //TODO: switch to common user after merge with login systems
        FileCardDataAccessObject cardDAO = new FileCardDataAccessObject("src/DB/cards.txt", "src/db/cards.db");
        User user = cardDAO.getUser("TestUser");
        cardDAO.setActiveUser(user);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
        GalleryViewModel galleryViewModel = new GalleryViewModel();
        GamblingViewModel gamblingViewModel = new GamblingViewModel();
        CardStatsViewModel cardStatsViewModel = new CardStatsViewModel();

        MainMenuView mainMenuView = MainMenuUseCaseFactory.create(viewManagerModel, mainMenuViewModel, galleryViewModel, gamblingViewModel);
        views.add(mainMenuView, mainMenuView.viewName);
        GalleryView galleryView = GalleryUseCaseFactory.create(viewManagerModel, galleryViewModel, cardStatsViewModel, cardDAO);
        views.add(galleryView, galleryView.viewName);
        GamblingView gamblingView = new GamblingView(gamblingViewModel);
        views.add(gamblingView, gamblingView.viewName);
        CardStatsView cardStatsView = new CardStatsView(cardStatsViewModel);
        views.add(cardStatsView, cardStatsView.viewName);

        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setLocationRelativeTo(null); // Center the frame on screen
        application.setVisible(true);

    }
}

