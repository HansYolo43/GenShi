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

    public static Stats deserialize(String line) {
        String[] parts = line.split(":");

        int level = Integer.parseInt(parts[0]);

        String affinity = parts[1];

        int baseHP = Integer.parseInt(parts[2]);
        int baseDEF = Integer.parseInt(parts[3]);
        int baseATK = Integer.parseInt(parts[4]);
        int baseCRIT = Integer.parseInt(parts[5]);
        String rarity = parts[6];

        return new Stats(level, affinity, baseHP, baseDEF, baseATK, baseCRIT, rarity);


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

    public String serializer() {
        return level + ":" +
                affinity + ":" +
                baseHP + ":" +
                baseDEF + ":" +
                baseATK + ":" +
                baseCRIT + ":";
    }
}
