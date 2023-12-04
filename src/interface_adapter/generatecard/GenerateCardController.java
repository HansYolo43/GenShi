package interface_adapter.generatecard;

import use_case.generatecard.GenerateCardInputBoundary;

public class GenerateCardController {
    private GenerateCardInputBoundary interactor;

    public GenerateCardController(GenerateCardInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void onClickGenerate(String theme) {
        interactor.execute(theme);
    }
}
