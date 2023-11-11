package Entities;

public class Card {
    private final Integer Id;
    private final String name;
    private final int imageID;
    private Stats stats;
    private String Desc;


    public Card(int id, String name, int imageID,String Description) {
        this.name = name;
        this.imageID = imageID;
//        this.stats = stats;
        this.Id = id;
        this.Desc = Description;
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

    public Integer getId(){return Id; }

    public String getDesc(){ return Desc; }


    public void setDesc(String newDescription) {
        Desc = newDescription;

    }
}
