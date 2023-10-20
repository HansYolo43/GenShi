public abstract class Card {
    private final String name;
    private final int imageID;
    private Stats stats;
    public Card(String name, int imageID, Stats stats) {
        this.name = name;
        this.imageID = imageID;
        this.stats = stats;
    }
    public String getName() {
        return name;
    }
    public int getImageID() {
        return imageID;
    }
    public Stats getStats() {
        return stats;
    }
    public String toString() {
        return name + " " + imageID + " " + stats;
    }
}
