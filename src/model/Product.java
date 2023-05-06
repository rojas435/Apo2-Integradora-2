package model;

import java.util.Comparator;

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
        switch (typeOfProduct){
            case 1:
                this.typeOfProduct = Categories.BOOKS;
                break;
            case 2:
                this.typeOfProduct = Categories.ELECTRONICS;
                break;
            case 3:
                this.typeOfProduct = Categories.CLOTHES;
                break;
            case 4:
                this.typeOfProduct = Categories.ACCESORIES;
                break;
            case 5:
                this.typeOfProduct = Categories.FOOD;
                break;
            case 6:
                this.typeOfProduct = Categories.DRINKS;
                break;
            case 7:
                this.typeOfProduct = Categories.STATIONERY;
                break;
            case 8:
                this.typeOfProduct = Categories.SPORTS;
                break;
            case 9:
                this.typeOfProduct = Categories.BEAUTY;
                break;
            case 10:
                this.typeOfProduct = Categories.PERSONAL;
                break;
            case 11:
                this.typeOfProduct = Categories.TOYS;
                break;
            case 12:
                this.typeOfProduct = Categories.GAMES;
                break;
            case 13:
                this.typeOfProduct = Categories.KIDS;
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

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public Categories getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(Categories typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
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
                Comparator<Product> categoryComparator = Comparator.comparing(product -> product.getTypeOfProduct());
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
                ", typeOfProduct=" + typeOfProduct +
                ", price=" + price +
                ", quantity=" + quantity +
                ", totalSales=" + totalSales +
                '}';
    }
}
