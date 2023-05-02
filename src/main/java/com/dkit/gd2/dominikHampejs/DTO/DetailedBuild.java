package com.dkit.gd2.dominikHampejs.DTO;

import java.util.ArrayList;

public class DetailedBuild extends Build{

    String championName;
    String itemName;

    public DetailedBuild(int championId, int itemId, String explanation, String championName, String itemName) {
        super(championId, itemId, explanation);
        this.championName = championName;
        this.itemName = itemName;
    }

    public String getChampionName() {
        return championName;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public String toString() {
        return "DetailedBuild{" +
                "championName='" + championName + '\'' +
                ", itemName='" + itemName + '\'' +
                '}';
    }
    @Override
    public void printBuild() {
        System.out.printf("%-5s %-15s %-5s %-35s %-15s\n",getChampionId(), championName, getItemId(), itemName, getExplanation());
    }
}
