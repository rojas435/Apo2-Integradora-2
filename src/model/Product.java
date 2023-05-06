package model;

public class Product {


    private String name;
    private String description;

    private Categories typeOfProduct;
    private double price;
    private int quantity;
    private int totalSales;

    //Constructor
    public Product(String name, String desription, double price, int quantity, int typeOfProduct, int totalSales){
        this.name = name;
        this.description = desription;
        this.price = price;
        this.quantity = quantity;
        this.totalSales = totalSales;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }
}
