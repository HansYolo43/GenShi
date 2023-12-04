package app;

import Entities.User;
import data_access.FileCardDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.card_stats.CardStatsViewModel;
import interface_adapter.gallery.GalleryViewModel;
import interface_adapter.gambling.GamblingViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
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

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        //TODO: switch to common user after merge with login systems

        // comment this out
        FileCardDataAccessObject cardDAO = new FileCardDataAccessObject("src/DB/cards.txt", "src/db/cards.db");
        User user = cardDAO.getUser("TestUser");
        cardDAO.setActiveUser(user);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        LoginViewModel loginViewModel = new LoginViewModel();
        //LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        MainMenuViewModel loggedInViewModel = new MainMenuViewModel();
        GalleryViewModel galleryViewModel = new GalleryViewModel();
        GamblingViewModel gamblingViewModel = new GamblingViewModel();
        CardStatsViewModel cardStatsViewModel = new CardStatsViewModel();


        FileCardDataAccessObject userDataAccessObject;
        try { //TODO: possible bug?
            userDataAccessObject = new FileCardDataAccessObject("src/DB/cards.txt", "src/db/cards.db");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                userDataAccessObject, loggedInViewModel);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);
//        LoggedInView loggedInView = new LoggedInView(loggedInViewModel);

        // this hopefully has logout embedded
        //MainMenuView loggedInView = LogoutUseCaseFactory.create(viewManagerModel,loginViewModel,mainMenuViewModel);
        MainMenuView loggedInView = MainMenuUseCaseFactory.create(viewManagerModel, loggedInViewModel, galleryViewModel, loginViewModel, gamblingViewModel);
        views.add(loggedInView, loggedInView.viewName);  // todo, change name convention


        GalleryView galleryView = GalleryUseCaseFactory.create(viewManagerModel, galleryViewModel, cardStatsViewModel, cardDAO);
        views.add(galleryView, galleryView.viewName);
        GamblingView gamblingView = new GamblingView(gamblingViewModel);
        views.add(gamblingView, gamblingView.viewName);
        //CardStatsView cardStatsView = new CardStatsView(cardStatsViewModel);
        //views.add(cardStatsView, cardStatsView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }}

