package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_menu.GalleryViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import view.GalleryView;
import view.MainMenuView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        JFrame application = new JFrame("Main Menu Test");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setDefaultLookAndFeelDecorated(true);
        application.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.cyan));


        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
        GalleryViewModel galleryViewModel = new GalleryViewModel();

        MainMenuView mainMenuView = MainMenuUseCaseFactory.create(viewManagerModel, mainMenuViewModel, galleryViewModel);
        views.add(mainMenuView, mainMenuView.viewName);
        GalleryView galleryView = new GalleryView(galleryViewModel);
        views.add(galleryView, galleryView.viewName);



        viewManagerModel.setActiveView(galleryView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
