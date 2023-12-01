package interface_adapter.main_menu;

public class GalleryState {
    // we have to keep track of two possible errors: cardView and back
    private String cardViewError = null;
    private String backError = null;
    public GalleryState(GalleryState copy) {
        cardViewError = copy.cardViewError;
        backError = copy.backError;
    }
    public GalleryState() {}
    public String getCardViewError() {
        return cardViewError;
    }
    public String getBackError() {
        return backError;
    }
    public void setCardViewError(String cardViewError) {
        this.cardViewError = cardViewError;
    }
    public void setBackError(String backError) {
        this.backError = backError;
    }
}
