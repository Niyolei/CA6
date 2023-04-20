package com.dkit.gd2.dominikHampejs.DTO;

public class Champion {
    private int id;
    private String name;
    private String role;
    private double winRate;

    public Champion(int id, String name, String role, double winRate) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.winRate = winRate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public double getWinRate() {
        return winRate;
    }

    @Override
    public String toString() {
        return "Champion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", winRate=" + winRate +
                '}';
    }

    public boolean equals(Object o){
        if(o instanceof Champion){
            Champion c = (Champion) o;
            return this.id == c.id && this.name.equals(c.name) && this.role.equals(c.role) && this.winRate == c.winRate;
        }
        return false;
    }
}
