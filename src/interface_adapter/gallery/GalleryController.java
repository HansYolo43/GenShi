package interface_adapter.gallery;

import Entities.Card;
import use_case.gallery.GalleryInputBoundary;

public class GalleryController {
    final GalleryInputBoundary galleryInteractor;

    public GalleryController(GalleryInputBoundary galleryInteractor) {
        this.galleryInteractor = galleryInteractor;
    }

    public void execute(Card card) {
        galleryInteractor.execute(card);
    }
}
