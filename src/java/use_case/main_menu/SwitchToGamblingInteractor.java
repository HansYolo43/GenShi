package use_case.main_menu;

public class SwitchToGamblingInteractor implements SwitchToGamblingInputBoundary {
    final SwitchToGamblingOutputBoundary switchToGamblingPresenter;

    public SwitchToGamblingInteractor(SwitchToGamblingOutputBoundary switchToGamblingPresenter) {
        this.switchToGamblingPresenter = switchToGamblingPresenter;
    }
    public void execute() {
        switchToGamblingPresenter.prepareSuccessView();
    }
}

