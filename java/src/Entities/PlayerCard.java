package Entities;

public class PlayerCard extends Card {
    private int rarity; // can you change rarity?
    public PlayerCard(int id, String name, int imageID,String Description) {
        super(id , name , imageID , Description);
        this.rarity = id;
    }
    public int getRarity() {
        return rarity;
    }
}
