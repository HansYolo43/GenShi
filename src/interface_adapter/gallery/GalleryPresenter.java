package interface_adapter.gallery;

import Entities.Card;
import interface_adapter.ViewManagerModel;
import interface_adapter.card_stats.CardStatsState;
import interface_adapter.card_stats.CardStatsViewModel;
import use_case.gallery.GalleryOutputBoundary;

public class GalleryPresenter implements GalleryOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final GalleryViewModel galleryViewModel;
    private final CardStatsViewModel cardStatsViewModel;

    public GalleryPresenter(ViewManagerModel viewManagerModel, GalleryViewModel galleryViewModel, CardStatsViewModel cardStatsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.galleryViewModel = galleryViewModel;
        this.cardStatsViewModel = cardStatsViewModel;
    }

    public void prepareSuccessView(Card card) {
        CardStatsState state = cardStatsViewModel.getState();
        System.out.println(card.getDesc());
        state.setName(card.getName());
        state.setRarity(card.getStats().getRarity());
        state.setDescription(card.getDesc());
        state.setImgpath(card.getimgpath());
        String path = card.getimgpath();
        path.replace("\\", "/");
        state.setImgpath(path);
        cardStatsViewModel.setState(state);
        System.out.println(cardStatsViewModel.getState().getImgpath());
        cardStatsViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
        viewManagerModel.setActiveView(cardStatsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareErrorView(String error) {
        System.out.println("GalleryPresenter: prepareErrorView");
    }
}
