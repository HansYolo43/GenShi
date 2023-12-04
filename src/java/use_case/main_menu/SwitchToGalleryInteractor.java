package use_case.main_menu;

public class SwitchToGalleryInteractor implements SwitchToGalleryInputBoundary{
    final SwitchToGalleryOutputBoundary switchToGalleryPresenter;

    public SwitchToGalleryInteractor(SwitchToGalleryOutputBoundary switchToGalleryPresenter) {
        this.switchToGalleryPresenter = switchToGalleryPresenter;
    }
    public void execute() {
        switchToGalleryPresenter.prepareSuccessView();
    }
}
