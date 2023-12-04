package Entities;

public class Card {
    private final Integer Id;
    private String name;
    private final int imageID;
    private String imgpath;
    private Stats stats;
    private String Desc;


    public Card(int id, String name, int imageID, String Description, String imgpath, Stats stats) {
        this.name = name;
        this.imageID = imageID;
        this.stats = stats;
        this.Id = id;
        this.Desc = Description;
        this.imgpath = imgpath;
    }

    public String getName() {
        return name;
    }

    public String getimgpath() {
        return imgpath;
    }

    public int getImageID() {
        return imageID;
    }

    public Stats getStats() {
        return stats;
    }



    public Integer getId() {
        return Id;
    }

    public String getDesc() {
        return Desc;
    }

//    public void setrarity(String rarity){
//        stats.setRarity(rarity);
//
//    }

    public void setDesc(String newDescription) {
        Desc = newDescription;

    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }


    public void setName(String updatedTestCard) {this.name = updatedTestCard;
    }
}
