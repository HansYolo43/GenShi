package app;

import data_access.FileCardDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.generatecard.GenerateCardController;
import interface_adapter.generatecard.GenerateCardPresenter;
import interface_adapter.generatecard.GenerateCardViewModel;
import use_case.generatecard.GenerateCardInputBoundary;
import use_case.generatecard.GenerateCardInteractor;
import use_case.generatecard.GenerateCardOutputBoundary;

import view.GenerateCardView;

public class GenerateCardUseCaseFactory {
    public static GenerateCardView createGenerateCardView(FileCardDataAccessObject fileCardDataAccessObject) {
        // Instantiate the ViewModel
        GenerateCardViewModel viewModel = new GenerateCardViewModel();

        // Instantiate the Presenter
        GenerateCardPresenter presenter = new GenerateCardPresenter(viewModel);

        // Instantiate the Interactor
        GenerateCardInteractor interactor = new GenerateCardInteractor(fileCardDataAccessObject, "APIkEY", presenter);

        // Instantiate the Controller
        GenerateCardController controller = new GenerateCardController(interactor);

        // Instantiate and return the View
        return new GenerateCardView(controller, viewModel);

    }
}