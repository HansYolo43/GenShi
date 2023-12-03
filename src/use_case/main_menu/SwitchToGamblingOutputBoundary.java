package use_case.main_menu;

public interface SwitchToGamblingOutputBoundary {
    void prepareSuccessView();

    void prepareFailView(String error);
}