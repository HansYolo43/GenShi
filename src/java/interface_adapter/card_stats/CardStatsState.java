package interface_adapter.card_stats;

public class CardStatsState {

    private String name ;
    private String imgpath ;
    private String rarity ;
    private String description ;

    public CardStatsState(CardStatsState copy) {
        name = copy.name;
        imgpath = copy.imgpath;
        rarity = copy.rarity;
        description = copy.description;
    }
    public CardStatsState() {}

    public String getName() {
        return name;
    }
    public String getImgpath() {
        return imgpath;
    }
    public String getRarity() {
        return rarity;
    }
    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
