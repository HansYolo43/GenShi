package use_case.main_menu;

public interface SwitchToGalleryOutputBoundary {
    void prepareSuccessView();

    void prepareFailView(String error);
}