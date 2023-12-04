package Entities;

public class Stats {
    private int level;
    private final String affinity;
    private final int baseHP;
    private final int baseDEF;
    private final int baseATK;
    private final int baseCRIT;

    private String rarity;



    //constructors below
    public Stats(int level, String affinity, int baseHP, int baseDEF, int baseATK, int baseCRIT, String rarity) {
        this.level = level;
        this.affinity = affinity;
        this.baseHP = baseHP;
        this.baseDEF = baseDEF;
        this.baseATK = baseATK;
        this.baseCRIT = baseCRIT;
        this.rarity = rarity;
    }



    //getters below
    public int getLevel() {
        return level;
    }

    //setters below
    public void setLevel(int level) {
        this.level = level;
    }

    public String getAffinity() {
        return affinity;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public int getBaseDEF() {
        return baseDEF;
    }

    public int getBaseATK() {
        return baseATK;
    }

    public int getBaseCRIT() {
        return baseCRIT;
    }

    public String getRarity(){return rarity;}

    public void setRarity(String rariy){
        rarity = rariy;
    }

//    public String serializer() {
//        return level + ":" +
//                affinity + ":" +
//                baseHP + ":" +
//                baseDEF + ":" +
//                baseATK + ":" +
//                baseCRIT + ":";
//    }
}
