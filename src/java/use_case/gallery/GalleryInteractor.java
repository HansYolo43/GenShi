package use_case.gallery;

import Entities.Card;

public class GalleryInteractor implements GalleryInputBoundary {
    final GalleryOutputBoundary galleryPresenter;

    public GalleryInteractor(GalleryOutputBoundary galleryPresenter) {
        this.galleryPresenter = galleryPresenter;
    }
    public void execute(Card card) {
        galleryPresenter.prepareSuccessView(card);
    }

    public void executeBack() {
        galleryPresenter.prepareBackView();
    }
}
