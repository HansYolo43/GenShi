package interface_adapter.gambling;

public class GamblingState {
    // we have to keep track of one possible error: gambling
    private String gamblingError = null;
    private String name ;
    private String imgpath ;
    private String rarity ;
    private String description ;
    public GamblingState(GamblingState copy) {
        gamblingError = copy.gamblingError;
        name = copy.name;
        imgpath = copy.imgpath;
        rarity = copy.rarity;
        description = copy.description;
    }
    public GamblingState() {}
    public String getGamblingError() {
        return gamblingError;
    }

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
    public void setGamblingError(String gamblingError) {
        this.gamblingError = gamblingError;
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
