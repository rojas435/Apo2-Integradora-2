package model;

import java.util.Comparator;

public class Product {


    private String name;
    private String description;

    private Categories categories;
    private double price;
    private int quantity;
    private double totalSales;

    //Constructor
    public Product(String name, String desription, double price, int quantity, int categories, double totalSales){
        this.name = name;
        this.description = desription;
        this.price = price;
        this.quantity = quantity;
        this.totalSales = totalSales;
        switch (categories){
            case 1:
                this.categories = Categories.BOOKS;
                break;
            case 2:
                this.categories = Categories.ELECTRONICS;
                break;
            case 3:
                this.categories = Categories.CLOTHES;
                break;
            case 4:
                this.categories = Categories.ACCESORIES;
                break;
            case 5:
                this.categories = Categories.FOOD;
                break;
            case 6:
                this.categories = Categories.DRINKS;
                break;
            case 7:
                this.categories = Categories.STATIONERY;
                break;
            case 8:
                this.categories = Categories.SPORTS;
                break;
            case 9:
                this.categories = Categories.BEAUTY;
                break;
            case 10:
                this.categories = Categories.PERSONAL;
                break;
            case 11:
                this.categories = Categories.TOYS;
                break;
            case 12:
                this.categories = Categories.GAMES;
                break;
            case 13:
                this.categories = Categories.KIDS;
                break;

        };
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

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }
    public Comparator<Product> comparatorForUse(int chosenComparator){
        switch (chosenComparator){
            case 1:
                Comparator<Product> nameComparator = Comparator.comparing(product -> product.getName());
                return nameComparator;
            case 2:
                Comparator<Product> priceComparator = Comparator.comparing(product -> product.getPrice());
                return priceComparator;
            case 3:
                Comparator<Product> categoryComparator = Comparator.comparing(product -> product.getCategories().ordinal());
                return categoryComparator;
            case 4:
                Comparator<Product> numSalesComparator = Comparator.comparing(product -> product.getTotalSales());
                return numSalesComparator;
        }
        return null;
    };

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", categories=" + categories +
                ", price=" + price +
                ", quantity=" + quantity +
                ", totalSales=" + totalSales +
                '}';
    }
}
