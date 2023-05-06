package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Order {
    private String name;
    private ArrayList<OrderedItem> items;
    private double totalPrice;
    private Date date;

    public Order(String name) {
        this.name = name;
        this.items = new ArrayList<OrderedItem>();
        this.totalPrice = 0;
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

    public double calculateTotalPrice() {
        double total = 0.0;
        for (OrderedItem order : items) {
            total += order.getProduct().getPrice()*order.getQuantity();
        }
        this.totalPrice = total;
        return total;
    }

    public Comparator<Order> comparatorForUse(int chosenComparator){
        switch (chosenComparator){
            case 1:
                Comparator<Order> nameComparator = Comparator.comparing(order -> order.getName());
                return nameComparator;
            case 2:
                Comparator<Order> totalPriceComparator = Comparator.comparing(order -> order.getTotalPrice());
                return totalPriceComparator;
            case 3:
                Comparator<Order> dateComparator = Comparator.comparing(order -> order.getDate());
                return dateComparator;
        }
        return null;
    };

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                '}';
    }
}
