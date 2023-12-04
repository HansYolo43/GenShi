package use_case.gallery;

import Entities.Card;

public interface GalleryOutputBoundary {
    void prepareSuccessView(Card card);

    void prepareErrorView(String error);
}
