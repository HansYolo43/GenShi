package use_case.main_menu;

import data_access.FileCardDataAccessObject;
import interface_adapter.gallery.GalleryState;
import view.GalleryView;

public class SwitchToGalleryInteractor implements SwitchToGalleryInputBoundary{
    final SwitchToGalleryOutputBoundary switchToGalleryPresenter;

    public SwitchToGalleryInteractor(SwitchToGalleryOutputBoundary switchToGalleryPresenter) {
        this.switchToGalleryPresenter = switchToGalleryPresenter;
    }
    public void execute() {
        switchToGalleryPresenter.prepareSuccessView();
    }


}
