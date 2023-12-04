package use_case.main_menu;

public class SwitchToGenerateCardInteractor implements SwitchToGenerateCardInputBoundary {
    final SwitchToGenerateCardOutputBoundary switchToGenerateCardPresenter;

    public SwitchToGenerateCardInteractor(SwitchToGenerateCardOutputBoundary switchToGenerateCardPresenter) {
        this.switchToGenerateCardPresenter = switchToGenerateCardPresenter;
    }
    public void execute() {
        switchToGenerateCardPresenter.prepareSuccessView();
        System.out.println("is this even being called");
    }
}
