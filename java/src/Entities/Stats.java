package Entities;

import java.util.ArrayList;

public class Stats {
    private int level;
    private String affinity;
    private ArrayList<Ability> abilities;
    private int baseHP;
    private int baseDEF;
    private int baseATK;
    private int baseCRIT;
    //constructors below
    public Stats(int level, String affinity, ArrayList<Ability> abilities, int baseHP, int baseDEF, int baseATK, int baseCRIT) {
        this.level = level;
        this.affinity = affinity;
        this.abilities = abilities;
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
    public ArrayList<Ability> getAbilities() {
        return abilities;
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
}
