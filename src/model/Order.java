package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private String name;
    private ArrayList<OrderedItem> items;
    private ArrayList<Product> productList;
    private double totalPrice;
    private Date date;

    public Order(String name, ArrayList<Product> productList) {
        this.name = name;
        this.productList = productList;
        this.totalPrice = calcularPrecioTotal();
        this.date = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<OrderedItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderedItem> items) {
        this.items = items;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private double calcularPrecioTotal() {
        double total = 0.0;
        for (Product producto : productList) {
            total += producto.getPrice();
        }
        return total;
    }
}
