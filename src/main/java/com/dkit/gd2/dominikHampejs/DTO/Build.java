package com.dkit.gd2.dominikHampejs.DTO;

public class Build {
    private int championId;
    private int itemId;
    private String explanation;

    public Build(int championId, int itemId, String explanation) {
        this.championId = championId;
        this.itemId = itemId;
        this.explanation = explanation;
    }

    public int getChampionId() {
        return championId;
    }

    public int getItemId() {
        return itemId;
    }

    public String getExplanation() {
        return explanation;
    }

    @Override
    public String toString() {
        return "Build{" + "championId=" + championId + ", itemId=" + itemId + ", explanation=" + explanation + '}';
    }

    public void printBuild() {
        System.out.printf("%-5d %-15d %-15s\n", championId, itemId, explanation);
    }
}
