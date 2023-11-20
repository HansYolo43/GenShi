package Entities;

import java.util.ArrayList;

public class Stats {
    private int level;
    private String affinity;
    private int baseHP;
    private int baseDEF;
    private int baseATK;
    private int baseCRIT;
    //constructors below
    public Stats(int level, String affinity, int baseHP, int baseDEF, int baseATK, int baseCRIT) {
        this.level = level;
        this.affinity = affinity;
        this.baseHP = baseHP;
        this.baseDEF = baseDEF;
        this.baseATK = baseATK;
        this.baseCRIT = baseCRIT;
    }
    //getters below
    public int getLevel() {
        return level;
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
    //setters below
    public void setLevel(int level) {
        this.level = level;
    }

    public String serializer(){
        return level + ":" +
                affinity + ":" +
                baseHP + ":" +
                baseDEF + ":" +
                baseATK + ":" +
                baseCRIT + ":" ;
    }

    public static Stats deserialize(String line){
        String[] parts = line.split(":");

        int level = Integer.parseInt(parts[0]);

        String affinity = parts[1];

        int baseHP = Integer.parseInt(parts[2]);
        int baseDEF = Integer.parseInt(parts[3]);
        int baseATK = Integer.parseInt(parts[4]);
        int baseCRIT = Integer.parseInt(parts[5]);

        return new Stats(level, affinity ,baseHP , baseDEF , baseATK ,baseCRIT);


    }
}
