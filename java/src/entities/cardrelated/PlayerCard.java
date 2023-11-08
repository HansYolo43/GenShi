package entities.cardrelated;

public class PlayerCard extends Card {
    private int rarity; // can you change rarity?
    public PlayerCard(String name, int imageID, int rarity, Stats stats) {
        super(name, imageID, stats);
        this.rarity = rarity;
    }
    public int getRarity() {
        return rarity;
    }
}
