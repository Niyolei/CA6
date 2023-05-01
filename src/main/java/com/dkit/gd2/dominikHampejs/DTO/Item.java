package com.dkit.gd2.dominikHampejs.DTO;

public class Item {
    private int id;
    private String name;
    private int price;
    private String type;
    private String description;

    public Item(int id, String name, int price, String type, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + ", description=" + description + '}';
    }

    public void printItem(){
        System.out.printf("%-5d %-15s %-15d %-15s %-15s\n", id, name, price, type, description);
    }
}
